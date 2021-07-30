package edu.fiuba.algo3.vista;

import java.awt.Color;

public class AsignadorDeColores {

    public static final Color AZUL = new Color(0, 119, 187);
    public static final Color ROJO = new Color(204, 51, 17);
    public static final Color AMARILLO = new Color(238, 119, 51);
    public static final Color VERDE = new Color(0, 153, 136);
    public static final Color ROSA = new Color(238, 51, 119);
    public static final Color NEGRO = new Color(0, 0, 0);

    private enum Colores{
        AZUL,
        ROJO,
        AMARILLO,
        VERDE,
        ROSA,
        NEGRO
    }

    public static Color colorSegunElNumero(int numeroJugador){
        return new Color(0,0,0);
    }

}
