package com.kh.product.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ProductController {

  @RequestMapping("/product")
  public String product(){

    return "product";
  }

}
