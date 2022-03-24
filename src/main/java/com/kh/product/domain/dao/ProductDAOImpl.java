package com.kh.product.domain.dao;

import com.kh.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

  private final JdbcTemplate jdbcTemplate;

  /**
   * 등록
   * @param product
   * @return
   */
  @Override
  public Product addProduct(Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into product (product_id, name, quantity, price) ");
    sql.append(" values (product_product_id_seq.nextval, ?, ?, ?) ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt =con.prepareStatement(
                sql.toString(),
                new String[]{"product_id"}
        );

        pstmt.setString(1, product.getName());
        pstmt.setLong(2, product.getQuantity());
        pstmt.setLong(3, product.getPrice());

        return pstmt;
      }
    }, keyHolder);

    Long productId = Long.valueOf(keyHolder.getKeys().get("product_id").toString());
    return selectProduct(productId);
  }

  /**
   * 조회
   * @param productId 상품ID
   * @return
   */
  @Override
  public Product selectProduct(Long productId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select product_id, name, quantity, price ");
    sql.append("   from product ");
    sql.append("  where product_id = ? ");

    List<Product> list = jdbcTemplate.query(
            sql.toString(), new BeanPropertyRowMapper<>(Product.class), productId);

    return (list.size() == 1) ? list.get(0) : null;
  }

  /**
   * 전체조회
   * @return
   */
  @Override
  public List<Product> selectAll() {
    StringBuffer sql = new StringBuffer();
    sql.append(" select product_id, name, quantity, price ");
    sql.append("   from product ");
    sql.append("  order by product_id ");

    List<Product> list = jdbcTemplate.query(
            sql.toString(), new BeanPropertyRowMapper<>(Product.class));

    return list;
  }

  /**
   * 수정
   * @param product
   * @return
   */
  @Override
  public Product updateProduct(Long productId, Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append(" update product ");
    sql.append("    set name = ? , ");
    sql.append("        quantity = ? , ");
    sql.append("        price = ? ");
    sql.append("  where product_id = ? ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
                sql.toString(),
                new String[]{"product_id"}
        );

        pstmt.setString(1, product.getName());
        pstmt.setLong(2, product.getQuantity());
        pstmt.setLong(3, product.getPrice());
        pstmt.setLong(4, productId);

        return pstmt;
      }
    },keyHolder);

    Long returnedId = Long.valueOf(keyHolder.getKeys().get("product_id").toString());
    return selectProduct(returnedId);
  }

  /**
   * 삭제
   * @param productId 상품ID
   * @return
   */
  @Override
  public int deleteProduct(Long productId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" delete from product ");
    sql.append("  where product_id = ? ");

    int result = jdbcTemplate.update(sql.toString(), productId);

    return result;
  }
}
