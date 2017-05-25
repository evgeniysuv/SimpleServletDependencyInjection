package ru.esuvorov.controller;

import ru.esuvorov.annotation.Inject;
import ru.esuvorov.dao.UserDao;
import ru.esuvorov.model.User;
import ru.esuvorov.model.enums.Gender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "User Servlet",
        urlPatterns = "/getServlet",
        initParams = {
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
        response.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String genderString = request.getParameter("gender");
        Gender gender = genderString.equalsIgnoreCase(Gender.MALE.toString()) ? Gender.MALE : Gender.FEMALE;
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");

        User user = new User(name, gender, email, phone, city);
        userDao.createUser(user);

        request.getRequestDispatcher("details.jsp").forward(request, response);
    }
}