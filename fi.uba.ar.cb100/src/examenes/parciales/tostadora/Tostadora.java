package examenes.parciales.tostadora;

import validaciones.Validaciones;

import java.util.Arrays;

public class Tostadora {

    private RanuraDeTostadora[] ranurasDeTostadora;
    private EstadoDeTostadora estadoDeTostadora;
    private int nivelDeCalor;

    public Tostadora(int cantidadDeRanuras) {
        Validaciones.validarNumeroMayorACero(cantidadDeRanuras, "'cantidad de ranuras' debe ser mayor > 0");
        inicializarRanuras(cantidadDeRanuras);
        estadoDeTostadora = EstadoDeTostadora.APAGADA;
        nivelDeCalor = 0;
    }

    // --- Métodos de comportamiento ---

    public void encender() {
        encender(nivelDeCalor);
    }

    public void encender(int nivelDeCalor) {
        Validaciones.validarVerdadero(!estaEncendida(), "La tostadora ya está encendida");
        setNivelDeCalor(nivelDeCalor);
        estadoDeTostadora = EstadoDeTostadora.ENCENDIDA;
    }

    public void apagar() {
        Validaciones.validarVerdadero(estaEncendida(), "La tostadora está apagada");
        estadoDeTostadora = EstadoDeTostadora.APAGADA;
        liberarRanuras();
    }

    public void iniciarTostado(int numeroDeRanura) {
        Validaciones.validarNumeroEntre(numeroDeRanura, 1, ranurasDeTostadora.length, "");
        validarEstadoEncendido();
        RanuraDeTostadora ranura = obtenerRanura(numeroDeRanura);
        ranura.ocuparParaTostar();
    }

    public void finalizarTostado(int numeroDeRanura) {
        Validaciones.validarNumeroEntre(numeroDeRanura, 1, ranurasDeTostadora.length, "");
        validarEstadoEncendido();
        RanuraDeTostadora ranura = obtenerRanura(numeroDeRanura);
        ranura.finalizarTostado();
    }

    public int contarRanurasLibres() {
        return (int) Arrays.stream(ranurasDeTostadora)
                .filter(RanuraDeTostadora::estaLibre)
                .count();
    }

    public RanuraDeTostadora obtenerRanuraQueMasTosto() {
        RanuraDeTostadora ranuraDeTostadoraMasUsada = null;
        for (RanuraDeTostadora ranuraDeTostadora : ranurasDeTostadora) {
            if ((ranuraDeTostadoraMasUsada == null) ||
                    (ranuraDeTostadora.getCantidadDePanesTostados() > ranuraDeTostadoraMasUsada.getCantidadDePanesTostados())) {
                ranuraDeTostadoraMasUsada = ranuraDeTostadora;
            }
        }
        return ranuraDeTostadoraMasUsada;
    }

    // --- Getters y Setters ---

    public EstadoDeTostadora getEstadoDeTostadora() {
        return estadoDeTostadora;
    }

    public int getNivelDeCalor() {
        return nivelDeCalor;
    }

    public void setNivelDeCalor(int nivelDeCalor) {
        Validaciones.validarNumeroEntre(nivelDeCalor, 1, 10, "Debe ser 1 <= 'nivel de calor' <= 10");
        this.nivelDeCalor = nivelDeCalor;
    }

    // --- Validaciones ---

    private void validarEstadoEncendido() {
        if (!estaEncendida()) {
            throw new RuntimeException("La tostadora está apagada");
        }
    }

    private void inicializarRanuras(int cantidadDeRanuras) {
        this.ranurasDeTostadora = new RanuraDeTostadora[cantidadDeRanuras];
        for (int i = 0; i < ranurasDeTostadora.length; i++) {
            ranurasDeTostadora[i] = new RanuraDeTostadora(i+1);
        }
    }

    private void liberarRanuras() {
        for (RanuraDeTostadora ranuraDeTostadora: ranurasDeTostadora) {
            ranuraDeTostadora.finalizarTostado();
        }
    }

    private boolean estaEncendida() {
        return estadoDeTostadora.equals(EstadoDeTostadora.ENCENDIDA);
    }

    private RanuraDeTostadora obtenerRanura(int numeroDeRanura) {
        Validaciones.validarNumeroEntre(numeroDeRanura, 1, ranurasDeTostadora.length, "Debe ser " +
                " 1 <= 'número de ranura' <= '# de ranuras'");
        return ranurasDeTostadora[numeroDeRanura-1];
    }
}