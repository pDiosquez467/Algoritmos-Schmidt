package examenes.parciales.restaurante;

import validaciones.Validaciones;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Restaurante {
    private static final int CAPACIDAD_INICIAL = 7;
    private static final int _FACTOR_REDIMENSION = 2;
    private static final int _FACTOR_COMPARACION = 4;

    private final MesaDeRestaurant[] mesas;
    private final Mozo[] mozos;
    private ServicioDeMesa[] serviciosActivos;
    private double mayorPropina;

    private int indiceProximoMozo;
    private int indiceProximoServicio;

    public Restaurante(int cantidadDeMesas) {
        Validaciones.validarNumeroMayorACero(cantidadDeMesas,
                "'cantidadDeMesas' debe ser > 0");
        this.mesas = new MesaDeRestaurant[cantidadDeMesas];
        this.mozos = new Mozo[cantidadDeMesas]; // Un máximo de un mozo por mesa
        this.serviciosActivos = new ServicioDeMesa[CAPACIDAD_INICIAL];
        this.mayorPropina          = 0;
        this.indiceProximoMozo     = 0;
        this.indiceProximoServicio = 0;

        inicializarMesas(cantidadDeMesas);
    }

    // === MÉTODOS DE COMPORTAMIENTO ===

    public void ingresarMozo(Mozo mozo) {
        Validaciones.validarNotNull(mozo,
                "'mozo' debe ser no nulo");
        validarNecesidadDeNuevoMozo();
        mozos[indiceProximoMozo++] = new Mozo(mozo.getNombre());
    }

    public void solicitarMesa() {
        MesaDeRestaurant mesaLibre = buscarMesaLibre();
        validarDisponibilidadDeMesas(mesaLibre);

        Mozo mozoLibre = buscarMozoLibre();
        validarDisponibilidadDeMozos(mozoLibre);

        ServicioDeMesa servicioDeMesa = new ServicioDeMesa(mesaLibre, mozoLibre);
        iniciarServicio(servicioDeMesa);
    }

    private void iniciarServicio(ServicioDeMesa servicioDeMesa) {
        servicioDeMesa.abrirServicio();
        if (requiereAumentarCapacidadDeServicios()) {
            ajustarCapacidadDeServicios(serviciosActivos.length * _FACTOR_REDIMENSION);
        }
        serviciosActivos[indiceProximoServicio++] = servicioDeMesa;
    }

    public void cerrarMesa(int numeroDeMesa, double propina) {
        int indiceServicioDeMesa = buscarServicioActivo(numeroDeMesa);
        cerrarServicio(indiceServicioDeMesa, propina);
        actualizarPropinaMaxima(propina);
    }

    public double mayorPropina() {
        return mayorPropina;
    }

    public Mozo mozoConMasPropina() {
        /*Mozo mozoAdinerado = null;
        for (Mozo mozo: mozos) {
            if ((mozoAdinerado == null) ||
                    mozo.getPropinaAcumulada() > mozoAdinerado.getPropinaAcumulada()) {
                mozoAdinerado = mozo;
            }
        }
        return mozoAdinerado
        */
        return Arrays.stream(mozos)
                .max(Comparator.comparingDouble(Mozo::getPropinaAcumulada))
                .orElse(null);
    }

    public double totalDePropinas() {
        return Arrays.stream(mozos)
                .mapToDouble(Mozo::getPropinaAcumulada)
                .sum();
    }

    // === HELPERS PRIVADOS ===

    private void inicializarMesas(int cantidadDeMesas) {
        for (int i = 0; i < cantidadDeMesas; i++) {
            mesas[i] = new MesaDeRestaurant(i+1);
        }
    }

    private void validarNecesidadDeNuevoMozo() {
        if (indiceProximoMozo == mesas.length) {
            throw new RuntimeException("Cupo de mozos completo");
        }
    }

    private Mozo buscarMozoLibre() {
        return Arrays.stream(mozos)
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    private MesaDeRestaurant buscarMesaLibre() {
        return Arrays.stream(mesas)
                .filter(MesaDeRestaurant::estaLibre)
                .findFirst().orElse(null);
    }

    private void ajustarCapacidadDeServicios(int nuevaCapacidad) {
        ServicioDeMesa[] nuevos = new ServicioDeMesa[nuevaCapacidad];
        System.arraycopy(serviciosActivos, 0, nuevos, 0, Math.min(nuevaCapacidad, indiceProximoServicio));
        serviciosActivos = nuevos;
    }

    private boolean requiereAumentarCapacidadDeServicios() {
        return indiceProximoServicio == serviciosActivos.length;
    }

    private boolean requiereDisminuirCapacidadDeServicios() {
        return indiceProximoServicio > CAPACIDAD_INICIAL &&
                indiceProximoServicio < (serviciosActivos.length / _FACTOR_COMPARACION);
    }

    private void compactarServiciosDesde(int desde) {
        for (int i = desde; i < indiceProximoServicio - 1; i++) {
            serviciosActivos[i] = serviciosActivos[i+1];
        }
        serviciosActivos[--indiceProximoServicio] = null;
    }

    private void validarDisponibilidadDeMozos(Mozo mozo) {
        if (mozo == null) {
            throw new RuntimeException("No hay mesas libres");
        }
    }

    private void validarDisponibilidadDeMesas(MesaDeRestaurant mesaDeRestaurant) {
        if (mesaDeRestaurant == null) {
            throw new RuntimeException("No hay mesas libres");
        }
    }

    private void actualizarPropinaMaxima(double propina) {
        if (propina > mayorPropina) {
            mayorPropina = propina;
        }
    }

    private void cerrarServicio(int indiceServicioDeMesa, double propina) {
        serviciosActivos[indiceServicioDeMesa].cerrarServicio(propina);
        compactarServiciosDesde(indiceServicioDeMesa);
        if (requiereDisminuirCapacidadDeServicios()) {
            ajustarCapacidadDeServicios(serviciosActivos.length / _FACTOR_REDIMENSION);
        }
    }

    private int buscarServicioActivo(int numeroDeMesa) {
        for (int i = 0; i < indiceProximoServicio; i++) {
            if (serviciosActivos[i].mesaDeRestaurant().getNumero() == numeroDeMesa) {
                return i;
            }
        }
        throw new RuntimeException("No hay mesa ocupada con el número dado");
    }
}
