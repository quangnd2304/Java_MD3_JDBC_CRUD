package ra.controller;

import ra.model.entity.Student;
import ra.model.service.StudentService;
import ra.model.serviceImp.StudentServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    private StudentService<Student,String> studentService = new StudentServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action!=null&&action.equals("Update")){
            //Thuc hien lay thong tin sinh vien theo studentId
            String studentId = request.getParameter("studentId");
            Student studentUpdate = studentService.getById(studentId);
            //Chuyen du lieu sang trang updateStudent.jsp
            request.setAttribute("studentUpdate",studentUpdate);
            request.getRequestDispatcher("views/updateStudent.jsp").forward(request,response);
        } else if (action!=null&&action.equals("Delete")) {
            //Thuc hien xoa sinh vien
            String studentId = request.getParameter("studentId");
            boolean result = studentService.delete(studentId);
            if (result){
                getAllStudent(request,response);
            }else {
                request.getRequestDispatcher("views/error.jsp").forward(request,response);
            }
        } else {
            getAllStudent(request, response);
        }
    }

    public void getAllStudent(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException{
        //Goi getAll cua StudentService
        List<Student> listStudent = studentService.getAll();
        //add vao request
        request.setAttribute("listStudent",listStudent);
        //chuyen du lieu sang students.jsp
        request.getRequestDispatcher("views/students.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        //Lay thong tin tu request
        Student st = new Student();
        st.setStudentId(request.getParameter("studentId"));
        st.setStudentName(request.getParameter("studentName"));
        st.setAge(Integer.parseInt(request.getParameter("age")));
        //convert String to java.util.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            st.setBirthDate(sdf.parse(request.getParameter("birthDate")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        st.setStudentStatus(Boolean.parseBoolean(request.getParameter("status")));
        if (action!=null&&action.equals("Create")){
            //Thuc hien them moi sinh vien
            boolean result = studentService.save(st);
            if (result){
                getAllStudent(request,response);
            }else {
                request.getRequestDispatcher("views/error.jsp").forward(request,response);
            }
        }else if (action!=null&&action.equals("Update")){
            //Thuc hien cap nhat sinh vien
            boolean result = studentService.update(st);
            if (result){
                getAllStudent(request,response);
            }else{
                request.getRequestDispatcher("views/error.jsp").forward(request,response);
            }
        }
    }
}
