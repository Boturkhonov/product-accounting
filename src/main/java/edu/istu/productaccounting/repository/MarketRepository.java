package edu.istu.productaccounting.repository;

import edu.istu.productaccounting.model.Market;
import edu.istu.productaccounting.model.Product;

import java.util.List;
import java.util.Optional;

public interface MarketRepository {
    int save(Market market);

    void update(Market market);

    void deleteById(int id);

    List<Market> findAll();

    Optional<Market> findById(int id);

}
