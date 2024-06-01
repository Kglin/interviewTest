package net.interview.test.service;


import net.interview.test.pojo.PurchaseOrder;
import net.interview.test.pojo.PurchaseReceipt;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */

public interface ShoppingService {

    PurchaseReceipt checkout(PurchaseOrder order);

}
