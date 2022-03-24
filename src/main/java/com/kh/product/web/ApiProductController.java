package com.kh.product.web;

import com.kh.product.domain.Product;
import com.kh.product.domain.svc.ProductSVC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/api")
public class ApiProductController {

  private final ProductSVC productSVC;

  //등록
  @PostMapping
  public ApiResult<Object> addProduct(@RequestBody Product product){
    Product addedProduct = productSVC.addProduct(product);
    return new ApiResult<>("00","success",addedProduct);
  }

  //조회
  @GetMapping("/{productId}")
  public ApiResult<Object> selectProduct(@PathVariable Long productId){
    return new ApiResult<>("00","success",productSVC.selectProduct(productId));
  }
  
  //전체조회
  @GetMapping
  public ApiResult<Object> selectAll(){
    List<Product> list = productSVC.findAll();
    return new ApiResult<>("00","success",list);
  }
  
  //수정
  @PatchMapping("/{productId}")
  public ApiResult<Object> updateProduct(@PathVariable Long productId, @RequestBody Product product){
    return new ApiResult<>("00","success",productSVC.updateProduct(productId, product));
  }
  
//삭제
  @DeleteMapping("/{productId}")
  public ApiResult<Object> deleteProduct(@PathVariable Long productId){
    return new ApiResult<>("00","success",productSVC.deleteProduct(productId));
  }
}
