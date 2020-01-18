package controller;

import bean.Author;
import bean.Book;
import bean.Bookimage;
import bean.Genre;
import repository.daoImpl.AuthorDaoImpl;
import repository.daoImpl.BookDaoImpl;
import repository.daoImpl.BookimageDaoImpl;
import repository.daoImpl.GenreDaoImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static utill.ApplicationConstants.*;

@WebServlet(name = "CreateBookServlet", urlPatterns = "/create")
public class CreateBookServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDaoImpl bookDao = new BookDaoImpl();
        BookimageDaoImpl bookimageDao = new BookimageDaoImpl();

        String bookname = request.getParameter(BOOKNAME_KEY);
        String authorname = request.getParameter(NAME_KEY);
        String authorsurame = request.getParameter(SURNAME_KEY);
        String genrename = request.getParameter(GENRENAME_KEY);
        String file = request.getParameter(FILE_KEY);
        System.out.println(file);
        HttpSession session = request.getSession();

        Author author = new Author();
        author.setName(authorname);
        author.setSurname(authorsurame);

        Genre genre = new Genre();
        genre.setGenrename(genrename);

        Book book = new Book();
        book.setBookname(bookname);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setStock(5);
        book = bookDao.insert(book);

        Bookimage bookimage = new Bookimage();
        bookimage.setBookId(book.getId());
        bookimage.setFilename(file);
        bookimageDao.insert(bookimage);

        List<Book> books = bookDao.getAll();
        session.setAttribute(LISTBOOKS_KEY, books);
        getServletContext().getRequestDispatcher(ADMIN_JSP).forward(request, response);
        return;
    }


}
