package examenes.parciales.cadena;

import semana03.ejercicios.utils.Validaciones;

public record Eslabon(double ancho, double largo) {
    public Eslabon {
        Validaciones.validarNumeroMayorACero(ancho, "El ancho debe ser mayor a cero");
        Validaciones.validarNumeroMayorACero(largo, "El largo debe ser mayor a cero");
    }
}
