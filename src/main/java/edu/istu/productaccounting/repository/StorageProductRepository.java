package edu.istu.productaccounting.repository;

import edu.istu.productaccounting.model.StorageProduct;

import java.util.List;
import java.util.Optional;

public interface StorageProductRepository {

    int save(StorageProduct storageProduct);

    void update(StorageProduct storageProduct);

    void deleteById(int id);

    List<StorageProduct> findAll();

    Optional<StorageProduct> findById(int id);

}
