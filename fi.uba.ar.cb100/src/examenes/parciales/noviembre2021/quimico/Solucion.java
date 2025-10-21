package examenes.parciales.noviembre2021.quimico;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.List;

public class Solucion {
    private final String codigo;
    private final List<Compuesto> compuestos;

    /**
     * post: Inicializa la solución con el código especificado, sin
     * compuestos asociados.
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
    public String codigo() {
        return codigo;
    }

    /**
     * post: Devuelve la lista de compuestos de la solución.
     * @return la lista de compuestos de la solución.
     */
    public List<Compuesto> compuestos() {
        return compuestos;
    }
}
