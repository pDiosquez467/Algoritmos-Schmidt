package examenes.parciales.restaurante;

import validaciones.Validaciones;

public class Mozo {
    private final String nombre;
    private double propinaAcumulada;

    public Mozo(String nombre) {
        Validaciones.validarNotNull(nombre,
                "'nombre' debe ser no nulo");
        Validaciones.validarNotBlank(nombre,
                "'nombre' debe ser no vacío");
        this.nombre           = nombre;
        this.propinaAcumulada = 0;
    }

    public void guardarPropina(double nuevaPropina) {
        Validaciones.validarNumeroMayorACero(nuevaPropina,
                "'nuevaPropina' debe ser > 0");
        propinaAcumulada += nuevaPropina;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPropinaAcumulada() {
        return propinaAcumulada;
    }
}
