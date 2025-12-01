package examenes.parciales.junio2015.mensajes;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cuenta {
    private final String nombre;
    private final List<Cuenta> remitentesBloqueados;

    /**
     * post: Inicializa una cuenta con el nombre indicado y sin remitentes
     * bloqueados.
     */
    public Cuenta(String nombre) {
        this.nombre = nombre;
        this.remitentesBloqueados = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve el nombre identificador de la cuenta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Devuelve aquellas cuentas de las que no se desean recibir
     * mensajes.
     */
    public List<Cuenta> getRemitentesBloqueados() {
        return Collections.unmodifiableList(this.remitentesBloqueados);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cuenta cuenta)) return false;
        return Objects.equals(nombre, cuenta.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    /**
     * post: Indica si la cuenta dada está bloqueada.
     */
    public boolean estaBloqueada(Cuenta cuenta) {
        Validaciones.validarNotNull(cuenta, "cuenta");
        return this.remitentesBloqueados.contains(cuenta);
    }
}
