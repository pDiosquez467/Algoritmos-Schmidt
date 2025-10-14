package examenes.parciales.diciembre2022.mensajes;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.List;

public class Foro {
    private final String nombre;
    private final List<Mensaje> mensajes;
    private final List<String> tematicas;

    /**
     * post: Inicializa el foro sin mensajes asociados.
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
    public String nombre() {
        return nombre;
    }

    /**
     * post: Devuelve la lista de mensajes del foro.
     * @return la lista de mensajes del foro.
     */
    public List<Mensaje> mensajes() {
        return mensajes;
    }

    /**
     * post: Devuelve la lista de temáticas del foro.
     * @return la lista de temáticas.
     */
    public List<String> tematicas() {
        return tematicas;
    }

    /**
     * post: Devuelve los mensajes del autor dado.
     * @param usuario: autor buscado.
     * @return los mensajes cuyo autor es el dado.
     */
    public List<Mensaje> obtenerMensajesDelAutor(String usuario) {
        return this.mensajes().stream()
                .filter(mensaje -> mensaje.usuario().equals(usuario))
                .toList();
    }

    /**
     * post: Indica si el foro tiene la temática dada.
     * @param tematica: temática buscada.
     * @return verdadero si el foro tiene la temática dada.
     */
    public boolean tieneLaTematica(String tematica) {
        return this.tematicas().contains(tematica);
    }
}
