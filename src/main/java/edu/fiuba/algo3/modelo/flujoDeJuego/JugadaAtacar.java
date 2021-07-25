package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tablero;

public class JugadaAtacar implements Jugada{

    private Pais atacante;
    private Pais defensor;
    private int cantidad;

    public JugadaAtacar(Pais atacante, Pais defensor, int cantidad){
        this.atacante = atacante;
        this.defensor = defensor;
        this.cantidad = cantidad;
    }

    public void ejecutar(Tablero tablero, Ronda ronda){
        boolean hayConquista = tablero.conquisto(this.atacante, this.defensor, this.cantidad);
        if(hayConquista)
            ronda.seProdujoConquista();
    }
}
