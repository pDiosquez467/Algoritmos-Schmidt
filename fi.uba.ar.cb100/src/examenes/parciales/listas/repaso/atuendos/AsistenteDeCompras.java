package examenes.parciales.listas.repaso.atuendos;

import tdas.conjunto.ConjuntoBasico;
import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.List;

public class AsistenteDeCompras {

    /**
     * post: Busca en 'atuendos' DOS atuendos por comprador que cumplan simultáneamente
     * las siguientes condiciones:
     * 1. Ambas prendas tienen el mismo color.
     * 2. El 'precio del atuendo1' + 'precio del atuendo2' <= 'presupuesto'.
     * 3. Los dos atuendos deben ser de distinto tipo.
     * Devuelve los atuendos no comprados. El atuendo se puede comprar una vez.
     */
    public List<Atuendo> comprarAtuendos(List<Atuendo> atuendos,
                                         List<Comprador> compradores) {

        Validaciones.validarNotNull(atuendos, "atuendos");
        Validaciones.validarNotNull(compradores, "compradores");
        ConjuntoBasico<Atuendo> atuendosComprados = new ConjuntoBasico<>();
        for (Comprador comprador: compradores) {
            if (comprador == null) continue;
            ConjuntoBasico<Atuendo> atuendosDelComprador =
                    obtenerAtuendosDelComprador(atuendos, atuendosComprados, comprador);
            if (atuendosDelComprador == null) {
                throw new RuntimeException("No hay atuendos con las especificaciones para el comprador "
                        + comprador);
            }
            atuendosComprados.agregarTodos(atuendosDelComprador);
        }
        ConjuntoBasico<Atuendo> todosLosAtuendos = new ConjuntoBasico<>(atuendos);
        return new ListaSimplementeEnlazada<>(todosLosAtuendos.diferencia(atuendosComprados));
    }

    /**
     * post: Devuelve un conjunto con dos atuendos de la lista de atuendos dada, con las especificaciones indicadas.
     * En caso de no encontrar alguno de los atuendos, devuelve null.
     */
    public ConjuntoBasico<Atuendo> obtenerAtuendosDelComprador(List<Atuendo> atuendos, ConjuntoBasico<Atuendo> otrosAtuendos, Comprador comprador) {
        Validaciones.validarNotNull(atuendos, "atuendos");
        Validaciones.validarNotNull(otrosAtuendos, "otrosAtuendos");
        Validaciones.validarNotNull(comprador, "comprador");
        ConjuntoBasico<Atuendo> atuendosDelComprador = new ConjuntoBasico<>();
        for (int i = 0; i < atuendos.size(); i++) {
            Atuendo atuendo = atuendos.get(i);
            if ((atuendo == null) || (otrosAtuendos.contiene(atuendo))) continue;
            Atuendo otroAtuendo = obtenerAtuendoDelCompradorDesde(atuendos, atuendo, otrosAtuendos, comprador, i+1);
            if (otroAtuendo != null) {
                atuendosDelComprador.agregar(atuendo);
                atuendosDelComprador.agregar(otroAtuendo);
                return atuendosDelComprador;
            }
        }
        return null;
    }

    /**
     * post: Obtiene un atuendo para el comprador dado, de la lista de atuendos dada,
     * desde la posición 'desde'. En caso de no encontrarlo, devuelve null.
     */
    public Atuendo obtenerAtuendoDelCompradorDesde(List<Atuendo> atuendos,
                                                   Atuendo atuendo,
                                                   ConjuntoBasico<Atuendo> otrosAtuendos,
                                                   Comprador comprador,
                                                   int desde) {
        Validaciones.validarNotNull(atuendos, "atuendos");
        Validaciones.validarNotNull(atuendo, "atuendo");
        Validaciones.validarNotNull(otrosAtuendos, "otrosAtuendos");
        Validaciones.validarNotNull(comprador, "comprador");
        Validaciones.validarNumeroEntre(desde, 0, atuendos.size(), "desde");
        for (int j = desde; j < atuendos.size(); j++) {
            Atuendo otro = atuendos.get(j);
            if (otrosAtuendos.contiene(otro)) continue;
            if (!atuendo.getColor().equals(otro.getColor())) continue;
            if (atuendo.getPrecio() + otro.getPrecio() > comprador.getPresupuesto()) continue;
            if (atuendo.getTipo().equals(otro.getTipo())) continue;
            return otro;
        }
        return null;
    }
}
