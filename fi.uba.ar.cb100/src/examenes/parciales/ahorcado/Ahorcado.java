package examenes.parciales.ahorcado;

import validaciones.Validaciones;

public class Ahorcado {
    public enum EstadoDelJuego {
        JUGANDO,
        GANADO,
        PERDIDO,
    }
    private static final String RESPUESTA_DEFAULT = "";

    private final String palabra;
    private final int cantidadDeErroresPermitidos;
    private int getCantidadDeErroresCometidos;
    private EstadoDelJuego estadoDelJuego;

    public Ahorcado(String palabra, int cantidadDeErroresPermitidos) {
        Validaciones.validarNotNull(palabra, "La palabra dada no debe ser null");
        Validaciones.validarNotBlank(palabra, "La palabra dada no debe ser vacía");
        Validaciones.validarNumeroMayorACero(cantidadDeErroresPermitidos, "La cantidad de errores debe ser " +
                "mayor a cero");
        this.palabra                       = palabra.trim().toLowerCase();
        this.cantidadDeErroresPermitidos   = cantidadDeErroresPermitidos;
        this.getCantidadDeErroresCometidos = 0;
        this.estadoDelJuego                = EstadoDelJuego.JUGANDO;
    }

    public int proponerLetra(Character letra) {
        Validaciones.validarNotNull(letra, "La letra dada no debe ser nula");
        if (noSePuedeSeguirJugando()) {
            throw new RuntimeException("No se puede seguir jugando");
        }
        int cantidadDeApariciones = this.cantidadDeApariciones(letra);
        if (cantidadDeApariciones == 0) {
            this.getCantidadDeErroresCometidos++;
        }
        if (getCantidadDeErroresCometidos == cantidadDeErroresPermitidos) {
            estadoDelJuego = EstadoDelJuego.PERDIDO;
        }
        return cantidadDeApariciones;
    }

    public boolean arriesgarPalabra(String palabra) {
        Validaciones.validarNotNull(palabra, "La palabra dada no debe ser nula");
        Validaciones.validarNotBlank(palabra, "La palabra dada no debe ser vacía");
        if(noSePuedeSeguirJugando()) {
            throw new RuntimeException("NO se puede seguir jugando");
        }

        if (this.palabra.equals(palabra.trim().toLowerCase())) {
            estadoDelJuego = EstadoDelJuego.GANADO;
            return true;
        }
        estadoDelJuego = EstadoDelJuego.PERDIDO;
        return false;
    }

    public int cantidadDeLetrasDePalabra() {
        return this.palabra.length();
    }

    public EstadoDelJuego estadoDelJuego() {
        return this.estadoDelJuego;
    }

    public int cantidadDeErroresPermitidos() {
        return this.cantidadDeErroresPermitidos;
    }

    public int getGetCantidadDeErroresCometidos() {
        return this.getCantidadDeErroresCometidos;
    }

    public String conocerPalabra() {
        if (noSePuedeSeguirJugando()) {
            return palabra;
        }
        return RESPUESTA_DEFAULT;
    }

    private int cantidadDeApariciones(Character letra) {
        Validaciones.validarNotNull(letra, "La letra dada no debe ser nula");
        int cantidad = 0;
        for (int i = 0; i < this.palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                cantidad++;
            }
        }
        return cantidad;
    }

    private boolean noSePuedeSeguirJugando() {
        return estadoDelJuego != EstadoDelJuego.JUGANDO;
    }
}
