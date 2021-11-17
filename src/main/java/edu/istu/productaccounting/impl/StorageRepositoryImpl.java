package edu.istu.productaccounting.impl;

import edu.istu.productaccounting.model.Storage;
import edu.istu.productaccounting.repository.StorageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StorageRepositoryImpl implements StorageRepository {
    @Override
    public int save(Storage storage) {
        return 0;
    }

    @Override
    public void update(Storage storage) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Storage> findAll() {
        return null;
    }

    @Override
    public Optional<Storage> findById(int id) {
        return Optional.empty();
    }
}
