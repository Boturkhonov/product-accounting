package edu.istu.productaccounting.repository;

import edu.istu.productaccounting.model.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageRepository {

    int save(Storage storage);

    void update(Storage storage);

    void deleteById(int id);

    List<Storage> findAll();

    Optional<Storage> findById(int id);

}
