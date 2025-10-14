package examenes.parciales.octubre2024.blog;

import tdas.conjunto.ConjuntoBasico;
import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Blog {
    private final String titulo;
    private final List<Articulo> articulos;

    /**
     * post: Inicializa el blog con el título 'titulo' y sin artículos.
     * @param titulo: el título del blog.
     */
    public Blog(String titulo) {
        this.titulo = titulo;
        this.articulos = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve los artículos que componen al blog.
     * @return la lista de artículos.
     */
    public List<Articulo> articulos() {
        return Collections.unmodifiableList(this.articulos);
    }

    /**
     * post: Devuelve una nueva lista con los artículos que tengan en su título
     * o en su texto todas las palabras clave (en cualquier orden), pero ninguna
     * debe estar prohibida.
     * @param palabrasClave: lista de palabras clave.
     * @return una nueva lista de artículos con los requisitos mencionados.
     */
    public List<Articulo> buscarArticulos(List<String> palabrasClave) {
        Validaciones.validarNotNull(palabrasClave, "'palabrasClave'");

        Set<Articulo> articulosFiltrados = new HashSet<>();
        for (Articulo articulo: this.articulos()) {
            if (articulo == null) continue;

            if ((this.tieneElArticuloTodasLasPalabras(articulo, palabrasClave)) &&
                    (!this.tieneElArticuloAlgunaPalabraProhibida(articulo, palabrasClave))) {
                articulosFiltrados.add(articulo);
            }
        }
        return articulosFiltrados
                .stream()
                .toList();
    }

    /**
     * post: Indica si el artículo tiene alguna palabra de la lista de
     * palabras clave, como prohibida (excluyente).
     * @param articulo: el artículo a considerar.
     * @param palabrasClave: lista de palabras clave.
     * @return verdadero si el artículo tiene alguna de las palabras dada
     * prohibida.
     */
    public boolean tieneElArticuloAlgunaPalabraProhibida(Articulo articulo, List<String> palabrasClave) {
        Validaciones.validarNotNull(articulo, "'articulo'");
        Validaciones.validarNotNull(palabrasClave, "'palabrasClave'");
        return palabrasClave.stream()
                .anyMatch(p -> articulo.palabrasExcluyentes().contains(p));
    }

    /**
     * post: Indica si el artículo tiene todas las palabras de la lista de palabras
     * clave, en su título o en su texto.
     * @param articulo: al artículo a considerar.
     * @param palabrasClave: lista de palabras clave.
     * @return verdadero si el artículo tiene todas las palabras dadas en su
     * título o texto.
     */
    public boolean tieneElArticuloTodasLasPalabras(Articulo articulo, List<String> palabrasClave) {
        Validaciones.validarNotNull(articulo, "'articulo'");
        Validaciones.validarNotNull(palabrasClave, "'palabrasClave'");

        String textoCompleto = " " + articulo.titulo().toLowerCase() + " " + articulo.texto().toLowerCase() + " ";
        return palabrasClave.stream()
                .allMatch(p -> textoCompleto.contains(" " + p.toLowerCase() + " "));
    }
}
