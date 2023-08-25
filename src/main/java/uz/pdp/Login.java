package uz.pdp;

import uz.pdp.model.Results;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User user=new User(email,password);
        DbServer server=new DbServer();
        Results results = server.saveData(user);
        if (results.isSuccess()) {
            resp.sendRedirect("cabinet.html");
        }
        else {
            resp.getWriter().write("<h1>"+results.getMassage()+"</h1>");
        }
    }
}
