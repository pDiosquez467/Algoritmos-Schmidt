package examenes.parciales.julio2010.facturas;

import validaciones.Validaciones;

import java.util.Objects;

public class Item {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String id;
    private final String descripcion;
    private int cantidad;
    private double precio;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Item(String id, String descripcion, int cantidad, double precio) {
        Validaciones.validarNotNull(id, "id");
        Validaciones.validarNotNull(descripcion, "descripción");
        this.id = id;
        this.descripcion = descripcion;
        this.setCantidad(cantidad);
        this.setPrecio(precio);
    }

    public Item(Item item) {
        this(item.getId(), item.getDescripcion(), item.getCantidad(), item.getPrecio());
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item item)) return false;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", descripción='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public double getMonto() {
        return this.cantidad * this.precio;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public String getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    public void setCantidad(int cantidad) {
        Validaciones.validarNumeroMayorACero(cantidad, "cantidad");
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        Validaciones.validarNumeroMayorACero(precio, "precio");
        this.precio = precio;
    }
}
