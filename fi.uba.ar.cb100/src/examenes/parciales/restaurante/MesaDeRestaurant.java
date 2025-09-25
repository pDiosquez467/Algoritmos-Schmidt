package examenes.parciales.restaurante;

import validaciones.Validaciones;

public class MesaDeRestaurant {
    private final int numero;
    private EstadoDeMesaDeRestaurant estadoDeMesaRestaurant;

    public MesaDeRestaurant(int numero) {
        Validaciones.validarNumeroMayorACero(numero,
                "'numero' debe ser > 0");
        this.numero = numero;
        this.estadoDeMesaRestaurant = EstadoDeMesaDeRestaurant.LIBRE;
    }

    public void ocuparMesa() {
        validarDisponibilidad();
        estadoDeMesaRestaurant = EstadoDeMesaDeRestaurant.OCUPADA;
    }

    public void liberarMesa() {
        estadoDeMesaRestaurant = EstadoDeMesaDeRestaurant.LIBRE;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoDeMesaDeRestaurant getEstadoDeMesaRestaurant() {
        return estadoDeMesaRestaurant;
    }

    public boolean estaLibre() {
        return estadoDeMesaRestaurant == EstadoDeMesaDeRestaurant.LIBRE;
    }

    private void validarDisponibilidad() {
        if (!estaLibre()) {
            throw new RuntimeException("Mesa ocupada");
        }
    }
}
