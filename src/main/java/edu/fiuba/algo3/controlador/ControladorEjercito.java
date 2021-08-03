package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.vista.MenuAtaque;
import edu.fiuba.algo3.vista.MenuColocacion;
import edu.fiuba.algo3.vista.MenuReagrupar;
import javafx.scene.input.MouseEvent;

public class ControladorEjercito {

    Ronda ronda;
    MenuAtaque panelMenuAtaque;
    MenuColocacion panelMenuColocacion;
    MenuReagrupar panelMenuReagrupar;

    public ControladorEjercito(Ronda ronda, MenuAtaque panelMenuAtaque, MenuColocacion panelMenuColocacion, MenuReagrupar panelMenuReagrupar){
        this.ronda = ronda;
        this.panelMenuAtaque = panelMenuAtaque;
        this.panelMenuColocacion = panelMenuColocacion;
        this.panelMenuReagrupar = panelMenuReagrupar;
    }

    public void tocarPais(MouseEvent e, Pais unPais){
        if(this.ronda.puedeColocar()) {

            this.panelMenuColocacion.aparecerMenu(e,unPais);
        }
        else if(this.ronda.seProdujoConquista()){
            this.panelMenuReagrupar.aparecerMenu(e,unPais);
            Pais origen = this.ronda.getConquistador();
            Pais destino = this.ronda.getConquistado();
            this.panelMenuReagrupar.limitarPaises(origen, destino);
        }
        else if(this.ronda.sePuedeReagrupar()){
            this.panelMenuReagrupar.aparecerMenu(e,unPais);
            this.panelMenuReagrupar.noEstaLimitado();
        }
        else{
            this.panelMenuAtaque.aparecerMenu(e,unPais);
        }
    }

}
