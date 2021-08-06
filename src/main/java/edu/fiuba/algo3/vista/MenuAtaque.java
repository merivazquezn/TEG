package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuColocacion;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MenuAtaque extends VistaMenuDesplegable implements Observer {
    private static MenuAtaque instancia;
    private Label etiquetaMenuAtaque;
    private Button botonMenuAtaque;
    private Button botonCancelar;

    private EstadoAtaque estadoActual;

    private Pais paisActual;
    private int jugadorActual;

    private MenuAtaque(Ronda ronda) throws IOException {
        super(ronda, 0, 0);
        this.estadoActual = new EstadoAtaque(ronda);
        inicializarEtiquetaMenuAtaque();
        inicializarBotonMenuAtaque();
        inicializarBotonCancelar();
        agregarElementosAlMenu();
    }

    private void inicializarEtiquetaMenuAtaque() {
        this.etiquetaMenuAtaque = new Label("");
        this.etiquetaMenuAtaque.setStyle("-fx-font: 22 arial;");
        this.etiquetaMenuAtaque.setTranslateY(-35);
    }

    private void inicializarBotonMenuAtaque() {
        this.botonMenuAtaque = new Button("Realizar ataque");
        this.botonMenuAtaque.setTranslateY(-5);
        this.botonMenuAtaque.setStyle("-fx-background-color: #f2f2e9; -fx-font-size: 10;");
        this.botonMenuAtaque.setMaxWidth(130);
        this.botonMenuAtaque.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            estadoActual.agregarPais(this.jugadorActual, this.paisActual);
            this.setVisible(false);
            e.consume();
        });
    }

    private void inicializarBotonCancelar() {
        this.botonCancelar = new Button("Cancelar ataque");
        this.botonCancelar.setStyle("-fx-background-color: #f2f2e9; -fx-font-size: 10;");
        this.botonCancelar.setMaxWidth(130);
        this.botonCancelar.setTranslateY(25);
        this.botonCancelar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            estadoActual.resetear();
            this.setVisible(false);
            e.consume();
        });
    }

    private void agregarElementosAlMenu() {
        this.getChildren().add(this.etiquetaMenuAtaque);
        this.getChildren().add(this.botonMenuAtaque);
        this.getChildren().add(this.botonCancelar);
    }

    public static void crearInstancia(Ronda ronda) throws IOException{
        if(instancia == null){
            instancia = new MenuAtaque(ronda);
        }
    }

    public static MenuAtaque obtenerInstancia(){
        return instancia;
    }

    public void establecerBotonesVisibles(Pais unPais){
        this.botonCancelar.setVisible(false);
        this.botonMenuAtaque.setVisible(false);
        if(this.estadoActual.visibilizaAtacante(this.jugadorActual, unPais)){
            visibilizarAtacante();
        }
        else if(this.estadoActual.visibilizaDefensor(this.jugadorActual, unPais)){
            visibilizarDefensor();
        }
    }

    private void visibilizarDefensor() {
        this.botonMenuAtaque.setVisible(true);
        this.botonMenuAtaque.setText("Confirmar ataque");
        this.botonMenuAtaque.setStyle("-fx-background-color: #f2f2e9; -fx-font-size: 10;");
        this.botonCancelar.setVisible(true);
    }

    private void visibilizarAtacante() {
        this.botonMenuAtaque.setVisible(true);
        this.botonMenuAtaque.setText("Realizar ataque desde aquí");
        this.botonMenuAtaque.setMaxWidth(160);
        this.botonMenuAtaque.setStyle("-fx-background-color: #f2f2e9; -fx-font-size: 10;");
    }

    public void aparecerMenu(MouseEvent evento, Pais unPais){
        String nombrePais = unPais.getNombre();
        this.setVisible(true);
        this.aparecer(evento.getSceneX(), evento.getSceneY());
        this.paisActual = unPais;
        this.etiquetaMenuAtaque.setText(nombrePais);
        this.etiquetaMenuAtaque.setStyle("-fx-text-fill: #f2f2e9; -fx-font-size: 16; -fx-font-weight: bold;");
        establecerBotonesVisibles(unPais);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.jugadorActual = this.ronda.jugadorActual().getNumero();
    }
}
