package edu.fiuba.algo3.vista;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AsignadorDeColores {

    public static final Color AZUL = Color.rgb(0, 119, 187);
    public static final Color ROJO = Color.rgb(204, 51, 17);
    public static final Color AMARILLO = Color.rgb(238, 119, 51);
    public static final Color VERDE = Color.rgb(0, 153, 136);
    public static final Color ROSA = Color.rgb(238, 51, 119);
    public static final Color NEGRO = Color.rgb(0, 0, 0);

    public static Color colorEjercitoSegunElNumero(int numeroJugador){
        ArrayList<Color> colores = new ArrayList<>();
        colores.add(AZUL);
        colores.add(ROJO);
        colores.add(AMARILLO);
        colores.add(VERDE);
        colores.add(ROSA);
        colores.add(NEGRO);
        return colores.get(numeroJugador-1);
    }

    public static Color colorEtiquetaSegunElNumero(int numeroJugador){
        if(numeroJugador != 6)
            return Color.BLACK;
        return Color.WHITE;
    }

}
