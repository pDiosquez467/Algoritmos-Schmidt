package examenes.parciales.listas.repaso.mensajesV2;

public class Mensaje {

    private final String usuario;
    private final String contenido;
    private int votos;

    /**
     * post: 'usuario' es el autor del mensaje con 'contenido' como contenido
     * y sin votos asociados.
     */
    public Mensaje(String usuario, String contenido) {
        this.usuario   = usuario;
        this.contenido = contenido;
        this.votos     = 0;
    }

    /**
     * post: Devuelve el usuario del mensaje.
     * @return el usuario del mensaje.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * post: Devuelve el contenido del mensaje.
     * @return el contenido del mensaje.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * post: Devuelve los votos del mensaje.
     * @return los votos del mensaje.
     */
    public int getVotos() {
        return votos;
    }

    /**
     * post: Suma un voto al mensaje.
     */
    public void votar() {
        this.votos++;
    }
}
