package controller;

import bean.Authenticate;
import bean.User;
import bean.UsersRoles;
import repository.daoImpl.AuthenticateDaoImpl;
import repository.daoImpl.RoleDaoImpl;
import repository.daoImpl.UserDaoImpl;
import repository.daoImpl.UsersRolesDaoImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static utill.ApplicationConstants.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/set"})
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        UserDaoImpl userDao = new UserDaoImpl();
        AuthenticateDaoImpl authDao = new AuthenticateDaoImpl();
        UsersRolesDaoImpl user_role = new UsersRolesDaoImpl();

        String login = request.getParameter(LOGIN_KEY);
        String password = request.getParameter(PASSWORD_KEY);
        String name = request.getParameter(NAME_KEY);
        String surname = request.getParameter(SURNAME_KEY);
        String email = request.getParameter(EMAIL_KEY);
        String rol = request.getParameter(ROLE_KEY);
        String action = request.getParameter(ACTION_KEY);
        int age = Integer.parseInt(request.getParameter(AGE_KEY));
        HttpSession session = request.getSession();
        Authenticate auth = new Authenticate();
        UsersRoles usrl = new UsersRoles();
        User user = new User();

        if (login == null || login.isEmpty()) {
            throw new RuntimeException("Invalid User Login");
        } else if (password == null || password.isEmpty()) {
            throw new RuntimeException("Invalid User Password");
        } else if (name == null || name.isEmpty()) {
            throw new RuntimeException("Invalid User Name");
        } else if (surname == null || surname.isEmpty()) {
            throw new RuntimeException("Invalid User Surname");
        } else if (email == null || email.isEmpty()) {
            throw new RuntimeException("Invalid User Email");
        } else if (age == 0) {
            throw new RuntimeException("Invalid User Age");
        } else {

            if (!authDao.isExistByLogin(login)) {
                auth.setLogin(login);
                auth.setPassword(password);
                auth.setProfile_enable("ON");
                authDao.insert(auth);
            } else {
                response.setContentType("text/html");
                PrintWriter writer = response.getWriter();
                try {
                    writer.println("<h2>This User Login is exist</h2>");
                } finally {
                    writer.close();
                }
            }

            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setAge(age);
            userDao.insert(user);
            usrl.setUser_id(user.getId());


            if (rol.toLowerCase().equals(USER_ROLE)) {
                usrl.setRole_id(2);
            } else if (rol.toLowerCase().equals(ADMIN_ROLE)) {
                usrl.setRole_id(1);
            }

            user_role.insert(usrl);
        }

        session.setAttribute(AUTHENT_KEY, authDao.getAll());
        if (action.toLowerCase().equals(LOGIN_KEY)) {
            getServletContext().getRequestDispatcher(START_JSP).forward(request, response);
            //return;
        } else if (action.toLowerCase().equals(ADD_KEY)) {
            getServletContext().getRequestDispatcher(ADMIN_JSP).forward(request, response);
            //return;
        }
    }

}

