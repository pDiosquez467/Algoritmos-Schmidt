package examenes.parciales.julio2021.noticias;

import validaciones.Validaciones;

import java.util.List;

public class FiltroDeNoticias {

    /**
     * post: Remueve de noticias aquellas noticias que tengan asociadas TODAS
     * las categorías dada en 'conCategorias'.
     * @param noticias: noticias disponibles.
     * @param conCategorias: lista de categorías a evaluar.
     */
    public void removerNoticias(List<Noticia> noticias, List<Categoria> conCategorias) {
        Validaciones.validarNotNull(noticias, "noticias");
        Validaciones.validarNotNull(conCategorias, "conCategorías");
        /* while (iteradorDeNoticias.hasNext()) {
            Noticia noticia = iteradorDeNoticias.next();
            if (this.tieneLaNoticiaTodasLasCategorias(noticia, conCategorias)) {
                iteradorDeNoticias.remove();
            }
        }
        */
        noticias.removeIf(noticia -> this.tieneLaNoticiaTodasLasCategorias(noticia, conCategorias));
    }

    /**
     * post: Indica si la noticia dada tiene asociadas TODAS las categorías
     * de la lista de categorías dada.
     * @param noticia: noticia recibida.
     * @param conCategorias: lista de categorías a revisar.
     * @return verdadero si la noticia tiene todas las categorías.
     */
    public boolean tieneLaNoticiaTodasLasCategorias(Noticia noticia, List<Categoria> conCategorias) {
        Validaciones.validarNotNull(noticia, "noticia");
        Validaciones.validarNotNull(conCategorias, "conCategorias");
        for (Categoria categoria: conCategorias) {
            if ((categoria != null) && (!noticia.categorias().contains(categoria))) {
                return false;
            }
        }
        return true;
    }
}
