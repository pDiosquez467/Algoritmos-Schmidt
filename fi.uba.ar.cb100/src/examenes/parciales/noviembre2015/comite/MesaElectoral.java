package examenes.parciales.noviembre2015.comite;

import validaciones.Validaciones;

import java.util.HashSet;
import java.util.Set;

public class MesaElectoral {

    private final int numero;
    private final Set<Voto> votos;

    /**
     * post: Inicializa una mesa electoral con el número indicado y sin
     * votos.
     */
    public MesaElectoral(int numero) {
        this.numero = numero;
        this.votos  = new HashSet<>();
    }

    /**
     * post: Devuelve el número de mesa.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * post: Devuelve todos los votos (válidos + recurridos).
     */
    public Set<Voto> getVotos() {
        return new HashSet<>(this.votos);
    }

    /**
     * post: Devuelve los votos que fueron recurridos.
     */
    public Set<Voto> getVotosRecurridos() {
        return null;
    }

    public boolean esVotoValido(Voto voto) {
        Validaciones.validarNotNull(voto, "voto");
        return !getVotosRecurridos().contains(voto);
    }

}
