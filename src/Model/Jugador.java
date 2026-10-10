package Model;

public class Jugador {
    private String nombreJugador;
    private int energia;
    private int creditos;
    private int llamadasDeAtencion;

    // Constructor rápido para controlador ya que el resto de atributos se inicializan con valores por defecto
    public Jugador(String nombreJugador) {
        this(nombreJugador, 10, 0, 0);
    }

    //Overloading en constructor
    
    // Constructor completo
    public Jugador(String nombreJugador, int energia, int creditos, int llamadasDeAtencion) {
        setNombreJugador(nombreJugador);
        this.energia = energia;
        this.creditos = creditos;
        this.llamadasDeAtencion = llamadasDeAtencion;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }


    public void setNombreJugador(String nombreJugador) {
        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío.");
        }
        this.nombreJugador = nombreJugador;
    }

    public int getEnergia() {
        return energia;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getLlamadasdeAtencion() {
        return llamadasDeAtencion;
    }


    //ACCIONES DEPENDIENDO DE LA CARTA JUGADA
    public void reducirEnergia (int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a reducir no puede ser negativa.");
        }
        this.energia -= cantidad;
        if (this.energia < 0) {
            this.energia = 0; // Evita que la energía sea negativa
        }
    }

    public void recuperarEnergia (int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a recuperar no puede ser negativa.");
        }
        this.energia += cantidad;
        if (this.energia > 10) {
            this.energia = 10; // Evita que la energía exceda el límite
        }
    }

    public void agregarLlamadasAtencion (int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad de llamadas de atención no puede ser negativa.");
        }
        this.llamadasDeAtencion += cantidad;
        if (this.llamadasDeAtencion < 0) {
            this.llamadasDeAtencion = 0; // Evita que las llamadas de atención sean negativas
        }
    }

    public void agregarCreditos (int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad de créditos no puede ser negativa.");
        }
        this.creditos += cantidad;
        if (this.creditos < 0) {
            this.creditos = 0; // Evita que los créditos a agregar sean negativos
        }
    }

        //Reglas de fin de juego

        public boolean haGanado() {
            return this.creditos >= 20; // El jugador gana si tiene 20 o más créditos
        }

        public boolean haPerdido() {
            return this.llamadasDeAtencion >= 5 || this.energia <= 0; // El jugador pierde si tiene 5 o más llamadas de atención o si se queda sin energía disponible
        }

        @Override 
        public String toString() {
            return "Jugador: " + nombreJugador + " | Energía: " + energia + " /10 | Créditos: " + creditos + " /20 | Faltas: " + llamadasDeAtencion + " /5";
        }
    
    }

    

    




