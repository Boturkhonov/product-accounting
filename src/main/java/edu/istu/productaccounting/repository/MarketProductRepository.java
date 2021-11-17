package edu.istu.productaccounting.repository;

import edu.istu.productaccounting.model.MarketProduct;

import java.util.List;
import java.util.Optional;

public interface MarketProductRepository {

    int save(MarketProduct marketProduct);

    void update(MarketProduct marketProduct);

    void deleteById(int id);

    List<MarketProduct> findAll();

    Optional<MarketProduct> findById(int id);

}
