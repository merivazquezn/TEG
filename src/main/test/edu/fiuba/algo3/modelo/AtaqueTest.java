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
        paisAtacante.colocarFichas(2);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarFichas(5);
        assertThrows(CantidadInvalidaDeEjercitosParaAtaqueExeption.class, () -> {
            Ataque ataque = new Ataque(paisAtacante, paisDefensor, 2);
        });
    }

    @Test
    public void test02devuelveErrorAlAtacarCon0Ejercitos() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarFichas(5);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarFichas(5);
        assertThrows(CantidadInvalidaDeEjercitosParaAtaqueExeption.class, () -> {
            Ataque ataque = new Ataque(paisAtacante, paisDefensor, 0);
        });
    }

    @Test
    public void test03devuelveErrorAlAtacarConMasDe4Ejercitos() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarFichas(5);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarFichas(5);
        assertThrows(CantidadInvalidaDeEjercitosParaAtaqueExeption.class, () -> {
            Ataque ataque = new Ataque(paisAtacante, paisDefensor, 4);
        });
    }

    @Test
    public void PaisAtacaCon2EjercitosContra1EjercitoDefensorYDefensorPierdeElEjercito() {
        Pais paisAtacante = mock(Pais.class);
        when(paisAtacante.getCantidadFichas()).thenReturn(2);
        Pais paisDefensor = mock(Pais.class);
        when(paisDefensor.getCantidadFichas()).thenReturn(1);
        IConjuntoDados atacante = mock(ConjuntoDados.class);
        IConjuntoDados defensor = mock(ConjuntoDados.class);
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

        assertEquals(paisAtacante.getCantidadFichas(), 3);
        assertEquals(paisDefensor.getCantidadFichas(), 0);
    }

}
