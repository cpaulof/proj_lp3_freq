package app.bd.service;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.EntityManager;

import app.Position;
import app.Utils;
import app.bd.EMFactory;
import app.bd.model.Horario;
import app.bd.model.Turma;
import app.bd.model.User;
import app.bd.repository.UserRep;

public class UserService {
    private UserRep userRep;
    private EntityManager manager;

    public UserService(){
        this.manager = new EMFactory().getEntityManager();
        this.userRep = new UserRep(manager);
    }
    
    public void createUser(String username, String password){
        try{
            this.manager.getTransaction().begin();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setLogged(false);
            this.userRep.salvaOuAtualiza(user);
            this.manager.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void save(User user){
        this.manager.getTransaction().begin();
        this.userRep.salvaOuAtualiza(user);
        this.manager.getTransaction().commit();
    }

    public User authenticate(String username, String password){
        try{
            User user = userRep.buscaPorUsername(username);
            if(user.getPassword().equals(password)){
                String token = userRep.generateToken(user);
                user.setToken(token);
                user.setLogged(true);
                save(user);
                return user;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            
        }
        return null;
    }

    public void logout(User user){
        user.setLogged(false);
        save(user);
    }

    public User getUserByToken(String token){
        try{
            return userRep.buscaPorToken(token);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;   
    }

    public int solicitaPresenca(ChamadaService chamadaService, Horario horario, User user, Turma turma, String position){
        LocalDate now = LocalDate.now();
        Boolean validDay = false;
        char today = Character.forDigit(now.getDayOfWeek().getValue() + 1, 10);
        String days = turma.getDias();
        for(int i=0; i<days.length(); i++){
            if(days.charAt(i) == today)
                validDay = true;
        }

        if(!validDay) 
            return 0;

        int a = turma.getInicio().compareTo(LocalTime.now());
        int b = turma.getFim().compareTo(LocalTime.now());
        if(a<0 || b>0)
            return 2;
        
        Position turmaPos = new Position(turma.getLat(), turma.getLon());
        Position alunoPos = new Position(position);
        Double distance = Utils.distanceBetween(turmaPos, alunoPos);

        if(distance < 150.0){
            chamadaService.adicionaChamada(horario);
            return 1;
        }
            
        return -1;
    }
}
