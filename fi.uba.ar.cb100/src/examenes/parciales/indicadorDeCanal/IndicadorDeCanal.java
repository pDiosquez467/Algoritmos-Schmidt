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
        Validaciones.validarNumeroMenorA(canalMinimo.getNumero(), canalMaximo.getNumero(),
                "El canal mínimo debe ser menor al canal máximo");

        inicializarCanales(canalMinimo, canalMaximo);
        this.indicesDeCanales = new int[]{0, 0}; // [indiceActual, indicePrevio]
    }

    // === MÉTODOS PÚBLICOS ===

    /**
     * Cuenta la cantidad de canales activos en el rango.
     */
    public int contar() {
        return (int) Arrays.stream(canales)
                .filter(Canal::estaActivo)
                .count();
    }

    /**
     * Obtiene el número del canal actualmente seleccionado.
     */
    public int obtener() {
        int indiceCanalActual = indicesDeCanales[0];
        return canales[indiceCanalActual].getNumero();
    }

    /**
     * Obtiene el canal actualmente seleccionado.
     */
    public Canal obtenerCanalActual() {
        return canales[indicesDeCanales[0]];
    }

    /**
     * Selecciona un canal específico dentro del rango.
     *
     * @param canal El canal a seleccionar
     * @throws IllegalArgumentException si el canal no está en el rango
     * @throws NoSuchElementException si el canal no existe en el array
     */
    public void seleccionar(Canal canal) {
        validarCanal(canal);
        int nuevoIndiceDeCanal = obtenerIndiceDelCanal(canal);
        int indiceCanalActual = indicesDeCanales[0];
        actualizarIndicesDeCanales(nuevoIndiceDeCanal, indiceCanalActual);
    }

    /**
     * Avanza al siguiente canal en forma circular.
     */
    public void avanzar() {
        int indiceCanalActual = indicesDeCanales[0];
        int nuevoIndice = (indiceCanalActual + 1) % canales.length;
        actualizarIndicesDeCanales(nuevoIndice, indiceCanalActual);
    }

    /**
     * Retrocede al canal anterior en forma circular.
     */
    public void retroceder() {
        int indiceCanalActual = indicesDeCanales[0];
        // Corrección: manejo correcto de índices negativos
        int nuevoIndice = (indiceCanalActual - 1 + canales.length) % canales.length;
        actualizarIndicesDeCanales(nuevoIndice, indiceCanalActual);
    }

    /**
     * Vuelve al canal previamente seleccionado.
     */
    public void volver() {
        int indiceActual = indicesDeCanales[0];
        int indicePrevio = indicesDeCanales[1];
        actualizarIndicesDeCanales(indicePrevio, indiceActual);
    }

    /**
     * Obtiene todos los canales en el rango.
     */
    public Canal[] getCanales() {
        return Arrays.copyOf(canales, canales.length);
    }

    // === HELPERS PRIVADOS ===

    private void inicializarCanales(Canal canalMinimo, Canal canalMaximo) {
        int min = canalMinimo.getNumero();
        int max = canalMaximo.getNumero();
        int longitudDeCanales = max - min + 1;
        canales = new Canal[longitudDeCanales];

        if (longitudDeCanales == 1) {
            canales[0] = canalMinimo;
            // Verificar que el canal mínimo y máximo sean el mismo
            if (min != max) {
                throw new IllegalArgumentException("El rango de canales es inconsistente");
            }
            return;
        }

        canales[0] = canalMinimo;
        canales[longitudDeCanales - 1] = canalMaximo;
        for (int i = 1; i < longitudDeCanales - 1; i++) {
            canales[i] = new Canal(min + i);
        }
    }

    private int obtenerIndiceDelCanal(Canal canal) {
        int numeroCanal = canal.getNumero();
        int minNumero = canales[0].getNumero();
        int maxNumero = canales[canales.length - 1].getNumero();

        if (numeroCanal < minNumero || numeroCanal > maxNumero) {
            throw new NoSuchElementException("El canal " + numeroCanal + " no existe en el rango");
        }

        int indiceCalculado = numeroCanal - minNumero;
        if (indiceCalculado < canales.length && canales[indiceCalculado].equals(canal)) {
            return indiceCalculado;
        }

        for (int i = 0; i < canales.length; i++) {
            if (canales[i].equals(canal)) {
                return i;
            }
        }
        throw new NoSuchElementException("No existe tal canal");
    }

    private void validarCanal(Canal canal) {
        if (canal == null) {
            throw new IllegalArgumentException("El canal no puede ser nulo");
        }
        if (!estaEnRangoDeCanales(canal)) {
            throw new IllegalArgumentException("El canal " + canal.getNumero() +
                    " no está en el rango válido [" + canales[0].getNumero() +
                    " - " + canales[canales.length-1].getNumero() + "]");
        }
    }

    private boolean estaEnRangoDeCanales(Canal canal) {
        int numeroDelCanal = canal.getNumero();
        int min = canales[0].getNumero();
        int max = canales[canales.length-1].getNumero();
        return numeroDelCanal >= min && numeroDelCanal <= max;
    }

    private void actualizarIndicesDeCanales(int nuevoIndiceActual, int nuevoIndicePrevio) {
        indicesDeCanales[0] = nuevoIndiceActual;
        indicesDeCanales[1] = nuevoIndicePrevio;
    }

    /**
     * Verifica si un canal está presente en el array de canales.
     */
    private boolean contieneCanal(Canal canal) {
        return Arrays.asList(canales).contains(canal);
    }
}
