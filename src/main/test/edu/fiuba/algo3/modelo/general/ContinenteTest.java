package edu.fiuba.algo3.modelo.general;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.general.Continente;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.ObjetivoDestruir;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContinenteTest {

    @Test
    public void test01OcupadoPorJugadorDevuelveFalseSiOtroJugadorTieneTodosLosPaises() {
        ArrayList<Pais> listaPaises = new ArrayList<Pais>();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());

        listaPaises.add(new Pais("Argentina"));
        listaPaises.add(new Pais("Chile"));
        listaPaises.add(new Pais("Brasil"));
        listaPaises.add(new Pais("Colombia"));
        listaPaises.add(new Pais("Peru"));
        listaPaises.add(new Pais("Uruguay"));

        listaPaises.get(0).asignarJugador(otroJugador);
        listaPaises.get(1).asignarJugador(otroJugador);
        listaPaises.get(2).asignarJugador(otroJugador);
        listaPaises.get(3).asignarJugador(otroJugador);
        listaPaises.get(4).asignarJugador(otroJugador);
        listaPaises.get(5).asignarJugador(otroJugador);

        Continente continente = new Continente(listaPaises, "America Del Sur", 3);

        assertFalse(continente.ocupadoPorJugador(unJugador));
    }

    @Test
    public void test02OcupadoPorJugadorDevuelveFalseSiOtrosJugadorsTieneTodosLosPaises() {
        ArrayList<Pais> listaPaises = new ArrayList<Pais>();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugadorMas = new Jugador(new ObjetivoDestruir());

        listaPaises.add(new Pais("Argentina"));
        listaPaises.add(new Pais("Chile"));
        listaPaises.add(new Pais("Brasil"));
        listaPaises.add(new Pais("Colombia"));
        listaPaises.add(new Pais("Peru"));
        listaPaises.add(new Pais("Uruguay"));

        listaPaises.get(0).asignarJugador(otroJugador);
        listaPaises.get(1).asignarJugador(otroJugador);
        listaPaises.get(2).asignarJugador(otroJugadorMas);
        listaPaises.get(3).asignarJugador(otroJugadorMas);
        listaPaises.get(4).asignarJugador(otroJugador);
        listaPaises.get(5).asignarJugador(otroJugador);

        Continente continente = new Continente(listaPaises, "America Del Sur", 3);

        assertFalse(continente.ocupadoPorJugador(unJugador));
    }

    @Test
    public void test03OcupadoPorJugadorDevuelveFalseSiLeFaltaConquistarUnPais() {
        ArrayList<Pais> listaPaises = new ArrayList<Pais>();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());

        listaPaises.add(new Pais("Argentina"));
        listaPaises.add(new Pais("Chile"));
        listaPaises.add(new Pais("Brasil"));
        listaPaises.add(new Pais("Colombia"));
        listaPaises.add(new Pais("Peru"));
        listaPaises.add(new Pais("Uruguay"));

        listaPaises.get(0).asignarJugador(otroJugador);
        listaPaises.get(1).asignarJugador(unJugador);
        listaPaises.get(2).asignarJugador(unJugador);
        listaPaises.get(3).asignarJugador(unJugador);
        listaPaises.get(4).asignarJugador(unJugador);
        listaPaises.get(5).asignarJugador(unJugador);

        Continente continente = new Continente(listaPaises, "America Del Sur", 3);

        assertFalse(continente.ocupadoPorJugador(unJugador));
    }

    @Test
    public void test04OcupadoPorJugadorDevuelveTrueSiTieneTodosLosPaises() {
        ArrayList<Pais> listaPaises = new ArrayList<Pais>();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());

        listaPaises.add(new Pais("Argentina"));
        listaPaises.add(new Pais("Chile"));
        listaPaises.add(new Pais("Brasil"));
        listaPaises.add(new Pais("Colombia"));
        listaPaises.add(new Pais("Peru"));
        listaPaises.add(new Pais("Uruguay"));

        listaPaises.get(0).asignarJugador(unJugador);
        listaPaises.get(1).asignarJugador(unJugador);
        listaPaises.get(2).asignarJugador(unJugador);
        listaPaises.get(3).asignarJugador(unJugador);
        listaPaises.get(4).asignarJugador(unJugador);
        listaPaises.get(5).asignarJugador(unJugador);

        Continente continente = new Continente(listaPaises, "America Del Sur", 3);

        assertTrue(continente.ocupadoPorJugador(unJugador));
    }


    @Test
    public void test05EjercitosParaJugadorDevuelve0SiJugadorNoConquistoTodoAmericaDelSur() {
        ArrayList<Pais> listaPaises = new ArrayList<Pais>();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());

        listaPaises.add(new Pais("Argentina"));
        listaPaises.add(new Pais("Chile"));
        listaPaises.add(new Pais("Brasil"));
        listaPaises.add(new Pais("Colombia"));
        listaPaises.add(new Pais("Peru"));
        listaPaises.add(new Pais("Uruguay"));

        listaPaises.get(0).asignarJugador(otroJugador);
        listaPaises.get(1).asignarJugador(unJugador);
        listaPaises.get(2).asignarJugador(unJugador);
        listaPaises.get(3).asignarJugador(otroJugador);
        listaPaises.get(4).asignarJugador(unJugador);
        listaPaises.get(5).asignarJugador(unJugador);

        Continente continente = new Continente(listaPaises, "America Del Sur", 3);

        assertEquals(continente.ejercitosParaJugador(unJugador), 0);
    }

    @Test
    public void test06EjercitosParaJugadorDevuelve3SiJugadorNoConquistoTodoElAmericaDelSur() {
        ArrayList<Pais> listaPaises = new ArrayList<Pais>();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());

        listaPaises.add(new Pais("Argentina"));
        listaPaises.add(new Pais("Chile"));
        listaPaises.add(new Pais("Brasil"));
        listaPaises.add(new Pais("Colombia"));
        listaPaises.add(new Pais("Peru"));
        listaPaises.add(new Pais("Uruguay"));

        listaPaises.get(0).asignarJugador(unJugador);
        listaPaises.get(1).asignarJugador(unJugador);
        listaPaises.get(2).asignarJugador(unJugador);
        listaPaises.get(3).asignarJugador(unJugador);
        listaPaises.get(4).asignarJugador(unJugador);
        listaPaises.get(5).asignarJugador(unJugador);

        Continente continente = new Continente(listaPaises, "America Del Sur", 5);

        assertEquals(continente.ejercitosParaJugador(unJugador), 5);
    }

    @Test
    public void test07UnJugadorTiene3PaisesEnAmericaDelSurYElContinenteDevuelveCantidad3() {
        ArrayList<Pais> listaPaises = new ArrayList<Pais>();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());

        listaPaises.add(new Pais("Argentina"));
        listaPaises.add(new Pais("Chile"));
        listaPaises.add(new Pais("Brasil"));
        listaPaises.add(new Pais("Colombia"));
        listaPaises.add(new Pais("Peru"));
        listaPaises.add(new Pais("Uruguay"));

        listaPaises.get(0).asignarJugador(unJugador);
        listaPaises.get(1).asignarJugador(otroJugador);
        listaPaises.get(2).asignarJugador(unJugador);
        listaPaises.get(3).asignarJugador(otroJugador);
        listaPaises.get(4).asignarJugador(unJugador);
        listaPaises.get(5).asignarJugador(otroJugador);

        Continente continente = new Continente(listaPaises, "America Del Sur", 5);

        assertEquals(3, continente.cantidadPaisesDeJugador(unJugador));
    }

    @Test
    public void test08UnContinenteDevuelveSuNombreCorrectamente() {
        ArrayList<Pais> listaPaises = new ArrayList<Pais>();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());

        listaPaises.add(new Pais("Argentina"));
        listaPaises.add(new Pais("Chile"));
        listaPaises.add(new Pais("Brasil"));
        listaPaises.add(new Pais("Colombia"));
        listaPaises.add(new Pais("Peru"));
        listaPaises.add(new Pais("Uruguay"));

        listaPaises.get(0).asignarJugador(unJugador);
        listaPaises.get(1).asignarJugador(unJugador);
        listaPaises.get(2).asignarJugador(unJugador);
        listaPaises.get(3).asignarJugador(unJugador);
        listaPaises.get(4).asignarJugador(unJugador);
        listaPaises.get(5).asignarJugador(unJugador);

        Continente continente = new Continente(listaPaises, "America Del Sur", 5);

        assertEquals("America Del Sur", continente.getNombre());
    }

}
