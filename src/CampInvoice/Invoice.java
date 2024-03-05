package CampInvoice;

import java.util.ArrayList;

public class Invoice {
    private double totalAmountDue = 0.0;
    private ArrayList<LineItem> items = new ArrayList<LineItem>();
    private Customer customer;

    public void addLineItemToInvoice(LineItem item){
        items.add(item);
    }
    public String generateInvoice(){
        String returnVal = "";
        for (LineItem item : items)
        {
            returnVal += "\n";
            returnVal += String.format("Item: %s    Price: $%.2f", item.getProduct().getName(), item.getCalculatedTotal());
        }
        return returnVal;
    }

    public double generateAmountDue(){
        double amountDue = 0.0;
        for(LineItem item : items){
            amountDue += item.calculateTotal();
        }
        return amountDue;
    }



}