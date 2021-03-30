package app.bd.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Turma implements Base{

    @Id @GeneratedValue
    private Integer id;

    private LocalDateTime inicio;

    private LocalDateTime fim;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return this.inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return this.fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

}

