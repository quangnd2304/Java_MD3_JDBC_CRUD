package ra.model.dao;

import java.util.List;

public interface StudentManagementDAO<T,V> {
    List<T> getAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(V id);
    T getById(V id);
}
