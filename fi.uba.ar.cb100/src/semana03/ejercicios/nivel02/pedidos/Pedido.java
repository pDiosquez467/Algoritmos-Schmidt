package semana03.ejercicios.nivel02.pedidos;

import semana03.ejercicios.nivel02.pedidos.excepciones.PedidoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.Comparator;
import java.util.Optional;

public class Pedido {
    private static final int CAPACIDAD_INICIAL = 10;

    private final String codigo;
    private Producto[] productos;
    private int indiceSiguiente;

    public Pedido(String codigo) {
        Validaciones.validarNotNull(codigo, "El código de un producto debe ser no nulo");
        Validaciones.validarNotBlank(codigo, "El código de un producto debe ser no vacío");
        this.codigo = codigo;
        this.productos = new Producto[CAPACIDAD_INICIAL];
        this.indiceSiguiente = 0;
    }

    // --- Métodos de comportamiento (públicos) ---

    public void agregarProducto(Producto producto) throws PedidoException {
        Validaciones.validarNotNull(producto, "El producto de un pedido debe ser no nulo");

        if (this.contieneProducto(producto)) {
            throw new PedidoException("El  producto ya forma parte de los productos pedidos");
        }

        if (this.debeAumentarCapacidad()) {
            this.ajustarCapacidad(this.productos.length * 2);
        }
        this.productos[indiceSiguiente++] = new Producto(producto.codigo(), producto.nombre(), producto.precio());
    }

    public void eliminarProducto(Producto producto) throws PedidoException {
        Validaciones.validarNotNull(producto, "El producto de un pedido debe ser no nulo");

        if (!this.contieneProducto(producto)) {
            throw new PedidoException("El producto no forma parte de los productos pedidos");
        }

        int indice = this.indiceDelProducto(producto);
        this.compactarProductosDesde(indice);

        if (this.debeReducirCapacidad()) {
            this.ajustarCapacidad(this.productos.length / 2);
        }
    }

    public double montoTotalPedido() {
        double total = 0.0;
        for (int i = 0; i < this.indiceSiguiente; i++) {
            total += this.productos[i].precio();
        }
        return total;
    }

    public Producto[] productos() {
        Producto[] copias = new Producto[this.indiceSiguiente];
        for (int i = 0; i < this.indiceSiguiente; i++) {
            Producto almacenado = this.productos[i];
            copias[i] = new Producto(almacenado.codigo(), almacenado.nombre(), almacenado.precio());
        }
        return copias;
    }

    public Producto productoMasCaro() throws PedidoException {
        if (!hayProductos()) {
            throw new PedidoException("No hay productos pedidos");
        }

        Producto productoMasCaro = this.productos[0];
        for (int i = 1; i < this.indiceSiguiente; i++) {
            if (this.productos[i].precio() > productoMasCaro.precio()) {
                productoMasCaro = this.productos[i];
            }
        }
        return new Producto(productoMasCaro.codigo(), productoMasCaro.nombre(), productoMasCaro.precio());
    }

    public int cantidadDeProductos() {
        return this.indiceSiguiente;
    }

    // --- Helpers privados ---

    private boolean contieneProducto(Producto producto) {
        return this.indiceDelProducto(producto) > -1;
    }

    private boolean hayProductos() {
        return this.indiceSiguiente > 0;
    }

    private boolean debeAumentarCapacidad() {
        return this.indiceSiguiente == this.productos.length;
    }

    private boolean debeReducirCapacidad() {
        return this.productos.length > CAPACIDAD_INICIAL && this.indiceSiguiente < (this.productos.length / 4);
    }

    private void compactarProductosDesde(int desde) {
        for (int i = desde; i < this.indiceSiguiente - 1; i++) {
            this.productos[i] = this.productos[i+1];
        }
        this.productos[--indiceSiguiente] = null;
    }

    private int indiceDelProducto(Producto producto) {
        for (int i = 0; i < this.indiceSiguiente; i++) {
            if (this.productos[i].equals(producto)) {
                return i;
            }
        }
        return -1;
    }

    private void ajustarCapacidad(int nuevaCapacidad) {
        Producto[] nuevos = new Producto[nuevaCapacidad];
        System.arraycopy(this.productos, 0, nuevos, 0, Math.min(nuevaCapacidad, this.indiceSiguiente));
        this.productos = nuevos;
    }
}
