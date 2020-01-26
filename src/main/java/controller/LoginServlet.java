package controller;

import bean.Authenticate;
import bean.Role;
import bean.User;
import repository.daoImpl.AuthenticateDaoImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utill.ApplicationConstants.*;
import static utill.ErrorConstant.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/set"})
public class LoginServlet extends HttpServlet {
    AuthenticateDaoImpl authenticateDao = new AuthenticateDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {

        String login = request.getParameter(LOGIN_KEY);
        String password = request.getParameter(PASSWORD_KEY);
        String name = request.getParameter(NAME_KEY);
        String surname = request.getParameter(SURNAME_KEY);
        String email = request.getParameter(EMAIL_KEY);
        String rol = request.getParameter(ROLE_KEY);
        String action = request.getParameter(ACTION_KEY);
        int age = Integer.parseInt(request.getParameter(AGE_KEY));
        HttpSession session = request.getSession();

        try {

            if (login == null || login.isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            } else if (password == null || password.isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            } else if (name == null || name.isEmpty()) {
                throw new RuntimeException(INVALID_USER_NAME);
            } else if (surname == null || surname.isEmpty()) {
                throw new RuntimeException(INVALID_USER_SURNAME);
            } else if (email == null || email.isEmpty()) {
                throw new RuntimeException(INVALID_USER_EMAIL);
            } else if (age == 0) {
                throw new RuntimeException(INVALID_USER_AGE);
            } else {

                if (!authenticateDao.isExistByLogin(login)) {
                    Authenticate authenticate = new Authenticate(login, password, "ON");
                    User user = new User(name, surname, email, age);
                    if (rol.toLowerCase().equals(USER_ROLE)) {
                        user.setRole(Role.USER);
                    } else if (rol.toLowerCase().equals(ADMIN_ROLE)) {
                        user.setRole(Role.ADMIN);
                    }
                    authenticate.setUser(user);
                    user.setAuthenticate(authenticate);
                    authenticateDao.save(authenticate);
                } else {
                    throw new RuntimeException(INVALID_USER_REGISTRATION_DATA);
                }
            }
        } catch (RuntimeException e) {
            session.setAttribute(ERROR_KEY, e.getMessage());
            getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
            return;
        }
        session.setAttribute(AUTHENT_KEY, authenticateDao.getAll());
        if (action.toLowerCase().equals(LOGIN_KEY)) {
            getServletContext().getRequestDispatcher(START_JSP).forward(request, response);
            return;
        } else if (action.toLowerCase().equals(ADD_KEY)) {
            getServletContext().getRequestDispatcher(ADMIN_JSP).forward(request, response);
            return;
        }
    }

}

