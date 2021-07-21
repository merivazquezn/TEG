package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Pais;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void test01elJugadorEsDerrotadoYDevuelveAlJugadorQueLoDerroto(){
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = mock(Pais.class);
        Pais otroPais = mock(Pais.class);
        unJugador.asignarPais(unPais);
        otroJugador.asignarPais(otroPais);
        assertNotEquals(otroJugador, unJugador.jugadorQueLoDerroto());
        unJugador.perdioPaisAnte(unPais, otroJugador);
        otroJugador.asignarPais(unPais);
        assertEquals(otroJugador, unJugador.jugadorQueLoDerroto());
    }

}
