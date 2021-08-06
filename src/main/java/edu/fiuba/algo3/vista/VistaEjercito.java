package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorEjercito;
import edu.fiuba.algo3.modelo.general.Pais;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Observable;
import java.util.Observer;

public class VistaEjercito implements Observer {
    private Circle circuloEjercito;
    private Label etiquetaCantidadEjercito;
    private Pais paisAsociado;

    public VistaEjercito(Pais unPais){
        this.paisAsociado = unPais;
        int numeroColorPais = this.paisAsociado.getJugador().getNumero();
        Color colorPais = AsignadorDeColores.colorEjercitoSegunElNumero(numeroColorPais);
        Color colorEtiquetaPais = AsignadorDeColores.colorEtiquetaSegunElNumero(numeroColorPais);
        inicializarCirculo(colorPais);
        inicializarEtiqueta(unPais, colorEtiquetaPais);
        agregarAccionAlClickear();
    }

    private void inicializarEtiqueta(Pais unPais, Color colorEtiquetaPais) {
        this.etiquetaCantidadEjercito = new Label("" + unPais.getCantidadEjercitos());
        this.etiquetaCantidadEjercito.setTextFill(colorEtiquetaPais);
    }

    private void inicializarCirculo(Color colorPais) {
        this.circuloEjercito = new Circle();
        this.circuloEjercito.setRadius(10.0f);
        this.circuloEjercito.setFill(colorPais);
    }

    private void agregarAccionAlClickear() {
        this.etiquetaCantidadEjercito.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            ControladorEjercito controlador = ControladorEjercito.obtenerInstancia();
            controlador.tocarPais(e, this.paisAsociado);
            e.consume();
        });
        this.circuloEjercito.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            ControladorEjercito controlador = ControladorEjercito.obtenerInstancia();
            controlador.tocarPais(e, this.paisAsociado);
            e.consume();
        });
    }

    public void setCenterX(double cX){
        this.circuloEjercito.setCenterX(cX);
        this.etiquetaCantidadEjercito.setTranslateX(cX-5);
    }

    public void setCenterY(double cY){
        this.circuloEjercito.setCenterY(cY);
        this.etiquetaCantidadEjercito.setTranslateY(cY-6);
    }

    @Override
    public void update(Observable o, Object arg) {
        int numeroColorPais = this.paisAsociado.getJugador().getNumero();
        this.etiquetaCantidadEjercito.setText(""+this.paisAsociado.getCantidadEjercitos());
        Color colorPais = AsignadorDeColores.colorEjercitoSegunElNumero(numeroColorPais);
        Color colorEtiquetaPais = AsignadorDeColores.colorEtiquetaSegunElNumero(numeroColorPais);
        this.etiquetaCantidadEjercito.setTextFill(colorEtiquetaPais);
        this.circuloEjercito.setFill(colorPais);
    }

    public Circle getCirculoEjercito(){
        return this.circuloEjercito;
    }

    public Label getEtiquetaEjercito(){
        return this.etiquetaCantidadEjercito;
    }
}
