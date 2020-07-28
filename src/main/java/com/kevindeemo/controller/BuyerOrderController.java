package com.kevindeemo.controller;

import com.kevindeemo.VO.ResultVO;
import com.kevindeemo.converter.OrderForm2OrderDTO;
import com.kevindeemo.dto.OrderDTO;
import com.kevindeemo.enums.ResultEnum;
import com.kevindeemo.exception.SellException;
import com.kevindeemo.form.OrderForm;
import com.kevindeemo.service.BuyerService;
import com.kevindeemo.service.OrderService;
import com.kevindeemo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //    创建订单
    @PostMapping("/create")
//    表单验证
    public ResultVO<Map<String, String>> orderCreate(@Valid OrderForm orderForm,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> mapReuslt = new HashMap<>();
        mapReuslt.put("orderId", result.getOrderId());
        return ResultVOUtil.success(mapReuslt);
    }

//    订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> orderListQuery(@RequestParam(value = "openid") String openid,
                                                   @RequestParam(value = "page",  defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> result = orderService.findList(openid, request);
        return ResultVOUtil.success(result.getContent());
    }

//    订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> orderDetailQuery(@RequestParam("openid") String openid, @RequestParam("orderId") String orderid){
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderid);
        return ResultVOUtil.success(orderDTO);
    }

//    取消订单
    @PostMapping("/cancel")
    public ResultVO orderCancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderid){
        buyerService.cancelOrder(openid, orderid);
        return ResultVOUtil.success();
    }



}
