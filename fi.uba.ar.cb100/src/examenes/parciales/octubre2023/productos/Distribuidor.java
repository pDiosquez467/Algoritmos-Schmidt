package examenes.parciales.octubre2023.productos;

import tdas.cola.Cola;
import validaciones.Validaciones;

import java.util.List;

public class Distribuidor {

    /**
     * post: Remueve los productos de la cola 'productosDisponibles' y los agrega a alguna
     * sucursal de 'sucursalesExistentes' que tengan la categoría del producto.
     * Aquellos productos que no pueden ser agregados a ninguna sucursal se devuelven a la cola
     * 'productosDisponibles'.
     */
    public void distribuirProductosEnSucursales(Cola<Producto> productosDisponibles,
                                                List<Sucursal> sucursalesExistentes) {
        Validaciones.validarNotNull(productosDisponibles, "productosDisponibles");
        Validaciones.validarNotNull(sucursalesExistentes, "sucursalesExistentes");
        Cola<Producto> productosRelegados = new Cola<>();
        while (!productosDisponibles.estaVacia()) {
            Producto producto = productosDisponibles.desencolar();
            Sucursal sucursal = buscarSucursalValidaParaElProducto(sucursalesExistentes, producto);
            if (sucursal == null) {
                productosRelegados.encolar(producto);
            } else {
                sucursal.agregarProducto(producto);
            }
        }

        agregarTodosLosProductos(productosRelegados, productosDisponibles);
    }

    /**
     * post: Busca en la lista de sucursales dada alguna que tenga la categoría
     * del producto dado. En caso de no encontrarla, devuelve null.
     */
    public Sucursal buscarSucursalValidaParaElProducto(List<Sucursal> sucursales, Producto producto) {
        Validaciones.validarNotNull(sucursales, "sucursales");
        Validaciones.validarNotNull(producto, "producto");
        Validaciones.validarNotNull(producto.categoria(), "Categoría del producto");
        for (Sucursal sucursal: sucursales) {
            if ((sucursal == null) || (sucursal.getCategorias() == null)) continue;
            if (sucursal.getCategorias().contains(producto.categoria())) {
                return sucursal;
            }
        }
        return null;
    }

    /**
     * post: Vacía la cola 'origen' encolando todos sus productos en la cola
     * 'destino'.
     */
    public void agregarTodosLosProductos(Cola<Producto> origen, Cola<Producto> destino) {
        Validaciones.validarNotNull(origen, "origen");
        Validaciones.validarNotNull(destino, "destino");
        while (!origen.estaVacia()) {
            destino.encolar(origen.desencolar());
        }
    }
}
