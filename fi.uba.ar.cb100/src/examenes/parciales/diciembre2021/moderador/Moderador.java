package examenes.parciales.diciembre2021.moderador;

import tdas.conjunto.Conjunto;
import validaciones.Validaciones;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Moderador {

    /**
     * post: Busca en la lista 'foros' el mensaje más votado que tenga el autor 'autorBuscado'
     * dentro de un foro que no incluya la temática 'tematicaBuscada' y el foro este activo.
     */
    public Mensaje buscarMensajeMasVotadoDelUsuarioSegunTematica(List<Foro> foros,
                                                                 String autorBuscado,
                                                                 String tematicaBuscada) {
        Validaciones.validarNotNull(foros, "foros");
        Validaciones.validarNotNull(autorBuscado, "autorBuscado");
        Validaciones.validarVerdadero(!autorBuscado.isEmpty(), "autorBuscado");
        Validaciones.validarNotNull(tematicaBuscada, "tematicaBuscada");
        Validaciones.validarVerdadero(!tematicaBuscada.isEmpty(), "tematicaBuscada");

        Set<Mensaje> mensajesFiltrados = new Conjunto<>();
        for (Foro foro: foros) {
            if (foro == null) continue;

            if ((!incluyeElForoLaTematica(foro, tematicaBuscada)) && (foro.estaActivo())) {
                mensajesFiltrados.addAll(obtenerTodosLosMensajesDelAutor(foro, autorBuscado));
            }
        }

        return obtenerElMensajeMasVotado(mensajesFiltrados);
    }

    /**
     * post: Devuelve el mensaje más votado de la lista de mensajes dada.
     * @param mensajes: lista de mensajes dada.
     * @return el mensaje con más votos.
     */
    public Mensaje obtenerElMensajeMasVotado(Collection<Mensaje> mensajes) {
        Validaciones.validarNotNull(mensajes, "mensajes");

        Mensaje mensajeMasVotado = null;
        for (Mensaje mensaje: mensajes) {
            if (mensaje == null) continue;
            if ((mensajeMasVotado == null) ||
                    (mensaje.votos() > mensajeMasVotado.votos())) {
                mensajeMasVotado = mensaje;
            }
        }
        return mensajeMasVotado;
    }

    /**
     * post: Devuelve una colección con todos los mensajes del autor dado en el
     * foro dado.
     * @param foro: el foro donde buscar.
     * @param autorBuscado: el autor buscado.
     * @return una colección con los mensajes del autor.
     */
    public Collection<Mensaje> obtenerTodosLosMensajesDelAutor(Foro foro, String autorBuscado) {
        Validaciones.validarNotNull(foro, "foro");
        Validaciones.validarNotNull(autorBuscado, "autorBuscado");
        Validaciones.validarVerdadero(!autorBuscado.isEmpty(), "autorBuscado");

        Set<Mensaje> mensajesDelAutor = new Conjunto<>();
        for (Mensaje mensaje: foro.mensajes()) {
            if (mensaje == null) continue;
            if (Objects.equals(mensaje.usuario(), autorBuscado)) {
                mensajesDelAutor.add(mensaje);
            }
        }
        return mensajesDelAutor;
    }

    /**
     * post: Indica si el foro dado incluye la temática dada.
     * @param foro: el foro donde buscar.
     * @param tematicaBuscada: la temática buscada.
     * @return verdadero si el foro tiene la temática buscada.
     */
    public boolean incluyeElForoLaTematica(Foro foro, String tematicaBuscada) {
        Validaciones.validarNotNull(foro, "foro");
        Validaciones.validarNotNull(tematicaBuscada, "tematicaBuscada");
        Validaciones.validarVerdadero(!tematicaBuscada.isEmpty(), "tematicaBuscada");

        return foro.tematicas().contains(tematicaBuscada);
    }
}
