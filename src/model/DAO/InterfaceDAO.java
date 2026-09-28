package model.DAO;

import java.util.List;

public interface InterfaceDAO<T> {
    void create(T objeto);
    T retrieve(int id);
    List<T> retrieve(String parametro, String valor);
    void update(T objeto);
    void delete(T objeto);
}
