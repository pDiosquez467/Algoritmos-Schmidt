package examenes.parciales.junio2014.examen;

import validaciones.Validaciones;

import java.util.Objects;

public class EjercicioDeExamen {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final int numero;
    private double porcentajeDeCorreccion;// Cuán correcto es...
    private boolean estaCorregido;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public EjercicioDeExamen(int numero) {
        Validaciones.validarNumeroMayorACero(numero, "numero");
        this.numero = numero;
        this.porcentajeDeCorreccion = 0;
        this.estaCorregido          = false;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EjercicioDeExamen that)) return false;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void puntuar(double porcentajeDeCorreccion) {
        Validaciones.validarNumeroEntre(porcentajeDeCorreccion, 0, 100, "porcentajeDeCorreccion");
        this.porcentajeDeCorreccion = porcentajeDeCorreccion;
        this.estaCorregido = true;
    }
    
    public boolean aunSinCorregir() {
        return !this.estaCorregido;
    }
    
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    
    public int getNumero() {
        return numero;
    }

    public double getPorcentajeDeCorreccion() {
        if (this.aunSinCorregir()) {
            throw new RuntimeException("El ejercicio está sin corregir");
        }
        return porcentajeDeCorreccion;
    }

}
