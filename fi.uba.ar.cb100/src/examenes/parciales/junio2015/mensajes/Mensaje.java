package examenes.parciales.junio2015.mensajes;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.Collections;
import java.util.List;

public class Mensaje {
    private final Cuenta remitente;
    private final String contenido;
    private final List<Cuenta> destinatarios;

    /**
     * post: Inicializa el mensaje con el contenido indicado y sin destinatarios.
     */
    public Mensaje(Cuenta remitente, String contenido) {
        this.remitente = remitente;
        this.contenido = contenido;
        this.destinatarios = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve la cuenta que envía el mensaje.
     */
    public Cuenta getRemitente() {
        return this.remitente;
    }

    /**
     * post: Devuelve el contenido del mensaje.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * post: Devuelve todas las cuentas a las que se debe enviar el mensaje.
     */
    public List<Cuenta> getDestinatarios() {
        return Collections.unmodifiableList(this.destinatarios);
    }
}
