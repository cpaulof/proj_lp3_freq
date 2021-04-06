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
import app.bd.service.TurmaService;
import app.bd.service.UserService;

@WebServlet(value="/presenca", name="presenca")
public class Presenca extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        TurmaService turmaService = new TurmaService();
        String type = "bad request";
        Double distance = null;

        String token = req.getParameter("token");
        String classId = req.getParameter("classid");
        String position = req.getParameter("position");
        try{
            if(token!=null||classId!=null||position!=null){
                User user = userService.getUserByToken(token);
                Turma turma = turmaService.getTurmaById(Integer.parseInt(classId));
                if(user==null || turma==null)
                    type = "invalid token";
                else{
                    //code
                    type = "success";
                    Position p1 = new Position("-2.536186, -44.278238");
                    Position p2 = new Position(position);
    
                    distance = Utils.distanceBetween(p1, p2);
    
                }
            }
        }catch(Exception e){
            type = "invalid request";
        }
        
        String result;
        if(distance!=null)
            result = String.format("{\"type\":\"%s\", \"distance\":%f}", type, distance);
        else
            result = String.format("{\"type\":\"%s\"}", type);
        

        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(result);
    }
}



