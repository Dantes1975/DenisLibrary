package controller;


import bean.Message;
import repository.daoImpl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static utill.ApplicationConstants.*;


@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete", "/on", "/off"})
public class OperationWhithUserServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        AuthenticateDaoImpl authDao = new AuthenticateDaoImpl();
        UsersRolesDaoImpl user_role = new UsersRolesDaoImpl();
        BorrowDaoImpl borrowDao = new BorrowDaoImpl();
        BookDaoImpl bookDao = new BookDaoImpl();
        MessageDaoImpl messageDao = new MessageDaoImpl();
        String action = request.getParameter(ACTION_KEY);
        String type = request.getParameter(TYPE_KEY);
        long id = Long.parseLong(request.getParameter(ID_KEY));
        long sender = Long.parseLong(request.getParameter(SENDER_KEY));
        HttpSession session = request.getSession();

        if (action.toLowerCase().equals(DELETE_KEY)) {
            userDao.delete(id);
            authDao.delete(id);
            user_role.delete(id);
        } else if (action.toLowerCase().equals(ON_KEY)) {
            authDao.authOn(id);
            session.setAttribute(AUTHENT_KEY, authDao.getAll());
        } else if (action.toLowerCase().equals(OFF_KEY)) {
            if (type.toLowerCase().equals(BLOCK_KEY)) {
                authDao.authBlock(id);
                session.setAttribute(AUTHENT_KEY, authDao.getAll());
            } else if (type.toLowerCase().equals(OFF_KEY)) {
                Message message = new Message();
                message.setSender(sender);
                message.setRecipient(id);
                message.setText("Block for borrow");
                messageDao.insert(message);
                List<Long> booksId = borrowDao.getBooksIdByUserId(id);
                for (Long bookId : booksId) {
                    bookDao.returnBook(bookId);
                }
                borrowDao.deleteByUserId(id);
                authDao.authOff(id);
                session.setAttribute(AUTHENT_KEY, authDao.getAll());
                session.setAttribute(MESSAGE_KEY, message);
            }
        }
        session.setAttribute(USERS_KEY, userDao.getAll());
        session.setAttribute(AUTHENT_KEY, authDao.getAll());
        getServletContext().getRequestDispatcher(ADMIN_JSP).forward(request, response);
        return;
    }
}
