package edu.istu.productaccounting.controller;

import edu.istu.productaccounting.model.Product;
import edu.istu.productaccounting.repository.ProductRepository;
import edu.istu.productaccounting.repository.UnitRepository;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;

    public ProductController(ProductRepository productRepository, UnitRepository unitRepository) {
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
    }

    @GetMapping()
    public String getProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("units", unitRepository.findAll());
        return "products";
    }

    @GetMapping("/product")
    @ResponseBody
    public Product getProduct(@RequestParam Integer id) {
        return productRepository.findById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        int id = productRepository.save(product);
        return productRepository.findById(id);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> delProduct(@RequestParam Integer id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public String updateProduct(@RequestBody Product product) {
        productRepository.update(product);
        return "";
    }

    @ExceptionHandler(PSQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleSqlExceptions(PSQLException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", Objects.requireNonNull(ex.getServerErrorMessage()).getMessage());
        return errors;
    }
}
