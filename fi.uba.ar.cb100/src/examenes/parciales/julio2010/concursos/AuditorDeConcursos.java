package examenes.parciales.julio2010.concursos;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.List;

public class AuditorDeConcursos {

    /**
     * post: Busca en 'concursosAuditados' aquellos concursos para los cuales
     * el promedio de los puntajes de los participantes supera al puntaje mínimo.
     * Devuelve una nueva lista con todos los concursos en esta condición.
     */
    public List<Concurso> buscarConcursos(List<Concurso> concursosAuditados,
                                          int puntajeMinimo) {
        Validaciones.validarNotNull(concursosAuditados, "concursosAuditados");
        Validaciones.validarNumeroMayorACero(puntajeMinimo, "puntajeMinimo");

        List<Concurso> concursosFiltrados = new ListaSimplementeEnlazada<>();
        for (Concurso concurso: concursosAuditados) {
            if (promedioPuntajeDeParticipantes(concurso) > puntajeMinimo) {
                concursosFiltrados.add(concurso);
            }
        }
        return concursosFiltrados;
    }

    public double promedioPuntajeDeParticipantes(Concurso concurso) {
        Validaciones.validarNotNull(concurso, "concurso");
        int puntajeTotal = 0;
        int cantidad = 0;
        for (Participante participante: concurso.getParticipantes()) {
            int puntaje = participante.getPuntos();
            if (puntaje> 0) {
                puntajeTotal += puntaje;
                cantidad++;
            }
        }
        return (cantidad > 0) ? (double) puntajeTotal / cantidad : 0;
    }


}
