package semana03.ejercicios.utils;

public class Validaciones {

    public static void validarNumeroMayorACero(Object value, String mensaje) throws Exception{
        double numero = 0.0;
        if (value instanceof Integer) {
            numero = Double.valueOf((Integer) value);
        }

        if (value instanceof Double) {
            numero = (Double) value;
        }

        if (numero <= 0) {
            throw new Exception(mensaje);
        }
    }
}
