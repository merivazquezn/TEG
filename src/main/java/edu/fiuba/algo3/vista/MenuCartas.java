package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuCartas;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Tarjeta;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MenuCartas extends StackPane implements Observer {

    private double puntoX;
    private double puntoY;
    private Ronda ronda;
    private ImageView interfazCartas;
    private Button activarTarjeta;
    private Button canjearTarjetas;
    private ArrayList<Tarjeta> tarjetasDisponibles;
    private ComboBox<String> eleccionTarjeta1;
    private ComboBox<String> eleccionTarjeta2;
    private ComboBox<String> eleccionTarjeta3;

    private void inicializarComboBox(){
        this.eleccionTarjeta1 = new ComboBox<>();
        this.eleccionTarjeta2 = new ComboBox<>();
        this.eleccionTarjeta3 = new ComboBox<>();
        this.eleccionTarjeta1.setTranslateY(-90);
        this.eleccionTarjeta2.setTranslateY(-40);
        this.eleccionTarjeta3.setTranslateY(10);
        this.getChildren().add(this.eleccionTarjeta1);
        this.getChildren().add(this.eleccionTarjeta2);
        this.getChildren().add(this.eleccionTarjeta3);
    }

    private void inicializarBotones(){
        this.activarTarjeta = new Button("Activar Tarjeta");
        this.activarTarjeta.setTranslateY(-90);
        this.activarTarjeta.setTranslateX(40);
        this.activarTarjeta.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(this.ronda.puedeColocar())
            {
                ControladorMenuCartas.activarTarjeta(this.ronda, this.tarjetasDisponibles.get(this.eleccionTarjeta1.getSelectionModel().getSelectedIndex()));
            }
            this.setVisible(false);
            e.consume();
        });
        this.canjearTarjetas = new Button("Canjear Tarjetas");
        this.canjearTarjetas.setTranslateY(30);
        this.canjearTarjetas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(this.ronda.puedeColocar())
            {
                Tarjeta tarjeta1 = this.tarjetasDisponibles.get(this.eleccionTarjeta1.getSelectionModel().getSelectedIndex());
                Tarjeta tarjeta2 = this.tarjetasDisponibles.get(this.eleccionTarjeta2.getSelectionModel().getSelectedIndex());
                Tarjeta tarjeta3 = this.tarjetasDisponibles.get(this.eleccionTarjeta3.getSelectionModel().getSelectedIndex());
                ControladorMenuCartas.canjearTarjetas(this.ronda, tarjeta1, tarjeta2, tarjeta3);
            }
            this.setVisible(false);
            e.consume();
        });
        this.getChildren().add(this.activarTarjeta);
        this.getChildren().add(this.canjearTarjetas);
    }

    public MenuCartas(Ronda ronda) throws IOException {
        FileInputStream inputImagenInterfaz = new FileInputStream("./src/imagenes/vistaDesplegable.png");
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.interfazCartas = new ImageView(imagenInterfaz);
        this.ronda = ronda;
        Label etiquetaCartasDisponibles = new Label("Tarjetas Disponibles:");
        etiquetaCartasDisponibles.setTranslateY(-100);
        this.getChildren().add(this.interfazCartas);
        inicializarComboBox();
        inicializarBotones();
        this.getChildren().add(etiquetaCartasDisponibles);
        this.puntoX = 160;
        this.puntoY = 530;
        this.relocate(this.puntoX, this.puntoY);
        this.setVisible(false);
    }

    public void aparecerMenu(MouseEvent evento){
        if(this.isVisible()){
            ocultarMenu(evento);
        }else{
            this.setVisible(true);
        }
    }

    public void ocultarMenu(MouseEvent evento){
        if(!this.adentro(evento.getSceneX(), evento.getSceneY())){
            this.setVisible(false);
        }
    }

    private boolean adentro(double mx, double my){
        return(mx >= this.puntoX && mx <= this.puntoX+100 && my >= this.puntoY && my <= this.puntoY+200);
    }

    private void actualizarTarjetasDisponibles(){
        this.eleccionTarjeta1.getItems().removeAll();
        this.eleccionTarjeta2.getItems().removeAll();
        this.eleccionTarjeta3.getItems().removeAll();
        for( Tarjeta tar : this.tarjetasDisponibles){
            String simbolo = stringPorSigno(tar.obtenerSigno().getIdentificador());
            String nombrePaisTarjeta = simbolo + " " + tar.getPais().getNombre();
            eleccionTarjeta1.getItems().add(nombrePaisTarjeta);
            eleccionTarjeta2.getItems().add(nombrePaisTarjeta);
            eleccionTarjeta3.getItems().add(nombrePaisTarjeta);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.tarjetasDisponibles = new ArrayList<>();
        Jugador jugadorActual = this.ronda.jugadorActual();
        this.tarjetasDisponibles = jugadorActual.obtenerTarjetas();
        actualizarTarjetasDisponibles();
    }

    public String stringPorSigno(int identificadorSigno){
        switch(identificadorSigno){
            case 0:
                return "#";
            case 1:
                return "-";
            case 2:
                return "+";
            default:
                return "*";
        }
    }

}
