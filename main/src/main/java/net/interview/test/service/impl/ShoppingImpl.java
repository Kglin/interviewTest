package net.interview.test.service.impl;

import net.interview.test.pojo.CategoryInfo;
import net.interview.test.pojo.PurchaseItem;
import net.interview.test.pojo.PurchaseOrder;
import net.interview.test.pojo.PurchaseReceipt;
import net.interview.test.pojo.TaxRate;
import net.interview.test.service.ShoppingService;
import net.interview.test.utils.CategoryUtils;
import net.interview.test.utils.TaxRateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */

@Service
public class ShoppingImpl implements ShoppingService {

    @Autowired
    private TaxRateUtils taxRateUtils;

    @Autowired
    private CategoryUtils categoryUtils;

    /**
     * Checks out a purchase order and generates a purchase receipt.
     *
     * @param order The purchase order, must not be null, and the order location must not be empty.
     * @return PurchaseReceipt The generated receipt containing order items, subtotal, tax, and total.
     * @throws IllegalArgumentException If the order or its location is null, this exception is thrown.
     * @throws RuntimeException         If the tax rates cannot be retrieved, this runtime exception is thrown.
     */
    @Override
    public PurchaseReceipt checkout(PurchaseOrder order) {
        if (order == null || order.getLocation() == null) {
            throw new IllegalArgumentException("Order and order location must not be null.");
        }

        Map<String, TaxRate> taxRates;
        try {
            taxRates = taxRateUtils.getTaxRates();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve tax rates.", e);
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal taxTotal = BigDecimal.ZERO;
        Map<String, String> categoryCache = new HashMap<>();
        TaxRate taxRate = taxRates.get(order.getLocation());

        for (PurchaseItem item : order.getItems()) {
            BigDecimal itemPrice = BigDecimal.valueOf(item.getPrice()).multiply(new BigDecimal(item.getQuantity()));
            subtotal = subtotal.add(itemPrice);

            String categories = categoryCache.computeIfAbsent(item.getName(), this::safeFindCategoryByObject);

            if (!taxRate.getExempt().contains(categories)) {
                taxTotal = taxTotal.add(itemPrice);
            }
        }

        BigDecimal tax = roundToNearestPointZeroFive(taxTotal.multiply(BigDecimal.valueOf(taxRate.getTax())));
        BigDecimal total = subtotal.add(tax);

        PurchaseReceipt receipt = new PurchaseReceipt();
        receipt.setItems(order.getItems());
        receipt.setSubtotal(subtotal.doubleValue());
        receipt.setTax(tax.doubleValue());
        receipt.setTotal(total.doubleValue());

        return receipt;
    }

    /**
     * Safely finds the category associated with an object by its name.
     * Tries to invoke `findCategoryByObject` to get the category of the item and,
     * in case of any exception, catches it and rethrows as a RuntimeException
     * with details about the failed operation and the original exception.
     *
     * @param itemName The name of the item whose category is to be found.
     * @return The category of the item as a string. If the lookup fails, a RuntimeException is thrown instead.
     */
    private String safeFindCategoryByObject(String itemName) {
        try {
            return findCategoryByObject(itemName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find category for item: " + itemName, e);
        }
    }

    /**
     * Rounds the given BigDecimal value to the nearest 0.05.
     *
     * @param value The BigDecimal object to be rounded.
     * @return A BigDecimal object rounded to the nearest 0.05.
     */
    private BigDecimal roundToNearestPointZeroFive(BigDecimal value) {
        return BigDecimal.valueOf(Math.ceil(value.doubleValue() * 20.0) / 20.0);
    }

    /**
     * Retrieves the category name associated with the given object.
     *
     * @param object The name of the object for which to find the category.
     * @return The category name if found, or an empty string if no matching category exists or the category list is null.
     */
    public String findCategoryByObject(String object) {
        List<CategoryInfo> categoryInfos = categoryUtils.getCategoryList();
        if (categoryInfos == null) {
            return "";
        }
        Optional<String> result = categoryInfos.stream()
                .filter(categoryInfo -> categoryInfo.getObject().contains(object))
                .map(CategoryInfo::getCategory)
                .findFirst();
        return result.orElse("");
    }

}
