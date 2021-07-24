package edu.fiuba.algo3.modelo.general;

import java.util.ArrayList;
import edu.fiuba.algo3.infraestructura.*;

public class Mazo {

    private ArrayList<Tarjeta> listaTarjetas;

    public Mazo(ArrayList<Tarjeta> listaTarjetas, IRandomizador randomizador) {
        this.listaTarjetas = listaTarjetas;
        randomizador.shuffle(this.listaTarjetas);
    }

    public Tarjeta entregarCarta(){
        return this.listaTarjetas.remove(this.listaTarjetas.size()-1);
    }


}
