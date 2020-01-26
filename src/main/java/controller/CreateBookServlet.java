package controller;

import bean.Author;
import bean.Book;
import bean.Bookimage;
import bean.Genre;
import repository.daoImpl.BookDaoImpl;
import repository.daoImpl.BookimageDaoImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static utill.ApplicationConstants.*;

@WebServlet(name = "CreateBookServlet", urlPatterns = "/create")
public class CreateBookServlet extends HttpServlet {
    BookDaoImpl bookDao = new BookDaoImpl();
    BookimageDaoImpl bookimageDao = new BookimageDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String bookname = request.getParameter(BOOKNAME_KEY);
        String authorname = request.getParameter(NAME_KEY);
        String authorsurame = request.getParameter(SURNAME_KEY);
        String genrename = request.getParameter(GENRENAME_KEY);
        String file = request.getParameter(FILE_KEY);
        System.out.println(file);
        HttpSession session = request.getSession();

        Author author = new Author(authorname, authorsurame);
        Genre genre = new Genre(genrename);
        Book book = new Book(bookname, author, genre, 5);
        book = bookDao.save(book);

        Bookimage bookimage = new Bookimage();
        bookimage.setBookId(book.getId());
        bookimage.setFilename(file);
        bookimageDao.save(bookimage);

        List<Book> books = bookDao.getAll();
        session.setAttribute(LISTBOOKS_KEY, books);
        getServletContext().getRequestDispatcher(ADMIN_JSP).forward(request, response);
        return;
    }


}
