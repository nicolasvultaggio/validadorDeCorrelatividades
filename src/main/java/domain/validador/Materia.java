package domain.validador;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Materia {

    private String nombre;
    private Set<Materia> correlativas;
    private TipoDeMateria tipo;
    public Materia(String nombre,TipoDeMateria tipo) {
        this.nombre = nombre;
        this.correlativas = new HashSet<>();
        this.tipo=tipo;
    }

    public void agregarCorrelativas(Materia ... materias){
        if(tipo.equals(TipoDeMateria.CONCORRELATIVAS)){
            Collections.addAll(this.correlativas,materias);
        }
    }
    public String getNombre() {
        return nombre;
    }

    public boolean puedeAnotarse(Alumno alumno){
        if(tipo.equals(TipoDeMateria.CONCORRELATIVAS)){
            return this.correlativas.stream().allMatch(alumno::aprobo);
        }
        return true;
    }

}


