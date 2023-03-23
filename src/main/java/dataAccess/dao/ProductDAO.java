package dataAccess.dao;

import model.Product;

/**
 * clasa extinde AbstractDAO, de aici se vor face apelarile pentru metodele de manipulare a produselor din baza de date
 */
public class ProductDAO extends AbstractDAO<Product> {

    public static ProductDAO dao = new ProductDAO();

    public ProductDAO() {
    }

    /**
     * metoda de adaugare a unui nou produs in baza de date
     * @param p produsul supus adaugarii
     */
    public void insertProduct(Product p) {
        insert(p);
    }

    /**
     * metoda de actualizare a produsului din baza de date
     * @param id id-ul clientului
     * @param p produsul dupa care se face actualizarea
     */
    public void updateProduct(int id, Product p) {
        update(p, id);
    }

    /**
     * metoda de stergere a unui produs din baza de date
     * @param id id-ul produsului supus stergerii
     */
    public void deleteProduct(int id) {
        delete(id);
    }
}
