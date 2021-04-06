package app.bd.repository;
 
import javax.persistence.EntityManager;

import app.bd.model.Turma;


public class TurmaRep {

    private final EntityManager manager;
    private DAOGenerico<Turma> daoGenerico;

    public TurmaRep(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<Turma>(manager);
    }

    public Turma buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Turma.class, id);
    }

    public Turma buscaPorNome(String nome) {
        return this.manager.createQuery("from Turma where nome=:nome", Turma.class).setParameter("nome", nome).getSingleResult();
    }

    public Turma salvaOuAtualiza(Turma turma) {
        return daoGenerico.salvaOuAtualiza(turma);
    }

    public void remove(Turma turma) {
        daoGenerico.remove(turma);
    }
}

