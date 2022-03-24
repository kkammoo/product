package com.kh.product.domain.svc;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductSVC {

  //등록
  Product addProduct(Product product);

  //조회
  Product selectProduct(Long productId);

  //전체조회
  List<Product> findAll();

  //수정
  Product updateProduct(Long productId, Product product);

  //삭제
  int deleteProduct(Long productId);
}
