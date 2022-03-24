package com.kh.product.domain.dao;

import com.kh.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  @DisplayName("상품 등록")
  void addProduct() {
    Product product = new Product();
    product.setName("무선마우스");
    product.setQuantity(20L);
    product.setPrice(1000L);

    Product addProduct = productDAO.addProduct(product);
    Assertions.assertThat(product.getName()).isEqualTo("무선마우스");

    log.info("productId={}", addProduct.getProductId());
  }

  @Test
  @DisplayName("상품 조회")
  void selectProduct() {
    Long productId = 29L;
    Product selectProduct = productDAO.selectProduct(productId);

    Assertions.assertThat(selectProduct.getName()).isEqualTo("무선마우스");

    log.info("productId={}", selectProduct.getProductId());
  }

  @Test
  @DisplayName("전체조회")
  void selectAll() {
    Assertions.assertThat(productDAO.selectAll().size()).isEqualTo(14);
  }

  @Test
  @DisplayName("상품수정")
  void updateProduct() {
    Long productId = 29L;

    // 수정 전
    Product beforeUpdateProduct = productDAO.selectProduct(productId);
    beforeUpdateProduct.setName("로지텍 마우스");
    beforeUpdateProduct.setQuantity(20L);
    beforeUpdateProduct.setPrice(800L);
    productDAO.updateProduct(productId, beforeUpdateProduct);

    // 수정 후
    Product afterUpdateProduct = productDAO.selectProduct(productId);

    // 수정 확인
    Assertions.assertThat(beforeUpdateProduct.getName()).isEqualTo(afterUpdateProduct.getName());
    Assertions.assertThat(beforeUpdateProduct.getQuantity()).isEqualTo(afterUpdateProduct.getQuantity());
    Assertions.assertThat(beforeUpdateProduct.getPrice()).isEqualTo(afterUpdateProduct.getPrice());

    log.info("productId={}", beforeUpdateProduct.getProductId());
    log.info("Name={}", beforeUpdateProduct.getName());
    log.info("Quantity={}", beforeUpdateProduct.getQuantity());
    log.info("Price={}", beforeUpdateProduct.getPrice());

  }

  @Test
  @DisplayName("상품 삭제")
  void deleteProduct() {
    Long productId = 29L;
    int deletedBbsItemCount = productDAO.deleteProduct(productId);
    Assertions.assertThat(deletedBbsItemCount).isEqualTo(1);

    Product product = productDAO.selectProduct(productId);
    Assertions.assertThat(product).isNull();
  }
}