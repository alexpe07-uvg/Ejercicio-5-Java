import View.VistaJuego;
import Controller.ControladorJuego;

public class Main {

    //se llama al resto de clases (cada una en su categoría MVC) para iniciar la partida
    public static void main(String[] args) {
        VistaJuego vista = new VistaJuego();
        ControladorJuego controlador = new ControladorJuego(vista);
        controlador.iniciar();
    }
       
}

