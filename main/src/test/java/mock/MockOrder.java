package mock;

import net.interview.test.pojo.PurchaseItem;
import net.interview.test.pojo.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

public class MockOrder {

    public static PurchaseOrder getOrderA() {

        PurchaseOrder order = new PurchaseOrder();
        order.setLocation("CA");

        List<PurchaseItem> items = new ArrayList<>();
        PurchaseItem shirt = new PurchaseItem();
        shirt.setName("book");
        shirt.setPrice(17.99);
        shirt.setQuantity(1);

        PurchaseItem pencil = new PurchaseItem();
        pencil.setName("potato chips");
        pencil.setPrice(3.99);
        pencil.setQuantity(1);

        items.add(shirt);
        items.add(pencil);

        order.setItems(items);

        return order;
    }

    public static PurchaseOrder getOrderB() {

        PurchaseOrder order = new PurchaseOrder();
        order.setLocation("NY");

        List<PurchaseItem> items = new ArrayList<>();
        PurchaseItem shirt = new PurchaseItem();
        shirt.setName("book");
        shirt.setPrice(17.99);
        shirt.setQuantity(1);

        PurchaseItem pencil = new PurchaseItem();
        pencil.setName("pencil");
        pencil.setPrice(2.99);
        pencil.setQuantity(3);

        items.add(shirt);
        items.add(pencil);

        order.setItems(items);

        return order;
    }

    public static PurchaseOrder getOrderC() {

        PurchaseOrder order = new PurchaseOrder();
        order.setLocation("NY");

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

        return order;
    }

}
