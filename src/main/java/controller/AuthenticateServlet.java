package controller;

import bean.*;
import repository.daoImpl.*;

import static utill.ApplicationConstants.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AuthenticateServlet", urlPatterns = {"/auth", "/guest"})

public class AuthenticateServlet extends HttpServlet {
    BookDaoImpl bookDao = new BookDaoImpl();
    AuthenticateDaoImpl authenticateDao = new AuthenticateDaoImpl();
    BorrowDaoImpl borrowDao = new BorrowDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Authenticate> authenticateList = authenticateDao.getAll();
        String login = request.getParameter(LOGIN_KEY);
        String password = request.getParameter(PASSWORD_KEY);
        String action = request.getParameter(ACTION_KEY);
        HttpSession session = request.getSession();


        if (action.toLowerCase().equals(LOGIN_KEY)) {
            if (login == null || login.isEmpty()) {
                throw new RuntimeException("Invalid User Login");
            } else if (password == null || password.isEmpty()) {
                throw new RuntimeException("Invalid User Password");
            } else {
                Authenticate authenticate = authenticateDao.getByLogin(login);

                if (authenticate != null) {
                    if (login.equals(authenticate.getLogin()) && password.equals(authenticate.getPassword())) {
                        User user = authenticate.getUser();
                        Role role = user.getRole();

                        session.setAttribute(AUTHENTICATE_KEY, authenticate);
                        session.setAttribute("user", user);
                        session.setAttribute(ROLE_KEY, role);
                        session.setAttribute(AUTHENT_KEY, authenticateList);

                        if (role.name().toLowerCase().equals(USER_ROLE)) {
                            List<Borrow> borrows = borrowDao.getAll();
                            List<Book> books = bookDao.getAll();
                            session.setAttribute(LISTBOOKS_KEY, books);
                            session.setAttribute(BORROWS_KEY, borrows);
                            getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
                            return;
                        } else if (role.name().toLowerCase().equals(ADMIN_ROLE)) {
                            getServletContext().getRequestDispatcher(ADMIN_JSP).forward(request, response);
                            return;
                        }

                    } else {
                        getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
                        return;
                    }
                } else {
                    getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
                    return;
                }

            }
        }
        if (action.toLowerCase().equals(GUEST_ROLE)) {
            List<Book> books = bookDao.getAll();
            session.setAttribute(LISTBOOKS_KEY, books);
            request.getRequestDispatcher(LIST_JSP).forward(request, response);
            return;
        }
    }
}


