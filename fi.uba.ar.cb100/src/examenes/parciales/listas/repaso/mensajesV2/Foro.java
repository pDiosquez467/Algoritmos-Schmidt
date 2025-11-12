package examenes.parciales.listas.repaso.mensajesV2;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.Collections;
import java.util.List;

public class Foro {

    private final String nombre;
    private boolean estaActivo;
    private final List<Mensaje> mensajes;
    private final List<String> tematicas;

    /**
     * post: Inicializa el foro con el nombre dado, sin mensajes asignados.
     * @param nombre: el nombre del foro.
     */
    public Foro(String nombre) {
        this.nombre = nombre;
        this.estaActivo = true;
        this.mensajes = new ListaSimplementeEnlazada<>();
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
     * post: Indica si el foro está activo.
     * @return está activo?
     */
    public boolean estaActivo() {
        return estaActivo;
    }

    /**
     * post: Devuelve una copía inmutable de la lista de mensajes del foro.
     * @return la lista de mensajes del foro.
     */
    public List<Mensaje> getMensajes() {
        return Collections.unmodifiableList(this.mensajes);
    }

    /**
     * post: Devuelve una copia inmutable de la lista de temáticas del foro.
     * @return la lista de temáticas del foro.
     */
    public List<String> getTematicas() {
        return Collections.unmodifiableList(this.tematicas);
    }

    /**
     * post: Modifica la condición de 'activo' del foro.
     * @param estaActivo: ¿está activo?
     */
    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
}
