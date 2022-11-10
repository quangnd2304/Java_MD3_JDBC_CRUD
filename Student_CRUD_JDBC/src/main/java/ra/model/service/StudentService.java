package ra.model.service;

import java.util.List;

public interface StudentService<T,V> extends StudentManagementService<T,V> {
    List<T> searchStudentByName(String name);
}
