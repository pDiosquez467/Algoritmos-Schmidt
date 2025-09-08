package semana03.ejercicios.nivel02.pedidos;

import semana03.ejercicios.utils.Validaciones;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;

    public Producto(String codigo, String nombre, double precio) {
        setCodigo(codigo);
        setNombre(nombre);
        setPrecio(precio);
    }

    public boolean esMasCaroQue(Producto otro) {
        return this.getPrecio() > otro.getPrecio();
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio= $" + precio +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Producto producto)) return false;
        return this.getCodigo().equals(producto.getCodigo());
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setCodigo(String codigo) {
        Validaciones.validarNotBlank(codigo, "El código de un producto no puede ser vacío!");
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        Validaciones.validarNotBlank(nombre, "El nombre de un producto no puede ser vacío!");
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        Validaciones.validarNumeroMayorACero(precio, "El precio de un producto debe ser mayor a cero!");
        this.precio = precio;
    }
}
