package controller;

import bean.Message;
import repository.daoImpl.MessageDaoImpl;
import repository.daoImpl.RoleDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static utill.ApplicationConstants.*;

@WebServlet(name = "MessageServlet", urlPatterns = {"/message", "/deleteServlet"})
public class MessageServlet extends HttpServlet {
    MessageDaoImpl messageDao = new MessageDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long sender = Long.parseLong(request.getParameter(SENDER_KEY));
        long recipient = Long.parseLong(request.getParameter(RECIPIENT_KEY));
        long id = Long.parseLong(request.getParameter(ID_KEY));
        String text = request.getParameter(TEXT_KEY);
        String action = request.getParameter(ACTION_KEY);
        HttpSession session = request.getSession();

        if (action.toLowerCase().equals(SEND_KEY)) {
            Message message = new Message();
            message.setSender(sender);
            message.setRecipient(recipient);
            message.setText(text);
            messageDao.insert(message);
            List<Message> messages = messageDao.getAll();
            session.setAttribute(MESSAGE_KEY, message);
            session.setAttribute(MESSAGES_KEY, messages);
            getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
            return;
        } else if (action.toLowerCase().equals(MESSAGES_KEY)) {
            List<Message> mymessages = messageDao.getMyMessages(recipient);
            session.setAttribute(MYMESSAGES_KEY, mymessages);
            getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
            return;
        } else if (action.toLowerCase().equals(DELETE_KEY)) {
            messageDao.delete(id);
            List<Message> mymessages = messageDao.getMyMessages(recipient);
            session.setAttribute(MYMESSAGES_KEY, mymessages);
            getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
            return;
        }


    }
}
