package domain.validador;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
//primero si o si se crean las materias, luego alumnos, luego inscripciones

public class Alumno {
    private Integer id;
    private String nombre;
    private String apellido;
    private Set<Materia> materiasAprobadas;
    private Inscripcion inscripcionActual;

    public Alumno(Integer id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        materiasAprobadas=new HashSet<>();
        inscripcionActual=null;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Set<Materia> getMateriasAprobadas(){
        return materiasAprobadas;
    }

    public void aprobar(Materia materia){
        materiasAprobadas.add(materia);
    }

    public boolean aprobo(Materia materia){
        return materiasAprobadas.contains(materia);
    }

    public boolean aproboMaterias(Materia ... materias){
        return Arrays.stream(materias).allMatch(this::aprobo);//lo mismo que materia->this.aprobo(materia)
    }

    public void realizarInscripcion(Materia ... materias){//se usa en las pruebas
        inscripcionActual = new Inscripcion(this);
        inscripcionActual.inscribirseA(materias);//generar una inscripcion con esas materias
    }

    public void cursar(){
        if(inscripcionActual.aprobada()) {
            inscripcionActual.getMaterias().forEach(this::aprobar);
        }
    }

    public boolean tieneInscripcionAprobada(){
        return inscripcionActual.aprobada();
    }
    public void mostrarMateriasAprobadas(){
        materiasAprobadas.forEach(materia -> System.out.println(materia.getNombre()));
    }


}
