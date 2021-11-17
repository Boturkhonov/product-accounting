package edu.istu.productaccounting.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private int id;
    private String name;
    private int unitId;
    private String unit;
}
