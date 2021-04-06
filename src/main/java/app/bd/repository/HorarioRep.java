package app.bd.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import app.bd.model.Horario;
import app.bd.model.Turma;
import app.bd.model.User;


public class HorarioRep {
    
    private final EntityManager manager;
    private DAOGenerico<Horario> daoGenerico;

    public HorarioRep(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<Horario>(manager);
    }

    public Horario buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Horario.class, id);
    }


    public Horario salvaOuAtualiza(Horario horario) {
        return daoGenerico.salvaOuAtualiza(horario);
    }

    public void remove(Horario horario) {
        daoGenerico.remove(horario);
    }

    public List<Horario> listaHorario(User user){
        return manager.createQuery("from Horario where user_id=:id", Horario.class).setParameter("id", user.getId()).getResultList();
    }

    public List<Horario> listaHorario(Turma turma){
        return manager.createQuery("from Horario where turma_id=:id", Horario.class).setParameter("id", turma.getId()).getResultList();
    }
}
