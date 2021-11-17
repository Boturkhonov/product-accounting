package edu.istu.productaccounting.controller;

import edu.istu.productaccounting.repository.MarketRepository;
import edu.istu.productaccounting.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @Value("${spring.application.name}")
    String appName;

    private final MarketRepository marketRepository;
    private final StorageRepository storageRepository;

    public MainController(MarketRepository marketRepository,
                          StorageRepository storageRepository) {
        this.marketRepository = marketRepository;
        this.storageRepository = storageRepository;
    }

    @GetMapping()
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }

}
