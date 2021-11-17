package edu.istu.productaccounting.repository;

import edu.istu.productaccounting.model.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitRepository {

    List<Unit> findAll();

    Optional<Unit> findById(int id);

}
