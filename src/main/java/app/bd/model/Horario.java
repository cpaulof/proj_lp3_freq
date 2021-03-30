package app.bd.model;

import javax.persistence.*;

@Entity
public class Horario implements Base{

    @Id @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="turma_id", nullable = false)
    private Turma turma;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Turma getTurma() {
        return this.turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}

