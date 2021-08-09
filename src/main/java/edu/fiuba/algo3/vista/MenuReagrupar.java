package edu.fiuba.algo3.vista;


import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Integer.parseInt;

public class MenuReagrupar extends VistaMenuDesplegable implements Observer {
    private static MenuReagrupar instancia;
    private Label etiquetaMenuReagrupacion;
    private Button botonMenuReagrupacion;
    private Button botonCancelar;

    private TextField inputCantidad;

    private int jugadorActual;
    private Pais paisActual;

    private EstadoReagrupar estadoActual;
    private int cantidadMaximaTransferible;

    private MenuReagrupar(Ronda ronda) throws IOException {
        super(ronda, 0, 0);
        establecerJugadorYEstadoActual(ronda);
        inicializarEtiquetaMenuReagrupacion();
        inicializarBotonReagrupacion();
        inicializarBotonCancelar();
        inicializarInputCantidad();
        agregarElementosAlMenu();
    }

    private void establecerJugadorYEstadoActual(Ronda ronda) {
        this.estadoActual = new EstadoReagrupar(ronda);
        this.jugadorActual = ronda.jugadorActual().getNumero();
    }

    private void inicializarEtiquetaMenuReagrupacion() {
        this.etiquetaMenuReagrupacion = new Label("");
        this.etiquetaMenuReagrupacion.setStyle("-fx-text-fill: #f2f2e9; -fx-font-size: 22; -fx-font-weight: bold;");
        this.etiquetaMenuReagrupacion.setTranslateY(-35);
    }

    private void inicializarBotonReagrupacion() {
        this.botonMenuReagrupacion = new Button("Mover ejercitos");
        this.botonMenuReagrupacion.setTranslateY(-10);
        this.botonMenuReagrupacion.setStyle("-fx-font-size: 10; -fx-background-color: #f2f2e9;");
        this.botonMenuReagrupacion.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            this.estadoActual.agregarPais(this.paisActual, parseInt(this.inputCantidad.getText()));
            this.setVisible(false);
            e.consume();
        });
    }

    private void inicializarBotonCancelar() {
        this.botonCancelar = new Button("Cancelar reagrupacion");
        this.botonCancelar.setTranslateY(25);
        this.botonCancelar.setStyle("-fx-font-size: 10; -fx-background-color: #f2f2e9;");
        this.botonCancelar.setMaxWidth(120);
        this.botonCancelar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            estadoActual.resetear();
            this.setVisible(false);
            e.consume();
        });
    }

    private void inicializarInputCantidad() {
        this.inputCantidad = new TextField();
        this.inputCantidad.setTranslateY(25);
        this.inputCantidad.setPrefWidth(50);
        this.inputCantidad.setMaxWidth(50);
        this.inputCantidad.setAlignment(Pos.CENTER);
        establecerParametrosValidosDelInput();
    }

    private void establecerParametrosValidosDelInput() {
        this.inputCantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputCantidad.setText("0");
            }
            newValue = inputCantidad.getText();

            try {
                if(parseInt(newValue) < 0 || parseInt(newValue) > cantidadMaximaTransferible){
                    inputCantidad.setText("0");
                } else {
                    inputCantidad.setText(String.valueOf(parseInt(newValue)));
                }

            } catch (NumberFormatException e) {

            }

        });
    }

    private void agregarElementosAlMenu() {
        this.getChildren().add(this.etiquetaMenuReagrupacion);
        this.getChildren().add(this.botonMenuReagrupacion);
        this.getChildren().add(this.botonCancelar);
        this.getChildren().add(this.inputCantidad);
    }

    public static void crearInstancia(Ronda ronda) throws IOException{
        if(instancia == null){
            instancia = new MenuReagrupar(ronda);
        }
    }

    public static MenuReagrupar obtenerInstancia(){
        return instancia;
    }

    public void establecerBotonesVisibles(Pais unPais) {
        this.botonCancelar.setVisible(false);
        this.botonMenuReagrupacion.setVisible(false);
        this.inputCantidad.setVisible(false);

        if(this.estadoActual.visibilizaDestino(this.jugadorActual, unPais)){
            visibilizarDestino();
        }
        else if(this.estadoActual.visibilizaOrigen(this.jugadorActual, unPais)){
            visibilizarOrigen();
        }
    }

    private void visibilizarOrigen() {
        this.botonMenuReagrupacion.setVisible(true);
        this.botonMenuReagrupacion.setText("Mover ejercitos");
        this.inputCantidad.setVisible(true);
    }

    private void visibilizarDestino() {
        this.botonMenuReagrupacion.setVisible(true);
        this.botonMenuReagrupacion.setText("Confirmar transferencia");
        this.botonCancelar.setVisible(true);
    }

    public void aparecerMenu(MouseEvent evento, Pais unPais){
        this.inputCantidad.setText("");
        String nombrePais = unPais.getNombre();
        this.paisActual = unPais;

        this.cantidadMaximaTransferible = unPais.getCantidadEjercitos()-1;

        this.setVisible(true);
        this.aparecer(evento.getSceneX(), evento.getSceneY());
        this.etiquetaMenuReagrupacion.setText(nombrePais);

        establecerBotonesVisibles(unPais);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.jugadorActual = this.ronda.jugadorActual().getNumero();
        this.estadoActual.resetear();
    }
}
