package ru.esuvorov.controller;

import ru.esuvorov.annotation.Inject;
import ru.esuvorov.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "User Servlet",
        urlPatterns = "/getServlet",
        initParams = {
                @WebInitParam(name = "path", value = "ru.esuvorov.dao.UserDaoImpl"),
                @WebInitParam(name = "appCtxPath", value = "/applicationContext.xml"),
                @WebInitParam(name = "appCtxClass", value = "ru.esuvorov.inject.ApplicationContextImpl")
        }
)
public class UserServlet extends DependencyInjectionServlet {

    @Inject(value = "userDaoImpl")
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get request");
        System.out.println(userDao.getName());
        response.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Name : " + request.getParameter("name"));
        System.out.println("Gender : " + request.getParameter("gender"));
        System.out.println("Email : " + request.getParameter("email"));
        System.out.println("Phone : " + request.getParameter("phone"));
        System.out.println("City : " + request.getParameter("city"));

        request.getRequestDispatcher("details.jsp").forward(request, response);
    }
}