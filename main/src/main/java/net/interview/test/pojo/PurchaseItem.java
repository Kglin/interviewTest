package net.interview.test.pojo;

import lombok.Data;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */

@Data
public class PurchaseItem {
    private String name;
    private double price;
    private int quantity;
}
