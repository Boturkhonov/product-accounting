package edu.istu.productaccounting.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StorageProduct {
    private int id;
    private int storageId;
    private int productId;
    private int quantity;
    private float price;
    private Product product;
}
