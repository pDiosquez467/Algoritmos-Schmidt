package examenes.parciales.julio2021.televisor;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIO PRUEBAS TELEVISOR ===\n");

        // 1. Crear televisor con 5 canales disponibles (máximo 140)
        System.out.println("1. Creando televisor con 5 canales...");
        Televisor tv = new Televisor(5, 140);
        System.out.println("Estado inicial: " + tv);
        System.out.println("¿Está encendido? " + tv.estaEncendido());

        // 2. Encender el televisor
        System.out.println("\n2. Encendiendo televisor...");
        tv.encender();
        System.out.println("Estado: " + tv);
        System.out.println("¿Está encendido? " + tv.estaEncendido());

        // 3. Probar navegación de canales
        System.out.println("\n3. Probando navegación de canales:");
        System.out.println("Canal actual (implícito): " + tv.ultimoVolumenRegistrado(1));

        tv.getSiguienteCanal();
        System.out.println("Pasando al siguiente canal...");
        System.out.println("Volumen canal 2: " + tv.ultimoVolumenRegistrado(2));

        tv.getSiguienteCanal();
        System.out.println("Pasando al siguiente canal...");
        System.out.println("Volumen canal 3: " + tv.ultimoVolumenRegistrado(3));

        tv.getAnteriorCanal();
        System.out.println("Volviendo al canal anterior...");
        System.out.println("Volumen canal 2: " + tv.ultimoVolumenRegistrado(2));

        // 4. Probar selección directa de canal
        System.out.println("\n4. Selección directa de canal 4:");
        tv.getCanal(4);
        System.out.println("Volumen canal 4: " + tv.ultimoVolumenRegistrado(4));

        // 5. Probar control de volumen
        System.out.println("\n5. Probando control de volumen:");
        System.out.println("Volumen actual: " + tv.volumenActual());

        System.out.println("Subiendo volumen 3 veces...");
        tv.subirVolumen();
        tv.subirVolumen();
        tv.subirVolumen();
        System.out.println("Volumen actual: " + tv.volumenActual());
        System.out.println("Volumen canal 4: " + tv.ultimoVolumenRegistrado(4));

        System.out.println("Bajando volumen 1 vez...");
        tv.bajarVolumen();
        System.out.println("Volumen actual: " + tv.volumenActual());

        // 6. Probar, mute
        System.out.println("\n6. Probando función mute:");
        System.out.println("¿Está muteado? " + tv.estaMuteada());

        System.out.println("Muteando...");
        tv.mutear();
        System.out.println("¿Está muteado? " + tv.estaMuteada());
        System.out.println("Estado: " + tv);

        System.out.println("Desmuteando...");
        tv.desmutear();
        System.out.println("¿Está muteado? " + tv.estaMuteada());
        System.out.println("Estado: " + tv);

        // 7. Probar máximo volumen por canal
        System.out.println("\n7. Probando registros de volumen por canal:");

        // Cambiar a canal 1 y subir volumen
        tv.getCanal(1);
        tv.subirVolumen();
        tv.subirVolumen();
        System.out.println("Canal 1 - Volumen actual: " + tv.volumenActual());
        System.out.println("Canal 1 - Máximo registrado: " + tv.maximoVolumenRegistrado(1));

        // Cambiar a canal 2 y subir más volumen
        tv.getCanal(2);
        tv.subirVolumen();
        tv.subirVolumen();
        tv.subirVolumen();
        tv.subirVolumen();
        System.out.println("Canal 2 - Volumen actual: " + tv.volumenActual());
        System.out.println("Canal 2 - Máximo registrado: " + tv.maximoVolumenRegistrado(2));

        // 8. Probar canal con máximo volumen
        System.out.println("\n8. Canal con máximo volumen registrado:");
        Canal canalMax = tv.getCanalConMaximoVolumenRegistrado();
        System.out.println("Canal: " + canalMax.numero());
        System.out.println("Máximo volumen: " + canalMax.maximoVolumenRegistrado());

        // 9. Probar apagado
        System.out.println("\n9. Apagando televisor...");
        tv.apagar();
        System.out.println("Estado final: " + tv);
        System.out.println("¿Está encendido? " + tv.estaEncendido());

        System.out.println("\n=== FIN PRUEBAS TELEVISOR ===");
    }
}
