package edu.fiuba.algo3.modelo.flujoDeJuego;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.general.*;

import java.util.HashMap;


public class JugadaColocarTest {

    @Test
    public void test01paisPasaDe3A5EjercitosTrasJugadaColocarCon2Ejercitos(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(3);
        int unaCantidad = 2;
        Tablero tablero = new Tablero(new HashMap<String, Continente>());
        Jugada jugada = new JugadaColocar(unPais, unaCantidad);
        jugada.ejecutar(tablero);
        assertEquals(5, unPais.getCantidadEjercitos());
    }

}
