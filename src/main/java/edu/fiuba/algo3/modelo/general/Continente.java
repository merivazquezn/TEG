package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.ArrayList;
import java.util.Map;

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
         for (Pais pais : this.paises) {
             if (!pais.esDeJugador(jugador))
                 return false;
         }

         return true;
     }
     public int cantidadPaisesDeJugador(Jugador jugador){
         int cantidadPaises = 0;
         for (Pais pais : this.paises) {
             if (pais.esDeJugador(jugador))
                 cantidadPaises++;
         }
         return cantidadPaises;
     }

     public String getNombre() {
         return this.getNombre();
     }

     public ArrayList<Pais> getListaPaises() {
        return this.paises;
     }

}
