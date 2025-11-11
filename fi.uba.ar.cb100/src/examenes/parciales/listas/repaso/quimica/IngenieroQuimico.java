package examenes.parciales.listas.repaso.quimica;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.List;

public class IngenieroQuimico {

    /**
     * post: Busca en 'solucionesDisponibles' las soluciones que tengan los mismos
     * compuestos que 'solucionRequerida', con cantidades iguales o superiores, pero
     * menores o iguales que el doble.
     */
    public List<Solucion> buscarSolucionesEquivalentes(Solucion solucionRequerida,
                                                       List<Solucion> solucionesDisponibles) {
        Validaciones.validarNotNull(solucionRequerida, "soluciónRequerida");
        Validaciones.validarNotNull(solucionesDisponibles, "solucionesDisponibles");
        List<Solucion> solucionesEquivalentes = new ListaSimplementeEnlazada<>();
        for (Solucion solucion: solucionesDisponibles) {
            if (solucion == null) continue;
            if (sonEquivalentes(solucionRequerida, solucion)) {
                solucionesEquivalentes.add(solucion);
            }
        }
        return solucionesEquivalentes;
    }

    /**
     * post: Indica si las soluciones dadas son equivalentes.
     */
    public boolean sonEquivalentes(Solucion solucion, Solucion otra) {
        Validaciones.validarNotNull(solucion, "solución");
        Validaciones.validarNotNull(otra, "otra");
        if (solucion.getCompuestos().size() != otra.getCompuestos().size()) return false;
        for (Compuesto compuesto: otra.getCompuestos()) {
            if (!perteneceConCantidadAdecuada(compuesto, solucion)) return false;
        }
        return true;
    }

    /**
     * post: Indica si el compuesto candidato pertenece a la lista de compuestos de la solución dada
     * en las cantidades adecuadas.
     */
    public boolean perteneceConCantidadAdecuada(Compuesto compuestoCandidato, Solucion solucion) {
        Validaciones.validarNotNull(compuestoCandidato, "compuestoCandidato");
        Validaciones.validarNotNull(solucion, "solución");
        for (Compuesto compuesto: solucion.getCompuestos()) {
            if (compuesto.equals(compuestoCandidato)) {
                if ((compuestoCandidato.getCantidad() >= compuesto.getCantidad()) && (compuestoCandidato.getCantidad() <= compuesto.getCantidad() * 2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
