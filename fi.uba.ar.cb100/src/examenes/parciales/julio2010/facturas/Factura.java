package examenes.parciales.julio2010.facturas;

import tdas.vector.Vector;
import tdas.vector.VectorImpl;
import validaciones.Validaciones;

public class Factura {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final String id;
    private final Vector<Item> items;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Factura(String id) {
        Validaciones.validarNotNull(id, "id");
        this.id = id;
        this.items = new VectorImpl<>();
    }
    
    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
    public boolean agregar(Item item) {
        Validaciones.validarNotNull(item, "item");
        return this.items.add(item);
    }
    
    public boolean remover(Item item) {
        Validaciones.validarNotNull(item, "item");
        return this.items.remove(item);
    }
    
    public Item getItem(String itemId) {
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            if (item.getId().equals(itemId)) {
                return new Item(item);
            }
        }
        return null;
    }
    
    public double getMonto() {
        double monto = 0;
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            monto += item.getMonto();
        }
        return monto;
    }
    
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    
    public Vector<Item> getItems() {
        Vector<Item> copias = new VectorImpl<>();
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            copias.add(new Item(item));
        }
        return copias;
    }
    
    public String getId() {
        return id;
    }
}
