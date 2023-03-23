package dataAccess.dao;

import model.Orders;

import java.util.List;

/**
 * clasa extinde AbstractDAO, de aici se vor face apelarile pentru metodele de manipulare a comenzilor din baza de date
 */
public class OrderDAO extends AbstractDAO<Orders> {

    public static OrderDAO dao = new OrderDAO();

    public OrderDAO() {
    }

    /**
     * metoda de returnare a celei mai recente comenzi
     * @return cea mai recenta comanda
     */
    public Orders getLastOrder() {
        List<Orders> o = findAll();
        return o.get(o.size() - 1);
    }

    /**
     * metoda de adaugare a unei noi comenzi in baza de date
     * @param o comanda supusa adaugarii
     */
    public void insertOrder(Orders o) {
        insert(o);
    }
}
