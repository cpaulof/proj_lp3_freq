package app.bd.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Chamada implements Base{

    @Id @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="horario_id", nullable = false)
    private Horario horario;

    private LocalDate data;


    

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Horario getHorario() {
        return this.horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}

