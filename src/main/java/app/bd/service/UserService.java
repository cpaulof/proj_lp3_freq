package app.bd.service;

import javax.persistence.EntityManager;

import app.bd.EMFactory;
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
}
