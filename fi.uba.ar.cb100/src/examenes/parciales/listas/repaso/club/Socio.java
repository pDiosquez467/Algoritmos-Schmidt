package examenes.parciales.listas.repaso.club;

import validaciones.Validaciones;

/**
 * Modela un socio del club social.
 */
public class Socio {

    private final String grupoFamiliar;
    private boolean tuvoDeuda;

    /**
     * post: Inicializa un socio con el grupo familiar dado y sin deudas.
     * @param grupoFamiliar: el grupo familiar del socio.
     */
    public Socio(String grupoFamiliar) {
        Validaciones.validarNotNull(grupoFamiliar, "grupoFamiliar");
        this.grupoFamiliar = grupoFamiliar;
        this.tuvoDeuda = false;
    }

    /**
     * post: Devuelve el grupo familiar del socio.
     * @return el grupo familiar de pertenencia.
     */
    public String getGrupoFamiliar() {
        return grupoFamiliar;
    }

    /**
     * post: Indica si el socio tuvo deuda.
     * @return verdadero si tuvo deuda.
     */
    public boolean tuvoDeuda() {
        return tuvoDeuda;
    }

    /**
     * post: Modifica el estado del socio, indicando si tuvo deuda.
     * @param tuvoDeuda: ¿tuvo deuda?
     */
    public void setTuvoDeuda(boolean tuvoDeuda) {
        this.tuvoDeuda = tuvoDeuda;
    }
}
