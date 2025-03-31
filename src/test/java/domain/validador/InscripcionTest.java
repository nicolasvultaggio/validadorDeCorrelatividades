package domain.validador;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class InscripcionTest {

    private Materia algebra;
    private Materia algoritmos;
    private Materia matematicaDiscreta;
    private Materia sistemasYOrganizaciones;
    private Materia arquitecturaDeComputadoras;
    private Materia sintaxis;
    private Materia paradigmas;
    private Materia analisisDeSistemas;
    private Materia sistemasOperativos;
    private Materia disenio;
    private Materia gestion;
    private Alumno mesi;

    @BeforeEach
    void setUp() {
        algebra = new Materia("Álgebra",TipoDeMateria.COMUN);
        algoritmos = new Materia("Algoritmos y Estructuras de Datos",TipoDeMateria.COMUN);
        matematicaDiscreta = new Materia("Matemática Discreta",TipoDeMateria.COMUN);
        sistemasYOrganizaciones = new Materia("Sistemas y Organizaciones",TipoDeMateria.COMUN);
        arquitecturaDeComputadoras = new Materia("Arquitectura de Computadoras",TipoDeMateria.COMUN);
        sintaxis = new Materia("Sintaxis y Semántica de Lenguajes",TipoDeMateria.CONCORRELATIVAS);
        sintaxis.agregarCorrelativas(algoritmos,matematicaDiscreta);
        paradigmas = new Materia("Paradigmas de Programación",TipoDeMateria.CONCORRELATIVAS);
        paradigmas.agregarCorrelativas(matematicaDiscreta,algoritmos);
        analisisDeSistemas = new Materia("Análisis de Sistemas",TipoDeMateria.CONCORRELATIVAS);
        analisisDeSistemas.agregarCorrelativas(sistemasYOrganizaciones);
        sistemasOperativos = new Materia("Sistemas Operativos",TipoDeMateria.CONCORRELATIVAS);
        sistemasOperativos.agregarCorrelativas(arquitecturaDeComputadoras,algoritmos,matematicaDiscreta);
        disenio = new Materia("Diseño de Sistemas",TipoDeMateria.CONCORRELATIVAS);
        disenio.agregarCorrelativas(paradigmas,analisisDeSistemas);
        gestion = new Materia("Gestion de datos",TipoDeMateria.CONCORRELATIVAS);
        gestion.agregarCorrelativas(analisisDeSistemas,paradigmas,analisisDeSistemas);

        mesi = new Alumno(10,"Lionel","Messi");
    }

    @AfterEach
    void tearDown() {
        algebra = null;
        algoritmos = null;
        matematicaDiscreta = null;
        sistemasYOrganizaciones = null;
        arquitecturaDeComputadoras = null;
        sintaxis = null;
        paradigmas = null;
        analisisDeSistemas = null;
        sistemasOperativos = null;
        disenio = null;
        gestion = null;

        mesi = null;
    }


    @Test
    @DisplayName("Puede anotarse a materias sin correlativas")
    public void sinCorrelativas(){
        mesi.realizarInscripcion(algoritmos,matematicaDiscreta);
        Assertions.assertTrue(mesi.tieneInscripcionAprobada());
    }

    @Test
    @DisplayName("Pruebo funcion principal: aprobada() de la clase Inscripcion en el caso que deba rechazar")
    public void aprobadaRechaza(){
        mesi.realizarInscripcion(algoritmos,matematicaDiscreta);
        mesi.cursar();
        mesi.mostrarMateriasAprobadas();
        mesi.realizarInscripcion(sintaxis,paradigmas,analisisDeSistemas);
        Assertions.assertFalse(mesi.tieneInscripcionAprobada());
    }

    @Test
    @DisplayName("Puede anotarse y aprobar a todas las materias")
    public void puedeCursarTodasLasMaterias(){
        mesi.realizarInscripcion(algoritmos,matematicaDiscreta,sistemasYOrganizaciones,arquitecturaDeComputadoras);
        mesi.cursar();
        mesi.realizarInscripcion(sintaxis,paradigmas,analisisDeSistemas);
        mesi.cursar();
        mesi.realizarInscripcion(sistemasOperativos,gestion,disenio);
        mesi.cursar();
        Assertions.assertTrue(mesi.aproboMaterias(algoritmos,matematicaDiscreta,sistemasYOrganizaciones,arquitecturaDeComputadoras,sintaxis,paradigmas,analisisDeSistemas,sistemasOperativos,gestion,disenio));
    }
}