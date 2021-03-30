package app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bd.model.User;
import app.bd.service.UserService;

@WebServlet(value="/logout", name="logoutServlet")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();

        String token = req.getParameter("token");
        System.out.println("Token: "+token);

        User user = userService.getUserByToken(token);

        String r = "{\"obs\": \"token not found or user is already logged out\"}";
        if(user!=null){
            r = "{\"obs\": \"\"}";
            userService.logout(user);
        }
       
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(r);
    }
}


