package semana03.ejercicios.utils;

import semana03.ejercicios.nivel03.cartas.Carta;

import java.util.List;

public class Validaciones {

    public static void validarNumeroMayorACero(Object value, String mensaje) {
        double numero = 0.0;
        if (value instanceof Integer) {
            numero = Double.valueOf((Integer) value);
        }

        if (value instanceof Double) {
            numero = (Double) value;
        }

        if (numero <= 0) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static void validarNumeroDistintoCero(Integer value, String mensaje) {
        if (value == 0) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static void validarNotNull(Object value, String mensaje) {
        if (value == null) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static void validarNotBlank(String value, String mensaje) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static void validarNumeroEntre(Integer value, Integer desde, Integer hasta, String mensaje) {
        if (value < desde || value > hasta) {
            throw new IllegalArgumentException(mensaje);
        }
    }

     public static void validarNoVacio(List<Object> values, String mensaje) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
     }

     public static void validarVerdadero(boolean value, String mensaje) {
        if (!value) {
            throw new IllegalArgumentException(mensaje);
        }
     }

    public static void validarFalso(boolean value, String mensaje) {
        if (!value) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
