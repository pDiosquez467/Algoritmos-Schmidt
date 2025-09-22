package examenes.parciales.bateria;

import validaciones.Validaciones;

/**
 * Representa una batería de auto eléctrico.
 * Invariantes:
 *  0 <= cargaActual <= capacidadMaxima
 *  capacidadMaxima > 0
 *  consumoPromedioPorKilometro > 0
 *
 * Diseño:
 *  - cargar(double): añade hasta completar la capacidad. No lanza excepción si la batería ya estaba llena.
 *  - desplazar(double): consume energía; lanza IllegalStateException si no hay carga suficiente.
 *
 * Excepciones:
 *  - IllegalArgumentException: argumentos inválidos (<= 0, NaN, Infinity)
 *  - IllegalStateException: operación inválida por el estado (p. ej. desplazar sin carga suficiente)
 */
public final class Bateria {
    private static final double EPS = 1e-9;
    private static final String MSG_MAYOR_CERO = "El valor debe ser mayor a cero";
    private static final String MSG_BATERIA_VACIA = "La batería está vacía";
    private static final String MSG_NO_HAY_CARGA_SUFICIENTE = "No hay carga suficiente para la distancia solicitada";

    private final double capacidadMaxima;
    private final double consumoPromedioPorKilometro;
    private double cargaActual;

    public Bateria(double capacidadMaxima, double consumoPromedioPorKilometro) {
        Validaciones.validarNumeroMayorACero(capacidadMaxima, MSG_MAYOR_CERO);
        Validaciones.validarNumeroMayorACero(consumoPromedioPorKilometro, MSG_MAYOR_CERO);
        this.capacidadMaxima = capacidadMaxima;
        this.consumoPromedioPorKilometro = consumoPromedioPorKilometro;
        this.cargaActual = 0.0;
    }

    /**
     * Añade carga. Si la cantidad supera el espacio disponible, la batería queda llena.
     * No lanza excepción si la batería ya está llena; simplemente no modifica el estado.
     *
     * @param carga cantidad de kWh a agregar (debe ser > 0)
     * @throws IllegalArgumentException si carga <= 0 o es NaN/Infinity
     */
    public void cargar(double carga) {
        Validaciones.validarNumeroMayorACero(carga, MSG_MAYOR_CERO);
        if (estaLlena()) {
            return;
        }
        double nuevaCarga = this.cargaActual + carga;
        this.cargaActual = Math.min(capacidadMaxima, nuevaCarga);
    }

    /**
     * Consume la carga según la distancia. Lanza IllegalStateException si
     * no hay carga suficiente para completar la distancia solicitada.
     *
     * @param distanciaEnKilometros distancia a recorrer (>0)
     * @throws IllegalArgumentException si distancia <= 0 o es NaN/Infinity
     * @throws IllegalStateException si no hay carga suficiente
     */
    public void desplazar(double distanciaEnKilometros) {
        Validaciones.validarNumeroMayorACero(distanciaEnKilometros, MSG_MAYOR_CERO);
        if (estaVacia()) {
            throw new IllegalStateException(MSG_BATERIA_VACIA);
        }
        if (!hayCargaSuficiente(distanciaEnKilometros)) {
            throw new IllegalStateException(MSG_NO_HAY_CARGA_SUFICIENTE);
        }
        double consumoDeCarga = distanciaEnKilometros * consumoPromedioPorKilometro;
        this.cargaActual = Math.max(0.0, this.cargaActual - consumoDeCarga);
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public double getAutonomia() {
        // consumoPromedioPorKilometro > 0 garantizado por constructor
        return cargaActual / consumoPromedioPorKilometro;
    }

    public boolean estaVacia() {
        return cargaActual <= EPS;
    }

    public boolean estaLlena() {
        return Math.abs(cargaActual - capacidadMaxima) <= EPS;
    }

    private boolean hayCargaSuficiente(double distanciaEnKilometros) {
        return getAutonomia() + EPS >= distanciaEnKilometros;
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getConsumoPromedioPorKilometro() {
        return consumoPromedioPorKilometro;
    }

}
