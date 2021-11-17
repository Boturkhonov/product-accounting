package edu.istu.productaccounting.impl;

import edu.istu.productaccounting.model.Market;
import edu.istu.productaccounting.repository.MarketRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MarketRepositoryImpl implements MarketRepository {

    private final JdbcTemplate jdbcTemplate;

    public MarketRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Market market) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("add_market");

        return simpleJdbcCall.executeFunction(Integer.class,
                new MapSqlParameterSource("p_name", market.getName()),
                new MapSqlParameterSource("p_address", market.getAddress())
        );
    }

    @Override
    public void update(Market market) {
        jdbcTemplate.update("call upd_market(?, ?, ?)", market.getName(), market.getAddress());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("call del_market(?)", id);
    }

    @Override
    public List<Market> findAll() {
        return jdbcTemplate.query(
                "select * from market",
                new BeanPropertyRowMapper<>(Market.class));
    }

    @Override
    public Optional<Market> findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select * from market where id = ?",
                new BeanPropertyRowMapper<>(Market.class),
                id
        ));
    }
}
