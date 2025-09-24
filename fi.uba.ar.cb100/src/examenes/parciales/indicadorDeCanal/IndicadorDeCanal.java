package examenes.parciales.indicadorDeCanal;

import validaciones.Validaciones;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndicadorDeCanal {
    private Canal[] canales;
    private final int[] indicesDeCanales;

    public IndicadorDeCanal(Canal canalMinimo, Canal canalMaximo) {
        Validaciones.validarNotNull(canalMinimo, "'canalMinimo' debe ser no nulo");
        Validaciones.validarNotNull(canalMaximo, "'canalMaximo' debe ser no nulo");
        Validaciones.validarNumeroMenorA(canalMinimo.getNumero(), canalMaximo.getNumero(), "Debe ser " +
                "'canalMinimo' < 'canalMaximo'");
        inicializarCanales(canalMinimo, canalMaximo);
        this.indicesDeCanales = new int[]{0, 0}; // (indiceActual, indicePrevio)
    }

    private void inicializarCanales(Canal canalMinimo, Canal canalMaximo) {
        int longitudDeCanales = canalMaximo.getNumero() - canalMinimo.getNumero() + 1;
        canales = new Canal[longitudDeCanales];
        canales[0] = canalMinimo;
        canales[longitudDeCanales-1] = canalMaximo;
        for (int i = 1; i < canales.length - 1; i++) {
            canales[i] = new Canal(canalMinimo.getNumero() + i);
        }
    }

    public int contar() {
        return (int) Arrays.stream(canales)
                .filter(Canal::estaActivo)
                .count();
    }

    public int obtener() {
        int indiceCanalActual = indicesDeCanales[0];
        return canales[indiceCanalActual].getNumero();
    }

    public void seleccionar(Canal canal) {
        validarCanal(canal);
        int nuevoIndiceDeCanal = obtenerIndiceDelCanal(canal);
        int indiceCanalActual = indicesDeCanales[0];
        actualizarIndicesDeCanales(nuevoIndiceDeCanal, indiceCanalActual);
    }

    private int obtenerIndiceDelCanal(Canal canal) {
        for (int i = 0; i < canales.length; i++) {
            if (canales[i].equals(canal)) {
                return i;
            }
        }
        throw new NoSuchElementException("No existe tal canal");
    }

    private void validarCanal(Canal canal) {
        if (!estaEnRangoDeCanales(canal)) {
            throw new RuntimeException("El canal no es válido");
        }
    }

    private boolean estaEnRangoDeCanales(Canal canal) {
        int numeroDelCanal = canal.getNumero();
        return (canales[0].getNumero() < numeroDelCanal) &&
                (numeroDelCanal < canales[canales.length-1].getNumero());
    }

    public void avanzar() {
        int indiceCanalActual = indicesDeCanales[0];
        actualizarIndicesDeCanales((indiceCanalActual + 1) % canales.length, indiceCanalActual);
    }

    public void retroceder() {
        int indiceCanalActual = indicesDeCanales[0];
        actualizarIndicesDeCanales((indiceCanalActual - 1) % canales.length, indiceCanalActual);

    }

    public void volver() {
        int indiceActual = indicesDeCanales[0];
        int indicePrevio = indicesDeCanales[1];
        actualizarIndicesDeCanales(indicePrevio, indiceActual);
    }

    private void actualizarIndicesDeCanales(int nuevoIndiceActual, int nuevoIndicePrevio) {
        indicesDeCanales[0] = nuevoIndiceActual;
        indicesDeCanales[1] = nuevoIndicePrevio;
    }
}
