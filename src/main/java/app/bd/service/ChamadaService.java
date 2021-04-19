package app.bd.service;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import app.ClassListObj;
import app.bd.EMFactory;
import app.bd.model.Chamada;
import app.bd.model.Horario;
import app.bd.repository.ChamadaRep;

public class ChamadaService {
    private ChamadaRep chamadaRep;
    private EntityManager manager;

    public ChamadaService(){
        this.manager = new EMFactory().getEntityManager();
        this.chamadaRep = new ChamadaRep(manager);
    }

    public void adicionaChamada(Horario horario){
        Chamada chamada  = new Chamada();
        chamada.setData(LocalDate.now());
        chamada.setHorario(horario);

        if(!chamadaRep.exists(horario)){
            manager.getTransaction().begin();
            chamadaRep.salvaOuAtualiza(chamada);
            manager.getTransaction().commit();
        }

        
    }
}
