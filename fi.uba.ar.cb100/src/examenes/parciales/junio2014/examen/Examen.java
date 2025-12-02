package examenes.parciales.junio2014.examen;

import tdas.vector.Vector;
import tdas.vector.VectorImpl;
import validaciones.Validaciones;

public class Examen {
//INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String corrector;
    private Vector<EjercicioDeExamen> ejercicios;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Examen(String corrector, int totalEjercicios) {
        Validaciones.validarNotNull(corrector, "corrector");
        this.corrector = corrector;
        this.ejercicios = new VectorImpl<>(totalEjercicios);
        for (int i = 0; i < this.ejercicios.size(); i++) {
            this.ejercicios.add(new EjercicioDeExamen(i+1));
        }
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------


    // TODO: Revisar, pues la consigna dice: "puntuar un ejercicio, indicando su porcentaje de corrección [0.0, 100.0]."
    public void puntuar(int numeroDeEjercicio, double porcentajeDeCorreccion) {
        Validaciones.validarNumeroEntre(numeroDeEjercicio, 1, this.ejercicios.size(), "numeroDeEjercicio");
        Validaciones.validarNumeroEntre(porcentajeDeCorreccion, 0, 100, "porcentajeDeCorreccion");
        EjercicioDeExamen ejercicioDeExamen = this.ejercicios.get(numeroDeEjercicio-1);
        ejercicioDeExamen.puntuar(porcentajeDeCorreccion);
    }

    public int cantidadDeEjercicios() {
        return this.ejercicios.size();
    }

    public int cantidadDeEjerciciosCorregidos() {
        int total = 0;
        for (int i = 0; i < this.ejercicios.size(); i++) {
            if (this.ejercicios.get(i).aunSinCorregir()) continue;
            total++;
        }
        return total;
    }

    public double notaFinal() {
        if (cantidadDeEjercicios() > this.cantidadDeEjerciciosCorregidos()) {
            throw new RuntimeException("El examen NO está totalmente corregido");
        }
        double totalPorcentajeDeCorreccion = 0;
        for (int i = 0; i < this.ejercicios.size(); i++) {
            totalPorcentajeDeCorreccion += this.ejercicios.get(i).getPorcentajeDeCorreccion();
        }
        return (totalPorcentajeDeCorreccion / (100 * this.ejercicios.size())) * 10;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public String getCorrector() {
        return corrector;
    }

    public Vector<EjercicioDeExamen> getEjercicios() {
        return ejercicios;
    }
}
