package dataAccess.dao;

import model.Client;

/**
 * clasa extinde AbstractDAO, de aici se vor face apelarile pentru metodele de manipulare a clientilor din baza de date
 */
public class ClientDAO extends AbstractDAO<Client> {

    public static ClientDAO dao = new ClientDAO();

    public ClientDAO() {
    }
      

    /**
     * metoda de adaugare a unui nou client in baza de date
     * @param c clinetul supus adaugarii
     */
    public void insertClient(Client c) {
        insert(c);
    }

    /**
     * metoda de actualizare a clientului din baza de date
     * @param id id-ul clientului
     * @param c clientul dupa care se face actualizarea
     */
    public void updateClient(int id, Client c) {
        update(c, id);
    }

    /**
     * metoda de stergere a unui client din baza de date
     * @param id id-ul clientului supus stergerii
     */
    public void deleteClient(int id) {
        delete(id);
    }
}
