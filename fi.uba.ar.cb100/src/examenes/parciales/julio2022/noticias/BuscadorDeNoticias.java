package examenes.parciales.julio2022.noticias;

import tdas.conjunto.Conjunto;
import validaciones.Validaciones;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class BuscadorDeNoticias {

    /**
     * post: Busca en noticias aquellas que tengan asociada alguna de las
     * categorias dadas en 'conCategorias' y que en el resultado no se repitan
     * categorias en las diferentes noticias.
     */
    public List<Noticia> buscarNoticias(List<Noticia> noticias, List<Categoria> conCategorias) {
        Validaciones.validarNotNull(noticias, "noticias");
        Validaciones.validarNotNull(conCategorias, "conCategorías");

        /*
        Set<Categoria> conCategoriasSet = new Conjunto<>(conCategorias);
        for (Noticia noticia : noticias) {
            boolean tieneCategoria = false;
            boolean tieneCategoriaVista = false;
            for (Categoria cat : noticia.categorias()) {
                if (conCategoriasSet.contains(cat)) tieneCategoria = true;
                if (categoriasVistas.contains(cat)) tieneCategoriaVista = true;
                if (tieneCategoria && tieneCategoriaVista) break;
            }
            if (tieneCategoria && !tieneCategoriaVista) {
                resultado.add(noticia);
                categoriasVistas.addAll(noticia.categorias());
            }
        }
        */

        Set<Noticia> resultado = new Conjunto<>();
        Set<Categoria> categoriasVistas = new Conjunto<>();
        for (Noticia noticia: noticias) {
            if (noticia == null) continue;

            if (tieneLaNoticiaAlgunaCategoria(noticia, conCategorias) &&
                    (!tieneLaNoticiaAlgunaCategoria(noticia, categoriasVistas))) {
                resultado.add(noticia);
                categoriasVistas.addAll(noticia.categorias());
            }
        }

        return resultado.stream().toList();
    }

    /**
     * post: Indica si la noticia tiene alguna categoria en la lista de categorias dada.
     * @param noticia: la noticia a considerar.
     * @param categorias: la lista de categorias dada.
     * @return verdadero si la noticia tiene al menos una categoria en la lista de categorias dada.
     */
    public boolean tieneLaNoticiaAlgunaCategoria(Noticia noticia, Collection<Categoria> categorias) {
        Validaciones.validarNotNull(noticia, "noticia");
        Validaciones.validarNotNull(categorias, "categorías");

        for (Categoria categoria: categorias) {
            if (categoria == null) continue;

            if (noticia.contieneLaCategoria(categoria)) {
                return true;
            }
        }
        return false;
    }
}
