package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.ArrayList;

public class Continente {
    private int cantidadEjercitosNuevos;
    private String nombre;
    private ArrayList<Pais> paises;


     public Continente(ArrayList<Pais> paises, String nombre, int cantidadEjercitosNuevos) {
         this.paises = paises;
         this.nombre = nombre;
         this.cantidadEjercitosNuevos = cantidadEjercitosNuevos;

     }

     public int ejercitosParaJugador(Jugador jugador){
        if (this.ocupadoPorJugador(jugador)){
            return this.cantidadEjercitosNuevos;
        }
        return 0;
     }

     public boolean ocupadoPorJugador(Jugador jugador) {
         for (int i = 0; i< paises.size(); i++) {
            if (!paises.get(i).esDeJugador(jugador))
                return false;
         }

         return true;
     }


}
