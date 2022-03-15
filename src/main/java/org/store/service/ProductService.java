package org.store.service;

import org.store.dao.ProductDao;
import org.store.web.entity.Product;

import java.util.List;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public boolean saveProduct(Product product) {
        return productDao.save(product);
    }

    public boolean deleteProduct(Long id) {
        return productDao.delete(id);
    }

    public List<Product> findByProductIds(List<Long> ids) {
        return productDao.findByIds(ids);
    }

}
