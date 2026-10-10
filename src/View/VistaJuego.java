package View;

import java.util.Scanner;
import Model.Carta;
import Model.Jugador;
import java.util.InputMismatchException;
import java.util.List;

public class VistaJuego {
    private Scanner scanner;

    public VistaJuego() {
        this.scanner = new Scanner(System.in);
    }



    public int pedirEntero(String mensaje) {
        boolean valido = false;
        int numero = 0;

        while (!valido) {
            try{
                System.out.println(mensaje);
                numero = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
         return numero;
            
        }
        

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    // Menu principal del juego donde el jugador puede explorar su mazo con total libertad antes de comenzar la partida
    public int mostrarMenuPrincipal() {
        System.out.println("""
            \n============= MENU PRINCIPAL DE UVG CARD BATTLE =============
            1. Explorar catálogo completo de cartas disponibles para el jugador (10 predefinidas)
            2. Buscar carta por ID
            3. Buscar carta por nombre
            4. Ordenar cartas predefinidas del jugador por costo de energía 
            5. Iniciar semestre/partida (AVISO: memoriza las características de tus cartas antes de seleccionar esta opción)
            6. Salir del menu principal
            ==============================================================
        """);
        return pedirEntero("Seleccione una opción (1-6): ");
        
    }

    //Menú interno al comenzar una partida seleccionando la opción 5 del menú principal
    public int mostrarMenuTurno() {
        System.out.println("""
            \n============= MENU DE TURNO DEL JUGADOR =============
            1. Jugar una carta de mi catálogo (se aplicaran los efectos de cada carta elegida)
            2. Robar carta al azar (BONO: +2 energía por el coraje de arriesgarse a ciegas)
            3. Pasar turno / descansar (BONO: +1 energía por el descanso)
            4. Salir de la partida y regresar al menú principal
            """);
            return pedirEntero("¿Qué deseas hacer?: ");
        }


    public void mostrarCartas(List<Carta> cartas) {
        if (cartas == null || cartas.isEmpty()) {
            System.out.println("No hay cartas disponibles para mostrar.");
            return;
        }

        System.out.println("\n============ LISTA DE CARTAS DEL JUGADOR ============");
        for (int i = 0; i < cartas.size(); i++) {
            System.out.println((i+1) + ". " + cartas.get(i).toString());
        }
    }

    public void mostrarCartasCompactas(List<Carta> cartas) {
        if (cartas == null || cartas.isEmpty()) {
            System.out.println("No tienes cartas en tu mazo.");
            return;
        }
    System.out.println("\n============ TUS CARTAS EN MANO (SOLO NOMBRES) ============");
        for (Carta c : cartas) {
            System.out.println("ID: " + c.getID() + " | " + c.getNombreDescriptivo());
        }
    System.out.println("============================================================");
}


    public void mostrarEstadoJugador(Jugador jugador) {
        System.out.println("\n============ ESTADO ACTUAL DEL JUGADOR ============");
        System.out.println(jugador.toString());
        System.out.println("====================================================");
    
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

}


