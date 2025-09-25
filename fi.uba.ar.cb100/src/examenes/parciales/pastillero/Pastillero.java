package examenes.parciales.pastillero;

import validaciones.Validaciones;

/**
 * TDA que representa un pastillero con control de tomas y horarios.
 * Gestiona la cantidad de pastillas disponibles y verifica el cumplimiento
 * del tiempo mínimo entre tomas.
 */
public class Pastillero {

    private int cantidadDePastillas;
    private final int frecuenciaDeToma;
    private final int[] horarios;
    private Integer ultimaHoraToma;
    private int indiceProximaToma;

    /**
     * Crea un nuevo pastillero con la cantidad y frecuencia especificadas.
     *
     * @param cantidadDePastillas Cantidad inicial de pastillas (debe ser > 0)
     * @param frecuenciaDeToma Tiempo mínimo entre tomas en horas (debe estar entre 1 y 24)
     * @throws IllegalArgumentException si los parámetros no son válidos
     */
    public Pastillero(int cantidadDePastillas, int frecuenciaDeToma) {
        Validaciones.validarNumeroMayorACero(cantidadDePastillas,
                "La cantidad de pastillas debe ser mayor a cero");
        Validaciones.validarNumeroEntre(frecuenciaDeToma, 1, 24,
                "La frecuencia de toma debe estar entre 1 y 24 horas");
        this.cantidadDePastillas = cantidadDePastillas;
        this.frecuenciaDeToma    = frecuenciaDeToma;
        this.horarios            = new int[cantidadDePastillas];
        this.ultimaHoraToma      = null;
        this.indiceProximaToma   = 0;
    }

    // === MÉTODOS PÚBLICOS ===

    /**
     * Obtiene la cantidad de pastillas restantes.
     *
     * @return Cantidad de pastillas disponibles
     */
    public int cantidadDePastillasRestantes() {
        return cantidadDePastillas;
    }

    /**
     * Registra la toma de una pastilla en la hora especificada.
     *
     * @param horaDelDia Hora de la toma (0-23)
     * @throws IllegalArgumentException si la hora no es válida o si no hay pastillas disponibles
     * @throws IllegalStateException si se intenta tomar antes del tiempo mínimo permitido
     */
    public void tomarPastilla(int horaDelDia) {
        Validaciones.validarNumeroEntre(horaDelDia, 0, 23,
                "La hora debe estar entre 0 y 23");
        validarDisponibilidadDePastillas();
        horarios[indiceProximaToma++] = horaDelDia;
        ultimaHoraToma = horaDelDia;
        cantidadDePastillas--;
    }

    /**
     * Calcula la hora recomendada para la próxima toma.
     *
     * @return Hora de la próxima toma (0-23)
     * @throws IllegalStateException si no se ha iniciado el tratamiento
     */
    public int horaProximaToma() {
        validarInicioTratamiento();
        return (ultimaHoraToma + frecuenciaDeToma) % 24;
    }

    /**
     * Devuelve la cantidad de veces que fue tomada una pastilla
     * habiendo pasado más tiempo que el indicado.
     * @return cantidad de tomas a deshoras.
     */
    public int cantidadDeTomasADeshora() {
        if (horarios.length < 2) {
            return 0; // Se necesitan al menos 2 tomas para evaluar
        }
        int tomasADeshora = 0;

        for (int i = 1; i < indiceProximaToma - 1; i++) {
            int horaTomaActual = horarios[i];
            int horaTomaAnterior = horarios[i-1];

            if (esTomaDeshora(horaTomaActual, horaTomaAnterior)) {
                tomasADeshora++;
            }
        }
        return tomasADeshora;
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
        return ultimaHoraToma != null;
    }

    private boolean esTomaDeshora(int horaTomaActual, int horaTomaAnterior) {
        int horasTranscurridas = calcularHorasTranscurridas(horaTomaAnterior, horaTomaActual);
        return horasTranscurridas < frecuenciaDeToma;
    }

    private int calcularHorasTranscurridas(int horaInicio, int horaFin) {
        if (horaFin >= horaInicio) {
            return horaFin - horaInicio;
        } else {
            // La toma fue al día siguiente
            return (24 - horaInicio) + horaFin;
        }
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

}
