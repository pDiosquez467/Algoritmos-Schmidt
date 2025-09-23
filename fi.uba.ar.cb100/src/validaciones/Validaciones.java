package validaciones;

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

    public static <T extends Number & Comparable<T>> void validarNumeroMayorA(T value, T otro, String mensaje) {
        if (value.compareTo(otro) <= 0) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static <T extends Number & Comparable<T>> void validarNumeroMenorA(T value, T otro, String mensaje) {
        if (value.compareTo(otro) >= 0) {
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

    public static <T extends Number & Comparable<T>> void validarNumeroEntre(T value, T desde, T hasta, String mensaje) {
        if (value.compareTo(desde) < 0 || value.compareTo(hasta) > 0) {
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
