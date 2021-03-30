package app.bd.repository;
 
import java.security.SecureRandom;

import javax.persistence.EntityManager;

import app.bd.model.User;


public class UserRep {

    private final EntityManager manager;
    private DAOGenerico<User> daoGenerico;

    public UserRep(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<User>(manager);
    }

    public User buscaPor(Integer id) {
        return daoGenerico.buscaPorId(User.class, id);
    }

    public User buscaPorUsername(String username) {
        return this.manager.createQuery("from User where username=:username", User.class).setParameter("username", username).getSingleResult();
    }

    public User buscaPorToken(String token) {
        return this.manager.createQuery("from User where token=:token and logged=1", User.class).setParameter("token", token).getSingleResult();
    }

    public String generateToken(User user){
        String chars = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM1234567890_";
        char symbols[] = chars.toCharArray();
        SecureRandom secRand = new SecureRandom();
        char r[] = new char[32];
        for(int i=0; i<r.length; i++){
            r[i] = symbols[secRand.nextInt(symbols.length)];
        }
        String token = new String(r);
        //user.setToken(token);
        return token;
    }

    public User salvaOuAtualiza(User user) {
        return daoGenerico.salvaOuAtualiza(user);
    }

    public void remove(User user) {
        daoGenerico.remove(user );
    }
}
