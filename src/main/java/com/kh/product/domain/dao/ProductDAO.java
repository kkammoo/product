package com.kh.product.domain.dao;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductDAO {

  /**
   * 등록
   * @param product
   * @return
   */
  Product addProduct(Product product);

  /**
   * 조회
   * @param productId 상품ID
   * @return 
   */
  Product selectProduct(Long productId);

  /**
   * 전체조회
   * @return
   */
  List<Product> selectAll();

  /**
   * 수정
   * @param product
   * @return
   */
  Product updateProduct(Long productId, Product product);

  /**
   * 삭제
   * @param productId 상품ID
   * @return 
   */
  int deleteProduct(Long productId);
}
