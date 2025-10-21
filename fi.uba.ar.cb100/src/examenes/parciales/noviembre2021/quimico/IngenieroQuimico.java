package examenes.parciales.noviembre2021.quimico;

import tdas.conjunto.Conjunto;
import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.List;
import java.util.Set;

public class IngenieroQuimico {

    /**
     * post: Busca en 'solucionesDisponibles' las soluciones que tengan los mismos
     * compuestos que 'solucionRequerida' con cantidades iguales o superiores,
     * pero menores o iguales al doble.
     */
    public List<Solucion> buscarSolucionesEquivalentes(Solucion solucionRequerida,
                                                       List<Solucion> solucionesDisponibles) {
        Validaciones.validarNotNull(solucionRequerida, "soluciónRequerida");
        Validaciones.validarNotNull(solucionesDisponibles, "solucionesDisponibles");
        Set<Solucion> solucionesEquivalentes = new Conjunto<>();
        for (Solucion solucion: solucionesDisponibles) {
            if (solucion == null) continue;

            if (sonSolucionesEquivalentes(solucion, solucionRequerida)) {
                solucionesEquivalentes.add(solucion);
            }
        }
        return solucionesEquivalentes.stream().toList();
    }

    /**
     * post: Indica si las dos soluciones dadas son equivalentes.
     * @param solucion: una de las soluciones.
     * @param otra: la otra solución.
     * @return verdadero si las soluciones con equivalentes.
     */
    public boolean sonSolucionesEquivalentes(Solucion solucion, Solucion otra) {
        Validaciones.validarNotNull(solucion, "solución");
        Validaciones.validarNotNull(otra, "otra");
        if (solucion.compuestos().size() != otra.compuestos().size()) return false;
        for (Compuesto compuesto: solucion.compuestos()) {
            if (compuesto == null) return false;
            if (!contieneLaSolucionAlCompuesto(otra, compuesto)) return false;
        }
        return true;
    }

    /**
     * post: Indica si la solución dada contiene al compuesto dado.
     * @param compuestoBuscado: el compuesto buscado.
     * @param solucion: la solución dada.
     * @return verdadero si la solución contiene al compuesto.
     */
    public boolean contieneLaSolucionAlCompuesto(Solucion solucion, Compuesto compuestoBuscado) {
        Validaciones.validarNotNull(compuestoBuscado, "compuestoBuscado");
        Validaciones.validarNotNull(solucion, "solución");
        for (Compuesto compuesto: solucion.compuestos()) {
            if (compuesto == null) continue;
            if (esElCompuestoEquivalenteA(compuesto, compuestoBuscado)) {
                return true;
            }
        }
        return false;
    }

    /**
     * post: Indica si el primer compuesto dado es equivalente al segundo compuesto.
     * @param compuesto: el primer compuesto dado.
     * @param otro: el otro compuesto.
     * @return verdadero si el primero de los compuestos es equivalente al otro.
     * @apiNote un compuesto es equivalente a otro si tienen el mismo nombre,
     * la misma unidad y la cantidad del primero es mayor o igual a la del otro,
     * pero menor o igual al doble.
     */
    public boolean esElCompuestoEquivalenteA(Compuesto compuesto, Compuesto otro) {
        Validaciones.validarNotNull(compuesto, "compuesto");
        Validaciones.validarNotNull(otro, "otro");
        if (!compuesto.nombre().equals(otro.nombre())) return false;
        if (!compuesto.unidadDeMedida().equals(otro.unidadDeMedida())) return false;
        return ((compuesto.cantidad() >= otro.cantidad())) &&
                ((compuesto.cantidad() <= otro.cantidad() * 2));
    }


    /**
     * post: Busca en 'solucionesDisponibles' las soluciones que tengan los mismos
     * compuestos que 'solucionRequerida' con cantidades iguales o superiores,
     * pero menores o iguales al doble.
     */
    public List<Solucion> buscarSolucionesEquivalentesV2(Solucion solucionRequerida,
                                                       List<Solucion> solucionesDisponibles) {
        Validaciones.validarNotNull(solucionRequerida, "soluciónRequerida");
        Validaciones.validarNotNull(solucionesDisponibles, "solucionesDisponibles");

        List<Solucion> solucionesEquivalentes = new ListaSimplementeEnlazada<>();

        for (Solucion candidata : solucionesDisponibles) {
            if (candidata != null && esEquivalenteARequerida(candidata, solucionRequerida)) {
                solucionesEquivalentes.add(candidata);
            }
        }

        return solucionesEquivalentes;
    }

    /**
     * post: Indica si la solución candidata es equivalente a la solución requerida.
     * Una solución candidata es equivalente si para cada uno de sus compuestos,
     * existe un compuesto equivalente en la solución requerida.
     */
    public boolean esEquivalenteARequerida(Solucion candidata, Solucion requerida) {
        // Verificar que cada compuesto de la candidata tenga su equivalente en la requerida
        for (Compuesto compuestoCandidato : candidata.compuestos()) {
            if (compuestoCandidato == null) {
                return false; // Compuesto nulo en candidata invalida la equivalencia
            }

            Compuesto compuestoRequerido = buscarCompuestoEquivalente(requerida, compuestoCandidato);
            if (compuestoRequerido == null) {
                return false; // La candidata tiene un compuesto que no existe en la requerida
            }

            if (!cumpleRangoDeCantidad(compuestoCandidato, compuestoRequerido)) {
                return false; // La cantidad no está en el rango permitido
            }
        }

        return true;
    }

    /**
     * Busca en la solución requerida un compuesto equivalente al compuesto candidato.
     * Dos compuestos son equivalentes si tienen el mismo nombre y unidad de medida.
     */
    public Compuesto buscarCompuestoEquivalente(Solucion requerida, Compuesto compuestoCandidato) {
        for (Compuesto compuestoRequerido : requerida.compuestos()) {
            if (compuestoRequerido != null &&
                    compuestoRequerido.nombre().equals(compuestoCandidato.nombre()) &&
                    compuestoRequerido.unidadDeMedida() == compuestoCandidato.unidadDeMedida()) {
                return compuestoRequerido;
            }
        }
        return null;
    }

    /**
     * Verifica si la cantidad del compuesto candidato cumple con el rango requerido:
     * cantidad_candidata >= cantidad_requerida && cantidad_candidata <= 2 * cantidad_requerida
     */
    public boolean cumpleRangoDeCantidad(Compuesto candidato, Compuesto requerido) {
        double cantidadRequerida = requerido.cantidad();
        double cantidadCandidato = candidato.cantidad();

        return cantidadCandidato >= cantidadRequerida &&
                cantidadCandidato <= 2 * cantidadRequerida;
    }
}
