package Model;

public class CursoCartas extends Carta {
    private int creditos;
    private int dificultad;


public CursoCartas(int ID, String nombreDescriptivo, int costoEnergiaParaJugar, String descripcion, int creditos, int dificultad) {
    super(ID, nombreDescriptivo, costoEnergiaParaJugar, descripcion);
    setCreditos(creditos);
    setDificultad(dificultad);
}


public int getCreditos() {
    return creditos;
}

// escala creditos de 1 a 5
public void setCreditos(int creditos) {
    if (creditos <= 0) {
        throw new IllegalArgumentException("Los créditos no pueden ser negativos.");
    }
    this.creditos = creditos;
    if (creditos > 5) {
        throw new IllegalArgumentException("Los créditos no pueden ser mayores a 5.");
    }
}

public int getDificultad() {
    return dificultad;
}


//escala dificultad de 1 a 10
public void setDificultad(int dificultad) {
    if (dificultad <= 0) {
        throw new IllegalArgumentException("La dificultad no puede ser negativa.");
    }
    this.dificultad = dificultad;
    if (dificultad > 10) {
        throw new IllegalArgumentException("La dificultad no puede ser mayor a 10.");
    }
}


@Override
    public void jugarCarta(Jugador jugador, boolean conBono) {
        jugador.reducirEnergia(this.getCostoEnergiaParaJugar());
        int creditosFinales = this.creditos;
        if (conBono) {
            creditosFinales = creditosFinales + 1;
        }
        jugador.agregarCreditos(creditosFinales);
    }

    // Sobrecarga estándar (llama al método principal con false)
    @Override
    public void jugarCarta(Jugador jugador) {
        jugarCarta(jugador, false);
    }

@Override
public String toString() {
    return super.toString() + " | Créditos: " + creditos + " | Dificultad: " + dificultad + " (tipo de carta: Curso)";
}

    
}
