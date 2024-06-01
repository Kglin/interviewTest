package net.interview.test.service.impl;

import mock.MockCategoryInfo;
import mock.MockOrder;
import mock.MockTaxRate;
import net.interview.test.pojo.PurchaseItem;
import net.interview.test.pojo.PurchaseOrder;
import net.interview.test.pojo.PurchaseReceipt;
import net.interview.test.utils.CategoryUtils;
import net.interview.test.utils.TaxRateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

class ShoppingImplTest {

    @InjectMocks
    private ShoppingImpl shoppingService;

    @Mock
    private TaxRateUtils taxRateUtils;

    @Mock
    private CategoryUtils categoryUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCheckoutWithValidOrderA_thenSuccess() {

        when(taxRateUtils.getTaxRates()).thenReturn(MockTaxRate.getTaxRate());

        when(categoryUtils.getCategoryList()).thenReturn(MockCategoryInfo.getCategoryInfo());

        PurchaseOrder order = MockOrder.getOrderA();
        PurchaseReceipt receipt = shoppingService.checkout(order);

        Assertions.assertNotNull(receipt);
        Assertions.assertEquals(order.getItems(), receipt.getItems());
        Assertions.assertEquals(receipt.getSubtotal(), 21.98);
        Assertions.assertEquals(receipt.getTax(), 1.80);
        Assertions.assertEquals(receipt.getTotal(), 23.78);
        Assertions.assertEquals(order.getItems().stream()
                        .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
                        .doubleValue(), receipt.getSubtotal(), 0.01);
    }

    @Test
    void whenCheckoutWithValidOrderB_thenSuccess() {

        when(taxRateUtils.getTaxRates()).thenReturn(MockTaxRate.getTaxRate());

        when(categoryUtils.getCategoryList()).thenReturn(MockCategoryInfo.getCategoryInfo());

        PurchaseOrder order = MockOrder.getOrderB();
        PurchaseReceipt receipt = shoppingService.checkout(order);

        Assertions.assertNotNull(receipt);
        Assertions.assertEquals(order.getItems(), receipt.getItems());
        Assertions.assertEquals(receipt.getSubtotal(), 26.96);
        Assertions.assertEquals(receipt.getTax(), 2.40);
        Assertions.assertEquals(receipt.getTotal(), 29.36);
        Assertions.assertEquals(order.getItems().stream()
                        .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
                        .doubleValue(), receipt.getSubtotal(), 0.01);
    }

    @Test
    void whenCheckoutWithValidOrderC_thenSuccess() {

        when(taxRateUtils.getTaxRates()).thenReturn(MockTaxRate.getTaxRate());

        when(categoryUtils.getCategoryList()).thenReturn(MockCategoryInfo.getCategoryInfo());

        PurchaseOrder order = MockOrder.getOrderC();
        PurchaseReceipt receipt = shoppingService.checkout(order);

        Assertions.assertNotNull(receipt);
        Assertions.assertEquals(order.getItems(), receipt.getItems());
        Assertions.assertEquals(receipt.getSubtotal(), 35.97);
        Assertions.assertEquals(receipt.getTax(), 0.55);
        Assertions.assertEquals(receipt.getTotal(), 36.52);
        Assertions.assertEquals(order.getItems().stream()
                        .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
                        .doubleValue(), receipt.getSubtotal(), 0.01);
    }

    @Test
    void whenCheckoutWithNullOrder_thenThrowException() {
        PurchaseOrder order = null;

        assertThrows(IllegalArgumentException.class, () -> shoppingService.checkout(order));
    }

    @Test
    void whenCheckoutWithNullLocation_thenThrowException() {
        String location = null;

        PurchaseOrder order = new PurchaseOrder();
        order.setLocation(location);

        List<PurchaseItem> items = new ArrayList<>();
        PurchaseItem shirt = new PurchaseItem();
        shirt.setName("shirt");
        shirt.setPrice(29.99);
        shirt.setQuantity(1);

        PurchaseItem pencil = new PurchaseItem();
        pencil.setName("pencil");
        pencil.setPrice(2.99);
        pencil.setQuantity(2);

        items.add(shirt);
        items.add(pencil);

        order.setItems(items);

        assertThrows(IllegalArgumentException.class, () -> shoppingService.checkout(order));
    }

    @Test
    void whenCheckoutAndTaxRateRetrievalFails_thenThrowRuntimeException() {
        String location = "NY";
        PurchaseOrder order = new PurchaseOrder();
        order.setLocation(location);

        List<PurchaseItem> items = new ArrayList<>();
        PurchaseItem shirt = new PurchaseItem();
        shirt.setName("shirt");
        shirt.setPrice(29.99);
        shirt.setQuantity(1);

        PurchaseItem pencil = new PurchaseItem();
        pencil.setName("pencil");
        pencil.setPrice(2.99);
        pencil.setQuantity(2);

        items.add(shirt);
        items.add(pencil);

        order.setItems(items);

        when(taxRateUtils.getTaxRates()).thenThrow(new RuntimeException("Failed to retrieve tax rates"));

        assertThrows(RuntimeException.class, () -> shoppingService.checkout(order));
    }
}
