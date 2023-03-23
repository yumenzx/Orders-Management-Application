package dataAccess.dao;

import dataAccess.ConnectionFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa care contine toate operatiile necesare de manipulare a bazei de date.
 * Clasele DAO doar o vor mosteni si se pot folosi de metodele acestia pentru accesul la datele bazei de date
 * @param <T> tipul clasei
 */
public abstract class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * meotda de creare a interogarii de inserare a unui noi tip de date
     * @param o tipul de date primit
     * @return stringul care contine interogarea
     */
    private String createInsertQuery(Object o) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(o);
                columns.append(field.getName()).append(",");
                values.append("'").append(value).append("',");
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        columns.deleteCharAt(columns.length() - 1);
        values.deleteCharAt(values.length() - 1);
        String className = o.getClass().getName().substring(6);
        return "INSERT INTO " + className + "(" + columns + ") VALUES(" + values + ")";
    }

    /**
     * meotda de creare a interogarii de actualizare a unui noi tip de date existente
     * @param o tipul de date dupa care se face actualizarea
     * @param id id-ul tipului de date supus actualizarii
     * @return stringul care contine interogarea de update
     */
    private String createUpdateQuery(Object o, int id) {
        StringBuilder data = new StringBuilder();

        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(o);
                data.append(field.getName()).append("='").append(value).append("',");
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        data.deleteCharAt(data.length() - 1);
        String className = o.getClass().getName().substring(6);
        return "UPDATE " + className + " SET " + data + " WHERE id=" + id;
    }

    /**
     * metoda de returnare a continutului tabelei sub forma de matrice
     * @return matricea care pe fiecare linie contine cate o tupla
     */
    public Object[][] getTableRecords() {
        List<T> records = findAll();
        Object[][] table = new Object[records.size() + 1][];

        List<Object> columns = new ArrayList<>(4);
        for (Field field : type.getDeclaredFields())
            columns.add(field.getName());
        table[0] = columns.toArray();

        int i = 1;
        for (T t : records) {
            List<Object> row = new ArrayList<>(4);
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    row.add(field.get(t));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                table[i] = row.toArray();
            }
            i++;
        }

        return table;
    }

    /**
     * metoda de cautare a unei inregistrari din baza de date dupa id-ul acestuia
     * @param id id-ul tipului supus gasirii
     * @return tipul cu atributele sale in urma cautarii
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName() + " WHERE id=" + id;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<T> set = createObjects(resultSet);

            if (set.size() > 0)
                return set.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * metoda de returnare tuturor tuplelor din tabela sub forma de lista
     * @return lista cu tuplele din tabela
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * metoda de creare a listei de rezultate in urma unei interogari
     * @param resultSet rezultatul interogarii
     * @return lista cu rezultatele interogarii
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * metoda de inserare a unui nou tip in baza de date
     * @param t
     */
    public void insert(T t) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * metoda de actualizare a unei inregistrari existente in baza de date
     * @param t tipul dupa care se va face actualizarea
     * @param id id-ul tipului pentru care se face actulaizarea
     */
    public void update(T t, int id) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = createUpdateQuery(t, id);
        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * metoda de stergere a unei inregistrari din baza de date
     * @param id id-ul tipului pentru care se doreste stergerea
     */
    public void delete(int id) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = "DELETE FROM " + type.getSimpleName() + " WHERE id=" + id;
        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e){
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
