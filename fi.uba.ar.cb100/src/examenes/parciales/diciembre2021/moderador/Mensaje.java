package examenes.parciales.diciembre2021.moderador;

public class Mensaje {
    private final String usuario;
    private final String texto;
    private int votos;

    /**
     * post: 'usuario' es el autor del mensaje con 'texto' como contenido,
     * sin votos asociados.
     * @param usuario: el autor del mensaje.
     * @param texto: el contenido del mensaje.
     */
    public Mensaje(String usuario, String texto) {
        this.usuario = usuario;
        this.texto = texto;
        this.votos = 0;
    }

    /**
     * post: Suma un voto al mensaje.
     */
    public void votar() {
        this.votos++;
    }

    /**
     * post: Devuelve el autor del mensaje.
     * @return el autor del mensaje.
     */
    public String usuario() {
        return usuario;
    }

    /**
     * post: Devuelve el contenido del mensaje.
     * @return el contenido del mensaje.
     */
    public String texto() {
        return texto;
    }

    /**
     * post: Devuelve la cantidad de votos del mensaje.
     * @return la cantidad de votos del mensaje.
     */
    public int votos() {
        return votos;
    }
}
