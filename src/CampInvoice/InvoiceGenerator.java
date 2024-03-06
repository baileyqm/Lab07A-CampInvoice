package CampInvoice;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class InvoiceGenerator extends JFrame {

    JPanel mainPnl;
    JPanel addressPnl;
    JPanel addItemPnl = new JPanel();
    JPanel invoicePnl = new JPanel();

    JLabel nameLbl;
    JTextField nameFld;

    JLabel streetLbl;
    JTextField streetFld;

    JLabel cityLbl;
    JTextField cityFld;

    JLabel stateLbl;
    JTextField stateFld;

    JLabel zipCodeLbl;
    JTextField zipCodeFld;

    JPanel AddItemPanelAndControlPnl;
    JButton addItemBtn;
    JLabel itemNameLbl;
    JTextField itemNameFld;
    JLabel itemPriceLbl;
    JTextField itemPriceFld;
    JButton generateBtn;
    JButton quitBtn;
    JTextField quantityFld;

    JTextArea invoiceArea;
    JScrollPane scroller = new JScrollPane();
    ArrayList<LineItem> currentLineItems = new ArrayList<>();


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public InvoiceGenerator(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Invoice Generator");
        mainPnl = new JPanel(new BorderLayout());
        add(mainPnl);


        //Panel creation method calls go here
        generateAddressPanel();
        mainPnl.add(addressPnl, BorderLayout.NORTH);

        generateInvoicePnl();
        mainPnl.add(invoicePnl, BorderLayout.CENTER);

        generateAddItemPanelAndControlPanel();
        mainPnl.add(AddItemPanelAndControlPnl, BorderLayout.SOUTH);



        pack();
        setSize((int)(.5*screenSize.width),(int)(screenSize.height));
        setLocationRelativeTo(null);
        setVisible(true);
    }



    public void generateAddressPanel(){
        addressPnl = new JPanel(new GridLayout(3,1));

        JPanel customerNamePnl =  new JPanel();
        nameLbl = new JLabel("Customer Name: ");
        nameFld = new JTextField(20);
        customerNamePnl.add(nameLbl);
        customerNamePnl.add(nameFld);
        addressPnl.add(customerNamePnl);

        JPanel streetPnl = new JPanel();
        streetLbl = new JLabel("Street Address: ");
        streetFld = new JTextField(20);
        streetPnl.add(streetLbl);
        streetPnl.add(streetFld);
        addressPnl.add(streetPnl);

        JPanel cityStateZipPnl = new JPanel(new GridLayout(1,3));
        JPanel cityPnl = new JPanel();
        cityLbl = new JLabel("City: ");
        cityFld = new JTextField(15);
        cityPnl.add(cityLbl);
        cityPnl.add(cityFld);
        cityStateZipPnl.add(cityPnl);

        JPanel statePnl = new JPanel();
        stateLbl = new JLabel("State: ");
        stateFld = new JTextField(2);
        statePnl.add(stateLbl);
        statePnl.add(stateFld);
        cityStateZipPnl.add(statePnl);

        JPanel zipPnl = new JPanel();
        zipCodeLbl = new JLabel("Zip Code: ");
        zipCodeFld = new JTextField(5);
        zipPnl.add(zipCodeLbl);
        zipPnl.add(zipCodeFld);
        cityStateZipPnl.add(zipPnl);

        addressPnl.add(cityStateZipPnl);
    }

    public void generateAddItemPanelAndControlPanel(){
        AddItemPanelAndControlPnl = new JPanel(new GridLayout(2,1));
        JPanel addItemPnl = new JPanel(new GridLayout(1,7));
        itemNameLbl = new JLabel("Item Name: ");
        itemNameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        itemNameFld = new JTextField(10);
        itemNameFld.setHorizontalAlignment(SwingConstants.LEFT);
        addItemPnl.add(itemNameLbl);
        addItemPnl.add(itemNameFld);
        JLabel quantityLbl = new JLabel("Quantity:");
        quantityLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        addItemPnl.add(quantityLbl);
        quantityFld = new JTextField(3);
        addItemPnl.add(quantityFld);

        itemPriceLbl = new JLabel("Price $:");
        itemPriceFld = new JTextField(4);
        addItemPnl.add(itemPriceLbl);
        addItemPnl.add(itemPriceFld);
        itemPriceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        itemPriceFld.setHorizontalAlignment(SwingConstants.LEFT);

        addItemBtn = new JButton("Add Item");
        addItemPnl.add(addItemBtn);
        AddItemPanelAndControlPnl.add(addItemPnl);
        addItemBtn.addActionListener(e -> {
            Product tempProduct = new Product(itemNameFld.getText(),Double.valueOf(itemPriceFld.getText()));
            LineItem tempLineItem = new LineItem(tempProduct, Integer.valueOf(quantityFld.getText()));
            currentLineItems.add(tempLineItem);
            itemNameFld.setText("");
            quantityFld.setText("");
            itemPriceFld.setText("");
            JOptionPane.showMessageDialog(invoiceArea,"Item Added");
        });

        JPanel cmdPnl = new JPanel(new GridLayout(1,2));
        generateBtn = new JButton("Generate Invoice");
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            System.exit(0);
        });
        cmdPnl.add(generateBtn);
        generateBtn.addActionListener(e -> {
            Invoice currentInvoice = new Invoice(currentLineItems);
            Customer currentCustomer = new Customer(nameFld.getText(), streetFld.getText(),cityFld.getText(),stateFld.getText(),zipCodeFld.getText());
            invoiceArea.setText(currentInvoice.generateInvoice(currentCustomer));
        });
        cmdPnl.add(quitBtn);
        AddItemPanelAndControlPnl.add(cmdPnl);
    }

    public void generateInvoicePnl(){
        invoicePnl = new JPanel();
        invoiceArea = new JTextArea(50,60);
        scroller.add(invoiceArea);
        invoicePnl.add(invoiceArea);
        invoiceArea.setFont(new Font("Courier New", Font.PLAIN, 18));
        invoiceArea.setEditable(false);
        }
    }
