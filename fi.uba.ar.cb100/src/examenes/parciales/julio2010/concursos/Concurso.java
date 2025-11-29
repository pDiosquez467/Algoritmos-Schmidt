package examenes.parciales.julio2010.concursos;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.List;

public class Concurso {
    private final String nombre;
    private final List<Participante> participantes;
    private Participante ganador;

    /**
     * post: Inicializa el concurso con el nombre indicado y
     * sin participantes asociados.
     * @param nombre: el nombre del concurso.
     */
    public Concurso(String nombre) {
        this.nombre = nombre;
        this.participantes = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el nombre del concurso.
     * @return el nombre del concurso.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Devuelve los participantes asociados.
     * @return los participantes asociados.
     */
    public List<Participante> getParticipantes() {
        return participantes;
    }

    /**
     * post: Devuelve el participante que ganó el concurso.
     * @return el ganador del concurso.
     */
    public Participante getGanador() {
        return ganador;
    }

    /**
     * post: Indica qué participante ganó el concurso.
     * @param ganador: el ganador del concurso.
     */
    public void setGanador(Participante ganador) {
        Validaciones.validarNotNull(ganador, "ganador");
        this.ganador = ganador;
    }
}
