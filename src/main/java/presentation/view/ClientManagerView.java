package presentation.view;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Interfata grafica pentru prelucrarea produselor
 */
public class ClientManagerView extends JFrame {

    private final JLabel lblID;

    private final JTextField textFieldID;
    private final JTextField textFieldName;
    private final JTextField textFieldAge;
    private final JTextField textFieldAddress;

    private final JButton btnAdd;
    private final JButton btnEdit;
    private final JButton btnRemove;
    private final JButton btnOK;
    private final JButton btnCancel;

    private final JTable table;

    /**
     * constructorul interfetei cu utilizatorul
     */
    public ClientManagerView() {
        this.setBounds(100, 100, 740, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setTitle("Client Manager");

        JLabel lbl1 = new JLabel("Manage Clients");
        lbl1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbl1.setBounds(74, 22, 134, 40);
        this.getContentPane().add(lbl1);

        lblID = new JLabel("ID");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblID.setBounds(20, 72, 24, 19);
        this.getContentPane().add(lblID);
        lblID.setVisible(false);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(20, 94, 52, 19);
        this.getContentPane().add(lblName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAge.setBounds(20, 117, 52, 19);
        this.getContentPane().add(lblAge);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAddress.setBounds(20, 140, 52, 16);
        this.getContentPane().add(lblAddress);

        textFieldID = new JTextField();
        textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldID.setBounds(74, 72, 140, 19);
        this.getContentPane().add(textFieldID);
        textFieldID.setColumns(10);
        textFieldID.setVisible(false);
        textFieldID.setEditable(false);

        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldName.setColumns(10);
        textFieldName.setBounds(74, 94, 140, 19);
        this.getContentPane().add(textFieldName);

        textFieldAge = new JTextField();
        textFieldAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldAge.setColumns(10);
        textFieldAge.setBounds(74, 117, 140, 19);
        this.getContentPane().add(textFieldAge);

        textFieldAddress = new JTextField();
        textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(74, 139, 140, 19);
        this.getContentPane().add(textFieldAddress);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAdd.setBounds(74, 178, 66, 21);
        this.getContentPane().add(btnAdd);

        btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEdit.setBounds(150, 178, 64, 21);
        this.getContentPane().add(btnEdit);

        btnRemove = new JButton("Remove");
        btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRemove.setBounds(86, 209, 111, 21);
        this.getContentPane().add(btnRemove);

        btnOK = new JButton("OK");
        btnOK.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnOK.setBounds(150, 178, 85, 21);
        this.getContentPane().add(btnOK);
        btnOK.setVisible(false);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancel.setBounds(55, 178, 85, 21);
        this.getContentPane().add(btnCancel);
        btnCancel.setVisible(false);
        btnCancel.addActionListener(e -> {
            clearFields();
            showButtons(false);
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(264, 24, 432, 221);
        this.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));

        this.setVisible(true);
    }

    /**
     * metoda de adaugare a ascultatorilor pentru butoane
     * @param actionAdd ascultator pentru butonul de adaugare
     * @param actionEdit ascultator pentru butonul de editare
     * @param actionRemove ascultator pentru butonul de stergere
     * @param actionOK ascultator pentru butonul OK
     */
    public void addListeners(ActionListener actionAdd, ActionListener actionEdit, ActionListener actionRemove, ActionListener actionOK) {
        btnAdd.addActionListener(actionAdd);
        btnEdit.addActionListener(actionEdit);
        btnRemove.addActionListener(actionRemove);
        btnOK.addActionListener(actionOK);
    }

    public String getTextFieldID() {
        return textFieldID.getText();
    }

    public void setTextFieldID(String textFieldID) {
        this.textFieldID.setText(textFieldID);
    }

    public String getTextFieldName() {
        return textFieldName.getText();
    }

    public void setTextFieldName(String textFieldName) {
        this.textFieldName.setText(textFieldName);
    }

    public String getTextFieldAge() {
        return textFieldAge.getText();
    }

    public void setTextFieldAge(String textFieldAge) {
        this.textFieldAge.setText(textFieldAge);
    }

    public String getTextFieldAddress() {
        return textFieldAddress.getText();
    }

    public void setTextFieldAddress(String textFieldAddress) {
        this.textFieldAddress.setText(textFieldAddress);
    }

    public void showButtons(Boolean show) {
        lblID.setVisible(show);
        textFieldID.setVisible(show);
        btnOK.setVisible(show);
        btnCancel.setVisible(show);
        btnAdd.setVisible(!show);
        btnEdit.setVisible(!show);
        btnRemove.setVisible(!show);
    }

    /**
     * metoda de actualizarea tabelei din interfata cu tabela din baza de date
     * @param data  contine tabela propriu zisa
     */
    public void updateTable(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, data[0]);
        model.removeRow(0);
        table.setModel(model);
    }

    /**
     * afiseaza o fereastra op up unde se poate citi un id
     * @return returneaza valoarea citita
     */
    public int getInputID() {
        String input = JOptionPane.showInputDialog("Introduceti id-ul clientului");
        try {
            return Integer.parseInt(input);
        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * sterge datele introduse in text fileduri
     */
    public void clearFields() {
        textFieldID.setText("");
        textFieldName.setText("");
        textFieldAge.setText("");
        textFieldAddress.setText("");
    }

    /**
     * afiseaza mesajul dat ca parametru intr-o fereastra pop up
     * @param message  mesajul de afisat
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
