package net.interview.test.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */

@Data
public class PurchaseReceipt {
    private List<PurchaseItem> items;
    private double subtotal;
    private double tax;
    private double total;
}
