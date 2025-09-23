package examenes.parciales.tostadora;

import validaciones.Validaciones;

import java.util.Objects;

/**
 * Representa una ranura de una tostadora.
 * Contiene un número identificatorio, un estado (libre u ocupada) y la cantidad de panes que tostó.
 */
public class RanuraDeTostadora {
    private final int numero;
    private EstadoDeRanuraDeTostadora estadoDeRanuraDeTostadora;
    private int cantidadDePanesTostados;

    /**
     * Crea una ranura de tostadora, que está unívocamente determinada por su número.
     * @param numero: id de la ranura.
     */
    public RanuraDeTostadora(int numero) {
        Validaciones.validarNumeroMayorACero(numero, "El número de la ranura debe ser mayor a cero");
        this.numero = numero;
        estadoDeRanuraDeTostadora = EstadoDeRanuraDeTostadora.LIBRE;
        cantidadDePanesTostados = 0;
    }

    public void ocuparParaTostar() {
        Validaciones.validarVerdadero(estaLibre(), "La ranura de tostadora está ocupada");
        estadoDeRanuraDeTostadora = EstadoDeRanuraDeTostadora.OCUPADA;
    }

    public void finalizarTostado() {
        Validaciones.validarVerdadero(!estaLibre(), "La ranura de tostadora está libre");
        estadoDeRanuraDeTostadora = EstadoDeRanuraDeTostadora.LIBRE;
        cantidadDePanesTostados++;
    }

    public boolean estaLibre() {
        return estadoDeRanuraDeTostadora.equals(EstadoDeRanuraDeTostadora.LIBRE);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RanuraDeTostadora ranuraDeTostadora)) return false;
        return numero == ranuraDeTostadora.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    public EstadoDeRanuraDeTostadora getEstadoDeRanura() {
        return estadoDeRanuraDeTostadora;
    }

    public int getCantidadDePanesTostados() {
        return cantidadDePanesTostados;
    }

}
