package app.bd.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import app.bd.EMFactory;
import app.bd.model.Turma;
import app.bd.model.User;
import app.bd.repository.TurmaRep;

public class TurmaService {
    private TurmaRep turmaRep;
    private EntityManager manager;

    public TurmaService(){
        this.manager = new EMFactory().getEntityManager();
        this.turmaRep = new TurmaRep(manager);
    }

    public Turma getTurmaById(Integer id){
        return turmaRep.buscaPor(id);
    }
}
