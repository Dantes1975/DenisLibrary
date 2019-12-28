package controller;

import bean.Authenticate;
import bean.User;
import repository.daoImpl.AuthenticateDaoImpl;
import repository.daoImpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utill.ApplicationConstants.*;

@WebServlet(name = "UpdateServlet", urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        AuthenticateDaoImpl authDao = new AuthenticateDaoImpl();

        String login = request.getParameter(LOGIN_KEY);
        String password = request.getParameter(PASSWORD_KEY);
        String name = request.getParameter(NAME_KEY);
        String surname = request.getParameter(SURNAME_KEY);
        String email = request.getParameter(EMAIL_KEY);
        String profile = request.getParameter(PROFILE_KEY);

        int age = Integer.parseInt(request.getParameter(AGE_KEY));
        long id = Long.parseLong(request.getParameter(ID_KEY));
        HttpSession session = request.getSession();
        Authenticate auth = new Authenticate(id, login, password, profile);
        User user = new User(id, name, surname, email, age);
        userDao.update(user);
        authDao.update(auth);
        session.setAttribute(AUTHENTICATE_KEY, auth);
        getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
    }

}
