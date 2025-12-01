package examenes.parciales.mayo2017.historiador;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Historiador {

    /**
     * post: Crea y devuelve una nueva línea de tiempo con el nombre indicado y con los
     * hitos que tienen las líneas de tiempo dadas en 'lineasDeTiempoPorCombinar', que
     * ocurrieron en los años dados en 'aniosElegidos'.
     */
    public LineaDeTiempo crearLineaDeTiempoCombinandoHitos(String nombre,
                                                           List<LineaDeTiempo> lineasDeTiempoPorCombinar,
                                                           List<Integer> aniosElegidos) {
        Validaciones.validarNotNull(nombre, "nombre");
        Validaciones.validarNotNull(lineasDeTiempoPorCombinar, "lineasDeTiempoPorCombinar");
        Validaciones.validarNotNull(aniosElegidos, "aniosElegidos");
        Set<Hito> hitosUnicos = new HashSet<>();
        for (LineaDeTiempo linea: lineasDeTiempoPorCombinar) {
            List<Hito> hitos = obtenerHitosQueOcurrieronEnLosAnios(linea, aniosElegidos);
            hitosUnicos.addAll(hitos);
        }

        LineaDeTiempo lineaDeTiempo = new LineaDeTiempo(nombre);
        for (Hito hito: hitosUnicos) {
            lineaDeTiempo.incluir(hito);
        }
        return lineaDeTiempo;
    }

    /**
     * post: Devuelve una lista con todos los hitos de la línea de tiempo que ocurrieron
     * en los años indicados.
     */
    public List<Hito> obtenerHitosQueOcurrieronEnLosAnios(LineaDeTiempo lineaDeTiempo, List<Integer> aniosElegidos) {
        Validaciones.validarNotNull(lineaDeTiempo, "lineaDeTiempo");
        Validaciones.validarNotNull(aniosElegidos, "aniosElegidos");
        List<Hito> hitos = new ListaSimplementeEnlazada<>();
        for (Hito hito: lineaDeTiempo.getHitos()) {
            if (ocurrioEnLosAnios(hito, aniosElegidos)) {
                hitos.add(hito);
            }
        }
        return hitos;
    }

    /**
     * post: Indica si el hito dado ocurrió en los años dados.
     */
    public boolean ocurrioEnLosAnios(Hito hito, List<Integer> anios) {
        Validaciones.validarNotNull(hito, "hito");
        Validaciones.validarNotNull(anios, "anios");
        for (Integer anio: anios) {
            if (anio == hito.anio()) {
                return true;
            }
        }
        return false;
    }
}
