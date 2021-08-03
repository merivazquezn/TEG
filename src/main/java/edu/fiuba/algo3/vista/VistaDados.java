package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuColocacion;
import edu.fiuba.algo3.modelo.general.Tablero;
import javafx.scene.control.Button;
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

public class VistaDados extends StackPane implements Observer {
    private ImageView imagenInterfaz;
    private ArrayList<ImageView> dadosAtacante;
    private ArrayList<ImageView> dadosDefensor;
    private ArrayList<Image> imagenesDados;
    private Button botonCerrar;
    Tablero tablero;

    static final int MAX_DADOS = 3;

    private void inicializarBotonCerrar(){
        this.botonCerrar = new Button("X");
        this.botonCerrar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            this.setVisible(false);
            e.consume();
        });
        this.botonCerrar.setTranslateX(200);
        this.botonCerrar.setTranslateY(-100);
    }

    public VistaDados(Tablero tablero) throws IOException {
        this.tablero = tablero;
        inicializarBotonCerrar();
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
        this.getChildren().add(this.botonCerrar);
        this.setTranslateY(300);
        this.setTranslateX(450);
        inicializarDados();
        this.setVisible(false);
    }

    private void inicializarDados(){
        this.dadosDefensor = new ArrayList<>();
        this.dadosAtacante = new ArrayList<>();
        for(int i=0; i < VistaDados.MAX_DADOS; i++){
            this.dadosAtacante.add(new ImageView());
            this.dadosAtacante.get(i).setTranslateX(-140+(i)*145);
            this.dadosAtacante.get(i).setTranslateY(-55);
            this.dadosDefensor.add(new ImageView());
            this.dadosDefensor.get(i).setTranslateX(-140+(i)*145);
            this.dadosDefensor.get(i).setTranslateY(55);
            this.getChildren().add(this.dadosAtacante.get(i));
            this.getChildren().add(this.dadosDefensor.get(i));
        }
    }

    private void ocultarDados(){
        for(int i=0; i < VistaDados.MAX_DADOS; i++){
            this.dadosAtacante.get(i).setVisible(false);
            this.dadosDefensor.get(i).setVisible(false);
        }
    }

    private void actualizarDados(ArrayList<Integer> valoresAtacante, ArrayList<Integer> valoresDefensor){
        ocultarDados();
        for(int i=0; i < valoresAtacante.size() ; i++){
            this.dadosAtacante.get(i).setImage(this.imagenesDados.get(valoresAtacante.get(i)-1));
            this.dadosAtacante.get(i).setVisible(true);
        }
        for(int i=0; i < valoresDefensor.size() ; i++){
            this.dadosDefensor.get(i).setImage(this.imagenesDados.get(valoresDefensor.get(i)-1));
            this.dadosDefensor.get(i).setVisible(true);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Integer> valoresAtacante = this.tablero.getUltimosDadosAtacante();
        ArrayList<Integer> valoresDefensor = this.tablero.getUltimosDadosDefensor();
        actualizarDados(valoresAtacante, valoresDefensor);
        this.setVisible(true);
    }


}
