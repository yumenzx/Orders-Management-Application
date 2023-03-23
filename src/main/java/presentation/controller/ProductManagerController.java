package presentation.controller;

import business.ProductBLL;
import model.Product;
import presentation.view.ProductManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * controllerul interfetei grafice pentru prelucrarea produselor
 */
public class ProductManagerController {

    private final ProductManagerView view;

    /**
     * construcorul creeaza o noua interfata grafica si actualizeaza tabela
     */
    public ProductManagerController() {
        view = new ProductManagerView();
        view.updateTable(ProductBLL.getTable());
        view.addListeners(new AddListenner(), new EditListenner(), new RemoveListenner(), new OKListenner());
    }

    /**
     * clasa ascultatoare pentru butonul adaugare
     */
    class AddListenner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name, price, stock;
            name = view.getTextFieldName();
            price = view.gettextFieldPrice();
            stock = view.gettextFieldStock();
            try {
                ProductBLL.addProduct(new Product(0, name, Integer.parseInt(price), Integer.parseInt(stock)));
            } catch (Exception exception) {
                view.showMessage("Datele introduse nu sunt valide");
            }
            view.updateTable(ProductBLL.getTable());
        }
    }

    /**
     * clasa ascultatoare pentru butonul editare
     */
    class EditListenner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getInputID();
            if (id != -1) {
                Product p = ProductBLL.findById(id);
                if (p != null) {
                    view.showButtons(true);
                    view.setTextFieldID(String.valueOf(p.getId()));
                    view.setTextFieldName(p.getName());
                    view.settextFieldPrice(String.valueOf(p.getPrice()));
                    view.settextFieldStock(String.valueOf(p.getStock()));
                    view.showMessage("Dupa modificarea datelor, confirmati actualizarea acestora prin butonul OK");
                } else {
                    view.showMessage("Produsul cu id-ul introdus nu exista in Baza De Date");
                }
            }
        }
    }

    /**
     * clasa ascultatoare pentru butonul stergere
     */
    class RemoveListenner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getInputID();
            if (id != -1) {
                ProductBLL.removeProduct(id);
                view.updateTable(ProductBLL.getTable());
            } else
                view.showMessage("Introduceti un ID valid");
        }
    }

    /**
     * clasa ascultatoare pentru butonul OK
     */
    class OKListenner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id, price, stock;
            String name;
            try {
                id = Integer.parseInt(view.getTextFieldID());
                name = view.getTextFieldName();
                price = Integer.parseInt(view.gettextFieldPrice());
                stock = Integer.parseInt(view.gettextFieldStock());
                Product p = new Product(id, name, price, stock);
                ProductBLL.editProduct(id, p);
                view.updateTable(ProductBLL.getTable());
                view.showMessage("Datele produsului au fost actualizate");
            } catch (Exception exception) {
                view.showMessage("Datele introduse nu sunt valide");
            }
            view.clearFields();
            view.showButtons(false);
        }
    }
}
