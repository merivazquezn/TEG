package edu.fiuba.algo3.modelo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AtaqueTest {

    @Test
    public void test01devuelveErrorAlAtacarConLaMismaCantidadDeEjercitosDelPais() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(2);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(5);
        assertThrows(CantidadInvalidaDeEjercitosParaAtaqueExeption.class, () -> {
            Ataque ataque = new Ataque(paisAtacante, paisDefensor, 2);
        });
    }

    @Test
    public void test02devuelveErrorAlAtacarCon0Ejercitos() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(5);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(5);
        assertThrows(CantidadInvalidaDeEjercitosParaAtaqueExeption.class, () -> {
            Ataque ataque = new Ataque(paisAtacante, paisDefensor, 0);
        });
    }

    @Test
    public void test03devuelveErrorAlAtacarConMasDe4Ejercitos() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(5);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(5);
        assertThrows(CantidadInvalidaDeEjercitosParaAtaqueExeption.class, () -> {
            Ataque ataque = new Ataque(paisAtacante, paisDefensor, 4);
        });
    }

    @Test
    public void test04paisAtacaCon2EjercitosContra1EjercitoDefensorYDefensorPierdeElEjercito() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(3);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(1);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.compararCon(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList<Integer>();
                lista.add(0);
                lista.add(1);
                return lista;
            }
        });

        Ataque ataque = new Ataque(paisAtacante, paisDefensor, 2);
        assertTrue(ataque.ejecutar(atacante, defensor));

        assertEquals(paisAtacante.getCantidadEjercitos(), 3);
        assertEquals(paisDefensor.getCantidadEjercitos(), 0);
    }

    @Test
    public void test05paisAtacaCon3EjercitosContra3EjercitosDefensoresAtacantePierde2Defensor1() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(4);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(3);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.compararCon(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList<Integer>();
                lista.add(2);
                lista.add(1);
                return lista;
            }
        });

        Ataque ataque = new Ataque(paisAtacante, paisDefensor, 3);
        assertFalse(ataque.ejecutar(atacante, defensor));

        assertEquals(paisAtacante.getCantidadEjercitos(), 2);
        assertEquals(paisDefensor.getCantidadEjercitos(), 2);
    }

    @Test
    public void test06paisAtacaCon1EjercitosContra3EjercitosDefensoresDefensorPierde1() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(2);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(3);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.compararCon(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList<Integer>();
                lista.add(0);
                lista.add(1);
                return lista;
            }
        });

        Ataque ataque = new Ataque(paisAtacante, paisDefensor, 1);
        assertFalse(ataque.ejecutar(atacante, defensor));

        assertEquals(paisAtacante.getCantidadEjercitos(), 2);
        assertEquals(paisDefensor.getCantidadEjercitos(), 2);
    }

    @Test
    public void test07paisAtacaCon3EjercitosContra3EjercitosDefensoresAtacantePierde3() {
        Pais paisAtacante = new Pais("Chile");
        paisAtacante.colocarEjercitos(4);
        Pais paisDefensor = new Pais("Argentina");
        paisDefensor.colocarEjercitos(3);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.compararCon(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList<Integer>();
                lista.add(3);
                lista.add(0);
                return lista;
            }
        });

        Ataque ataque = new Ataque(paisAtacante, paisDefensor, 3);
        assertFalse(ataque.ejecutar(atacante, defensor));

        assertEquals(paisAtacante.getCantidadEjercitos(), 1);
        assertEquals(paisDefensor.getCantidadEjercitos(), 3);
    }

}
