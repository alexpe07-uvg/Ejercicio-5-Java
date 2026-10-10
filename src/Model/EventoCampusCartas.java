package Model;

public class EventoCampusCartas extends Carta {
    private int efectoCreditos;

public EventoCampusCartas(int ID, String nombreDescriptivo, int costoEnergiaParaJugar, String descripcion, int efectoCreditos) {
        super(ID, nombreDescriptivo, costoEnergiaParaJugar, descripcion);
        this.efectoCreditos = efectoCreditos;
}


public int getEfectoCreditos() {
    return efectoCreditos;
}

public void setEfectoCreditos(int efectoCreditos) {
    if (efectoCreditos < 0) {
        throw new IllegalArgumentException("El efecto de créditos no puede ser negativo al ser actividades del campus.");
    }
    this.efectoCreditos = efectoCreditos;
}

@Override
public void jugarCarta(Jugador jugador, boolean conBono) {
    jugador.reducirEnergia(this.getCostoEnergiaParaJugar());

   
    int totalCreditos = this.efectoCreditos;
    if (conBono) {
        jugador.recuperarEnergia(1);
    }
    jugador.agregarCreditos(totalCreditos);
}

@Override 
public void jugarCarta(Jugador jugador) {
    jugarCarta(jugador, false);

}


@Override 
public String toString() {
    return super.toString() + " (tipo de carta: Evento de Campus)";
}



    
}
