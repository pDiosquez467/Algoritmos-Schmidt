package semana03.ejercicios.nivel03.cartas;

import semana03.ejercicios.utils.Validaciones;

import java.util.NoSuchElementException;

public class Jugador {
    private static final int CAPACIDAD_INICIAL = 5;

    private final String nombre;
    private Carta[] mano;
    private int indiceProximaCarta;

    public Jugador(String nombre) {
        Validaciones.validarNotNull(nombre, "El nombre del jugador debe ser no nulo");
        Validaciones.validarNotBlank(nombre, "El nombre del jugador debe ser no vacío");
        this.nombre = nombre;
        this.mano = new Carta[CAPACIDAD_INICIAL];
        this.indiceProximaCarta = 0;
    }

    public void recibirCarta(Carta carta) {
        Validaciones.validarNotNull(carta, "La carta recibida debe ser no nula");
        Validaciones.validarVerdadero(this.contieneCarta(carta), "El jugador ya tiene la carta dada");
        if (this.debeAumentarCapacidad()) {
            this.ajustarCapacidad(this.mano.length * 2);
        }
        this.mano[indiceProximaCarta++] = carta;
    }

    public void jugarUnaCarta(Carta carta) {
        Validaciones.validarNotNull(carta, "La carta recibida debe ser no nula");
        Validaciones.validarVerdadero(!this.contieneCarta(carta), "El jugador no tiene la carta dada");
        int indiceCarta = this.indiceDeLaCarta(carta);
        this.compactarCartasDesde(indiceCarta);
        if (this.debeReducirCapacidad()) {
            this.ajustarCapacidad(this.mano.length / 2);
        }
    }

    // --- Helpers privados ---

    private boolean contieneCarta(Carta carta) {
        for (int i = 0; i < indiceProximaCarta; i++) {
            if (this.mano[i].equals(carta)) {
                return true;
            }
        }
        return false;
    }

    private boolean debeAumentarCapacidad() {
        return indiceProximaCarta >= this.mano.length;
    }

    private boolean debeReducirCapacidad() {
        return this.mano.length > CAPACIDAD_INICIAL && this.indiceProximaCarta < (this.mano.length / 4);
    }

    private void ajustarCapacidad(int nuevaCapacidad) {
        Carta[] nuevas = new Carta[nuevaCapacidad];
        System.arraycopy(this.mano, 0, nuevas, 0, Math.min(nuevaCapacidad, indiceProximaCarta));
        this.mano = nuevas;
    }

    private void compactarCartasDesde(int desde) {
        for (int i = desde; i < indiceProximaCarta - 1; i++) {
            this.mano[i] = this.mano[i+1];
        }
        this.mano[--indiceProximaCarta] = null;
    }

    private int indiceDeLaCarta(Carta carta) {
        for (int i = 0; i < indiceProximaCarta; i++) {
            if (this.mano[i].equals(carta)) {
                return i;
            }
        }
        throw new NoSuchElementException("La carta no pertenece a la mano del jugador");
    }
}
