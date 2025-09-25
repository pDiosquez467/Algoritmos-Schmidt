package examenes.parciales.imagen;

import validaciones.Validaciones;

public class Pixel {
    private final Componente rojo;
    private final Componente verde;
    private final Componente azul;

    public Pixel(int valorRojo, int valorVerde, int valorAzul) {
        rojo  = new Componente(Color.ROJO, valorRojo);
        verde = new Componente(Color.VERDE, valorVerde);
        azul  = new Componente(Color.AZUL, valorAzul);
    }

    public Componente getRojo() {
        return rojo;
    }

    public Componente getVerde() {
        return verde;
    }

    public Componente getAzul() {
        return azul;
    }

    public void aplicarFiltroDeBrillo(int x) {
        rojo.cambiarValorEn(x);
        verde.cambiarValorEn(x);
        azul.cambiarValorEn(x);
    }

}
