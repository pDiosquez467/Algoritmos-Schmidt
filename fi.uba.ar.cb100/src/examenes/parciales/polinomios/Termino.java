package examenes.parciales.polinomios;

import tdas.vector.Vector;
import validaciones.Validaciones;

import java.util.Objects;

public class Termino {
    private final int grado;
    private double coeficiente;

    public Termino(int grado, double coeficiente) {
        Validaciones.validarNumeroMayorOIgualACero(grado, "El grado de un término debe ser mayor o igual a cero");
        this.grado = grado;
        this.coeficiente = coeficiente;
    }

    public int getGrado() {
        return grado;
    }

    public double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(double coeficiente) {
        this.coeficiente = coeficiente;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Termino termino)) return false;
        return grado == termino.grado && Double.compare(coeficiente, termino.coeficiente) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grado, coeficiente);
    }
}
