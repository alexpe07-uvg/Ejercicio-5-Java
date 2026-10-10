package Model;

public abstract class Carta implements Comparable<Carta> { //Comparable<Carta> para poder ordenar las cartas por costo de energía y cumplir con la opción 3 del menu principal
    private int ID;
    private String nombreDescriptivo;
    private int costoEnergiaParaJugar;
    private String descripcion;

public Carta(int ID, String nombreDescriptivo, int costoEnergiaParaJugar, String descripcion) {
        setID(ID);
        this.nombreDescriptivo = nombreDescriptivo;
        setCostoEnergiaParaJugar(costoEnergiaParaJugar);
        this.descripcion = descripcion;
    }

    public int getID() {
        return ID;
    }

    public String getNombreDescriptivo() {
        return nombreDescriptivo;
    }

    public int getCostoEnergiaParaJugar() {
        return costoEnergiaParaJugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setID(int ID) {
        if (ID < 0) {
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        }
        this.ID = ID;
    }

    public void setNombreDescriptivo(String nombreDescriptivo) {
        this.nombreDescriptivo = nombreDescriptivo;
    }

    public void setCostoEnergiaParaJugar(int costoEnergiaParaJugar) {
        if (costoEnergiaParaJugar < 0) {
            throw new IllegalArgumentException("El costo de energía de una carta no puede ser negativo.");
        }
        this.costoEnergiaParaJugar = costoEnergiaParaJugar;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public abstract void jugarCarta(Jugador jugador);


    // Overloading del método jugarCarta 
    public abstract void jugarCarta(Jugador jugador, boolean conBono);


    // método que permite ordenar las cartas por costo de energía de menor a mayor
    @Override 
    public int compareTo(Carta otraCarta) {
        return Integer.compare(this.costoEnergiaParaJugar, otraCarta.getCostoEnergiaParaJugar());
    }


    // verifica si dos cartas son iguales comparando su ID
   @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Carta)) {
            return false;
        }
        Carta otra = (Carta) obj;
        return this.ID == otra.getID();
    }


    //MUESTRA LA INFORMACION DE LA CARTA CON ATRIBUTOS GENERALES

    @Override
    public String toString() {
        return "ID: " + ID + " | Nombre: " + nombreDescriptivo + " | Costo de Energía: " + costoEnergiaParaJugar + " | Descripción: " + descripcion;
    }

}

