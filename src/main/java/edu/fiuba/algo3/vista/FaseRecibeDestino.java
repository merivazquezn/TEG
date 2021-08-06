package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuReagrupar;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class FaseRecibeDestino implements FaseEstadoReagrupacion{

    private Pais origen;
    private int cantidad;

    public FaseRecibeDestino(Pais unPais, int cantidad){
        this.origen = unPais;
        this.cantidad = cantidad;
    }

    public FaseEstadoReagrupacion agregarPais(Ronda ronda, Pais unPais, int cantidad) {
        if(unPais.sonLimitrofes(this.origen)){
            ControladorMenuReagrupar.realizarJugada(ronda, this.origen, unPais, this.cantidad);

            try {
                Reproductor.reproducirSonido("./src/sonidos/soldier_deploy.wav");
            } catch (UnsupportedAudioFileException error) {
                error.printStackTrace();
            } catch (IOException | LineUnavailableException error) {
                error.printStackTrace();
            }

            return new FaseRecibeOrigen();
        }
        return this;
    }

    public boolean visibilizarDestino(int numeroJugadorActual, Pais unPais) {
        return (this.origen.sonLimitrofes(unPais) && numeroJugadorActual == unPais.getJugador().getNumero());
    }
}
