package examenes.parciales.listas.repaso.quimica;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.Collections;
import java.util.List;

public class Solucion {

    private final String codigo;
    private final List<Compuesto> compuestos;

    /**
     * post: Inicializa la solución con el código dado y sin compuestos
     * asociados.
     * @param codigo: el código de la solución.
     */
    public Solucion(String codigo) {
        this.codigo = codigo;
        this.compuestos = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el código de la solución.
     * @return el código de la solución.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * post: Devuelve la lista de compuestos de la solución.
     * @return los compuestos de la solución.
     */
    public List<Compuesto> getCompuestos() {
        return Collections.unmodifiableList(this.compuestos);
    }
}
