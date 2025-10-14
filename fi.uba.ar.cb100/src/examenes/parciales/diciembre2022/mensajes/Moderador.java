package examenes.parciales.diciembre2022.mensajes;

import validaciones.Validaciones;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;

public class Moderador {

    /**
     * post: Busca en la lista de foros el mensaje más votado del usuario
     * 'usuarioBuscado' dentro de un foro que no incluye la temática 'temáticaBuscada'.
     * @param foros: lista de foros.
     * @param usuarioBuscado: usuario buscado.
     * @param tematicaBuscada: temática buscada.
     * @return el mensaje con más votos.
     */
    public Mensaje buscarMensajeMasVotadoDelUsuarioSegunTematica(List<Foro> foros,
                                                                 String usuarioBuscado,
                                                                 String tematicaBuscada) {
        Validaciones.validarNotNull(foros, "'foros'");
        Validaciones.validarNotNull(usuarioBuscado, "'usuarioBuscado'");
        Validaciones.validarNotNull(tematicaBuscada, "'tematicaBuscada'");

        Set<Mensaje> mensajesFiltrados = new HashSet<>();
        for (Foro foro: foros) {
            if (foro == null) continue;
            if (!tieneElForoLaTematica(foro, tematicaBuscada)) {
                List<Mensaje> mensajes = buscarMensajesDelAutor(foro, usuarioBuscado);
                if (mensajes == null) continue;
                mensajesFiltrados.addAll(mensajes);
            }
        }

        return obtenerMensajeMasVotado(mensajesFiltrados);
    }

    /**
     * post: Devuelve el mensaje más votado de la lista de mensajes dada.
     * @param mensajes: lista de mensajes.
     * @return el mensaje más votado.
     */
    public Mensaje obtenerMensajeMasVotado(Collection<Mensaje> mensajes) {
        Validaciones.validarNotNull(mensajes, "'mensajes'");

        Mensaje mensajeMasVotado = null;
        for (Mensaje mensaje: mensajes) {
            if (mensaje == null) continue;

            if ((mensajeMasVotado == null) || (mensaje.votos() > mensajeMasVotado.votos())) {
                mensajeMasVotado = mensaje;
            }
        }

        return mensajeMasVotado;
    }

    /**
     * post: Devuelve una lista con los mensajes del foro cuyo autor es
     * 'usuario'.
     * @param foro: el foro donde buscar.
     * @param usuario: el usuario buscado.
     * @return una lista de mensajes del autor dado.
     */
    public List<Mensaje> buscarMensajesDelAutor(Foro foro, String usuario) {
        return foro.obtenerMensajesDelAutor(usuario);
    }

    /**
     * post: Indica si el foro tiene la temática buscada.
     * @param foro: el foro donde buscar.
     * @param tematica: la temática buscada.
     * @return verdadero si el foro tiene la temática buscada.
     */
    public boolean tieneElForoLaTematica(Foro foro, String tematica) {
        Validaciones.validarNotNull(foro, "'foro'");
        Validaciones.validarNotNull(tematica, "'tematica'");

        return foro.tieneLaTematica(tematica);
    }
}
