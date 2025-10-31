package examenes.parciales.abril2022.noticias;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.List;

public class BuscadorDeNoticias {

    /**
     * post: Busca en noticias aquellas que tengan asociadas todas las categorías
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
            if ((noticia.categorias().size() >= cantidadMinimaDeCategorias)
                    && (contieneTodasLasCategorias(noticia, conCategorias))
                    && (!contieneAlMenosUnaCategoria(noticia, sinCategorias))) {
                noticiasFiltradas.add(noticia);
            }
        }
        return noticiasFiltradas;
    }

    /**
     * post: Indica si la noticia dada tiene al menos una categoría de la lista
     * de categorías dada.
     */
    public boolean contieneAlMenosUnaCategoria(Noticia noticia, List<Categoria> categorias) {
        Validaciones.validarNotNull(noticia, "noticia");
        Validaciones.validarNotNull(categorias, "categorias");
        List<Categoria> categoriasDeLaNoticia = noticia.categorias();
        for (Categoria categoria: categorias) {
            if (categoria == null) continue;
            if (categoriasDeLaNoticia.contains(categoria)) return true;
        }
        return false;
    }

    /**
     * post: Indica si la noticia dada tiene todas las categorías de la lista
     * de categorias dada.
     */
    public boolean contieneTodasLasCategorias(Noticia noticia, List<Categoria> categorias) {
        Validaciones.validarNotNull(noticia, "noticia");
        Validaciones.validarNotNull(categorias, "categorias");
        List<Categoria> categoriasDeLaNoticia = noticia.categorias();
        for (Categoria categoria: categorias) {
            if (categoria == null) continue;
            if (!categoriasDeLaNoticia.contains(categoria)) return false;
        }
        return true;
    }
}
