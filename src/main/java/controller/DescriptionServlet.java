package controller;

import bean.Bookimage;
import repository.daoImpl.BookimageDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utill.ApplicationConstants.*;

@WebServlet(name = "DescriptionServlet", urlPatterns = "/description")
public class DescriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookimageDaoImpl bookimageDao = new BookimageDaoImpl();
        long bookid = Long.parseLong(request.getParameter(BOOKID_KEY));

        if (bookid<=3) {
            getServletContext().getRequestDispatcher(DESCRIPTION_JSP).forward(request, response);
            return;
        } else {
            Bookimage bookimage = bookimageDao.getById(bookid);
            response.getOutputStream().write(bookimage.getBookimage());
        }

    }


}
