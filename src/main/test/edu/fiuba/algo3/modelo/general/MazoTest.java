package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MazoTest {

    @Test
    public void test01elMazoNuevoDeUnaCartaDevuelveLaUnicaCarta(){

        Signo signo = new Signo(0); //barco
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        Pais unPais = new Pais("Argentina");
        Tarjeta unaTarjeta = new Tarjeta(unPais, signo);
        tarjetas.add(unaTarjeta);
        Mazo mazo = new Mazo(tarjetas, new Randomizador());
        assertEquals(unaTarjeta, mazo.entregarCarta());
    }

    @Test
    public void test02elMazoMezclaLasCartasAlCrearse(){

        Signo signo = new Signo(0); //barco
        Signo otroSigno = new Signo(1);
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");
        Tarjeta unaTarjeta = new Tarjeta(unPais, signo);
        Tarjeta otraTarjeta = new Tarjeta(otroPais, otroSigno);
        tarjetas.add(unaTarjeta);
        tarjetas.add(otraTarjeta);

        IRandomizador randomMock = mock(Randomizador.class);

        Mazo mazo = new Mazo(tarjetas, randomMock);
        verify(randomMock, times(1)).shuffle(any());
    }

    @Test
    public void test03elMazoMezclaDevuelveLasCartasEnElOrdenIndicado(){

        Signo signo = new Signo(0); //barco
        Signo otroSigno = new Signo(1);
        Signo otroSignoMas = new SignoComodin();
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");
        Pais otroPaisMas = new Pais("Francia");
        Tarjeta unaTarjeta = new Tarjeta(unPais, signo);
        Tarjeta otraTarjeta = new Tarjeta(otroPais, otroSigno);
        Tarjeta otraTarjetaMas = new Tarjeta(otroPaisMas, otroSignoMas);
        tarjetas.add(unaTarjeta);
        tarjetas.add(otraTarjeta);
        tarjetas.add(otraTarjetaMas);

        IRandomizador randomMock = mock(Randomizador.class);

        Mazo mazo = new Mazo(tarjetas, randomMock);
        Tarjeta tarjetaActual = mazo.entregarCarta();
        assertTrue(tarjetaActual.equals(otraTarjetaMas));
        tarjetaActual = mazo.entregarCarta();
        assertTrue(tarjetaActual.equals(otraTarjeta));
        tarjetaActual = mazo.entregarCarta();
        assertTrue(tarjetaActual.equals(unaTarjeta));
    }

}
