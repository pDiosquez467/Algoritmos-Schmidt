package examenes.parciales.listas.repaso.blogs;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;

import java.util.List;
import java.util.Objects;

public class Articulo {
    private final String titulo;
    private final String texto;
    private final List<String> palabrasExcluidas;

    /**
     * post: Inicializa el artículo con el título y el texto dados,
     * sin palabras excluidas.
     */
    public Articulo(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
        this.palabrasExcluidas = new ListaSimplementeEnlazada<>();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Articulo articulo)) return false;
        return Objects.equals(titulo, articulo.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    /**
     * post: Devuelve el título del artículo.
     * @return el título del artículo.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * post: Devuelve el texto del artículo.
     * @return el texto del artículo.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * post: Devuelve la lista de palabras excluidas.
     * @return las palabras excluidas.
     */
    public List<String> getPalabrasExcluidas() {
        return palabrasExcluidas;
    }
}
