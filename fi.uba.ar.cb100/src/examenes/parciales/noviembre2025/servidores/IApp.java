package examenes.parciales.noviembre2025.servidores;

public interface IApp {
    String getNombre();
    void apagar();
    void ejecutar();
    boolean enEjecucion();
    double getConsumoDeRam();
    double getConsumoDeDisco();
}
