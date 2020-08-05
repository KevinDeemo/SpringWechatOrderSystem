package com.kevindeemo.dataobject.mapper;

import com.kevindeemo.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
//        String 名字与ProductCategoryInterface里的注解名称保持一致
        map.put("category_name", "烧烤啤酒");
        map.put("category_type", 4);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("炸串");
        productCategory.setCategoryType(5);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory result = mapper.findByCategoryType(5);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> result = mapper.findByCategoryName("炸串");
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("炸串1", 6);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("炸串");
        productCategory.setCategoryType(6);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(6);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByCategory() {
        ProductCategory productCategory = mapper.selectByCategoryType(5);
        Assert.assertNotNull(productCategory );
    }
}