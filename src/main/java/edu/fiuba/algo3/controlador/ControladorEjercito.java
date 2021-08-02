package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.vista.MenuAtaque;
import edu.fiuba.algo3.vista.MenuColocacion;
import javafx.scene.input.MouseEvent;

public class ControladorEjercito {

    Ronda ronda;
    MenuAtaque panelMenuAtaque;
    MenuColocacion panelMenuColocacion;

    public ControladorEjercito(Ronda ronda, MenuAtaque panelMenuAtaque, MenuColocacion panelMenuColocacion){
        this.ronda = ronda;
        this.panelMenuAtaque = panelMenuAtaque;
        this.panelMenuColocacion = panelMenuColocacion;
    }

    public void tocarPais(MouseEvent e, Pais unPais){
        if(this.ronda.puedeAvanzar()) {
            this.panelMenuAtaque.aparecerMenu(e, unPais);
        }
        else{
            this.panelMenuColocacion.aparecerMenu(e,unPais);
        }
    }

}
