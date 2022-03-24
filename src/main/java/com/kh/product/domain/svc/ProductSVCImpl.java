package com.kh.product.domain.svc;

import com.kh.product.domain.dao.ProductDAO;
import com.kh.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

  private final ProductDAO productDAO;

  //등록
  @Override
  public Product addProduct(Product product) {
    return productDAO.addProduct(product);
  }

  //조회
  @Override
  public Product selectProduct(Long productId) {
    return productDAO.selectProduct(productId);
  }

  //전체조회
  @Override
  public List<Product> findAll() {
    return productDAO.selectAll();
  }

  //수정
  @Override
  public Product updateProduct(Long productId, Product product) {
    return productDAO.updateProduct(productId, product);
  }

  //삭제
  @Override
  public int deleteProduct(Long productId) {
    return productDAO.deleteProduct(productId);
  }
}
