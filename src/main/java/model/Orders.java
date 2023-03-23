package model;

/**
 * Clasa Orders reprezinta o comanda formata dintr-un id unic, id-ul clientului, id-ul produsului si bucata
 */
public class Orders {
    private int id;
    private int idClient;
    private int idProduct;
    private int amount;

    public Orders() {
    }

    public Orders(int id, int idClient, int idProduct, int amount) {
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
