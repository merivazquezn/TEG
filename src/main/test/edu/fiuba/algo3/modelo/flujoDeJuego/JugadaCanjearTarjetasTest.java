package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.*;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tarjeta;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

public class JugadaCanjearTarjetasTest {



    @Test
    public void test01seRealizaCanjeYLaRondaEsNotificada(){
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Chila");
        Pais otroPaisMas = new Pais("Peru");

        Tarjeta tarjeta1 = new Tarjeta(unPais, new Signo(1));
        Tarjeta tarjeta2 = new Tarjeta(otroPais, new Signo(1));
        Tarjeta tarjeta3 = new Tarjeta(otroPaisMas, new Signo(1));
        Jugada jugada = new JugadaCanjearTarjetas(tarjeta1, tarjeta2, tarjeta3);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        Ronda ronda = mock(Ronda.class);
        jugada.ejecutar(tablero, ronda);
        verify(ronda, times(1)).seRealizoCanje(any(ConjuntoTarjetas.class));

    }

    @Test
    public void test02noSeRealizaCanjeYLaRondaNoEsNotificada(){
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Chila");
        Pais otroPaisMas = new Pais("Peru");

        Tarjeta tarjeta1 = new Tarjeta(unPais, new Signo(1));
        Tarjeta tarjeta2 = new Tarjeta(otroPais, new Signo(2));
        Tarjeta tarjeta3 = new Tarjeta(otroPaisMas, new Signo(1));
        Jugada jugada = new JugadaCanjearTarjetas(tarjeta1, tarjeta2, tarjeta3);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        Ronda ronda = mock(Ronda.class);
        jugada.ejecutar(tablero, ronda);

        verify(ronda, times(0)).seRealizoCanje(new ConjuntoTarjetas(tarjeta1, tarjeta2, tarjeta3, new Mazo(new ArrayList<Tarjeta>(), new Randomizador())));

    }

}
