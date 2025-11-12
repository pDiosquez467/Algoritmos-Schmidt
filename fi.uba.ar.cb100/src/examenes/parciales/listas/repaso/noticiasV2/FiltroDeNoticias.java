package examenes.parciales.listas.repaso.noticiasV2;

import validaciones.Validaciones;

import java.util.List;
import java.util.ListIterator;

public class FiltroDeNoticias {

    /**
     * post: Remueve de noticias aquellas que tengan asociadas TODAS las categorías
     * dadas en 'conCategorias'.
     * - Si 'conCategorias' está vacío, no se remueve ninguna noticia.
     * - Las categorías nulas en 'conCategorias' se ignoran.
     * - Las noticias nulas se ignoran.
     */
    public void removerNoticias(List<Noticia> noticias, List<Categoria> conCategorias) {
        Validaciones.validarNotNull(noticias, "noticias");
        Validaciones.validarNotNull(conCategorias, "conCategorias");
        if (conCategorias.isEmpty()) {
            return;
        }
        ListIterator<Noticia> it = noticias.listIterator();
        while (it.hasNext()) {
            Noticia noticia = it.next();
            if (noticia == null) continue;
            if (contieneTodasLasCategorias(noticia, conCategorias)) {
                it.remove();
            }
        }
    }

    /**
     * post: Indica si la noticia dada contiene TODAS las categorías de la lista
     * 'conCategorías'.
     */
    public boolean contieneTodasLasCategorias(Noticia noticia, List<Categoria> categorias) {
        Validaciones.validarNotNull(noticia, "noticia");
        Validaciones.validarNotNull(noticia.getCategorias(), "Categorías de la noticia");
        Validaciones.validarNotNull(categorias, "categorias");
        List<Categoria> categoriasDeLaNoticia = noticia.getCategorias();
        for (Categoria categoria: categorias) {
            if (categoria == null) continue;
            if (!categoriasDeLaNoticia.contains(categoria)) return false;
        }
        return true;
    }
}
