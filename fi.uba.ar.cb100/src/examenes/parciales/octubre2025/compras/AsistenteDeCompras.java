package examenes.parciales.octubre2025.compras;

import java.util.*;

import validaciones.Validaciones;

public class AsistenteDeCompras {

    /**
     * post: Busca en 'stockDeAtuendos' dos atuendos por comprador que cumplan simultáneamente
     * las condiciones:
     * 1. Ambas prendas tienen exactamente el mismo 'color'.
     * 2. El 'precio' del 'atuendo1' + el 'precio' del atuendo2 es <= 'presupuesto'.
     * 3. Los dos atuendos deben ser de distinto tipo.
     * Devuelve los atuendos NO comprados. El atuendo se puede comprar 1 vez.
     *
     */
    public List<Atuendo> comprarAtuendos(List<Atuendo> stockDeAtuendos, List<Comprador> compradores) {
        Validaciones.validarNotNull(stockDeAtuendos, "stockDeAtuendos");
        Validaciones.validarNotNull(compradores, "compradores");
        Set<Atuendo> atuendosComprados = new HashSet<>();
        for (Comprador comprador: compradores) {
            List<Atuendo> atuendosDelComprador = obtenerAtuendosQueCombinan(stockDeAtuendos, atuendosComprados, comprador);
            if (atuendosDelComprador.isEmpty()) continue;
            atuendosComprados.addAll(atuendosDelComprador);
        }
        List<Atuendo> atuendosFiltrados = new ArrayList<>(stockDeAtuendos);
        atuendosFiltrados.removeAll(atuendosComprados);
        return atuendosFiltrados;
    }

    /**
     * post: Devuelve una lista con dos atuendos que 'combinan' según las especificaciones indicadas.
     * En caso de no encontrar tales atuendos, devuelve una lista vacía.
     */
    public List<Atuendo> obtenerAtuendosQueCombinan(Collection<Atuendo> atuendos, Collection<Atuendo> otrosAtuendos, Comprador comprador) {
        Validaciones.validarNotNull(atuendos, "atuendos");
        Validaciones.validarNotNull(otrosAtuendos, "otrosAtuendos");
        Validaciones.validarNotNull(comprador, "comprador");
        for (Atuendo atuendo: atuendos) {
            if (otrosAtuendos.contains(atuendo)) continue;
            Atuendo otroAtuendo = obtenerSegundoAtuendo(atuendo, atuendos, otrosAtuendos, comprador);
            if (otroAtuendo == null) continue;
            return List.of(atuendo, otroAtuendo);
        }
        return List.of();
    }

    /**
     * post: Busca un segundo atuendo que combine con el indicado, en la lista de 'atuendos', que no esté comprado y que se adapte
     * a las especificaciones del comprador 'comprador'. Si no lo encuentra, devuelve null.
     */
    public Atuendo obtenerSegundoAtuendo(Atuendo atuendo, Collection<Atuendo> stockDeAtuendos, Collection<Atuendo> atuendosComprados, Comprador comprador) {
        Validaciones.validarNotNull(atuendo, "atuendo");
        Validaciones.validarNotNull(stockDeAtuendos, "stockDeAtuendos");
        Validaciones.validarNotNull(atuendosComprados, "atuendosComprados");
        Validaciones.validarNotNull(comprador, "comprador");
        for (Atuendo atuendoGuardado: stockDeAtuendos) {
            if ((atuendoGuardado.equals(atuendo)) || (atuendosComprados.contains(atuendoGuardado))) continue;
            if ((atuendoGuardado.color().equals(atuendo.color())) &&
                    (atuendoGuardado.precio() + atuendo.precio() <= comprador.presupuesto()) &&
                    (!atuendoGuardado.tipo().equals(atuendo.tipo()))) {
                return atuendoGuardado;
            }
        }
        return null;
    }

}
