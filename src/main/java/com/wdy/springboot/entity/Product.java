package com.wdy.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wgch
 * @date 2020/7/10
 */
@Data
@AllArgsConstructor
public class Product {
    private String name;
    private double price;
    private int inStock;
}
