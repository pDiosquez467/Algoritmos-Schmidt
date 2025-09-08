package semana03.ejercicios.nivel02.pedidos;

import semana03.ejercicios.nivel02.pedidos.excepciones.PedidoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Pedido {
    private String codigo;
    private final List<Producto> productos;

    public Pedido(String codigo) {
        this.codigo = codigo;
        this.productos = new ArrayList<>();
    }

    // Métodos de comportamiento

    public void agregarProducto(Producto producto) throws PedidoException {
        if (this.esProductoPedido(producto)) {
            throw new PedidoException("El producto ya forma parte de la lista de productos pedidos");
        }
        this.productos.add(producto);
    }

    public void eliminarProducto(Producto producto) throws PedidoException {
        if (!this.esProductoPedido(producto)) {
            throw new PedidoException("El producto no forma parte de la lista de productos pedidos");
        }
        this.productos.remove(producto);
    }

    public double montoTotalPedido() {
        return this.productos.stream()
                .map(Producto::getPrecio)
                .reduce(0.0, Double::sum);
    }

    public String resumenPedido() {
        return "Resumen del pedido:" +
                "Producto más caro: " + this.productoMasCaro() +
                "Producto más barato: " + this.productoMasBarato() +
                "Cantidad de productos:" + this.productos.size() +
                "Monto total: $" + this.montoTotalPedido();
    }

    private Producto productoMasCaro() {
        Optional<Producto> productoMasCaro = this.productos.stream().max(Comparator.comparingDouble(Producto::getPrecio));
        return productoMasCaro.orElse(null);
    }

    private Producto productoMasBarato() {
        Optional<Producto> productoMasBarato = this.productos.stream().max(Comparator.comparingDouble(Producto::getPrecio));
        return productoMasBarato.orElse(null);
    }

    private boolean esProductoPedido(Producto producto) {
        return this.productos.stream().anyMatch(p -> p.equals(producto));
    }

    // Getters y Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        Validaciones.validarNotBlank(codigo, "El código de un producto no debe ser vacío!");
        this.codigo = codigo;
    }
}
