package Controller;

import Model.*;
import View.VistaJuego;
import java.util.Random;

public class ControladorJuego {
    private Jugador jugador;
    private Mazo mazoCatalogoGeneral;
    private Mazo mazoCartasJugador;
    private VistaJuego vistaJuego;
    private Random random;


    // constructor secundario ideal para iniciar el juego normal desde Main sin ocurrir ningún problema y aplicando buenas prácticas
    public ControladorJuego(VistaJuego vistaJuego) {
        this.vistaJuego = vistaJuego;
        this.mazoCatalogoGeneral = new Mazo();
        this.mazoCatalogoGeneral.agregarCartasAlMazo(); // Inicializa el mazo con las cartas predefinidas
        this.random = new Random();
        this.mazoCartasJugador = new Mazo();
    }
    public ControladorJuego(Jugador jugador, Mazo mazo, VistaJuego vistaJuego) {
        this.vistaJuego = vistaJuego;
        this.jugador = jugador;
        this.mazoCatalogoGeneral = new Mazo();
        this.mazoCatalogoGeneral.agregarCartasAlMazo(); // Inicializa el mazo con las cartas predefinidas
        this.random = new Random();
        this.mazoCartasJugador = new Mazo();
    }

    public void iniciar(){
        registrarJugador();
        prepararMazoCartasJugador();

        boolean salir = false;
        while(!salir) {
            int option = vistaJuego.mostrarMenuPrincipal();

            switch (option) {
                case 1: 
                    vistaJuego.mostrarCartas(mazoCartasJugador.getCartas());
                    break;

                case 2:
                    buscarPorID();
                    break;

                case 3:
                    buscarPorNombre();
                    break;

                case 4:
                    mazoCartasJugador.ordenarCartasPorEnergia();
                    vistaJuego.mostrarMensaje("Las cartas de tu mazo han sido ordenadas por costo de energía de forma ascendente.");
                    vistaJuego.mostrarMensaje("Utilizalas para formar una estrategia de juego eficiente. RECUERDA: es importante memorizar la mayor cantidad de cartas y sus características ya que no podrás verlas durante la partida.");
                    vistaJuego.mostrarCartas(mazoCartasJugador.getCartas());
                    break;

                case 5:
                    iniciarPartida();
                    break;

                case 6:
                    salir = true;
                    vistaJuego.mostrarMensaje("Gracias por jugar UVG CARD BATTLE. ¡Hasta la próxima!");
                    break;

                default:
                    vistaJuego.mostrarMensaje("Opción inválida. Por favor, seleccione un número entre 1 y 6.");

            }

        }

    }

            // métodos que indican lo que se podrá hacer en cada opción de los menús del juego y verifica posibles errores de entrada de datos del jugador
            public void registrarJugador() {
                boolean registrado = false;
                while (!registrado) {
                    try {
                        String nombre = vistaJuego.pedirTexto("Ingrese el nombre del jugador: ");
                        this.jugador = new Jugador (nombre);
                        registrado = true;
                        vistaJuego.mostrarMensaje("Bienvenido al juego UVG Card Battle, " + jugador.getNombreJugador() + ". El proceso de registro ha sido exitoso. ¡Disfruta tu partida!");
                    } catch (IllegalArgumentException e) {
                        vistaJuego.mostrarMensaje(e.getMessage());
                    }

                }
        
    }

            public void prepararMazoCartasJugador() {
            // Selecciona 10 cartas aleatorias del mazo completo para el jugador
                
                while (mazoCartasJugador.getCantidadCartas() < 10) {
                    int indexAleatorio = random.nextInt(mazoCatalogoGeneral.getCantidadCartas());
                    Carta cartaSeleccionada = mazoCatalogoGeneral.getCartas().get(indexAleatorio);
                    if (mazoCartasJugador.buscarCartaPorID(cartaSeleccionada.getID()) == null) {
                        mazoCartasJugador.agregarCarta(cartaSeleccionada);
                    }
                }

                
            }

            public void buscarPorID () {
                int id = vistaJuego.pedirEntero("Ingresa el ID específico de la carta a buscar. Si no recuerdas el ID, explora el catálogo completo de cartas disponibles en la opción 1 del menú principal: ");
                Carta cartaEncontrada = mazoCartasJugador.buscarCartaPorID(id);
                if (cartaEncontrada != null) {
                    vistaJuego.mostrarMensaje("Carta encontrada: \n" + cartaEncontrada.toString());
                } else {
                    vistaJuego.mostrarMensaje("No se encontró ninguna carta con el ID: " + id + " en su mazo. Porfavor, intente nuevamente con otro ID.");
                }
            }

            public void buscarPorNombre () {
                String nombre = vistaJuego.pedirTexto("Ingresa el nombre de la carta a buscar. Si no lo recuerda, explore la opción 1 del menú principal: ");
                Carta cartaEncontrada = mazoCartasJugador.buscarCartaPorNombre(nombre);
                if (cartaEncontrada != null) {
                    vistaJuego.mostrarMensaje("Carta encontrada: \n" + cartaEncontrada.toString());
                } else {
                    vistaJuego.mostrarMensaje("No se encontró ninguna carta con el nombre: " + nombre + " en su mazo. Por favor, intente nuevamente con otro nombre.");
                }
            }

            private void iniciarPartida () {
                if (jugador.haGanado()) {
                    vistaJuego.mostrarMensaje("Ya has completado y ganado este semestre con " + jugador.getCreditos() + " créditos. ¡Excelente trabajo!");
                return;
                }
                if (jugador.haPerdido()) {
                    vistaJuego.mostrarMensaje("Ya has reprobado este semestre. Debes reiniciar el programa para comenzar una nueva partida.");
                    return;
                }

                boolean enPartida = true;

                while (enPartida) {
                    vistaJuego.mostrarEstadoJugador(jugador);

                    if (jugador.haGanado()) {
                        vistaJuego.mostrarMensaje("\n===============================================================================================");
                        vistaJuego.mostrarMensaje("MUCHAS FELICIDADES! Has logrado alcanzar sus 20 créditos y ha completado el semestre con éxito");
                        vistaJuego.mostrarMensaje("===============================================================================================");
                        break;
                    }

                    if (jugador.haPerdido()) {
                        vistaJuego.mostrarMensaje("\n===============================================================================================");
                        vistaJuego.mostrarMensaje("Lamentablemente has perdido el semestre.");
                        vistaJuego.mostrarMensaje("================================================================================================");
                        if (jugador.getEnergia() <= 0) {
                            vistaJuego.mostrarMensaje("CAUSA: Te has quedado sin energía y ya no puedes continuar el semestre.");
                        }
                        if (jugador.getLlamadasdeAtencion() >= 5) {
                            vistaJuego.mostrarMensaje("CAUSA: Has recibido 5 llamadas de atención y has sido expulsado del semestre.");
                            vistaJuego.mostrarMensaje("===============================================================================================");
                        }
                        vistaJuego.mostrarMensaje("===============================================================================================");
                        break;
                    }

                    int accionTurno = vistaJuego.mostrarMenuTurno();

                    switch (accionTurno) {
                        case 1:
                            jugarCartaTurno();
                            break;

                        case 2:
                            robarCartaAzar();
                            break;

                        case 3:
                            pasarTurno();
                            break;

                        case 4:
                            enPartida = false;
                            vistaJuego.mostrarMensaje("Has decidido salir de la partida y regresar al menú principal. Esperamos verte pronto nuevamente!");
                            break;

                        default:
                            vistaJuego.mostrarMensaje("Opción inválida. Por favor, seleccione un número entre 1 y 4.");
                    }

                }

            }

            public void jugarCartaTurno () {
                if (mazoCartasJugador.getCantidadCartas() == 0) {
                    vistaJuego.mostrarMensaje("No tienes cartas disponibles para jugar. Por favor, selecciona la opción 2 del menú de turno para robar una carta al azar.");
                    return;
                }

                vistaJuego.mostrarCartasCompactas(mazoCartasJugador.getCartas());
                int idCartaSelect = vistaJuego.pedirEntero("Ingresa el ID de la carta que desea jugar: ");
                Carta cartaSeleccionada = mazoCartasJugador.buscarCartaPorID(idCartaSelect);

                if (cartaSeleccionada == null) {
                    vistaJuego.mostrarMensaje("No se encontró ninguna carta con el ID: " + idCartaSelect + " en su mazo. Porfavor, intente nuevamente con otro ID.");
                    return;
                }

                if (jugador.getEnergia() < cartaSeleccionada.getCostoEnergiaParaJugar()) {
                    vistaJuego.mostrarMensaje("No tienes suficiente energía para jugar esta carta. Necesitas " + cartaSeleccionada.getCostoEnergiaParaJugar() + " de energía, pero solo tienes " + jugador.getEnergia() + ". Prueba seleccionando otra o robando una al azar.");
                    return;
                }

                try {
                    cartaSeleccionada.jugarCarta(jugador, false); // Se juega la carta sin bono
                    mazoCartasJugador.removerCarta(cartaSeleccionada);
                    vistaJuego.mostrarMensaje("Has jugado la carta: " + cartaSeleccionada.getNombreDescriptivo() + ". Sus efectos han sido aplicados.");
                } catch (IllegalArgumentException e) {
                    vistaJuego.mostrarMensaje("Ocurrió un error al jugar la carta: " + e.getMessage());
                }
            }

            private void robarCartaAzar() {
                int indexAleatorio = random.nextInt(mazoCatalogoGeneral.getCantidadCartas());
                Carta cartaRobada = mazoCatalogoGeneral.getCartas().get(indexAleatorio);

    
                jugador.recuperarEnergia(2);

                vistaJuego.mostrarMensaje("Has robado la carta: " + cartaRobada.getNombreDescriptivo() + ". Además, has recibido un bono de 2 puntos de energía por el coraje de arriesgarte a ciegas.");
                    if (jugador.getEnergia() < cartaRobada.getCostoEnergiaParaJugar()) {
                        vistaJuego.mostrarMensaje(">> ¡Mala suerte! La carta requería " + cartaRobada.getCostoEnergiaParaJugar() + " de energía y no te alcanza ni con el bono. Se descarta sin aplicar beneficios.");
                    return;
    }

                // Se aplica de inmediato con bono (conBono = true)
                try {
                    cartaRobada.jugarCarta(jugador, true);
                    vistaJuego.mostrarMensaje("Efectos de la carta aplicados con bono exitosamente.");
                } catch (IllegalArgumentException e) {
                    vistaJuego.mostrarMensaje("Error al procesar los efectos: " + e.getMessage());
                }
            }

            


            private void pasarTurno() {
            jugador.recuperarEnergia(1);
                vistaJuego.mostrarMensaje("Has recuperado 1 punto de energía por tomarte un descanso. Sabes muy bien cuando pausar y analizar la situación. ¡Bien hecho!");
            }
    
}
