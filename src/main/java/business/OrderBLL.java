package business;


import dataAccess.dao.OrderDAO;
import model.Orders;

/**
 * clasa corepsunzatoare de manipulare a tabelei de comenzi
 */
public class OrderBLL {

    /**
     * metoda de obtinerea celei mai recente comenzi plasate
     * @return cea mai recenta comanda
     */
    public static Orders getLastOrder() {
        return OrderDAO.dao.getLastOrder();
    }

    /**
     * metoda de returnare a continutului tabelei sub forma de matrice
     * @return matricea care pe fiecare linie contine cate o tupla
     */
    public static Object[][] getTable() {
        return OrderDAO.dao.getTableRecords();
    }

    /**
     * metoda de adaugare a unei noi comenzi in baza de date
     * @param o comanda supusa adaugarii
     */
    public static void addOrder(Orders o) {
        OrderDAO.dao.insertOrder(o);
    }

}