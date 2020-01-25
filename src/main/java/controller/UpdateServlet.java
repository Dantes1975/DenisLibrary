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

    UserDaoImpl userDao = new UserDaoImpl();
    AuthenticateDaoImpl authenticateDao = new AuthenticateDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String login = request.getParameter(LOGIN_KEY);
        String password = request.getParameter(PASSWORD_KEY);
        String name = request.getParameter(NAME_KEY);
        String surname = request.getParameter(SURNAME_KEY);
        String email = request.getParameter(EMAIL_KEY);
        String profile = request.getParameter(PROFILE_KEY);

        int age = Integer.parseInt(request.getParameter(AGE_KEY));
        long id = Long.parseLong(request.getParameter(ID_KEY));
        HttpSession session = request.getSession();
        Authenticate authenticate = new Authenticate(id, login, password, profile);
        User user = new User(id, name, surname, email, age);
        authenticate.setUser(user);
        user.setAuthenticate(authenticate);
        //userDao.update(user);
        authenticateDao.update(authenticate);
        session.setAttribute(AUTHENTICATE_KEY, authenticate);
        session.setAttribute(USER_ROLE, user);

        getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
    }

}
