package examenes.parciales.listas.repaso.noticias;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.List;

public class BuscadorDeNoticias {

    /**
     * post: Busca en 'noticias' aquellas que tengan asociadas todas las categorías
     * dadas en 'conCategorias' y que NO contengan ninguna de las categorías de
     * 'sinCategorias', teniendo un mínimo de categorías.
     */
    public List<Noticia> buscarNoticias(List<Noticia> noticias,
                                        List<Categoria> conCategorias,
                                        List<Categoria> sinCategorias,
                                        int cantidadMinimaDeCategorias) {
        Validaciones.validarNotNull(noticias, "noticias");
        Validaciones.validarNotNull(conCategorias, "conCategorias");
        Validaciones.validarNotNull(sinCategorias, "sinCategorias");
        Validaciones.validarNumeroMayorACero(cantidadMinimaDeCategorias, "cantidadMinimaDeCategorias");
        List<Noticia> noticiasFiltradas = new ListaSimplementeEnlazada<>();
        for (Noticia noticia: noticias) {
            if (noticia == null) continue;
            List<Categoria> categoriasDeLaNoticia = noticia.getCategorias();
            if (categoriasDeLaNoticia.size() < cantidadMinimaDeCategorias) continue;
            if (!contieneTodasLasCategorias(noticia, conCategorias)) continue;
            if (contieneAlgunaCategoria(noticia, sinCategorias)) continue;
            noticiasFiltradas.add(noticia);
        }
        return noticiasFiltradas;
    }

    /**
     * post: Indica si la noticia contiene ALGUNA categoría de la lista dada.
     */
    public boolean contieneAlgunaCategoria(Noticia noticia, List<Categoria> categoriasProhibidas) {
        Validaciones.validarNotNull(noticia, "noticia");
        Validaciones.validarNotNull(categoriasProhibidas, "categoriasProhibidas");
        for (Categoria categoria : categoriasProhibidas) {
            if (categoria != null && noticia.getCategorias().contains(categoria)) {
                return true;
            }
        }
        return false;
    }

    /**
     * post: Indica si la noticia dada tiene todas las categorías de la lista de
     * categorías dada.
     */
    public boolean contieneTodasLasCategorias(Noticia noticia, List<Categoria> categorias) {
        Validaciones.validarNotNull(noticia, "noticia");
        Validaciones.validarNotNull(categorias, "categorias");
        for (Categoria categoria: categorias) {
            if (categoria == null) continue;
            if (!noticia.getCategorias().contains(categoria)) return false;
        }
        return true;
    }
}
