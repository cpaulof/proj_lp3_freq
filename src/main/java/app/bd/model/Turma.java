package app.bd.model;

import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class Turma implements Base{

    @Id @GeneratedValue
    private Integer id;

    private String nome;
    

    private String dias;

    private LocalTime inicio;

    private LocalTime fim;

    private Double lat;
    private Double lon;

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return this.lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDias() {
        return this.dias;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public LocalTime getInicio() {
        return this.inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFim() {
        return this.fim;
    }

    public void setFim(LocalTime fim) {
        this.fim = fim;
    }
    
    @Override
    public String toString(){
        String format = "{\"id\": %d, \"nome\": \"%s\", \"inicio\": \"%s\", \"fim\": \"%s\", \"dias\": \"%s\"}";
        return String.format(format, id, nome, inicio, fim, dias);
    }
}

