package app.bd.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import app.ClassListObj;
import app.bd.EMFactory;
import app.bd.model.Horario;
import app.bd.model.Turma;
import app.bd.model.User;
import app.bd.repository.HorarioRep;

public class HorarioService {
    private HorarioRep horarioRep;
    private EntityManager manager;

    public HorarioService(){
        this.manager = new EMFactory().getEntityManager();
        this.horarioRep = new HorarioRep(manager);
    }

    public ArrayList<ClassListObj> getClassList(User user){
        ArrayList<ClassListObj> r = new ArrayList<ClassListObj>();
        for (Horario horario : horarioRep.listaHorario(user)) {
            r.add(new ClassListObj(horario.getTurma(), horario));
        }
        return r;
    }

    public Horario get(Integer id){
        return horarioRep.buscaPor(id);
    }

    public Boolean hasUser(User user, Turma turma){
        Horario horario = manager.createQuery("from Horario where user_id=:id and turma_id=:turma", Horario.class).setParameter("id", user.getId()
            ).setParameter("turma", turma.getId()).getSingleResult();
        return horario!=null;
    }

    
    public Horario getHorario(User user, Turma turma){
        Horario horario = manager.createQuery("from Horario where user_id=:id and turma_id=:turma", Horario.class).setParameter("id", user.getId()
            ).setParameter("turma", turma.getId()).getSingleResult();
        return horario;
    }
}
