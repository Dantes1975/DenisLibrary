package controller;

import bean.Authenticate;
import bean.Role;
import bean.User;
import bean.Users_roles;
import repository.daoImpl.AuthenticateDaoImpl;
import repository.daoImpl.RoleDaoImpl;
import repository.daoImpl.UserDaoImpl;
import repository.daoImpl.Users_rolesDaoImpl;


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
        Users_rolesDaoImpl user_role = new Users_rolesDaoImpl();
        RoleDaoImpl roleDao = new RoleDaoImpl();

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
        Users_roles usrl = new Users_roles();
        User user = new User();
        //Role role;


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
 //           role = roleDao.getById(usrl.getRole_id());
//            session.setAttribute(AUTHENTICATE_KEY, auth);
//            session.setAttribute(USER_ROLE, user);
//            session.setAttribute(ROLE_KEY, role);

        }

        session.setAttribute(AUTHENT_KEY, authDao.getAll());
        if (action.toLowerCase().equals(LOGIN_KEY)) {
//            if (role.getRole().toLowerCase().equals(USER_ROLE)) {
//                getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
//            } else if (role.getRole().toLowerCase().equals(ADMIN_ROLE)) {
                getServletContext().getRequestDispatcher(START_JSP).forward(request, response);
         //   }
        } else if(action.toLowerCase().equals(ADD_KEY)){
            getServletContext().getRequestDispatcher(ADMIN_JSP).forward(request, response);
        }
    }

}

