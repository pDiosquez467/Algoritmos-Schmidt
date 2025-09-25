package examenes.parciales.imagen;

import validaciones.Validaciones;

import java.util.Arrays;
import java.util.Comparator;

public class Imagen2D {
    private int ancho;
    private int alto;
    private Pixel[][] pixeles;

    public Imagen2D(int ancho, int alto) {
        Validaciones.validarNumeroMayorACero(ancho,
                "'ancho' debe ser mayor a cero");
        Validaciones.validarNumeroMayorACero(alto,
                "'alto' debe ser mayor a cero");
        this.ancho = ancho;
        this.alto = alto;

        this.pixeles = new Pixel[ancho][alto];
        for (int i = 0; i < pixeles.length; i++) {
            for (int j = 0; j < pixeles[0].length; j++) {
                pixeles[i][j] = new Pixel(0,0,0);
            }
        }
    }

    public void aplicarFiltroDeBrillo(int x) {
        for (Pixel[] pixel : pixeles) {
            for (int j = 0; j < pixeles[0].length; j++) {
                pixel[j].aplicarFiltroDeBrillo(x);
            }
        }
    }

    public Pixel obtenerPixelMasRojo() {
       return obtenerMaxPixel((pixel, otro) -> pixel.getRojo().getValor() - otro.getRojo().getValor());
    }

    public Pixel obtenerPixelMasVerde() {
       return obtenerMaxPixel((pixel, otro) -> pixel.getVerde().getValor() - otro.getVerde().getValor());
    }

    public Pixel obtenerPixelMasAzul() {
       return obtenerMaxPixel((pixel, otro) -> pixel.getAzul().getValor() - otro.getAzul().getValor());
    }

    public void invertirImagenHorizontal() {
        int alto = pixeles.length;
        int ancho = pixeles[0].length;
        Pixel[][] invertidos = new Pixel[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                int indiceColumnaOriginal = ancho - 1 - j;
                invertidos[i][j] = pixeles[i][indiceColumnaOriginal];
            }
        }
        pixeles = invertidos;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public Pixel[][] getPixeles() {
        Pixel[][] copias = new Pixel[ancho][alto];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                Pixel almacenado = pixeles[i][j];
                copias[i][j] = new Pixel(
                        almacenado.getRojo().getValor(),
                        almacenado.getVerde().getValor(),
                        almacenado.getAzul().getValor()
                );
            }
        }
        return copias;
    }

    private Pixel obtenerMaxPixel(Comparator<Pixel> comparador) {
        if (pixeles == null || pixeles.length == 0 || pixeles[0].length == 0) {
            return null;
        }
        Pixel maxPixel = pixeles[0][0];
        for (Pixel[] fila : pixeles) {
            for (Pixel p : fila) {
                if (comparador.compare(p, maxPixel) > 0) {
                    maxPixel = p;
                }
            }
        }
        return maxPixel;
    }
}
