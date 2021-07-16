package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Continente {
    private Integer cantidadEjercitosNuevos;
    private String nombre;
    private ArrayList<Pais> paises;


     public Continente(ArrayList<Pais> paises, String nombre, Integer cantidadEjercitosNuevos) {
         this.paises = paises;
         this.nombre = nombre;
         this.cantidadEjercitosNuevos = cantidadEjercitosNuevos;
     }


}
