package examenes.parciales.listas.repaso.club;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdministradorClubSocial {

    /**
     * post: Devuelve una nueva lista con los socios, uno por familia,
     * cuyo grupo familiar NO tuvo deudas.
     * @param socios: la lista de socios a evaluar.
     * @return una lista con los socios aptos para descuento.
     */
    public List<Socio> buscarSocioParaDescuento(List<Socio> socios) {
        Validaciones.validarNotNull(socios, "socios");
        Set<String> gruposFamiliares = new HashSet<>();
        Set<String> gruposFamiliaresConDeuda = new HashSet<>();
        for (Socio socio: socios) {
            if (socio == null) continue;
            String grupoFamiliar = socio.getGrupoFamiliar();
            boolean tuvoDeuda = socio.tuvoDeuda();
            gruposFamiliares.add(grupoFamiliar); // Sin repeticiones en el Set
            if (tuvoDeuda) gruposFamiliaresConDeuda.add(grupoFamiliar);
        }
        gruposFamiliares.removeAll(gruposFamiliaresConDeuda);
        return obtenerSociosSegunGrupoFamiliar(socios, gruposFamiliares);
    }

    /**
     * post: Devuelve una lista de socios cuyos grupos familiares son los dados.
     * @param socios: la lista de socios.
     * @param gruposFamiliares: la lista de grupos familiares dada.
     * @return una lista con socios cuyos grupos familiares son los indicados.
     */
    public List<Socio> obtenerSociosSegunGrupoFamiliar(List<Socio> socios, Set<String> gruposFamiliares) {
        Validaciones.validarNotNull(socios, "socios");
        Validaciones.validarNotNull(gruposFamiliares, "gruposFamiliares");
        List<Socio> sociosAptos = new ListaSimplementeEnlazada<>();
        for (String grupoFamiliar: gruposFamiliares) {
            Socio socio = obtenerSocio(socios, grupoFamiliar);
            if (socio == null) continue;
            sociosAptos.add(socio);
        }
        return sociosAptos;
    }

    /**
     * post: Devuelve el primer socio de la lista de socios dada cuyo grupo familiar es el dado.
     * Si no encuentra ninguno, devuelve null.
     * @param socios: la lista de socios a evaluar.
     * @param grupoFamiliar: el grupo familiar indicado.
     * @return un socio con el grupo familiar dado.
     */
    public Socio obtenerSocio(List<Socio> socios, String grupoFamiliar) {
        Validaciones.validarNotNull(socios, "socios");
        Validaciones.validarNotNull(grupoFamiliar, "grupoFamiliar");
        for (Socio socio: socios) {
            if (socio == null) continue;
            if (socio.getGrupoFamiliar().equals(grupoFamiliar)) return socio;
        }
        return null;
    }
}
