package presentation.controller;

import business.ClientBLL;
import business.OrderBLL;
import business.ProductBLL;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import model.Client;
import model.Orders;
import model.Product;
import presentation.view.OrderManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * controllerul interfetei grafice pentru prelucrarea comenzilor
 */
public class OrderManagerController {

    private final OrderManagerView view;

    /**
     * construcorul creeaza o noua interfata grafica si actualizeaza tabela
     */
    public OrderManagerController() {
        view = new OrderManagerView();
        view.updateTable(ClientBLL.getTable(), ProductBLL.getTable(), OrderBLL.getTable());
        view.addPlaceOrderListenner(new PlaceOrderListenner());
    }

    /**
     * metoda de generare a fisierului pdf pentru chitanta/factura
     */
    private void printBill() {
        Orders o = OrderBLL.getLastOrder();
        Client c = ClientBLL.findById(o.getIdClient());
        Product p = ProductBLL.findById(o.getIdProduct());
        Document doc = new Document(PageSize.A4);
        String s = String.format("\t Bill nr. %d\n\nClient: %s \t %d\n%d BUC %s x %d RON ..... %d RON\nTotal: %d RON", o.getId(), c.getName(), c.getId(), o.getAmount(), p.getName(), p.getPrice(), o.getAmount() * p.getPrice(), o.getAmount() * p.getPrice());
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("bill_" + o.getId() + ".pdf"));
            doc.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            doc.add(new Paragraph(s, font));
            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * clasa ascultatoare pentru butonul de plasare a unei comenzi
     */
    class PlaceOrderListenner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idClient, idProduct, amount;
            try {
                idClient = view.getSelectedClient();
                idProduct = view.getSelectedProduct();
                amount = Integer.parseInt(view.getTextFieldAmount());
                Product p = ProductBLL.findById(idProduct);
                if (p.getStock() >= amount) {
                    Orders o = new Orders(0, idClient, idProduct, amount);
                    OrderBLL.addOrder(o);
                    p.setStock(p.getStock() - amount);
                    ProductBLL.editProduct(idProduct, p);
                    printBill();
                    view.showMessage("Comanda a fost plasata");
                } else {
                    view.showMessage("Nu exista stock disponibil pentru acest produs");
                }
                view.updateTable(ClientBLL.getTable(), ProductBLL.getTable(), OrderBLL.getTable());
            } catch (Exception exception) {
                view.showMessage("Selectati clientul, produsul si introduceti valoarea pentru Amount");
            }
        }
    }
}
