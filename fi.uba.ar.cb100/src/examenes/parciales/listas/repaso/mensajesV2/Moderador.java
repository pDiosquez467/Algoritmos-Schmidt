package examenes.parciales.listas.repaso.mensajesV2;

import validaciones.Validaciones;

import java.util.List;

public class Moderador {

    /**
     * post: Busca en la lista de foros 'foros' el mensaje más votado que tenga el
     * autor 'autorBuscado', dentro de un foro que NO incluya la temática 'tematicaBuscada'
     * y que el foro esté activo.
     */
    public Mensaje buscarMensajeMasVotadoDelUsuarioSegunTematica(List<Foro> foros,
                                                                 String autorBuscado,
                                                                 String tematicaBuscada) {
        Validaciones.validarNotNull(foros, "foros");
        Validaciones.validarNotNull(autorBuscado, "autorBuscado");
        Validaciones.validarNotNull(tematicaBuscada, "tematicaBuscada");
        Mensaje mensajeMasVotadoAlMomento = null;
        for (Foro foro: foros) {
            if ((foro == null) || (!foro.estaActivo())) continue;
            List<String> tematicasDelForo = foro.getTematicas();
            if ((tematicasDelForo == null) || (tematicasDelForo.contains(tematicaBuscada))) continue;
            Mensaje mensaje = mensajeMasVotadoDelUsuario(foro, autorBuscado);
            if (mensaje == null) continue;
            if ((mensajeMasVotadoAlMomento == null) ||
                    (mensaje.getVotos() > mensajeMasVotadoAlMomento.getVotos())) {
                mensajeMasVotadoAlMomento = mensaje;
            }
        }
        return mensajeMasVotadoAlMomento;
    }

    /**
     * post: Devuelve el mensaje más votado del autor 'autorBuscado' en la lista
     * de mensajes del foro.
     */
    public Mensaje mensajeMasVotadoDelUsuario(Foro foro, String autorBuscado) {
        Validaciones.validarNotNull(foro, "foro");
        Validaciones.validarNotNull(autorBuscado, "autorBuscado");
        Mensaje mensajeMasVotado = null;
        for (Mensaje mensaje: foro.getMensajes()) {
            if (mensaje == null) continue;
            if (!mensaje.getUsuario().equals(autorBuscado)) continue;
            if ((mensajeMasVotado == null) ||
                    (mensaje.getVotos() > mensajeMasVotado.getVotos())) {
                mensajeMasVotado = mensaje;
            }
        }
        return mensajeMasVotado;
    }
}
