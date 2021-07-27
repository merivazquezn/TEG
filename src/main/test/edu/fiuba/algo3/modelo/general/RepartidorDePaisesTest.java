package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.jugador.Objetivo;
import edu.fiuba.algo3.modelo.jugador.ObjetivoDestruir;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepartidorDePaisesTest {

    @Test
    public void test01SeReparten4PaisesEntre2JugadoresCadaUnoTiene2() {
        HashMap<String, Pais> hashPaises = new HashMap<String, Pais>();
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());

        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);

        hashPaises.put("Terranova", new Pais("Terranova"));
        hashPaises.put("Argentina", new Pais("Argentina"));
        hashPaises.put("Iran", new Pais("Iran"));
        hashPaises.put("Madagascar", new Pais("Madagascar"));

        RepartidorDePaises repartidor = new RepartidorDePaises(hashPaises, listaJugadores);
        repartidor.repartirPaisesPorJugadores();
        assertEquals(listaJugadores.siguiente().cantidadPaises(),  2);
        assertEquals(listaJugadores.siguiente().cantidadPaises(),  2);
    }

    @Test
    public void test02SeReparten5PaisesEntre2JugadoresElPrimeroTiene2ElSegundo3() {
        HashMap<String, Pais> hashPaises = new HashMap<String, Pais>();
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());

        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);

        hashPaises.put("Terranova", new Pais("Terranova"));
        hashPaises.put("Argentina", new Pais("Argentina"));
        hashPaises.put("Iran", new Pais("Iran"));
        hashPaises.put("Madagascar", new Pais("Madagascar"));
        hashPaises.put("Chile", new Pais("Chile"));

        RepartidorDePaises repartidor = new RepartidorDePaises(hashPaises, listaJugadores);
        repartidor.repartirPaisesPorJugadores();
        assertEquals(listaJugadores.siguiente().cantidadPaises(),  2);
        assertEquals(listaJugadores.siguiente().cantidadPaises(),  3);
    }


    @Test
    public void test03SeReparten0PaisesEntre2JugadoresNingunoTienePaises() {
        HashMap<String, Pais> hashPaises = new HashMap<String, Pais>();
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());

        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);

        RepartidorDePaises repartidor = new RepartidorDePaises(hashPaises, listaJugadores);
        repartidor.repartirPaisesPorJugadores();
        assertEquals(listaJugadores.siguiente().cantidadPaises(),  0);
        assertEquals(listaJugadores.siguiente().cantidadPaises(),  0);
    }



}
