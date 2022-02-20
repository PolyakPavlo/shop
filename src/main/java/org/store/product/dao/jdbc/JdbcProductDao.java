package org.store.product.dao.jdbc;

import org.store.product.dao.ProductDao;
import org.store.product.web.domain.Product;

import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String FIND_ALL_SQL = "SELECT id, name, description, price, date FROM products LIMIT 100;";
    private static final String INSERT_BY_ID_SQL = "INSERT INTO products (name, description, price) VALUES (?, ?, ?);";
    private static final String UPDATE_SQL = "UPDATE products SET (name, description, price) VALUES (?, ?, ?);";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM products WHERE id=?;";

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.getQuery(FIND_ALL_SQL);
    }

    @Override
    public int save(Product product) {
        return jdbcTemplate.setQuery(product, INSERT_BY_ID_SQL);
    }

    @Override
    public int update(Product product){
        return jdbcTemplate.setQuery(product, UPDATE_SQL);
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.setQueryById(id, DELETE_BY_ID_SQL);
    }

}