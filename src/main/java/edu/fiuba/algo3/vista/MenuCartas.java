package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuCartas;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.ConjuntoTarjetas;
import edu.fiuba.algo3.modelo.general.Tarjeta;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MenuCartas extends VistaMenu implements Observer {

    private static MenuCartas instancia;
    private ImageView interfazCartas;
    private Button botonActivarTarjeta;
    private Button botonCanjearTarjetas;
    private Button botonInformacionExtra;
    private Label etiquetaInformacionExtra;
    private ArrayList<Tarjeta> tarjetasDisponibles;
    private ComboBox<String> eleccionTarjeta1;
    private ComboBox<String> eleccionTarjeta2;
    private ComboBox<String> eleccionTarjeta3;

    private void ejecutarSonidoCanjeo(){
        try {
            Reproductor.reproducirSonido("./src/sonidos/canjeo.wav");
            //AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("./src/sonidos/canjeo.wav"));
            //Clip clip = AudioSystem.getClip();
            //clip.open(audioInput);
            //clip.start();
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
        inicializarBotonInformacionExtra();
    }

    private void inicializarBotonActivarTarjeta() {
        this.botonActivarTarjeta = new Button("Activar Tarjeta");
        this.botonActivarTarjeta.setTranslateY(-80);
        this.botonActivarTarjeta.setTranslateX(100);
        this.botonActivarTarjeta.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            clickearBotonActivarTarjeta(e);
        });
        this.botonActivarTarjeta.setVisible(false);
        this.getChildren().add(this.botonActivarTarjeta);
    }

    private void inicializarBotonInformacionExtra() {
        this.botonInformacionExtra = new Button("?");
        this.botonInformacionExtra.setTranslateY(105);
        this.botonInformacionExtra.setTranslateX(-120);
        this.botonInformacionExtra.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(this.etiquetaInformacionExtra.isVisible())
                this.etiquetaInformacionExtra.setVisible(false);
            else
                this.etiquetaInformacionExtra.setVisible(true);
        });
        this.getChildren().add(this.botonInformacionExtra);
    }

    private void inicializarEtiquetaInformacionExtra() {
        this.etiquetaInformacionExtra = new Label("El simbolo * es comodín");
        this.etiquetaInformacionExtra.setStyle("-fx-text-fill: #f2f2e9");
        this.etiquetaInformacionExtra.setTranslateY(105);
        this.etiquetaInformacionExtra.setTranslateX(-40);
        this.etiquetaInformacionExtra.setVisible(false);
        this.getChildren().add(this.etiquetaInformacionExtra);
    }

    private void clickearBotonActivarTarjeta(MouseEvent e) {
        int tarjetaSeleccionada = this.eleccionTarjeta1.getSelectionModel().getSelectedIndex();
        if(this.ronda.puedeColocar() && tarjetaSeleccionada >= 0)
            ControladorMenuCartas.activarTarjeta(this.ronda, this.tarjetasDisponibles.get(tarjetaSeleccionada));
        this.setVisible(false);
        e.consume();
    }

    private void inicializarBotonCanjearTarjetas() {
        this.botonCanjearTarjetas = new Button("Canjear Tarjetas");
        this.botonCanjearTarjetas.setTranslateY(70);
        this.botonCanjearTarjetas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            clickearBotonCanjearTarjetas(e);
        });
        this.botonCanjearTarjetas.setVisible(false);
        this.getChildren().add(this.botonCanjearTarjetas);
    }

    public static void crearInstancia(Ronda ronda) throws IOException{
        if(instancia == null){
            instancia = new MenuCartas(ronda);
        }
    }

    public static MenuCartas obtenerInstancia(){
        return instancia;
    }

    private void clickearBotonCanjearTarjetas(MouseEvent e) {
        int tarjeta1Seleccionada = this.eleccionTarjeta1.getSelectionModel().getSelectedIndex();
        int tarjeta2Seleccionada = this.eleccionTarjeta2.getSelectionModel().getSelectedIndex();
        int tarjeta3Seleccionada = this.eleccionTarjeta3.getSelectionModel().getSelectedIndex();
        if(sePuedeCanjear(tarjeta1Seleccionada, tarjeta2Seleccionada, tarjeta3Seleccionada)) {
            ejecutarCanjeoDeTarjetas(tarjeta1Seleccionada, tarjeta2Seleccionada, tarjeta3Seleccionada);
        }
        this.setVisible(false);
        e.consume();
    }

    private boolean sePuedeCanjear(int tarjeta1Seleccionada, int tarjeta2Seleccionada, int tarjeta3Seleccionada) {
        return this.ronda.puedeColocar() && tarjeta1Seleccionada >= 0 && tarjeta2Seleccionada >= 0 && tarjeta3Seleccionada >= 0;
    }

    private void ejecutarCanjeoDeTarjetas(int tarjeta1Seleccionada, int tarjeta2Seleccionada, int tarjeta3Seleccionada) {
        Tarjeta tarjeta1 = this.tarjetasDisponibles.get(tarjeta1Seleccionada);
        Tarjeta tarjeta2 = this.tarjetasDisponibles.get(tarjeta2Seleccionada);
        Tarjeta tarjeta3 = this.tarjetasDisponibles.get(tarjeta3Seleccionada);
        ControladorMenuCartas.canjearTarjetas(this.ronda, tarjeta1, tarjeta2, tarjeta3);
        ejecutarSonidoCanjeo();
    }

    private MenuCartas(Ronda ronda) throws IOException {
        super(ronda, 260, 530);
        inicializarInterfaz();
        inicializarCaracteristicasMenuCartas(ronda);
        inicializarEtiquetaCartasDisponibles();
        inicializarComboBox();
        inicializarBotones();
        inicializarEtiquetaInformacionExtra();
    }

    private void inicializarCaracteristicasMenuCartas(Ronda ronda) {
        this.ronda = ronda;
        this.relocate(this.puntoX, this.puntoY);
        this.setVisible(false);
    }

    private void inicializarEtiquetaCartasDisponibles() {
        Label etiquetaCartasDisponibles = new Label("Tarjetas Disponibles:");
        etiquetaCartasDisponibles.setStyle("-fx-text-fill: #f2f2e9");
        etiquetaCartasDisponibles.setTranslateY(-105);
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
            this.etiquetaInformacionExtra.setVisible(false);
        }
    }

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX && mx <= this.puntoX+300 && my >= this.puntoY && my <= this.puntoY+250);
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
            agregarTarjetaAEleeccionTarjeta(tar);
        }
    }

    private void agregarTarjetaAEleeccionTarjeta(Tarjeta tar) {
        String simbolo = stringPorSigno(tar.obtenerSigno().getIdentificador());
        String nombrePaisTarjeta = simbolo + " " + tar.getPais().getNombre();
        this.eleccionTarjeta1.getItems().add(nombrePaisTarjeta);
        this.eleccionTarjeta2.getItems().add(nombrePaisTarjeta);
        this.eleccionTarjeta3.getItems().add(nombrePaisTarjeta);
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
        if(!tarjeta1.fueActivada() && tarjeta1.getJugador() == this.ronda.jugadorActual())
            this.botonActivarTarjeta.setVisible(true);
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
            this.botonCanjearTarjetas.setVisible(true);
        }
    }

    private void seProdujoCambioDeElecciones(){
        this.botonCanjearTarjetas.setVisible(false);
        this.botonActivarTarjeta.setVisible(false);
        verificarActivacionDeTarjeta();
        verificarCanjeabilidadDeTarjetas();
    }

}
