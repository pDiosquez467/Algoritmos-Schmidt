package examenes.parciales.restaurante;

import validaciones.Validaciones;

public record ServicioDeMesa(MesaDeRestaurant mesaDeRestaurant, Mozo mozo) {

    public ServicioDeMesa {
        Validaciones.validarNotNull(mesaDeRestaurant,
                "'mesa' debe ser no nula");
        Validaciones.validarNotNull(mozo,
                "'mozo' debe ser no nulo");
    }

    public void abrirServicio() {
        Validaciones.validarVerdadero(mesaDeRestaurant.estaLibre(),
                "La mesa está ocupada");
        mesaDeRestaurant.ocuparMesa();
    }

    public void cerrarServicio(double propina) {
        Validaciones.validarNumeroMayorACero(propina,
                "'propina' debe ser > 0");
        mesaDeRestaurant.liberarMesa();
        mozo.guardarPropina(propina);
    }
}
