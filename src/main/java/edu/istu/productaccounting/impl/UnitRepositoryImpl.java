package edu.istu.productaccounting.impl;

import edu.istu.productaccounting.model.Unit;
import edu.istu.productaccounting.repository.UnitRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UnitRepositoryImpl implements UnitRepository {

    private final JdbcTemplate jdbcTemplate;

    public UnitRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Unit> findAll() {
        return jdbcTemplate.query(
                "select * from unit",
                new BeanPropertyRowMapper<>(Unit.class));
    }

    @Override
    public Optional<Unit> findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select * from unit where id = ?",
                new BeanPropertyRowMapper<>(Unit.class),
                id
        ));
    }
}
