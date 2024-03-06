package CampInvoice;

import java.util.ArrayList;

public class Invoice {
    private double totalAmountDue = 0.0;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

    public Invoice(ArrayList<LineItem> lineItems){
        this.lineItems = lineItems;
    }

    public void addLineItemToInvoice(LineItem item){
        lineItems.add(item);
    }
    public String generateInvoice(Customer currentCustomer){
        String returnVal = "";
        returnVal += "\n\t\t\t   Invoice\n";
        returnVal += currentCustomer.generateAddressBlock() + "\n=============================================================\n";
        returnVal += "Item\t\t\t\tQty\tPrice\tTotal\n";
        for (LineItem lineItem : lineItems)
        {
            returnVal +=  lineItem.getProduct().getName() + "\t\t\t\t" + lineItem.getQuantity() + "\t" +lineItem.getProduct().getUnitPrice() + "\t" + lineItem.calculateTotal() + "\n";
        }
        returnVal += "\n=============================================================\n" + "Amount Due: $" + generateAmountDue();
        return returnVal;
    }

    public double generateAmountDue(){
        double amountDue = 0.0;
        for(LineItem lineItem : lineItems){
            amountDue += lineItem.calculateTotal();
        }
        return amountDue;
    }



}