package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.general.Tablero;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VistaDados extends StackPane implements Observer {
    private ImageView imagenInterfaz;
    private ImageView primerDadoAtacante;
    private ImageView segundoDadoAtacante;
    private ImageView tercerDadoAtacante;
    private ImageView primerDadoDefensor;
    private ImageView segundoDadoDefensor;
    private ImageView tercerDadoDefensor;
    private ArrayList<Image> imagenesDados;
    private Button botonCerrar;
    private Label etiquetaAtacante;
    private Label etiquetaDefensor;
    Tablero tablero;


    public VistaDados(Tablero tablero) throws IOException {
        this.tablero = tablero;
        this.botonCerrar = new Button("Cerrar");
        this.etiquetaAtacante = new Label("Dados del atacante:");
        this.etiquetaDefensor = new Label("Dados del defensor:");
        this.etiquetaAtacante.setTranslateY(100);
        this.etiquetaDefensor.setTranslateY(200);
        this.setTranslateX(400);
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
        String rutaInterfazDado = "./src/imagenes/vistaDados.png";
        FileInputStream inputImagenInterfaz = new FileInputStream(rutaInterfazDado);
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.imagenInterfaz = new ImageView(imagenInterfaz);
        this.getChildren().add(this.imagenInterfaz);
        this.setTranslateY(400);
        this.setTranslateX(400);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setVisible(true);
    }



}
