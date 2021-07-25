package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.CantidadInvalidaDeDadosError;
import edu.fiuba.algo3.modelo.ataque.ConjuntoDados;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.flujoDeJuego.JugadaCanjearTarjetas;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ConjuntoTarjetasTest {

    //0 para Barco; 1 para Globo; 2 para Cañon
    @Test
    public void test01SeCanjean3CartasDeTipoGloboYSonCanjeables(){
        Mazo mazo = new Mazo(new ArrayList<>(), new Randomizador());

        Tarjeta tarjeta1 = new Tarjeta(new Pais("Argentina"), new Signo(1));
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Brasil"), new Signo(1));
        Tarjeta tarjeta3 = new Tarjeta(new Pais("Chile"), new Signo(1));

        ConjuntoTarjetas conjuntoTarjetas = new ConjuntoTarjetas(tarjeta1, tarjeta2, tarjeta3, mazo);
        assertTrue(conjuntoTarjetas.sePudoCanjear());
    }

    @Test
    public void test02SeCanjean2CartasDeTipoGloboYUnaDeTipoCanonYNoSonCanjeables(){
        Mazo mazo = new Mazo(new ArrayList<>(), new Randomizador());

        Tarjeta tarjeta1 = new Tarjeta(new Pais("Argentina"), new Signo(1));
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Brasil"), new Signo(1));
        Tarjeta tarjeta3 = new Tarjeta(new Pais("Chile"), new Signo(2));

        ConjuntoTarjetas conjuntoTarjetas = new ConjuntoTarjetas(tarjeta1, tarjeta2, tarjeta3, mazo);
        assertFalse(conjuntoTarjetas.sePudoCanjear());
    }

    @Test
    public void test03SeCanjean3CartasDeDistintoTipoYSonCanjeables(){
        Mazo mazo = new Mazo(new ArrayList<>(), new Randomizador());

        Tarjeta tarjeta1 = new Tarjeta(new Pais("Argentina"), new Signo(0));
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Brasil"), new Signo(1));
        Tarjeta tarjeta3 = new Tarjeta(new Pais("Chile"), new Signo(2));

        ConjuntoTarjetas conjuntoTarjetas = new ConjuntoTarjetas(tarjeta1, tarjeta2, tarjeta3, mazo);
        assertTrue(conjuntoTarjetas.sePudoCanjear());
    }


}
