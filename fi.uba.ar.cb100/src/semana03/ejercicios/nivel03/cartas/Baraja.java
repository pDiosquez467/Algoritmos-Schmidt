package semana03.ejercicios.nivel03.cartas;

import semana03.ejercicios.nivel03.cartas.excepciones.JuegoDeCartasException;
import semana03.ejercicios.utils.Validaciones;

import java.util.Arrays;
import java.util.Collections;

public class Baraja {
    private final Carta[] cartas;
    private int indiceProximaCarta;

    public Baraja(Carta[] cartas) {
        Validaciones.validarNotNull(cartas, "El arreglo de cartas no debe ser nulo");
        this.cartas = cartas;
        this.indiceProximaCarta = 0;
    }

    public void barajar() {
        Collections.shuffle(Arrays.asList(this.cartas));
    }

    public Carta[] repartir(int cantidad) throws JuegoDeCartasException {
        Validaciones.validarNumeroMayorACero(cantidad, "La cantidad a repartir debe ser mayor a cero");

        if (!this.haySuficientesCartas(cantidad)) {
            throw new JuegoDeCartasException("No hay suficientes cartas");
        }

        Carta[] repartidas = new Carta[cantidad];
        for (int i = indiceProximaCarta; i < this.cartas.length ; i++) {
            Carta guardada = this.cartas[i];
            repartidas[i - indiceProximaCarta] = new Carta(guardada.palo(), guardada.valor());
        }
        indiceProximaCarta += cantidad;
        return repartidas;
    }

    public Carta[] verRestantes() {
        Carta[] restantes = new Carta[this.cartas.length - indiceProximaCarta];
        for (int i = indiceProximaCarta; i < this.cartas.length ; i++) {
            Carta guardada = this.cartas[i];
            restantes[i - indiceProximaCarta] = new Carta(guardada.palo(), guardada.valor());
        }
        return restantes;
    }

    public void inicializarBaraja() {
        indiceProximaCarta = 0;
    }

    // --- Helpers privados ---

    private boolean haySuficientesCartas(int cantidad) {
        return this.cartas.length - indiceProximaCarta < cantidad;
    }

}
