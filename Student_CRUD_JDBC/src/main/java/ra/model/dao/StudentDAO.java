package ra.model.dao;

import java.util.List;

public interface StudentDAO<T,V> extends StudentManagementDAO<T,V> {
    List<T> searchStudentByName(String name);
}
