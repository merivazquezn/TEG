package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.flujoDeJuego.*;
import edu.fiuba.algo3.modelo.general.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import edu.fiuba.algo3.controlador.EleccionInicial;

public class RondaInicialTest {

    @Test
    public void test01SeIntentaCrearUnaRondaCon3JugadoresYSeCreaLaListaDeJugadores(){
        EleccionInicial eleccion = mock(EleccionInicial.class);
        Juego juego = new Juego();
        Tablero tablero = mock(Tablero.class);
        when(eleccion.cantidadDeJugadores()).thenReturn(2);
        RondaInicial ronda = new RondaInicial(juego, eleccion);
        ronda.realizarRondaYContinuar(tablero);
        assertEquals(2, juego.cantidadJugadores());
    }

}
