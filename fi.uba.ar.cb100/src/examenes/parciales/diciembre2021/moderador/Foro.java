package examenes.parciales.diciembre2021.moderador;

import java.util.ArrayList;
import java.util.List;

public class Foro {
    private final String nombre;
    private final List<Mensaje> mensajes;
    private final List<String> tematicas;
    private boolean estaActivo;

    /**
     * post: Inicializa el foro sin mensajes asociados.
     *
     * @param nombre: el nombre del foro.
     */
    public Foro(String nombre) {
        this.nombre = nombre;
        this.mensajes = new ArrayList<>();
        this.tematicas = new ArrayList<>();
        this.estaActivo = false;
    }

    /**
     * post: Devuelve el nombre del foro.
     *
     * @return el nombre del foro.
     */
    public String nombre() {
        return nombre;
    }

    /**
     * post: Devuelve la lista de mensajes del foro.
     *
     * @return la lista de mensajes del foro.
     */
    public List<Mensaje> mensajes() {
        return mensajes;
    }

    /**
     * post: Devuelve la lista de temáticas del foro.
     *
     * @return la lista de temáticas del foro.
     */
    public List<String> tematicas() {
        return tematicas;
    }

    /**
     * post: Indica si el foro está activo.
     * @return verdadero si el foro está activo.
     */
    public boolean estaActivo() {
        return estaActivo;
    }

    /**
     * post: Modifica el estado del foro.
     * @param estaActivo: nuevo estado del foro.
     * @return el foro actualizado.
     */
    public Foro setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
        return this;
    }
}
