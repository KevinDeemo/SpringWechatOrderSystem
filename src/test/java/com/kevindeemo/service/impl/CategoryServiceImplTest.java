package com.kevindeemo.service.impl;

import com.kevindeemo.dataobject.ProductCategory;
import com.kevindeemo.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService service;

    @Test
    public void findOne() {
        ProductCategory productCategory = service.findOne(1);
        Assert.assertEquals(Integer.valueOf(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> result = service.findAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> result = service.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("儿童玩具",2);
        ProductCategory result = service.save(productCategory);
        Assert.assertNotNull(result);
    }
}