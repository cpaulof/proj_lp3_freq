package app;

import app.bd.model.Horario;
import app.bd.model.Turma;

public class ClassListObj {
    public Turma turma;
    public Horario horario;

    public ClassListObj(Turma t, Horario h){
        turma = t;
        horario = h;
    }

    @Override
    public String toString(){
        String format = "{\"id\": %d, \"nome\": \"%s\", \"inicio\": \"%s\", \"fim\": \"%s\", \"dias\": \"%s\", \"horarioid\": %d}";
        return String.format(format, turma.getId(), turma.getNome(), turma.getInicio(), turma.getFim(), turma.getDias(), horario.getId());
    }
}
