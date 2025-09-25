package examenes.parciales.pastillero;

import validaciones.Validaciones;

/**
 * Representa un pastillero que tiene una cantidad de pastillas y dosificación
 * que indica frecuencia de toma de las mismas.
 */
public class Pastillero {

    private int cantidadDePastillas;
    private final int frecuenciaDeToma;
    private final int[] horarios;
    private int indiceProximaToma;

    /**
     * Constructor.
     * @param cantidadDePastillas: cantidad de pastillas totales.
     * @param frecuenciaDeToma: frecuencia de toma las pastillas. Debe ser un
     *                        número entero en el intervalo [1, 24].
     */
    public Pastillero(int cantidadDePastillas, int frecuenciaDeToma) {
        Validaciones.validarNumeroMayorACero(cantidadDePastillas, "'cantidadDePastillas' debe ser > 0");
        Validaciones.validarNumeroEntre(frecuenciaDeToma, 1, 24, "Debe ser " +
                "1 <= 'frecuenciaDeToma' <= 24");
        this.cantidadDePastillas = cantidadDePastillas;
        this.frecuenciaDeToma    = frecuenciaDeToma;
        this.horarios            = new int[cantidadDePastillas];
        this.indiceProximaToma   = 0;
    }

    // === MÉTODOS DE COMPORTAMIENTO (PÚBLICOS) ===

    /**
     * Devuelve la cantidad de pastillas restantes en el pastillero.
     * @return cantidad de pastillas restantes.
     */
    public int cantidadDePastillasRestantes() {
        return cantidadDePastillas;
    }

    public void tomarPastilla(int horaDelDia) {
        Validaciones.validarNumeroEntre(horaDelDia, 0, 23, "Debe ser 0 <= 'horaDelDia' <= 23");
        validarDisponibilidadDePastillas();
        cantidadDePastillas--;
        horarios[indiceProximaToma++] = horaDelDia;
    }

    /**
     * Devuelve el horario de la próxima toma de pastillas.
     * @return horario de la proxima toma.
     */
    public int horaProximaToma() {
        validarInicioTratamiento();
        int horaUltimaToma = horarios[indiceProximaToma - 1];
        return (horaUltimaToma + frecuenciaDeToma) % 24;
    }

    /**
     * Devuelve la cantidad de veces que fue tomada una pastilla
     * habiendo pasado más tiempo que el indicado.
     * @return cantidad de tomas a deshoras.
     */
    public int cantidadDeTomasADeshora() {
        int total = 0;
        int[] horariosIdeales = obtenerHorariosDeToma();

        for (int i = 0; i < indiceProximaToma; i++) {
            if (horarios[i] > horariosIdeales[i]) {
                total++;
            }
        }
        return total;
    }

    /**
     * Indica si el pastillero está vacío.
     * @return verdadero si el pastillero no tiene más pastillas.
     */
    public boolean estaVacio() {
        return cantidadDePastillas == 0;
    }

    // === HELPERS PRIVADOS ===

    /**
     * Indica si el tratamiento se ha iniciado.
     * @return verdadero si se ha tomado al menos una pastilla.
     */
    private boolean seHaIniciadoElTratamiento() {
        return indiceProximaToma > 0;
    }

    /**
     * Validación de disponibilidad de pastillas.
     */
    private void validarDisponibilidadDePastillas() {
        if (estaVacio()) {
            throw new RuntimeException("El pastillero está vacío");
        }
    }

    /**
     * Validación de inicio del tratamiento.
     */
    private void validarInicioTratamiento() {
        if (!seHaIniciadoElTratamiento()) {
            throw new RuntimeException("Aún no se han tomado pastillas");
        }
    }

    /**
     * Devuelve un arreglo con los horarios correctos de toma de pastillas.
     * En caso de no haber tomado ninguna, devuelve un arreglo vacío.
     * @return el arreglo de horarios de toma correctos.
     */
    private int[] obtenerHorariosDeToma() {
        int[] horariosIdeales = new int[indiceProximaToma];
        int horarioPrimerToma = horarios[0];
        for (int i = 0; i < indiceProximaToma; i++) {
            horariosIdeales[i] = (horarioPrimerToma + frecuenciaDeToma * i) % 24;
        }
        return horariosIdeales;
    }

}
