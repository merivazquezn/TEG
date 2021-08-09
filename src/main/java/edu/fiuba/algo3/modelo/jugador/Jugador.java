package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Jugador {
    private ArrayList<Pais> listaPaises;
    private int numeroJugador;
    private static int numeroDeJugadorSiguiente = 1;
    Jugador jugadorQueLoDerroto;
    private CantidadCanjes cantidadCanjes;
    private Objetivo objetivoGeneral;
    private Objetivo objetivoPrivado;
    private boolean conquisto;
    private ArrayList<Tarjeta> tarjetas;

    static public void reiniciarClase(){
        Jugador.numeroDeJugadorSiguiente = 1;
    }

    public Jugador(Objetivo objetivo){
        this.tarjetas = new ArrayList<Tarjeta>();
        listaPaises = new ArrayList<>();
        this.asignarAtributosDeJugadorValido();
        this.cantidadCanjes = new CeroCanjes();
        this.objetivoGeneral = new ObjetivoGeneral(this);
        this.objetivoPrivado = objetivo;
        this.conquisto = false;

    }

    protected void asignarAtributosDeJugadorValido(){
        this.numeroJugador = numeroDeJugadorSiguiente;
        numeroDeJugadorSiguiente++;
        this.jugadorQueLoDerroto = new JugadorNulo();
    }

    public boolean jugadorGano(Tablero tablero){
        return (this.objetivoGeneral.haGanado(tablero) || this.objetivoPrivado.haGanado(tablero));
    }


    public void asignarPais(Pais unPais){
        this.listaPaises.add(unPais);
    }


    public int cantidadPaises (){
        return listaPaises.size();
    }

    public Jugador jugadorQueLoDerroto(){
        return this.jugadorQueLoDerroto;
    }

    public void perdioPaisAnte(Pais pais, Jugador jugador){
        int i = 0;
        boolean borrado = false;
        while( borrado == false && i < this.listaPaises.size()){
            if(this.listaPaises.get(i).equals(pais)){
                borrado = true;
                this.listaPaises.remove(i);
            }
            i++;
        }
        if(this.listaPaises.size() == 0)
            this.jugadorQueLoDerroto = jugador;
    }

    public int getNumero() {
        return this.numeroJugador;
    }

    private void eliminarTarjetas(ConjuntoTarjetas conjuntoTarjetas) {
        ArrayList<Tarjeta> tarjetas = conjuntoTarjetas.getTarjetas();
        for(Tarjeta tarjeta : tarjetas) {
            this.tarjetas.remove(tarjeta);
        }
    }

    public void realizarCanje(ConjuntoTarjetas conjuntoTarjetas){
        this.eliminarTarjetas(conjuntoTarjetas);
        this.cantidadCanjes = this.cantidadCanjes.siguiente();
    }

    public int cantidadAColocarPorCanje(){ return this.cantidadCanjes.cantidadAColocarPorCanje();}

    public boolean esNulo() { return false; }

    public boolean tienePais(Pais pais) {
        return listaPaises.contains(pais);
    }

    public String nombreObjetivo(){
        return this.objetivoPrivado.nombreObjetivo();
    }

    public String descripcionObjetivo(){
        return this.objetivoPrivado.descripcionObjetivo();
    }

    public void habilitarTarjetaPorConquista(Mazo mazo) {
        if(this.conquisto){
            Tarjeta tarjeta = mazo.entregarTarjeta();
            tarjeta.asignarJugador(this);
            this.tarjetas.add(tarjeta);
        }
    }

    public ArrayList<Tarjeta> obtenerTarjetas() {
        return this.tarjetas;
    }

    public void reiniciarEstadoConquista() {
        this.conquisto = false;
    }
    public void conquisto(){
        this.conquisto = true;
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        tarjeta.asignarJugador(this);
        this.tarjetas.add(tarjeta);
    }

    public boolean fueDerrotado() {
        return !jugadorQueLoDerroto.esNulo();
    }



}
