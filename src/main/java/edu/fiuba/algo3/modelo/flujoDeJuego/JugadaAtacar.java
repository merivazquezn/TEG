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

    public int cantidadEjercitosAtacantes(Pais pais) {
        int cant = pais.getCantidadEjercitos();

        if (cant>3)
            return 3;

        return cant-1;
    }

    public JugadaAtacar(Pais atacante, Pais defensor){
        this.atacante = atacante;
        this.defensor = defensor;
        this.cantidad = this.cantidadEjercitosAtacantes(atacante);
    }

    public void ejecutar(Tablero tablero, Ronda ronda){
        boolean hayConquista = tablero.conquisto(this.atacante, this.defensor, this.cantidad);
        if(hayConquista)
            ronda.seProdujoConquista();

        tablero.notifyObservers();
    }
}
