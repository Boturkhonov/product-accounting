package edu.istu.productaccounting.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MarketProduct {
    private int id;
    private int marketId;
    private int productId;
    private int quantity;
    private float price;
    private Product product;
}
