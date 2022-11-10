package ra.model.daoImp;

import ra.model.dao.StudentDAO;
import ra.model.entity.Student;
import ra.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO<Student, String> {
    @Override
    public List<Student> searchStudentByName(String name) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> listStudent = null;
        try {
            //1. Thuc hien ket noi den CSDL
            conn = ConnectionDB.openConnection();
            //2. Khoi tao doi tuon CallableStatement de goi procedure
            callSt = conn.prepareCall("{call proc_getAllStudent()}");
            //3. Thuc hien callSt va nhan ResultSet
            //--proc la cau select thi thuc hien phuong thuc executeQuery()
            //--proc la cau lenh thuc hien insert, update, delete ma chi co tham so vao - executeUpdate()
            //--proc la cau lenh thuc hien insert, update, delete ma co tham so ra - execute()
            ResultSet rs = callSt.executeQuery();
            listStudent = new ArrayList<>();
            //4. Duyet resultset day du lieu ra listStudent
            while (rs.next()) {
                Student st = new Student();
                st.setStudentId(rs.getString("StudentId"));
                st.setStudentName(rs.getString("StudentName"));
                st.setAge(rs.getInt("Age"));
                st.setBirthDate(rs.getDate("BirthDate"));
                st.setStudentStatus(rs.getBoolean("StudentStatus"));
                //add vao list
                listStudent.add(st);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudent;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_UpdateStudent(?,?,?,?,?)}");
            //THuc hien set gia tri cho cac tham so vao
            callSt.setString(1, student.getStudentId());
            callSt.setString(2, student.getStudentName());
            callSt.setInt(3, student.getAge());
            //convert java.util.Date --> java.sql.Date
            callSt.setDate(4,new Date(student.getBirthDate().getTime()));
            callSt.setBoolean(5,student.isStudentStatus());
            //Thuc hien goi procedure
            callSt.executeUpdate();
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertStudent(?,?,?,?,?,?)}");
            //THuc hien set gia tri cho cac tham so vao
            callSt.setString(1, student.getStudentId());
            callSt.setString(2, student.getStudentName());
            callSt.setInt(3, student.getAge());
            //convert java.util.Date --> java.sql.Date
            callSt.setDate(4,new Date(student.getBirthDate().getTime()));
            callSt.setBoolean(5,student.isStudentStatus());
            //Thuc hien dang ky kieu du lieu cua cac tham so ra
            callSt.registerOutParameter(6, Types.INTEGER);
            //Thuc hien goi procedure
            callSt.execute();
            //Lay gia tri tham so ra
            int cnt = callSt.getInt(6);

        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_DeleteStudent(?)}");
            //THuc hien set gia tri cho cac tham so vao
            callSt.setString(1, id);
            //Thuc hien goi procedure
            callSt.executeUpdate();
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public Student getById(String id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student studentInfo = null;
        try {
            //1. Thuc hien ket noi den CSDL
            conn = ConnectionDB.openConnection();
            //2. Khoi tao doi tuon CallableStatement de goi procedure
            callSt = conn.prepareCall("{call proc_GetStudentById(?)}");
            //set gia tri cho tham so vao
            callSt.setString(1,id);
            //3. Thuc hien callSt va nhan ResultSet
            ResultSet rs = callSt.executeQuery();
            studentInfo = new Student();
            //4. Duyet resultset day du lieu ra studentInfo
            if (rs.next()) {
                studentInfo.setStudentId(rs.getString("StudentId"));
                studentInfo.setStudentName(rs.getString("StudentName"));
                studentInfo.setAge(rs.getInt("Age"));
                studentInfo.setBirthDate(rs.getDate("BirthDate"));
                studentInfo.setStudentStatus(rs.getBoolean("StudentStatus"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return studentInfo;
    }
}
