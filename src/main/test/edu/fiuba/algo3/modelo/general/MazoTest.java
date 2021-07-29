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
        assertEquals(unaTarjeta, mazo.entregarTarjeta());
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
        Tarjeta tarjetaActual = mazo.entregarTarjeta();
        assertTrue(tarjetaActual.equals(otraTarjetaMas));
        tarjetaActual = mazo.entregarTarjeta();
        assertTrue(tarjetaActual.equals(otraTarjeta));
        tarjetaActual = mazo.entregarTarjeta();
        assertTrue(tarjetaActual.equals(unaTarjeta));
    }

    @Test
    public void test04seLeDevuelven3TarjetasAlMazoYAumentaEn3LaCantidad() {
        Signo signo1 = new SignoComodin();
        Signo signo2 = new Signo(0);
        Signo signo3 = new Signo(1);
        Signo signo4 = new Signo(0);
        Signo signo5 = new Signo(2);

        Pais pais1 = new Pais("Argentina");
        Pais pais2 = new Pais("Brasil");
        Pais pais3 = new Pais("Francia");
        Pais pais4 = new Pais("Sudafrica");
        Pais pais5 = new Pais("Australia");


        Tarjeta tarjeta1 = new Tarjeta(pais1, signo1);
        Tarjeta tarjeta2 = new Tarjeta(pais2, signo2);
        Tarjeta tarjeta3 = new Tarjeta(pais3, signo3);
        Tarjeta tarjeta4 = new Tarjeta(pais4, signo4);
        Tarjeta tarjeta5 = new Tarjeta(pais5, signo5);

        ArrayList<Tarjeta> listaTarjetas = new ArrayList<Tarjeta>();
        listaTarjetas.add(tarjeta1);
        listaTarjetas.add(tarjeta2);

        Mazo mazo = new Mazo(listaTarjetas, new Randomizador());

        int cantInicial = mazo.largoMazo();
        mazo.agregarTarjetas(tarjeta3, tarjeta4, tarjeta5);

        assertEquals(cantInicial+3, mazo.largoMazo());
    }

}
