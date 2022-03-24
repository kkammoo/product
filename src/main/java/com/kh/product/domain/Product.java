package com.kh.product.domain;

import lombok.Data;

@Data
public class Product {
  private Long productId;        //product_id    number(5),
  private String name;           //name          varchar2(30),
  private Long quantity;         //quantity      number(5),
  private Long price;            //price         number(10)

}
