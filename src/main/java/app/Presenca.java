package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;
//import javax.json.Json;
//import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bd.model.Horario;
import app.bd.model.Turma;
import app.bd.model.User;
import app.bd.service.ChamadaService;
import app.bd.service.HorarioService;
import app.bd.service.TurmaService;
import app.bd.service.UserService;

@WebServlet(value="/presenca", name="presenca")
public class Presenca extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        TurmaService turmaService = new TurmaService();
        HorarioService horarioService = new HorarioService();
        ChamadaService chamadaService = new ChamadaService();
        String type = "bad request";
        int typeCode = -333;
        String token = req.getParameter("token");
        String classId = req.getParameter("classid");
        String horarioid = req.getParameter("horarioid");
        String position = req.getParameter("position");
        try{
            if(token!=null||classId!=null||position!=null||horarioid!=null){
                User user = userService.getUserByToken(token);
                Turma turma = turmaService.getTurmaById(Integer.parseInt(classId));
                if(user==null || turma==null)
                    type = "invalid token";
                else if(!horarioService.hasUser(user, turma))
                    type = "Nao inscrito na disciplina";
                else{
                    Horario horario = horarioService.getHorario(user, turma);
                    int success = userService.solicitaPresenca(chamadaService, horario, user, turma, position);
                    switch(success){
                        case 0:
                        type = "Dia Invalido para o horario";
                        typeCode = 0;
                        break;

                        case 1:
                        type = "success";
                        typeCode = 1;
                        break;

                        case 2:
                        type = "Fora do horario";
                        typeCode = 2;
                        break;

                        default:
                        typeCode = -1;
                        type = "Fora do raio de presenca!";
                    }
                }
            }
        }catch(Exception e){
            type = "invalid request";
            System.out.println(e.getMessage());
        }
        
        String result;
        result = String.format("{\"type\":\"%s\", \"code\":\"%d\"}", type, typeCode);
        

        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(result);
    }
}



