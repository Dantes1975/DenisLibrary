package controller;

import bean.Book;
import bean.Borrow;
import repository.daoImpl.BookDaoImpl;
import repository.daoImpl.BorrowDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static utill.ApplicationConstants.*;

@WebServlet(name = "OperationWhitsBooksServlet", urlPatterns = {"/listbooks", "/takebooks", "/borrows", "/return"})
public class OperationWhitsBooksServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDaoImpl bookDao = new BookDaoImpl();
        BorrowDaoImpl borrowDao = new BorrowDaoImpl();

        long bookid = Long.parseLong(request.getParameter(BOOKID_KEY));
        long userid = Long.parseLong(request.getParameter(USERID_KEY));
        int days = Integer.parseInt(request.getParameter(DAYS_KEY));
        String action = request.getParameter(ACTION_KEY);
        HttpSession session = request.getSession();
        Borrow borrow = new Borrow();


        if (action.toLowerCase().equals(LIST_KEY)) {
            List<Book> books = bookDao.getAll();
            session.setAttribute(LISTBOOKS_KEY, books);
            getServletContext().getRequestDispatcher(LIST_JSP).forward(request, response);
        } else if (action.toLowerCase().equals(BOOKS_KEY)) {
            List<Book> books = bookDao.getAll();
            session.setAttribute(LISTBOOKS_KEY, books);
            getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
        } else if (action.toLowerCase().equals(TAKE_KEY)) {
            LocalDate borrowdate = LocalDate.now();
            LocalDate returndate = borrowdate.plusDays(days);
            Book book = bookDao.getById(bookid);
            borrow.setBook(book);
            borrow.setUser(userid);
            borrow.setBorrowDate(Date.valueOf(borrowdate));
            borrow.setReturnDate(Date.valueOf(returndate));
            borrowDao.insert(borrow);
            bookDao.takeBook(bookid);
            List<Borrow> borrows = borrowDao.getAll();
            List<Book> books = bookDao.getAll();
            session.setAttribute(LISTBOOKS_KEY, books);
            session.setAttribute(BORROWS_KEY, borrows);
            getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
        } else if ((action.toLowerCase().equals(BORROWS_KEY))) {
            List<Borrow> borrows = borrowDao.getBooksByUserId(userid);
            session.setAttribute(BORROWS_KEY, borrows);
            getServletContext().getRequestDispatcher(BORROWS_JSP).forward(request, response);
        } else if ((action.toLowerCase().equals(RETURN_KEY))) {
            borrowDao.delete(bookid);
            bookDao.returnBook(bookid);
            List<Borrow> borrows = borrowDao.getBooksByUserId(userid);
            List<Book> books = bookDao.getAll();
            session.setAttribute(LISTBOOKS_KEY, books);
            session.setAttribute(BORROWS_KEY, borrows);
            getServletContext().getRequestDispatcher(BOOKS_JSP).forward(request, response);
        }
    }


}
