package examenes.parciales.octubre2023.productos;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
public class Sucursal {
    private final List<Producto> productos;
    private final List<String> categorias;

    /**
     * post: Inicializa una sucursal sin productos, ni categorías asociadas.
     */
    public Sucursal() {
        this.productos  = new ListaSimplementeEnlazada<>();
        this.categorias = new ListaSimplementeEnlazada<>();
    }

    /**
     * post: Devuelve la lista de productos de la sucursal.
     * @return los productos de la sucursal.
     */
    public List<Producto> getProductos() {
        return Collections.unmodifiableList(this.productos);
    }

    /**
     * post: Devuelve las categorías de la sucursal.
     * @return las categorías de la sucursal.
     */
    public List<String> getCategorias() {
        return Collections.unmodifiableList(this.categorias);
    }

    public boolean agregarProducto(Producto producto) {
        Validaciones.validarNotNull(producto, "producto");
        return this.productos.add(producto);
    }
}
