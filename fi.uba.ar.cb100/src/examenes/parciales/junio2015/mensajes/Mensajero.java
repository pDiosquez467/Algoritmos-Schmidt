package examenes.parciales.junio2015.mensajes;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.List;

public class Mensajero {

    /**
     * post: Procesa 'mensajesPendientes' y busca aquellos mensajes cuya cuenta remitente tiene entre
     * sus remitentes bloqueados a uno de sus destinatarios, por lo tanto, esos mensajes no van a poder ser respondidos.
     * Devuelve una nueva lista con los mensajes encontrados.
     */
    public List<Mensaje> buscarMensajesQueNoPuedenSerRespondidos(List<Mensaje> mensajesPendientes) {
        Validaciones.validarNotNull(mensajesPendientes, "mensajesPendientes");
        List<Mensaje> mensajesQueNoPuedenSerRespondidos = new ListaSimplementeEnlazada<>();
        for (Mensaje mensaje: mensajesPendientes) {
            if (mensaje == null) continue;
            if (tieneAlMenosUnDestinatarioBloqueado(mensaje.getRemitente(), mensaje.getDestinatarios())) {
                mensajesQueNoPuedenSerRespondidos.add(mensaje);
            }
        }
        return mensajesQueNoPuedenSerRespondidos;
    }

    /**
     * post: Indica si la cuenta dada tiene al menos un destinatario de la lista de destinatarios dada,
     * bloqueado.
     */
    public boolean tieneAlMenosUnDestinatarioBloqueado(Cuenta cuenta, List<Cuenta> destinatarios) {
        Validaciones.validarNotNull(cuenta, "cuenta");
        Validaciones.validarNotNull(destinatarios, "destinatarios");
        for (Cuenta destinatario: destinatarios) {
            if (destinatario == null) continue;
            if (cuenta.estaBloqueada(destinatario)) {
                return true;
            }
        }
        return false;
    }
}
