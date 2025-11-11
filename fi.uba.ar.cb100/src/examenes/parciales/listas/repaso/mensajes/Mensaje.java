package examenes.parciales.listas.repaso.mensajes;

import java.util.Objects;

public class Mensaje {

    private final String autor;
    private final String contenido;
    private int votos;

    /**
     * post: Inicializa un mensaje con el autor y el contenido dados,
     * sin votos asociados.
     */
    public Mensaje(String autor, String contenido) {
        this.autor = autor;
        this.contenido = contenido;
        this.votos = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Mensaje mensaje)) return false;
        return Objects.equals(autor, mensaje.autor) && Objects.equals(contenido, mensaje.contenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autor, contenido);
    }

    /**
     * post: Devuelve el autor del mensaje.
     * @return el autor del mensaje.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * post: Devuelve el contenido del mensaje.
     * @return el contenido del mensaje.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * post: Devuelve la cantidad de votos del mensaje.
     * @return la cantidad de votos.
     */
    public int getVotos() {
        return votos;
    }

    /**
     * post: Permite votar por el mensaje.
     */
    public void votar() {
        this.votos++;
    }
}
