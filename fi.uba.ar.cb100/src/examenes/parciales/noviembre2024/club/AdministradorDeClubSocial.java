package examenes.parciales.noviembre2024.club;

import tdas.conjunto.ConjuntoBasico;
import validaciones.Validaciones;

import java.util.List;

public class AdministradorDeClubSocial {

    /**
     * post: Devuelve una nueva lista con los socios, uno por familia,
     * cuyo grupo familiar no tuvo deudas.
     * @param socios: lista de socios.
     */
    public List<Socio> buscarSocioParaDescuento(List<Socio> socios) {
        Validaciones.validarNotNull(socios, "'socios'");

        ConjuntoBasico<Socio> sociosFiltrados =
                this.sociosSet(socios).diferencia(this.sociosConDeudas(socios));
        return sociosFiltrados.elementos().stream().toList();
    }

    /**
     * post: Devuelve un nuevo conjunto (sin repeticiones) con los socios de la lista dada.
     * @param socios: lista de socios.
     * @return un conjunto de socios.
     */
    public ConjuntoBasico<Socio> sociosSet(List<Socio> socios) {
        Validaciones.validarNotNull(socios, "'socios'");
        ConjuntoBasico<Socio> sociosSet = new ConjuntoBasico<>();
        for (Socio socio: socios) {
            if (socio == null) continue;
            sociosSet.agregar(socio);
        }
        return sociosSet;
    }

    /**
     * post: Devuelve un nuevo conjunto con todos los socios de la lista dada
     * que tuvieron deuda.
     * @param socios: lista de socios
     * @return un conjunto de socios morosos.
     */
    public ConjuntoBasico<Socio> sociosConDeudas(List<Socio> socios) {
        Validaciones.validarNotNull(socios, "'socios'");
        ConjuntoBasico<Socio> resultado = new ConjuntoBasico<>();
        for (Socio socio: socios) {
            if (socio == null) continue;
            if (socio.tuvoDeuda()) {
                resultado.agregar(socio);
            }
        }
        return resultado;
    }
}