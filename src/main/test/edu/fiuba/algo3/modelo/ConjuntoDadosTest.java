package edu.fiuba.algo3.modelo;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import org.mockito.invocation.*;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConjuntoDadosTest {

    @Test
    public void test01ConjuntoGeneraLaCantidadDeDadosPedidosEntre1Y3(){
        ConjuntoDados conjunto = new ConjuntoDados();
        conjunto.generar(2, new Randomizador());
        assertEquals(2,conjunto.size());
    }

    @Test
    public void test02ConjuntoTiraErrorAlGenerarConjuntoConMenosDe1Dado() {
        assertThrows(CantidadInvalidaDeDadosError.class, () -> {
            ConjuntoDados conjunto = new ConjuntoDados();
            conjunto.generar(0, new Randomizador());
        });
    }

    @Test
    public void test03ConjuntoTiraErrorAlGenerarConjuntoConMasDe3Dados() {
        assertThrows(CantidadInvalidaDeDadosError.class, () -> {
            ConjuntoDados conjunto = new ConjuntoDados();
            conjunto.generar(4, new Randomizador());
        });
    }

    @Test
    public void test04CunjuntoSeGeneraConLosDadosOrdenadosDeMayorAMenor() {
        ConjuntoDados conjunto = new ConjuntoDados();
        conjunto.generar(3, new Randomizador());
        boolean ordenados = true;
        ArrayList<Dado> lista = conjunto.obtenerDados();

        for(int i=0;i<lista.size()-1;i++){
            if(lista.get(i).compareTo(lista.get(i+1)) == Dado.DADO_PERDIO)
                ordenados = false;
        }
        assertTrue(ordenados);
    }

    @Test
    public void test05ConjuntoDadoComparaConOtroConjuntoYDeberiaPerder1AtacanteY2Defensor() {
        IRandomizador random = mock(Randomizador.class);
        when(random.generar(1,6)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count == 1){
                    count++;
                    return 2;
                }
                else if(count == 2){
                    count++;
                    return 3;
                }
                count++;
                return 1;
            }
        });
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(3, random);
        when(random.generar(1,6)).thenReturn(1);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, random);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(1, resultado.get(0));
        assertEquals(2, resultado.get(1));
    }

    @Test
    public void test06ConjuntoDadoComparaConOtroConjuntoYDeberiaPerder1AtacanteY1Defensor() {
        IRandomizador random = mock(Randomizador.class);
        when(random.generar(1,6)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count == 1){
                    count++;
                    return 6;
                }
                count++;
                return 5;
            }
        });
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(2, random);
        when(random.generar(1,6)).thenReturn(5);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, random);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(1, resultado.get(0));
        assertEquals(1, resultado.get(1));
    }

}
