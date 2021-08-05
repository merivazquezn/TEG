package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuCartas;
import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.ConjuntoTarjetas;
import edu.fiuba.algo3.modelo.general.Tarjeta;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MenuCartas extends StackPane implements Observer {

    private static final double puntoX = 260;
    private static final double puntoY = 530;
    private Ronda ronda;
    private ImageView interfazCartas;
    private Button activarTarjeta;
    private Button canjearTarjetas;
    private ArrayList<Tarjeta> tarjetasDisponibles;
    private ComboBox<String> eleccionTarjeta1;
    private ComboBox<String> eleccionTarjeta2;
    private ComboBox<String> eleccionTarjeta3;

    private void ejecutarSonidoCanjeo(){
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("./src/sonidos/canjeo.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void inicializarComboBox(){
        this.eleccionTarjeta1 = new ComboBox<>();
        this.eleccionTarjeta2 = new ComboBox<>();
        this.eleccionTarjeta3 = new ComboBox<>();
        this.eleccionTarjeta1.setTranslateY(-80);
        this.eleccionTarjeta2.setTranslateY(-30);
        this.eleccionTarjeta3.setTranslateY(20);
        this.eleccionTarjeta1.setTranslateX(-70);
        this.eleccionTarjeta2.setTranslateX(-70);
        this.eleccionTarjeta3.setTranslateX(-70);
        this.eleccionTarjeta1.valueProperty().addListener((observableValue, s, t1) -> seProdujoCambioDeElecciones());
        this.eleccionTarjeta2.valueProperty().addListener((observableValue, s, t1) -> seProdujoCambioDeElecciones());
        this.eleccionTarjeta3.valueProperty().addListener((observableValue, s, t1) -> seProdujoCambioDeElecciones());
        this.getChildren().add(this.eleccionTarjeta1);
        this.getChildren().add(this.eleccionTarjeta2);
        this.getChildren().add(this.eleccionTarjeta3);
    }

    private void inicializarBotones(){
        inicializarBotonActivarTarjeta();
        inicializarBotonCanjearTarjetas();
    }

    private void inicializarBotonActivarTarjeta() {
        this.activarTarjeta = new Button("Activar Tarjeta");
        this.activarTarjeta.setTranslateY(-80);
        this.activarTarjeta.setTranslateX(100);
        this.activarTarjeta.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            int tarjetaSeleccionada = this.eleccionTarjeta1.getSelectionModel().getSelectedIndex();
            if(this.ronda.puedeColocar() && tarjetaSeleccionada >= 0)
                ControladorMenuCartas.activarTarjeta(this.ronda, this.tarjetasDisponibles.get(tarjetaSeleccionada));
            this.setVisible(false);
            e.consume();
        });
        this.activarTarjeta.setVisible(false);
        this.getChildren().add(this.activarTarjeta);
    }

    private void inicializarBotonCanjearTarjetas() {
        this.canjearTarjetas = new Button("Canjear Tarjetas");
        this.canjearTarjetas.setTranslateY(70);
        this.canjearTarjetas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            clickearBotonCanjearTarjetas(e);
        });
        this.canjearTarjetas.setVisible(false);
        this.getChildren().add(this.canjearTarjetas);
    }

    private void clickearBotonCanjearTarjetas(MouseEvent e) {
        int tarjeta1Seleccionada = this.eleccionTarjeta1.getSelectionModel().getSelectedIndex();
        int tarjeta2Seleccionada = this.eleccionTarjeta2.getSelectionModel().getSelectedIndex();
        int tarjeta3Seleccionada = this.eleccionTarjeta3.getSelectionModel().getSelectedIndex();
        if(this.ronda.puedeColocar() && tarjeta1Seleccionada >= 0 && tarjeta2Seleccionada >= 0 && tarjeta3Seleccionada >= 0)
        {
            ejecutarCanjeoDeTarjetas(tarjeta1Seleccionada, tarjeta2Seleccionada, tarjeta3Seleccionada);
        }
        this.setVisible(false);
        e.consume();
    }

    private void ejecutarCanjeoDeTarjetas(int tarjeta1Seleccionada, int tarjeta2Seleccionada, int tarjeta3Seleccionada) {
        Tarjeta tarjeta1 = this.tarjetasDisponibles.get(tarjeta1Seleccionada);
        Tarjeta tarjeta2 = this.tarjetasDisponibles.get(tarjeta2Seleccionada);
        Tarjeta tarjeta3 = this.tarjetasDisponibles.get(tarjeta3Seleccionada);
        ControladorMenuCartas.canjearTarjetas(this.ronda, tarjeta1, tarjeta2, tarjeta3);
        ejecutarSonidoCanjeo();
    }

    public MenuCartas(Ronda ronda) throws IOException {
        inicializarInterfaz();
        inicializarCaracteristicasMenuCartas(ronda);
        inicializarEtiquetaCartasDisponibles();
        inicializarComboBox();
        inicializarBotones();
    }

    private void inicializarCaracteristicasMenuCartas(Ronda ronda) {
        this.ronda = ronda;
        this.relocate(this.puntoX, this.puntoY);
        this.setVisible(false);
    }

    private void inicializarEtiquetaCartasDisponibles() {
        Label etiquetaCartasDisponibles = new Label("Tarjetas Disponibles:");
        etiquetaCartasDisponibles.setTranslateY(-100);
        this.getChildren().add(etiquetaCartasDisponibles);
    }

    private void inicializarInterfaz() throws FileNotFoundException {
        FileInputStream inputImagenInterfaz = new FileInputStream("./src/imagenes/vistaDesplegable.png");
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.interfazCartas = new ImageView(imagenInterfaz);
        this.getChildren().add(this.interfazCartas);
    }

    public void aparecerMenu(MouseEvent evento){
        if(this.isVisible()){
            ocultarMenu(evento);
        }else if(this.ronda.puedeColocar()){
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
        eliminarEleccionDeTarjetasDelMenu();
        inicializarComboBox();
        agregarTarjetasDisponiblesAEleccionDeTarjetas();
        seleccionarPrimerElementoDeEleccionDeTarjetas();
        seProdujoCambioDeElecciones();
    }

    private void agregarTarjetasDisponiblesAEleccionDeTarjetas() {
        for( Tarjeta tar : this.tarjetasDisponibles){
            String simbolo = stringPorSigno(tar.obtenerSigno().getIdentificador());
            String nombrePaisTarjeta = simbolo + " " + tar.getPais().getNombre();
            this.eleccionTarjeta1.getItems().add(nombrePaisTarjeta);
            this.eleccionTarjeta2.getItems().add(nombrePaisTarjeta);
            this.eleccionTarjeta3.getItems().add(nombrePaisTarjeta);
        }
    }

    private void seleccionarPrimerElementoDeEleccionDeTarjetas() {
        this.eleccionTarjeta1.getSelectionModel().selectFirst();
        this.eleccionTarjeta2.getSelectionModel().selectFirst();
        this.eleccionTarjeta3.getSelectionModel().selectFirst();
    }

    private void eliminarEleccionDeTarjetasDelMenu() {
        this.getChildren().remove(this.eleccionTarjeta1);
        this.getChildren().remove(this.eleccionTarjeta2);
        this.getChildren().remove(this.eleccionTarjeta3);
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

    private void verificarActivacionDeTarjeta(){
        int tarjeta1Seleccionada = this.eleccionTarjeta1.getSelectionModel().getSelectedIndex();
        if(tarjeta1Seleccionada < 0)
            return;
        Tarjeta tarjeta1 = this.tarjetasDisponibles.get(tarjeta1Seleccionada);
        if(!tarjeta1.fueActivada() && tarjeta1.getJugador() == ronda.jugadorActual())
            this.activarTarjeta.setVisible(true);
    }

    private void verificarCanjeabilidadDeTarjetas(){
        if(this.tarjetasDisponibles.size() < 3)
            return;
        int tarjeta1Seleccionada = this.eleccionTarjeta1.getSelectionModel().getSelectedIndex();
        int tarjeta2Seleccionada = this.eleccionTarjeta2.getSelectionModel().getSelectedIndex();
        int tarjeta3Seleccionada = this.eleccionTarjeta3.getSelectionModel().getSelectedIndex();
        if(tarjeta1Seleccionada < 0 || tarjeta2Seleccionada < 0 || tarjeta3Seleccionada < 0)
            return;
        visibilizarBotonCanjeoSiTarjetasSonCanjeables(tarjeta1Seleccionada, tarjeta2Seleccionada, tarjeta3Seleccionada);
    }

    private void visibilizarBotonCanjeoSiTarjetasSonCanjeables(int tarjeta1Seleccionada, int tarjeta2Seleccionada, int tarjeta3Seleccionada) {
        Tarjeta tarjeta1 = this.tarjetasDisponibles.get(tarjeta1Seleccionada);
        Tarjeta tarjeta2 = this.tarjetasDisponibles.get(tarjeta2Seleccionada);
        Tarjeta tarjeta3 = this.tarjetasDisponibles.get(tarjeta3Seleccionada);
        if (ConjuntoTarjetas.sonCanjeables(tarjeta1, tarjeta2, tarjeta3)) {
            this.canjearTarjetas.setVisible(true);
        }
    }

    private void seProdujoCambioDeElecciones(){
        this.canjearTarjetas.setVisible(false);
        this.activarTarjeta.setVisible(false);
        verificarActivacionDeTarjeta();
        verificarCanjeabilidadDeTarjetas();
    }

}
