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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AuthenticateServlet", urlPatterns = {"/auth", "/guest"})

public class AuthenticateServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDaoImpl bookDao = new BookDaoImpl();
        AuthenticateDaoImpl authenticateDao = new AuthenticateDaoImpl();
        UsersRolesDaoImpl users_rolesDao = new UsersRolesDaoImpl();
        RoleDaoImpl roleDao = new RoleDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        Authenticate aut;
        Role role;
        User user;
        UsersRoles users_roles;
        List<Authenticate> authenticateList = authenticateDao.getAll();
        String login = request.getParameter(LOGIN_KEY);
        String password = request.getParameter(PASSWORD_KEY);
        String action = request.getParameter(ACTION_KEY);
        HttpSession session = request.getSession();


        if (action.toLowerCase().equals(LOGIN_KEY)) {
            if (login == null || login.isEmpty()) {
                response.setContentType("text/html");
                PrintWriter writer = response.getWriter();
                try {
                    writer.println("<h2> Invalid User Login</h2>");
                } finally {
                    writer.close();
                }
            } else if (password == null || password.isEmpty()) {
                response.setContentType("text/html");
                PrintWriter writer = response.getWriter();
                try {
                    writer.println("<h2>Invalid User Password</h2>");
                } finally {
                    writer.close();
                }
            } else {

                aut = authenticateDao.getByLogin(login);

                if (aut != null) {
                    if (login.equals(aut.getLogin()) && password.equals(aut.getPassword())) {
                        user = userDao.getById(aut.getId());
                        users_roles = users_rolesDao.getByUserID(user.getId());
                        role = roleDao.getById(users_roles.getRole_id());

                        session.setAttribute(AUTHENTICATE_KEY, aut);
                        session.setAttribute("user", user);
                        session.setAttribute(ROLE_KEY, role);
                        session.setAttribute(AUTHENT_KEY, authenticateList);

                        if (role.getRole().toLowerCase().equals(USER_ROLE)) {
                            getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
                            //return;
                        } else if (role.getRole().toLowerCase().equals(ADMIN_ROLE)) {
                            getServletContext().getRequestDispatcher(ADMIN_JSP).forward(request, response);
                           // return;
                        }

                    } else {
                        getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
                        //return;
                    }
                } else {
                    getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
                   // return;
                }

            }
        }
        if (action.toLowerCase().equals(GUEST_ROLE)) {
            List<Book> books = bookDao.getAll();
            session.setAttribute(LISTBOOKS_KEY, books);
            request.getRequestDispatcher(LIST_JSP).forward(request, response);
          // return;
        }
    }
}


