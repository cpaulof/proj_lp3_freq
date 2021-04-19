package app.bd.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import app.bd.model.Chamada;
import app.bd.model.Horario;
import app.bd.model.Turma;
import app.bd.model.User;


public class ChamadaRep {
    
    private final EntityManager manager;
    private DAOGenerico<Chamada> daoGenerico;

    public ChamadaRep(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<Chamada>(manager);
    }

    public Chamada buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Chamada.class, id);
    }


    public Chamada salvaOuAtualiza(Chamada chamada) {
        return daoGenerico.salvaOuAtualiza(chamada);
    }

    public void remove(Chamada chamada) {
        daoGenerico.remove(chamada);
    }

    public boolean exists(Horario horario){
        Chamada h = null;
        try{
            LocalDate data = LocalDate.now();
            h = manager.createQuery("from Chamada where data=:data and horario_id=:id", Chamada.class).setParameter("data", data
            ).setParameter("id", horario.getId()).getSingleResult();
        }catch(Exception e){
            System.out.println("erro exists: "+e.getMessage());
            return false;
        }
        return h!=null;
    }
}
