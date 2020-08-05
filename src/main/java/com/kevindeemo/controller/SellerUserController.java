package com.kevindeemo.controller;

import com.kevindeemo.config.ProjectUrlConfig;
import com.kevindeemo.constant.CookieConstant;
import com.kevindeemo.constant.RedisConstant;
import com.kevindeemo.dataobject.SellerInfo;
import com.kevindeemo.enums.ResultEnum;
import com.kevindeemo.service.SellerService;
import com.kevindeemo.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              Map<String, Object> map,
                              HttpServletResponse response){
//        1. openId去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo==null){
            map.put("msg", ResultEnum.LOGIN_FAIL);
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

//        2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

//        3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

//        重定向调整用完整的http地址（绝对地址），而不要用相对路径地址（容易出问题）
        return new ModelAndView("redirect:" + projectUrlConfig.getSell()+"/sell/seller/order/list");
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletResponse response,
                       HttpServletRequest request,
                       Map<String, Object> map){
//        1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

//        2. 清除redis
        if (cookie!=null){
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        }

//        3. 清除cookie
        CookieUtil.set(response, CookieConstant.TOKEN, null, 0);

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
