package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.vista.MenuAtaque;
import javafx.scene.input.MouseEvent;

public class ControladorEjercito {

    Ronda ronda;
    MenuAtaque panelMenuAtaque;

    private enum Estado{
        FASE_ATAQUE,
        FASE_COLOCACION,
        FASE_REAGRUPE
    }

    Estado estadoActual;

    public ControladorEjercito(Ronda ronda, MenuAtaque panelMenuAtaque){
        this.ronda = ronda;
        this.panelMenuAtaque = panelMenuAtaque;
        this.estadoActual = Estado.FASE_COLOCACION;
    }

    public void tocarPais(MouseEvent e, Pais unPais){
        panelMenuAtaque.aparecerMenu(e, unPais);
    }

}
