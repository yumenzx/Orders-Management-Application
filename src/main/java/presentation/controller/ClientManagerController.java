package presentation.controller;

import business.ClientBLL;
import model.Client;
import presentation.view.ClientManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * controllerul interfetei grafice pentru prelucrarea clientilor
 */
public class ClientManagerController {

    private final ClientManagerView view;

    /**
     * construcorul creeaza o noua interfata grafica si actualizeaza tabela
     */
    public ClientManagerController() {
        view = new ClientManagerView();
        view.updateTable(ClientBLL.getTable());
        view.addListeners(new AddListenner(), new EditListenner(), new RemoveListenner(), new OKListenner());
    }

    /**
     * clasa ascultatoare pentru butonul adaugare
     */
    class AddListenner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name, age, address;
            name = view.getTextFieldName();
            age = view.getTextFieldAge();
            address = view.getTextFieldAddress();
            try {
                ClientBLL.addClient(new Client(0, name, Integer.parseInt(age), address));
            } catch (Exception exception) {
                view.showMessage("Datele introduse nu sunt valide");
            }
            view.updateTable(ClientBLL.getTable());
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
                Client c = ClientBLL.findById(id);
                if (c != null) {
                    view.showButtons(true);
                    view.setTextFieldID(String.valueOf(c.getId()));
                    view.setTextFieldName(c.getName());
                    view.setTextFieldAge(String.valueOf(c.getAge()));
                    view.setTextFieldAddress(c.getAddress());
                    view.showMessage("Dupa modificarea datelor, confirmati actualizarea acestora prin butonul OK");
                } else {
                    view.showMessage("Clientul cu id-ul introdus nu exista in Baza De Date");
                }
            }
        }
    }

    /**
     * clasa ascultatoare pentru butonul stegere
     */
    class RemoveListenner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getInputID();
            if (id != -1) {
                ClientBLL.removeClient(id);
                view.updateTable(ClientBLL.getTable());
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
            int id, age;
            String name, address;
            try {
                id = Integer.parseInt(view.getTextFieldID());
                name = view.getTextFieldName();
                age = Integer.parseInt(view.getTextFieldAge());
                address = view.getTextFieldAddress();
                Client c = new Client(id, name, age, address);
                ClientBLL.editClient(id, c);
                view.updateTable(ClientBLL.getTable());
                view.showMessage("Datele clientului au fost actualizate");
            } catch (Exception exception) {
                view.showMessage("Datele introduse nu sunt valide");
            }
            view.clearFields();
            view.showButtons(false);
        }
    }
}
