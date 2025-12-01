package examenes.parciales.mayo2017.historiador;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LineaDeTiempo {
    private final String nombre;
    private final List<Hito> hitos;

    /**
     * post: Inicializa una línea de tiempo sin hitos asociados.
     */
    public LineaDeTiempo(String nombre) {
        this.nombre = nombre;
        this.hitos = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve los hitos ordenados temporalmente.
     * @return una copia de los hitos asociados a la línea de tiempo.
     */
    public List<Hito> getHitos() {
        return Collections.unmodifiableList(this.hitos);
    }

    /**
     * post: Agrega un nuevo hito a la línea de tiempo,
     * ordenándolo cronológicamente.
     * @param hito: un nuevo hito.
     */
    public void incluir(Hito hito) {
        this.hitos.add(hito);
        this.hitos.sort(Comparator.comparingInt(Hito::anio));
    }

    /**
     * post: Devuelve el nombre de la línea de tiempo.
     */
    public String getNombre() {
        return nombre;
    }
}
