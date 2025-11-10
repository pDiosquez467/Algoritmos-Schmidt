package examenes.parciales.listas.repaso.blogs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

public class Blog {

    private final List<Articulo> articulos;

    /**
     * post: Inicializa un blog sin artículos asociados.
     */
    public Blog() {
        this.articulos = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve la lista de artículos del blog.
     * @return la lista de artículos del blog.
     */
    public List<Articulo> getArticulos() {
        return articulos;
    }

    /**
     * post: Devuelve una nueva lista con los artículos que tengan en su título
     * o texto todas las palabras clave (en cualquier orden), pero ninguna debe estar
     * excluida.
     */
    public List<Articulo> buscarArticulos(List<String> palabrasClaves) {
        Validaciones.validarNotNull(palabrasClaves, "palabrasClaves");
        List<Articulo> articulosFiltrados = new ListaSimplementeEnlazada<>();
        for (Articulo articulo: this.getArticulos()) {
            if ((articulo == null)) continue;
            if ((tieneTodasLasPalabras(articulo, palabrasClaves)) &&
                    (!tieneAlMenosNPalabrasExcluidas(articulo, palabrasClaves, 1))) {
                articulosFiltrados.add(articulo);
            }
        }
        return articulosFiltrados;
    }

    /**
     * post: Indica si el artículo dado tiene todas las palabras de la lista de palabras
     * 'palabrasClaves' en su título o en su texto. Es case-insensitive.
     */
    public boolean tieneTodasLasPalabras(Articulo articulo, List<String> palabrasClaves) {
        Validaciones.validarNotNull(articulo, "articulo");
        Validaciones.validarNotNull(palabrasClaves, "palabrasClaves");
        String str = (articulo.getTitulo() + " " + articulo.getTexto()).toLowerCase();
        str = str.replaceAll("[^a-zA-Z0-9\\s]", " ");
        String[] palabrasDelTextoArray = str.split("\\s+");
        Set<String> palabrasDelTextoSet = new HashSet<>(Arrays.asList(palabrasDelTextoArray));
        palabrasDelTextoSet.remove("");
        for (String palabraClave : palabrasClaves) {
            if (palabraClave == null) continue;
            String palabraClaveLimpia = palabraClave.trim().toLowerCase();
            if (!palabrasDelTextoSet.contains(palabraClaveLimpia)) {
                return false;
            }
        }
        return true;
    }

    /**
     * post: Indica si el artículo tiene al menos 'numeroMinimoDePalabras' palabras
     * de la lista de palabras clave dada en su lista de palabras excluidas.
     */
    public boolean tieneAlMenosNPalabrasExcluidas(Articulo articulo, List<String> palabrasClaves, int numeroMinimoDePalabras) {
        Validaciones.validarNotNull(articulo, "articulo");
        Validaciones.validarNotNull(palabrasClaves, "palabrasClaves");
        Validaciones.validarNumeroMayorACero(numeroMinimoDePalabras, "numeroMinimoDePalabras");
        int total = 0;
        for (String palabra: articulo.getPalabrasExcluidas()) {
            if (palabra == null) continue;
            if (palabrasClaves.contains(palabra)) {
                total++;
                if (total >= numeroMinimoDePalabras) return true;
            }
        }
        return false;
    }
}
