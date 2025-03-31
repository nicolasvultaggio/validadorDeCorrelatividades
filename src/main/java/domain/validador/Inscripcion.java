package domain.validador;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Inscripcion {
    private Set<Materia> materias;
    private final Alumno alumno;

    public Inscripcion(Alumno alumno) {
        this.alumno = alumno;
        this.materias = new HashSet<>();
    }

    public Set<Materia> getMaterias() {
        return materias;
    }

    public void inscribirseA(Materia ... materiasAInscribir){
        Collections.addAll(this.materias,materiasAInscribir);
    }


    public boolean aprobada(){
        return  this.materias.stream().allMatch(materia-> materia.puedeAnotarse(alumno));// para cada materia a la que se anoto el alumno, preguntar si tiene correlativas aprobadas
    };

}