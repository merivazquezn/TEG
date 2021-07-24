package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.general.*;

import java.util.ArrayList;
import java.util.HashMap;


public class JugadaColocarTest {

    @Test
    public void test01paisPasaDe3A5EjercitosTrasJugadaColocarCon2Ejercitos(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(3);
        int unaCantidad = 2;
        Tablero tablero = new Tablero(new HashMap<String, Continente>(), new ConstructorDeConjuntoDados(new Randomizador()));
        Jugada jugada = new JugadaColocar(unPais, unaCantidad);
        jugada.ejecutar(tablero, new Ronda(tablero, new ListaJugadores(2, new Randomizador())));
        assertEquals(5, unPais.getCantidadEjercitos());
    }

    @Test
    public void test02paisPasaDe5A8EjercitosTrasJugadaColocarCon3Ejercitos(){
        Pais unPais = new Pais("Chile");
        unPais.colocarEjercitos(5);
        int unaCantidad = 3;
        Tablero tablero = new Tablero(new HashMap<String, Continente>(), new ConstructorDeConjuntoDados(new Randomizador()));
        Jugada jugada = new JugadaColocar(unPais, unaCantidad);
        jugada.ejecutar(tablero, new Ronda(tablero, new ListaJugadores(2, new Randomizador())));
        assertEquals(8, unPais.getCantidadEjercitos());
    }

}
