package examenes.parciales.listas.repaso.mensajes;

import validaciones.Validaciones;

import java.util.List;

public class Moderador {

    /**
     * post: Busca en la lista de foros el mensaje más votado del autor 'autorBuscado'
     * dentro de un foro que NO incluya la temática 'tematicaBuscada'.
     */
    public Mensaje buscarMensajeMasVotadoDelUsuarioSegunTematica(List<Foro> foros,
                                                                 String usuarioBuscado,
                                                                 String tematicaBuscada) {
        Validaciones.validarNotNull(foros, "foros");
        Validaciones.validarNotNull(usuarioBuscado, "usuarioBuscado");
        Validaciones.validarNotNull(tematicaBuscada, "tematicaBuscada");
        Mensaje mensajeMasVotadoAlMomento = null;
        int votosDelMensajeMasVotadoAlMomento = -1;
        for (Foro foro: foros) {
            if ((foro == null) || (foro.getTematicas().contains(tematicaBuscada))) continue;
            Mensaje mensajeMasVotadoDelForo = buscarMensajeMasVotadoDelUsuarioEnForo(foro, usuarioBuscado);
            if (mensajeMasVotadoDelForo == null) continue;
            int votosActuales = mensajeMasVotadoDelForo.getVotos();
            if ((mensajeMasVotadoAlMomento == null)
                    || (votosActuales > votosDelMensajeMasVotadoAlMomento)) {
                mensajeMasVotadoAlMomento = mensajeMasVotadoDelForo;
                votosDelMensajeMasVotadoAlMomento = votosActuales;
            }
        }
        return mensajeMasVotadoAlMomento;
    }

    /**
     * post: Busca el mensaje más votado del foro indicado, del autor 'autorBuscado'.
     * Si no lo encuentra, devuelve null.
     */
    public Mensaje buscarMensajeMasVotadoDelUsuarioEnForo(Foro foro, String usuarioBuscado) {
        Validaciones.validarNotNull(foro, "foro");
        Validaciones.validarNotNull(foro.getMensajes(), "Mensajes del foro");
        Validaciones.validarNotNull(usuarioBuscado, "usuarioBuscado");
        Mensaje mensajeMasVotado = null;
        for (Mensaje mensaje: foro.getMensajes()) {
            if ((mensaje == null) || (mensaje.getAutor() == null))  continue;
            if (mensaje.getAutor().equals(usuarioBuscado)) {
                if ((mensajeMasVotado == null) || (mensaje.getVotos() > mensajeMasVotado.getVotos())) {
                    mensajeMasVotado = mensaje;
                }
            }
        }
        return mensajeMasVotado;
    }
}
