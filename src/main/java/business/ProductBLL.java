package business;


import dataAccess.dao.ProductDAO;
import model.Product;

/**
 * clasa corepsunzatoare de manipulare a tabelei de produse
 */
public class ProductBLL {

    /**
     * metoda de returnare a continutului tabelei sub forma de matrice
     * @return matricea care pe fiecare linie contine cate o tupla
     */
    public static Object[][] getTable() {
        return ProductDAO.dao.getTableRecords();
    }

    /**
     * metoda de gasirea unei produse dupa id-ul acestuia
     * @param id id-ul produsului
     * @return produsul cu id-ul corepunzator din baza de date
     */
    public static Product findById(int id) {
        return ProductDAO.dao.findById(id);
    }

    /**
     * metoda de adaugare a unui nou produs in baza de date
     * @param p produsul supus adaugarii
     */
    public static void addProduct(Product p) {
        ProductDAO.dao.insertProduct(p);
    }

    /**
     * metoda de actualizare a produsului din baza de date
     * @param id id-ul clientului
     * @param p produsul dupa care se face actualizarea
     */
    public static void editProduct(int id, Product p) {
        ProductDAO.dao.updateProduct(id, p);
    }

    /**
     * metoda de stergere a unui produs din baza de date
     * @param id id-ul produsului supus stergerii
     */
    public static void removeProduct(int id) {
        ProductDAO.dao.deleteProduct(id);
    }
}
