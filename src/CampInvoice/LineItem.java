package CampInvoice;

import java.util.ArrayList;

public class LineItem {
    private Product product;
    private int quantity = 0;
    private double calculatedTotal = 0.0;


    public LineItem(Product product,int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double calculateTotal(){
        return quantity * product.getUnitPrice();
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "quantity=" + quantity +
                ", calculatedTotal=" + calculatedTotal +
                ", product=" + product +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCalculatedTotal() {
        calculateTotal();
        return calculatedTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
