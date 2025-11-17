package examenes.parciales.extra.pila.invertidas;

import tdas.pila.teorica.Stack;
import validaciones.Validaciones;

public class PalabrasInvertidas {

    public static void main(String[] args) {
        System.out.println(invertirPalabra("Acá va el texto de prueba"));
    }

    public String invertirCadaPalabra(String frase) {
        Validaciones.validarNotNull(frase, "frase");
        StringBuilder res = new StringBuilder();
        String[] palabras = frase.split(" ");
        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];
            if (!palabra.isEmpty()) {
                res.append(invertirPalabra(palabra));
            }
            if (i < palabras.length - 1) {
                res.append(" ");
            }
        }
        return res.toString().trim();
    }

    public static String invertirPalabra(String palabra) {
        Validaciones.validarNotNull(palabra, "palabra");
        Stack<Character> caracteres = new Stack<>();
        for (int i = 0; i < palabra.length(); i++) {
            caracteres.push(palabra.charAt(i));
        }
        StringBuilder alReves = new StringBuilder();
        while (!caracteres.isEmpty()) {
            alReves.append(caracteres.pop());
        }
        return alReves.toString();
    }
}
