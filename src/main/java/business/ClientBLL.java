package business;

import dataAccess.dao.ClientDAO;
import model.Client;

/**
 * clasa corepsunzatoare de manipulare a tabelei de clienti
 */
public class ClientBLL {

    /**
     * metoda de returnare a continutului tabelei sub forma de matrice
     * @return matricea care pe fiecare linie contine cate o tupla
     */
    public static Object[][] getTable() {
        return ClientDAO.dao.getTableRecords();
    }

    /**
     * metoda de cautarea unui client din baza de date dupa id-ul acestuia
     * @param id id-ul clientului supus gasirii
     * @return clientul in urma cautarii
     */
    public static Client findById(int id) {
        return ClientDAO.dao.findById(id);
    }

    /**
     * metoda de adaugare a unui nou client in baza de date
     * @param c clinetul supus adaugarii
     */
    public static void addClient(Client c) {
        ClientDAO.dao.insertClient(c);

    }

    /**
     * metoda de actualizare a clientului din baza de date
     * @param id id-ul clientului
     * @param c clientul dupa care se face actualizarea
     */
    public static void editClient(int id, Client c) {
        ClientDAO.dao.updateClient(id, c);
    }

    /**
     * metoda de stergere a unui client din baza de date
     * @param id id-ul clientului supus stergerii
     */
    public static void removeClient(int id) {
        ClientDAO.dao.deleteClient(id);
    }
}
