package examenes.parciales.listas.repaso.mensajes;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.List;

public class Foro {

    private final String nombre;
    private final List<Mensaje> mensajes;
    private final List<String> tematicas;

    /**
     * post: Inicializa el foro con el nombre dado, sin mensajes ni
     * temáticas asignadas.
     * @param nombre: el nombre del foro.
     */
    public Foro(String nombre) {
        this.nombre    = nombre;
        this.mensajes  = new ListaSimplementeEnlazada<>();
        this.tematicas = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el nombre del foro.
     * @return el nombre del foro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Devuelve la lista de mensajes del foro.
     * @return los mensajes del foro.
     */
    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    /**
     * post: Devuelve la lista de temáticas del foro.
     * @return las temáticas del foro.
     */
    public List<String> getTematicas() {
        return tematicas;
    }
}
