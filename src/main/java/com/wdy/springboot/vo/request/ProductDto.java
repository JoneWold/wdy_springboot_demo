package com.wdy.springboot.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wgch
 * @date 2020/7/10
 */
@Data
@AllArgsConstructor
public class ProductDto {
    private String name;
    private Double price;
    private Integer inStock;
}
