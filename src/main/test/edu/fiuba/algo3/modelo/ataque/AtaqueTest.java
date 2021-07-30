package edu.fiuba.algo3.modelo.ataque;

import static org.mockito.Mockito.*;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.general.*;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AtaqueTest {

    @Test
    public void test01devuelveErrorAlAtacarConLaMismaCantidadDeEjercitosDelPais() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(1);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(4);

        ConstructorDeConjuntoDados constructor = new ConstructorDeConjuntoDados(new Randomizador());

        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> {
            Ataque ataque = new Ataque(constructor, paisAtacante, paisDefensor, 2);
        });
    }

    @Test
    public void test02devuelveErrorAlAtacarCon0Ejercitos() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(5);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(5);

        ConstructorDeConjuntoDados constructor = new ConstructorDeConjuntoDados(new Randomizador());

        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> {
            Ataque ataque = new Ataque(constructor, paisAtacante, paisDefensor, 0);
        });
    }

    @Test
    public void test03devuelveErrorAlAtacarConMasDe4Ejercitos() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(5);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(5);

        ConstructorDeConjuntoDados constructor = new ConstructorDeConjuntoDados(new Randomizador());

        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> {
            Ataque ataque = new Ataque(constructor, paisAtacante, paisDefensor, 4);
        });
    }

    @Test
    public void test04paisAtacaCon2EjercitosContra1EjercitoDefensorYDefensorPierdeElEjercito() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(2);
        Pais paisDefensor = new Pais("Chile");
        paisAtacante.agregarLimitrofe(paisDefensor);
        paisDefensor.agregarLimitrofe(paisAtacante);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);

        ArrayList<ConjuntoDados> arrayConjuntos = new ArrayList<>();
        arrayConjuntos.add(atacante);
        arrayConjuntos.add(defensor);

        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);

        when(constructor.obtenerConjuntosDados(2,1)).thenReturn(arrayConjuntos);

        when(atacante.ejercitosPerdidos(defensor)).thenAnswer((Answer) invocation -> {
            ArrayList<Integer> lista = new ArrayList<Integer>();
            lista.add(0);
            lista.add(1);
            return lista;
        });

        Ataque ataque = new Ataque(constructor, paisAtacante, paisDefensor, 2);
        assertTrue(ataque.conquisto());
        assertEquals(paisAtacante.getCantidadEjercitos(), 2);
        assertEquals(paisDefensor.getCantidadEjercitos(), 1);
    }


    @Test
    public void test05paisAtacaCon3EjercitosContra3EjercitosDefensoresAtacantePierde2Defensor1() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(3);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(2);
        paisAtacante.agregarLimitrofe(paisDefensor);
        paisDefensor.agregarLimitrofe(paisAtacante);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);


        ArrayList<ConjuntoDados> arrayConjuntos = new ArrayList<ConjuntoDados>();
        arrayConjuntos.add(atacante);
        arrayConjuntos.add(defensor);

        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        when(constructor.obtenerConjuntosDados(3,3)).thenReturn(arrayConjuntos);

        when(atacante.ejercitosPerdidos(defensor)).thenAnswer((Answer) invocation -> {
            ArrayList<Integer> lista = new ArrayList<Integer>();
            lista.add(2);
            lista.add(1);
            return lista;
        });

        Ataque ataque = new Ataque(constructor, paisAtacante, paisDefensor, 3);
        assertFalse(ataque.conquisto());

        assertEquals(2, paisAtacante.getCantidadEjercitos());
        assertEquals(2, paisDefensor.getCantidadEjercitos());
    }

    @Test
    public void test06paisAtacaCon1EjercitosContra3EjercitosDefensoresDefensorPierde1() {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(1);
        Pais paisDefensor = new Pais("Chile");
        paisDefensor.colocarEjercitos(2);
        paisAtacante.agregarLimitrofe(paisDefensor);
        paisDefensor.agregarLimitrofe(paisAtacante);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);

        ArrayList<ConjuntoDados> arrayConjuntos = new ArrayList<>();
        arrayConjuntos.add(atacante);
        arrayConjuntos.add(defensor);

        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        when(constructor.obtenerConjuntosDados(1,3)).thenReturn(arrayConjuntos);

        when(atacante.ejercitosPerdidos(defensor)).thenAnswer((Answer) invocation -> {
            ArrayList<Integer> lista = new ArrayList<Integer>();
            lista.add(0);
            lista.add(1);
            return lista;
        });

        Ataque ataque = new Ataque(constructor, paisAtacante, paisDefensor, 1);
        assertFalse(ataque.conquisto());

        assertEquals(paisAtacante.getCantidadEjercitos(), 2);
        assertEquals(paisDefensor.getCantidadEjercitos(), 2);
    }

    @Test
    public void test07paisAtacaCon3EjercitosContra3EjercitosDefensoresAtacantePierde3() {
        Pais paisAtacante = new Pais("Chile");
        paisAtacante.colocarEjercitos(3);
        Pais paisDefensor = new Pais("Argentina");
        paisDefensor.colocarEjercitos(2);
        paisAtacante.agregarLimitrofe(paisDefensor);
        paisDefensor.agregarLimitrofe(paisAtacante);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);

        ArrayList<ConjuntoDados> arrayConjuntos = new ArrayList<>();
        arrayConjuntos.add(atacante);
        arrayConjuntos.add(defensor);

        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        when(constructor.obtenerConjuntosDados(3,3)).thenReturn(arrayConjuntos);

        when(atacante.ejercitosPerdidos(defensor)).thenAnswer((Answer) invocation -> {
            ArrayList<Integer> lista = new ArrayList<Integer>();
            lista.add(3);
            lista.add(0);
            return lista;
        });

        Ataque ataque = new Ataque(constructor, paisAtacante, paisDefensor, 3);
        assertFalse(ataque.conquisto());

        assertEquals(paisAtacante.getCantidadEjercitos(), 1);
        assertEquals(paisDefensor.getCantidadEjercitos(), 3);
    }

    @Test
    public void test08paisIntentaAtacarAPaisNoLimitrofeYLanzaExcepcion() {
        Pais paisLimitrofeChile = new Pais("PaisLimitrofeChile");
        Pais paisLimitrofeArgentina = new Pais("PaisLimitrofeArgentina");

        Pais paisAtacante = new Pais("Chile");
        paisAtacante.colocarEjercitos(4);
        paisAtacante.agregarLimitrofe(paisLimitrofeChile);

        Pais paisDefensor = new Pais("Argentina");
        paisDefensor.colocarEjercitos(3);
        paisAtacante.agregarLimitrofe(paisLimitrofeArgentina);

        long start2 = System.currentTimeMillis();
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        long end2 = System.currentTimeMillis();

        ConjuntoDados defensor = mock(ConjuntoDados.class);

        ArrayList<ConjuntoDados> arrayConjuntos = new ArrayList<>();
        arrayConjuntos.add(atacante);
        arrayConjuntos.add(defensor);

        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        when(constructor.obtenerConjuntosDados(3,3)).thenReturn(arrayConjuntos);

        when(atacante.ejercitosPerdidos(defensor)).thenAnswer((Answer) invocation -> {
            ArrayList<Integer> lista = new ArrayList<Integer>();
            lista.add(3);
            lista.add(0);
            return lista;
        });


        Ataque ataque = new Ataque(constructor, paisAtacante, paisDefensor, 3);


        assertThrows(AtaqueDePaisNoLimitrofeException.class, () -> {
            ataque.conquisto();
        });


        System.out.println(end2-start2 + "ms");
    }

}

