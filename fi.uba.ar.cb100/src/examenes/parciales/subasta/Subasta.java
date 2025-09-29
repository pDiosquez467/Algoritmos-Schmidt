package examenes.parciales.subasta;

import validaciones.Validaciones;

import java.util.ArrayList;
import java.util.List;

public class Subasta {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final Articulo articulo;
    private final double montoBase;
    private EstadoDeSubasta estadoDeSubasta;
    private Oferta ofertaGanadora;
    private final List<Oferta> ofertasRealizadas;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Subasta(Articulo articulo, double montoBase) {
        Validaciones.validarNotNull(articulo, "Artículo");
        Validaciones.validarNumeroMayorACero(montoBase, "'montoBase' debe ser mayor a cero");
        this.articulo = articulo;
        this.montoBase = montoBase;
        this.estadoDeSubasta = EstadoDeSubasta.PENDIENTE;
        this.ofertaGanadora = null;
        this.ofertasRealizadas = new ArrayList<>();
    }

    public Subasta(Articulo articulo) {
        this(articulo, 0);
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void abrirSubasta() {
        if (estaCerrada()) {
            throw new RuntimeException("La subasta está cerrada. No puede volver a abrirse");
        }
        if (estaAbierta()) {
            throw new RuntimeException("La subasta ya está abierta");
        }
        estadoDeSubasta = EstadoDeSubasta.ABIERTA;
    }

    public void cerrarSubasta() {
        if (estaCerrada()) {
            throw new RuntimeException("La subasta ya está cerrada");
        }
        estadoDeSubasta = EstadoDeSubasta.CERRADA;
    }

    public void realizarOferta(Oferta oferta) {
        Validaciones.validarNotNull(oferta, "Oferta");
        if (!aceptaOfertas()) {
            throw new RuntimeException("La subasta NO acepta ofertas");
        }
        if (oferta.monto() < montoBase) {
            throw new RuntimeException("El monto ofertado es menor al monto base.");
        }
        if (ofertasRealizadas.contains(oferta)) {
            throw new RuntimeException("Esta oferta ya fue realizada");
        }
        ofertasRealizadas.add(oferta);
        if (ofertaGanadora == null || oferta.monto() > ofertaGanadora.monto()) {
            ofertaGanadora = oferta;
        }
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    public Oferta obtenerOfertaGanadora() {
        return ofertaGanadora;
    }

    public Articulo obtenerArticuloSubastado() {
        return articulo;
    }

    private boolean aceptaOfertas() {
        return estaAbierta();
    }

    private boolean estaCerrada() {
        return estadoDeSubasta == EstadoDeSubasta.CERRADA;
    }

    private boolean estaAbierta() {
        return estadoDeSubasta == EstadoDeSubasta.ABIERTA;
    }

    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public double getMontoBase() {
        return montoBase;
    }

    public EstadoDeSubasta getEstadoDeSubasta() {
        return estadoDeSubasta;
    }
}
