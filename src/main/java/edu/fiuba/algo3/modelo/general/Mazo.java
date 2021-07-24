package edu.fiuba.algo3.modelo.general;

import java.util.ArrayList;
import edu.fiuba.algo3.infraestructura.*;

public class Mazo {

    private ArrayList<Tarjeta> listaTarjetas;
    IRandomizador randomizador;

    public Mazo(ArrayList<Tarjeta> listaTarjetas, IRandomizador randomizador) {
        this.listaTarjetas = listaTarjetas;
        this.randomizador = randomizador;
        randomizador.shuffle(this.listaTarjetas);
    }

    public Tarjeta entregarCarta(){
        return this.listaTarjetas.remove(this.listaTarjetas.size()-1);
    }

    public int largoMazo() {
        return this.listaTarjetas.size();
    }

    public void agregarTarjetas(Tarjeta t1, Tarjeta t2, Tarjeta t3){
        this.listaTarjetas.add(t1);
        this.listaTarjetas.add(t2);
        this.listaTarjetas.add(t3);
        this.randomizador.shuffle(this.listaTarjetas);
    }

}
