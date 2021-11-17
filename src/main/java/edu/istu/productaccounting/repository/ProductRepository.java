package edu.istu.productaccounting.repository;

import edu.istu.productaccounting.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    int save(Product product);

    void update(Product product);

    void deleteById(int id);

    List<Product> findAll();

    Product findById(int id);

}
