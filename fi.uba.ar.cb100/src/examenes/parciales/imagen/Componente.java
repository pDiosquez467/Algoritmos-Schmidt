package examenes.parciales.imagen;

import validaciones.Validaciones;

public class Componente {
    private final Color color;
    private int valor;

    public Componente(Color color, int valor) {
        Validaciones.validarNumeroEntre(valor, 0, 255,
                "El valor de una componente debe estar entre 0 y 255");
        this.color = color;
        this.valor = valor;
    }

    public Color getColor() {
        return color;
    }

    public int getValor() {
        return valor;
    }

    public void cambiarValorEn(int delta) {
        if ((valor + delta) < 0 || (valor + delta) > 255) {
            throw new RuntimeException("El nuevo valor está fuera del rango [0, 255]");
        }
        valor += delta;
    }
}
