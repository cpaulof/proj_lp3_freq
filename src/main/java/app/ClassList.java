package app;

import java.io.IOException;
import java.util.ArrayList;

//import javax.json.Json;
//import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bd.model.Turma;
import app.bd.model.User;
import app.bd.service.HorarioService;
import app.bd.service.UserService;

@WebServlet(value="/classlist", name="classList")
public class ClassList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        HorarioService horarioService = new HorarioService();
        String type = "bad request";
        String classes = null;
        String token = req.getParameter("token");
        if(token!=null){
            User user = userService.getUserByToken(token);
            if(user==null)
                type = "invalid token";
            
            else{
                //code
                type = "success";
                classes = "[";
                ArrayList<Turma> turmas = horarioService.getClassList(user);
                for(int i=0; i<turmas.size(); i++){
                    classes += turmas.get(i).toString();
                    if(i < turmas.size()-1)
                        classes+=",";
                }
                classes += "]";
            }
        }
        String result;
        if(classes!=null)
            result = String.format("{\"type\":\"%s\", \"classes\":%s}, %f", type, classes, Utils.distanceBetween(new Position("-2.61408,-44.25136"), new Position("-2.61546,-44.23684")));
        else
            result = String.format("{\"type\":\"%s\"}", type);
        

        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(result);
    }
}



