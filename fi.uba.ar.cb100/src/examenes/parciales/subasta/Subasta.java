package examenes.parciales.subasta;

import validaciones.Validaciones;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * Crea una subasta con el artículo y un monto base.
     * @param articulo el artículo a subastar; no puede ser {@code null}
     * @param montoBase el monto mínimo aceptado en la subasta; debe ser >= 0
     * @throws IllegalArgumentException si {@code articulo} es {@code null} o {@code montoBase} < 0
     */
    public Subasta(Articulo articulo, double montoBase) {
        Validaciones.validarNotNull(articulo, "Artículo");
        Validaciones.validarNumeroMayorOIgualACero(montoBase, "'montoBase' debe ser mayor a cero");
        this.articulo = articulo;
        this.montoBase = montoBase;
        this.estadoDeSubasta = EstadoDeSubasta.PENDIENTE;
        this.ofertaGanadora = null;
        this.ofertasRealizadas = new ArrayList<>();
    }

    /**
     * Crea una subasta con monto base 0.
     * @param articulo el artículo a subastar; no puede ser {@code null}
     */
    public Subasta(Articulo articulo) {
        this(articulo, 0);
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Subasta{" +
                "articulo=" + articulo +
                ", montoBase=" + montoBase +
                ", estado=" + estadoDeSubasta +
                ", ofertaGanadora=" + ofertaGanadora +
                ", totalOfertas=" + ofertasRealizadas.size() +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Abre la subasta para comenzar a recibir ofertas.
     * @throws IllegalStateException si la subasta ya está ABIERTA o si ya fue CERRADA
     */
    public void abrirSubasta() {
        if (estaCerrada()) {
            throw new RuntimeException("La subasta está cerrada. No puede volver a abrirse");
        }
        if (estaAbierta()) {
            throw new RuntimeException("La subasta ya está abierta");
        }
        estadoDeSubasta = EstadoDeSubasta.ABIERTA;
    }

    /**
     * Cierra la subasta; a partir de este momento no acepta más ofertas.
     * @throws IllegalStateException si la subasta ya está CERRADA
     */
    public void cerrarSubasta() {
        if (estaCerrada()) {
            throw new RuntimeException("La subasta ya está cerrada");
        }
        estadoDeSubasta = EstadoDeSubasta.CERRADA;
    }


    /**
     * Registra una oferta para la subasta.
     * @param oferta la oferta a registrar
     * @throws IllegalArgumentException si {@code oferta} es {@code null} o su monto &lt; {@link #montoBase}
     * @throws IllegalStateException si la subasta no está en estado que acepta ofertas
     */
    // TODO: hace muchas comprobaciones; extraer validaciones auxiliares (validarAceptaOfertas(oferta))
    public void ofertar(Oferta oferta) {
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

    /**
     * Devuelve la oferta ganadora actual.
     * @return la {@link Oferta} ganadora
     * @throws IllegalStateException si aún no se registraron ofertas
     */
    public Oferta obtenerOfertaGanadora() {
        if (!seHanRecibidoOfertas()) {
            throw new RuntimeException("No se han recibido ofertas en esta subasta");
        }
        return ofertaGanadora;
    }

    /**
     * Devuelve el artículo subastado.
     * @return el {@link Articulo} subastado (inmutable)
     */
    public Articulo obtenerArticuloSubastado() {
        return articulo;
    }

    /**
     * Indica si la subasta acepta ofertas.
     * @return verdadero si la subasta acepta ofertas.
     */
    private boolean aceptaOfertas() {
        return estaAbierta();
    }

    /**
     * Indica si la subasta está cerrada.
     * @return verdadero si la subasta está cerrada.
     */
    private boolean estaCerrada() {
        return estadoDeSubasta == EstadoDeSubasta.CERRADA;
    }

    /**
     * Indica si la subasta está abierta.
     * @return verdadero si la subasta está abierta.
     */
    private boolean estaAbierta() {
        return estadoDeSubasta == EstadoDeSubasta.ABIERTA;
    }

    /**
     * Indica si la subasta ha recibido ofertas.
     * @return verdadero si la subasta ha recibido ofertas.
     */
    private boolean seHanRecibidoOfertas() {
        return ofertaGanadora != null;
    }

    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Devuelve el monto base de la subasta.
     * @return monto base (>= 0)
     */
    public double getMontoBase() {
        return montoBase;
    }

    /**
     * Devuelve el estado actual de la subasta.
     * @return {@link EstadoDeSubasta}
     */
    public EstadoDeSubasta getEstadoDeSubasta() {
        return estadoDeSubasta;
    }

    /**
     * Devuelve una vista no modificable de las ofertas realizadas.
     *
     * @return lista inmutable de {@link Oferta}
     */
    public List<Oferta> getOfertasRealizadas() {
        return Collections.unmodifiableList(ofertasRealizadas);
    }
}
