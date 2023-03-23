package presentation;

import presentation.controller.ClientManagerController;
import presentation.controller.OrderManagerController;
import presentation.controller.ProductManagerController;

import javax.swing.*;
import java.awt.Font;

/**
 * Clasa principala de unde incepe executia, reprezinta interfata pentru selectarea interfetelor de prelucrare a clinetilor, produselor si comenzilor
 */
public class MainPanel extends JFrame {

    public MainPanel() {
        this.setBounds(100, 100, 469, 315);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblMainPanel = new JLabel("Main Panel");
        lblMainPanel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMainPanel.setBounds(184, 15, 97, 25);
        this.getContentPane().add(lblMainPanel);

        JButton btnManageClients = new JButton("Manage Clients");
        btnManageClients.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnManageClients.setBounds(149, 71, 160, 33);
        this.getContentPane().add(btnManageClients);
        btnManageClients.addActionListener(e -> new ClientManagerController());

        JButton btnManageProducts = new JButton("Manage Products");
        btnManageProducts.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnManageProducts.setBounds(149, 114, 160, 33);
        this.getContentPane().add(btnManageProducts);
        btnManageProducts.addActionListener(e -> new ProductManagerController());

        JButton btnPlaceOrders = new JButton("Place Orders");
        btnPlaceOrders.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnPlaceOrders.setBounds(149, 157, 160, 33);
        this.getContentPane().add(btnPlaceOrders);
        btnPlaceOrders.addActionListener(e -> new OrderManagerController());

        JButton btnSignOut = new JButton("Sign Out");
        btnSignOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSignOut.setBounds(10, 247, 97, 21);
        this.getContentPane().add(btnSignOut);
        btnSignOut.addActionListener(e -> {
            this.dispose();
            System.exit(0);
        });

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainPanel();
    }

}
