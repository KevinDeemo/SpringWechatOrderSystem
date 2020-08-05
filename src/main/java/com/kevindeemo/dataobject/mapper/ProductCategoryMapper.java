package com.kevindeemo.dataobject.mapper;

import com.kevindeemo.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/*
* SellApplication类里配置ProductCategoryMapper被扫描
* */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name, category_type) values(#{category_name, jdbcType = VARCHAR}, #{category_type, jdbcType=INTEGER} )")
    int insertByMap(Map<String, Object> map);

    //    用Object的时候要保证注释里sql语句变量名称与对象里变量名称一致
    @Insert("insert into product_category(category_name, category_type) values(#{categoryName, jdbcType = VARCHAR}, #{categoryType, jdbcType=INTEGER} )")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
//            设置筛选出的字段
//            通过这种方法将变量名和数据库里字段联系起来
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
//            设置筛选出的字段
//            通过这种方法将变量名和数据库里字段联系起来
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);

    /*
    * 注意需要在application.yml里加入xml文件路径
    * */
//  用xml的方式来配置mybatis
    ProductCategory selectByCategoryType(Integer categoryType);

}
