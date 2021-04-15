package app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bd.model.User;
import app.bd.service.UserService;

@WebServlet(value="/", name="doc")
public class Doc extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String r = "<html><body><p><h1>Autenticação</h1><h2>/auth?user=USERNAME&password=PASSWORD</h2></p><p><h1>Listar Disciplinas</h1><h2>/classlist?token=TOKEN</h2></p><p><h1>Solicitar presenca</h1><h2>/presenca?token=TOKEN&classid=ID&position=POSITION</h2></p></body></html>";
       
        resp.setStatus(200);
        resp.setHeader("Content-Type", "text/html");
        resp.getWriter().print(r);
    }
}


