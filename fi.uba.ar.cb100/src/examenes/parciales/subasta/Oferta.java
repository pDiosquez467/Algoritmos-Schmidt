package examenes.parciales.subasta;

import validaciones.Validaciones;

/**
 * Oferta realizada por un oferente identificado por 'numero' con un monto.
 * Se utiliza el equals/hashCode generado por el record (numero + monto).
 */
public record Oferta(int numero, double monto) {

    public Oferta {
        Validaciones.validarNumeroMayorACero(numero, "'numero' debe ser mayor a cero");
        Validaciones.validarNumeroMayorACero(monto, "'monto' debe ser mayor a cero");
    }
}
