package Model;

public class ProfesorCartas extends Carta {
    private String departamento;
    private int puntosLlamadaDeAtencion;
    private int tiempoAtencion;


public ProfesorCartas (int ID, String nombreDescriptivo, int costoEnergiaParaJugar, String descripcion, String departamento, int puntosLlamadaDeAtencion, int tiempoAtencion) {
    super(ID, nombreDescriptivo, costoEnergiaParaJugar, descripcion);
    this.departamento = departamento;
    setPuntosLlamadaDeAtencion(puntosLlamadaDeAtencion);
    setTiempoAtencion(tiempoAtencion);

}

public String getDepartamento() {
    return departamento;
}

public void setDepartamento(String departamento) {
    this.departamento = departamento;
}

public int getPuntosLlamadaDeAtencion() {
    return puntosLlamadaDeAtencion;
}

public void setPuntosLlamadaDeAtencion(int puntosLlamadaDeAtencion) {
    if (puntosLlamadaDeAtencion < 0) {
        throw new IllegalArgumentException("Los puntos de llamada de atención no pueden ser negativos.");
    }
    this.puntosLlamadaDeAtencion = puntosLlamadaDeAtencion;

}

public int getTiempoAtencion() {
    return tiempoAtencion;
}

public void setTiempoAtencion(int tiempoAtencion) {
    if (tiempoAtencion <= 0) {
        throw new IllegalArgumentException("El tiempo de atención por catedrático debe ser mayor a 0 minutos.");
    }
    this.tiempoAtencion = tiempoAtencion;
}

@Override
    public void jugarCarta(Jugador jugador, boolean conBono) {
        jugador.reducirEnergia(this.getCostoEnergiaParaJugar());
        jugador.agregarLlamadasAtencion(this.puntosLlamadaDeAtencion);

        // lógica para recuperar energía teniendo como tiempos de atención 30, 60 y 90min tal y como se establecen en el mazo de cartas
        int energiaRecuperada = this.tiempoAtencion / 30;
        if (conBono) {
            energiaRecuperada = energiaRecuperada + 1;
        }
        jugador.recuperarEnergia(energiaRecuperada);
    }

    @Override
    public void jugarCarta(Jugador jugador) {
        jugarCarta(jugador, false);
    }

@Override 
public String toString() {
    return super.toString() + " | Departamento: " + departamento + " | Puntos de Llamada de Atención: " + puntosLlamadaDeAtencion + " | Tiempo de Atención: " + tiempoAtencion + " minutos (tipo de carta: Catedrático)";
}
}
