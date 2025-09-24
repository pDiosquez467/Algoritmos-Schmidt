package examenes.parciales.indicadorDeCanal;

import java.util.Arrays;

public class IndicadorDeCanal {

    // TODO: 1. Crear arr de canales
    // TODO: 2. Validar límites en 'avanzar' y 'retroceder'
    // TODO: 3. Revisar la lógica de 'seleccionar' para revisar que sea un canal válido.
    // TODO: 4. Revisar la lógica de 'volver' y ver si tiene sentido. (?)
    // TODO: 5. Revisar que no se rompa el encapsulamiento.
    // TODO: 6. Decidir qué hacer con los límites de los canales al 'avanzar' o 'retroceder' (romper o %)

    private final Canal canalMinimo;
    private final Canal canalMaximo;
    private Canal[] canales;
    private Canal canalActual;
    private Canal canalPrevio;
    private int indiceProximoCanal;

    public IndicadorDeCanal(Canal canalMinimo, Canal canalMaximo) {
        this.canalMinimo = canalMinimo;
        this.canalMaximo = canalMaximo;
        this.canalActual = canalMinimo;
        this.canalPrevio = null;
        this.indiceProximoCanal = 0;
    }

    public int contar() {
        return (int) Arrays.stream(canales)
                .filter(Canal::estaActivo)
                .count();
    }

    public int obtener() {
        return canalActual.getNumero();
    }

    public void seleccionar(Canal canal) {
        canalActual = canal;
    }

    public void avanzar() {
        canalActual = canales[++indiceProximoCanal];
    }

    public void retroceder() {
        canalActual = canales[--indiceProximoCanal];
    }

    public void volver() {
        Canal temp = canalActual;
        canalActual = canalPrevio;
        canalPrevio = temp;
    }
}
