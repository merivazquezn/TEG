package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.general.Tablero;
import javafx.scene.control.Button;
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

public class VistaDados extends StackPane implements Observer {
    private static VistaDados instancia;
    private ImageView imagenInterfaz;
    private ArrayList<ImageView> dadosAtacante;
    private ArrayList<ImageView> dadosDefensor;
    private ArrayList<Image> imagenesDados;
    private Image imagenTickGanador;
    private Image imagenCruzPerdedor;
    private ArrayList<ImageView> resultadoDadosAtacante;
    private ArrayList<ImageView> resultadoDadosDefensor;
    private Button botonCerrar;
    private Tablero tablero;

    static final int MAX_DADOS = 3;

    private void ejecutarSonidoPelea(){
        try {
            ArrayList<String> sonidosPelea = new ArrayList<>();
            sonidosPelea.add("./src/sonidos/pelea1.wav");
            sonidosPelea.add("./src/sonidos/pelea2.wav");

            Reproductor.reproducirSonido(sonidosPelea);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void inicializarBotonCerrar(){
        this.botonCerrar = new Button("X");
        this.botonCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            this.setVisible(false);
            ejecutarSonidoPelea();
            e.consume();
        });
        this.botonCerrar.setTranslateX(220);
        this.botonCerrar.setTranslateY(-100);
        this.getChildren().add(this.botonCerrar);
    }

    private void inicializarImagenesDados() throws IOException{
        this.imagenesDados = new ArrayList<>();
        String rutaDado1 = "./src/imagenes/dado1.png";
        String rutaDado2 = "./src/imagenes/dado2.png";
        String rutaDado3 = "./src/imagenes/dado3.png";
        String rutaDado4 = "./src/imagenes/dado4.png";
        String rutaDado5 = "./src/imagenes/dado5.png";
        String rutaDado6 = "./src/imagenes/dado6.png";
        FileInputStream inputImagenDado1 = new FileInputStream(rutaDado1);
        Image imagenDado1 = new Image(inputImagenDado1);
        FileInputStream inputImagenDado2 = new FileInputStream(rutaDado2);
        Image imagenDado2 = new Image(inputImagenDado2);
        FileInputStream inputImagenDado3 = new FileInputStream(rutaDado3);
        Image imagenDado3 = new Image(inputImagenDado3);
        FileInputStream inputImagenDado4 = new FileInputStream(rutaDado4);
        Image imagenDado4 = new Image(inputImagenDado4);
        FileInputStream inputImagenDado5 = new FileInputStream(rutaDado5);
        Image imagenDado5 = new Image(inputImagenDado5);
        FileInputStream inputImagenDado6 = new FileInputStream(rutaDado6);
        Image imagenDado6 = new Image(inputImagenDado6);
        this.imagenesDados.add(imagenDado1);
        this.imagenesDados.add(imagenDado2);
        this.imagenesDados.add(imagenDado3);
        this.imagenesDados.add(imagenDado4);
        this.imagenesDados.add(imagenDado5);
        this.imagenesDados.add(imagenDado6);
        inicializarImagenesGanoOPerdio();

    }

    private void inicializarImagenesGanoOPerdio() throws FileNotFoundException {
        String rutaDadoGanador = "./src/imagenes/tildeAfirmativo.png";
        String rutaDadoPerdedor = "./src/imagenes/cruzNegadora.png";
        FileInputStream inputImagenDadoGanador = new FileInputStream(rutaDadoGanador);
        this.imagenTickGanador = new Image(inputImagenDadoGanador);
        FileInputStream inputImagenDadoPerdedor = new FileInputStream(rutaDadoPerdedor);
        this.imagenCruzPerdedor = new Image(inputImagenDadoPerdedor);
    }

    private void inicializarInterfazDados() throws IOException{
        String rutaInterfazDado = "./src/imagenes/vistaDados.png";
        FileInputStream inputImagenInterfaz = new FileInputStream(rutaInterfazDado);
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.imagenInterfaz = new ImageView(imagenInterfaz);
        this.getChildren().add(this.imagenInterfaz);
    }

    private void inicializarEtiquetasJugadores(){
        Label etiquetaAtacante = new Label("Dados del atacante:");
        etiquetaAtacante.setTranslateY(-100);
        etiquetaAtacante.setTranslateX(-140);
        Label etiquetaDefensor = new Label("Dados del defensor:");
        etiquetaDefensor.setTranslateY(5);
        etiquetaDefensor.setTranslateX(-140);
        this.getChildren().add(etiquetaAtacante);
        this.getChildren().add(etiquetaDefensor);
    }

    public VistaDados(Tablero tablero) throws IOException {
        this.tablero = tablero;
        inicializarImagenesDados();
        inicializarInterfazDados();
        inicializarBotonCerrar();
        inicializarDados();
        inicializarEtiquetasJugadores();
        this.setTranslateY(300);
        this.setTranslateX(450);
        this.setVisible(false);
    }

    public static void crearInstancia(Tablero tablero) throws IOException{
        if(instancia == null){
            instancia = new VistaDados(tablero);
        }
    }

    public static VistaDados obtenerInstancia(){
        return instancia;
    }

    private void inicializarDados(){
        this.dadosDefensor = new ArrayList<>();
        this.dadosAtacante = new ArrayList<>();
        this.resultadoDadosDefensor = new ArrayList<>();
        this.resultadoDadosAtacante = new ArrayList<>();
        for(int i=0; i < VistaDados.MAX_DADOS; i++){
            inicializarDadoEnIndice(i);
        }
    }

    private void inicializarDadoEnIndice(int i) {
        this.dadosAtacante.add(new ImageView());
        this.dadosAtacante.get(i).setTranslateX(-140+ i *145);
        this.dadosAtacante.get(i).setTranslateY(-51);
        this.resultadoDadosAtacante.add(new ImageView());
        this.resultadoDadosAtacante.get(i).setTranslateX(-170+ i *145);
        this.resultadoDadosAtacante.get(i).setTranslateY(-15);
        this.dadosDefensor.add(new ImageView());
        this.dadosDefensor.get(i).setTranslateX(-140+ i *145);
        this.dadosDefensor.get(i).setTranslateY(55);
        this.resultadoDadosDefensor.add(new ImageView());
        this.resultadoDadosDefensor.get(i).setTranslateX(-170+ i *145);
        this.resultadoDadosDefensor.get(i).setTranslateY(95);
        this.getChildren().add(this.dadosAtacante.get(i));
        this.getChildren().add(this.dadosDefensor.get(i));
        this.getChildren().add(this.resultadoDadosAtacante.get(i));
        this.getChildren().add(this.resultadoDadosDefensor.get(i));
    }

    private void ocultarDados(){
        for(int i=0; i < VistaDados.MAX_DADOS; i++){
            this.dadosAtacante.get(i).setVisible(false);
            this.dadosDefensor.get(i).setVisible(false);
            this.resultadoDadosAtacante.get(i).setVisible(false);
            this.resultadoDadosDefensor.get(i).setVisible(false);
        }
    }

    private void establecerDadoDefensorGanador(int indice){
        this.resultadoDadosDefensor.get(indice).setImage(this.imagenTickGanador);
        this.resultadoDadosAtacante.get(indice).setImage(this.imagenCruzPerdedor);
        this.resultadoDadosAtacante.get(indice).setVisible(true);
        this.resultadoDadosDefensor.get(indice).setVisible(true);
    }

    private void establecerDadoAtacanteGanador(int indice){
        this.resultadoDadosAtacante.get(indice).setImage(this.imagenTickGanador);
        this.resultadoDadosDefensor.get(indice).setImage(this.imagenCruzPerdedor);
        this.resultadoDadosAtacante.get(indice).setVisible(true);
        this.resultadoDadosDefensor.get(indice).setVisible(true);
    }

    private void actualizarDados(ArrayList<Integer> valoresAtacante, ArrayList<Integer> valoresDefensor){
        ocultarDados();
        actualizarDadosAtacante(valoresAtacante, this.dadosAtacante);
        actualizarDadosDefensor(valoresDefensor, this.dadosDefensor);
        actualizarTildesYCruces(valoresAtacante, valoresDefensor);
    }

    private void actualizarTildesYCruces(ArrayList<Integer> valoresAtacante, ArrayList<Integer> valoresDefensor) {
        for(int i = 0; i < valoresAtacante.size() && i < valoresDefensor.size(); i++){
            establecerGanadorYPerdedor(valoresAtacante, valoresDefensor, i);
        }
    }

    private void establecerGanadorYPerdedor(ArrayList<Integer> valoresAtacante, ArrayList<Integer> valoresDefensor, int i) {
        if(valoresDefensor.get(i) >= valoresAtacante.get(i))
            establecerDadoDefensorGanador(i);
        else
            establecerDadoAtacanteGanador(i);
    }

    private void actualizarDadosDefensor(ArrayList<Integer> valoresDefensor, ArrayList<ImageView> dadosDefensor) {
        for (int i = 0; i < valoresDefensor.size(); i++) {
            dadosDefensor.get(i).setImage(this.imagenesDados.get(valoresDefensor.get(i) - 1));
            dadosDefensor.get(i).setVisible(true);
        }
    }

    private void actualizarDadosAtacante(ArrayList<Integer> valoresAtacante, ArrayList<ImageView> dadosAtacante) {
        for (int i = 0; i < valoresAtacante.size(); i++) {
            dadosAtacante.get(i).setImage(this.imagenesDados.get(valoresAtacante.get(i) - 1));
            dadosAtacante.get(i).setVisible(true);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Integer> valoresAtacante = this.tablero.getUltimosDadosAtacante();
        ArrayList<Integer> valoresDefensor = this.tablero.getUltimosDadosDefensor();
        actualizarDados(valoresAtacante, valoresDefensor);
        try {
            ArrayList<String> sonidosDados = new ArrayList<>();
            sonidosDados.add("./src/sonidos/dados2.wav");
            sonidosDados.add("./src/sonidos/dados1.wav");

            Reproductor.reproducirSonido(sonidosDados);
            //reproducirSonido("./src/sonidos/dados2.wav", "./src/sonidos/dados1.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    /*private void reproducirSonido(String s, String s2) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        IRandomizador randomizador = new Randomizador();
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(s));
        if (randomizador.generar(0, 2) == 1) {
            audioInput = AudioSystem.getAudioInputStream(new File(s2));
        }
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
    }

     */


}
