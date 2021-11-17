package edu.istu.productaccounting.impl;

import edu.istu.productaccounting.model.Product;
import edu.istu.productaccounting.model.Unit;
import edu.istu.productaccounting.repository.ProductRepository;
import edu.istu.productaccounting.repository.UnitRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UnitRepository unitRepository;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate, UnitRepository unitRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.unitRepository = unitRepository;
    }

    @Override
    public int save(Product product) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("add_product");

        return simpleJdbcCall.executeFunction(Integer.class,
                product.getName(),
                product.getUnitId()
        );
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("call del_product(?)", id);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = jdbcTemplate.query(
                "select * from product",
                new BeanPropertyRowMapper<>(Product.class));
        products.forEach(this::fillProduct);
        return products;
    }

    private void fillProduct(Product product) {
        Unit unit = unitRepository.findById(product.getUnitId())
                .orElseThrow(() -> new RuntimeException("unit not found"));
        product.setUnit(unit.getName());
    }

    @Override
    public Product findById(int id) {
        Product product = Optional.ofNullable(jdbcTemplate.queryForObject(
                "select * from product where id = ?",
                new BeanPropertyRowMapper<>(Product.class),
                id
        )).orElseThrow(() -> new RuntimeException("Product not found"));
        fillProduct(product);
        return product;
    }
}
