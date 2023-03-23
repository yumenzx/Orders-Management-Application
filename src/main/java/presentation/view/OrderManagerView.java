package presentation.view;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Interfata grafica pentru prelucrarea comenzilor
 */
public class OrderManagerView extends JFrame {

    private final JTextField textFieldAmount;

    private final JButton btnPlaceOrder;

    private final JTable tableClient;
    private final JTable tableProduct;
    private final JTable tableOrders;

    /**
     * constructorul interfetei cu utilizatorul
     */
    public OrderManagerView() {

        this.setBounds(100, 100, 1117, 435);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setTitle("Order Manager");

        JLabel lblTittle = new JLabel("Orders Manager");
        lblTittle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTittle.setBounds(497, 20, 159, 20);
        this.getContentPane().add(lblTittle);

        JScrollPane scrollPaneClients = new JScrollPane();
        scrollPaneClients.setBounds(20, 63, 357, 266);
        this.getContentPane().add(scrollPaneClients);

        tableClient = new JTable();
        scrollPaneClients.setViewportView(tableClient);
        tableClient.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JScrollPane scrollPaneProduct = new JScrollPane();
        scrollPaneProduct.setBounds(406, 63, 323, 266);
        this.getContentPane().add(scrollPaneProduct);

        tableProduct = new JTable();
        scrollPaneProduct.setViewportView(tableProduct);
        tableProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JScrollPane scrollPaneOrders = new JScrollPane();
        scrollPaneOrders.setBounds(761, 63, 323, 266);
        this.getContentPane().add(scrollPaneOrders);

        tableOrders = new JTable();
        scrollPaneOrders.setViewportView(tableOrders);
        tableOrders.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JLabel lblCurrentOrders = new JLabel("Current Orders");
        lblCurrentOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCurrentOrders.setBounds(876, 339, 110, 20);
        this.getContentPane().add(lblCurrentOrders);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAmount.setBounds(303, 355, 58, 20);
        this.getContentPane().add(lblAmount);

        textFieldAmount = new JTextField();
        textFieldAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldAmount.setBounds(360, 356, 79, 19);
        this.getContentPane().add(textFieldAmount);
        textFieldAmount.setColumns(10);

        btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnPlaceOrder.setBounds(505, 354, 120, 21);
        this.getContentPane().add(btnPlaceOrder);

        this.setVisible(true);
    }

    public void addPlaceOrderListenner(ActionListener action) {
        btnPlaceOrder.addActionListener(action);
    }

    /**
     * metoda de actualizarea tabelelor din interfata cu tabela din baza de date
     *
     * @param dataClient tabela clientilor
     * @param dataProduct tabela produselor
     * @param dataOrders tabela comenzilor
     */
    public void updateTable(Object[][] dataClient, Object[][] dataProduct, Object[][] dataOrders) {
        DefaultTableModel modelClient = new DefaultTableModel(dataClient, dataClient[0]);
        DefaultTableModel modelProduct = new DefaultTableModel(dataProduct, dataProduct[0]);
        DefaultTableModel modelOrders = new DefaultTableModel(dataOrders, dataOrders[0]);
        modelClient.removeRow(0);
        modelProduct.removeRow(0);
        modelOrders.removeRow(0);
        tableClient.setModel(modelClient);
        tableProduct.setModel(modelProduct);
        tableOrders.setModel(modelOrders);
    }

    public String getTextFieldAmount() {
        return textFieldAmount.getText();
    }

    public int getSelectedClient() {
        int row = tableClient.getSelectedRow();
        return Integer.parseInt(String.valueOf(tableClient.getValueAt(row, 0)));
    }

    public int getSelectedProduct() {
        int row = tableProduct.getSelectedRow();
        return Integer.parseInt(String.valueOf(tableProduct.getValueAt(row, 0)));
    }

    /**
     * afiseaza mesajul dat ca parametru intr-o fereastra pop up
     * @param message  mesajul de afisat
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
