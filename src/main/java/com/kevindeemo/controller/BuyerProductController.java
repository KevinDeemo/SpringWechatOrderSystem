package com.kevindeemo.controller;

import com.kevindeemo.VO.ProductInfoVO;
import com.kevindeemo.VO.ProductVO;
import com.kevindeemo.VO.ResultVO;
import com.kevindeemo.dataobject.ProductCategory;
import com.kevindeemo.dataobject.ProductInfo;
import com.kevindeemo.repository.ProductCategoryRepository;
import com.kevindeemo.service.CategoryService;
import com.kevindeemo.service.ProductService;
import com.kevindeemo.service.impl.CategoryServiceImpl;
import com.kevindeemo.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    ProductService productCategoryRepository;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list")
//    可以使用"#"的方式动态输入key，并且设置condition，unless用来设置取反条件
//    condition是在方法执行之前判断
//    unless是在方法执行之后判断，用于对方法的返回结果判断
    @Cacheable(cacheNames = "product", key = "123", condition = "", unless = "#result.getCode()!=0")
    public ResultVO list() {
//        1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productCategoryRepository.findUpAll();

//        2. 查询类目（一次性查询）
//        List< Integer> categoryTypeList = new ArrayList<>();
//////        传统方法
////        for (ProductInfo productInfo : productInfoList){
////            categoryTypeList.add(productInfo.getCategoryType());
////        }

//        精简方法(java8, lambda)
//        Collection类有stream（）方法，再通过map（）映射，最后再拼接成List
        List<Integer> categoryTypeList = productInfoList.stream().
                map(e -> e.getCategoryType()).
                collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

//        3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType() == productVO.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //啰嗦
//                    productInfoVO.setProductId(productInfo.getProductId());
//                    productInfoVO.setProductDescription(productInfo.getProductDescription());
//                    productInfoVO.setProductIcon(productInfo.getProductIcon());
//                    productInfoVO.setProductName(productInfo.getProductName());
//                    productInfoVO.setProductPrice(productInfo.getProductPrice());

                    //精简
                    BeanUtils.copyProperties(productInfo, productInfoVO);

                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
