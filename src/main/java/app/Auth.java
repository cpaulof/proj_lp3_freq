package app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bd.model.User;
import app.bd.service.UserService;

@WebServlet(value="/auth", name="authServlet")
public class Auth extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();

        String username = req.getParameter("user");
        String password = req.getParameter("password");

        User user = userService.authenticate(username, password);
        String r = "{\"authentication\": \"failed\"}";
        if(user!=null){
            r = String.format("{\"authentication: \"sucess\", \"token\":\"%s\", \"username\":\"%s\"}", user.getToken(), user.getUsername());

        }
       
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(r);
    }
}


