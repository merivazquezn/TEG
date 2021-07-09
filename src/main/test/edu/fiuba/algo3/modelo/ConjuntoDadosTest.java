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
    public void test05AtacanteSaca123Defensor111DeberiaPerder1AtacanteY2Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenAnswer(new Answer() {
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
        conjuntoAtacante.generar(3, randomMock);
        when(randomMock.generar(1,6)).thenReturn(1);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(1, resultado.get(0));
        assertEquals(2, resultado.get(1));
    }

    @Test
    public void test06AtacanteSaca56Defensor55DeberiaPerder1AtacanteY1Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenAnswer(new Answer() {
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
        conjuntoAtacante.generar(2, randomMock);
        when(randomMock.generar(1,6)).thenReturn(5);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(1, resultado.get(0));
        assertEquals(1, resultado.get(1));
    }

    @Test
    public void test07AtacanteSaca666Defensor6DeberiaPerder1AtacanteY0Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenReturn(6);
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(3, randomMock);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(1, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(1, (int) resultado.get(0));
        assertEquals(0, (int) resultado.get(1));
    }
    @Test
    public void test08AtacanteSaca2Defensor111DeberiaPerder0AtacanteY1Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenReturn(2);
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(1, randomMock);
        when(randomMock.generar(1,6)).thenReturn(1);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(0, (int) resultado.get(0));
        assertEquals(1, (int) resultado.get(1));
    }
    @Test
    public void test09AtacanteSaca1Defensor111DeberiaPerder1AtacanteY0Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenReturn(1);
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(1, randomMock);
        when(randomMock.generar(1,6)).thenReturn(1);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(1, (int) resultado.get(0));
        assertEquals(0, (int) resultado.get(1));
    }
    @Test
    public void test10AtacanteSaca556Defensor555DeberiaPerder2AtacanteY1Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenAnswer(new Answer() {
            private int count = 0;
            public Object answer(InvocationOnMock invocation) {
                if (count == 1){
                    count++;
                    return 6;
                }
                else if (count == 2){
                    count++;
                    return 5;
                }
                count++;
                return 5;
            }
        });
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(3, randomMock);
        when(randomMock.generar(1,6)).thenReturn(5);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(2, (int) resultado.get(0));
        assertEquals(1, (int) resultado.get(1));
        assertEquals(2, (int) resultado.get(0)); //perdidos por el atacante
        assertEquals(1, (int) resultado.get(1)); //perdidos por el defensor
    }

    @Test
    public void test11AtacanteSaca444DefensorSaca444DeberiaPerder3Atacante0Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenReturn(4);
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(3, randomMock);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(resultado.get(0), 3);
        assertEquals(resultado.get(1), 0);
    }

    @Test
    public void test12AtacanteSaca666DefensorSaca666DeberiaPerder3Atacante0Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenReturn(6);
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(3, randomMock);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(resultado.get(0), 3);
        assertEquals(resultado.get(1), 0);
    }

    @Test
    public void test13AtacanteSaca111DefensorSaca111DeberiaPerder3Atacante0Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count == 1){
                    count++;
                    return 5;
                }
                else if (count == 2){
                    count++;
                    return 4;
                }
                count++;
                return 6;
            }
        });
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(3, randomMock);
        when(randomMock.generar(1,6)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count == 1){
                    count++;
                    return 4;
                }
                else if (count == 2){
                    count++;
                    return 3;
                }
                count++;
                return 5;
            }
        });
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(resultado.get(0), 0);
        assertEquals(resultado.get(1), 3);
    }

    @Test
    public void test14AtacanteSaca456DefensorSaca543DeberiPerder0Atacante3Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenReturn(6);
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(3, randomMock);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(resultado.get(0), 3);
        assertEquals(resultado.get(1), 0);
    }

    @Test
    public void test15AtacanteSaca544DefensorSaca661DeberiaPerder2Atacante1Defensor() {
        IRandomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(1,6)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count == 1){
                    count++;
                    return 4;
                }
                else if (count == 2){
                    count++;
                    return 4;
                }
                count++;
                return 5;
            }
        });
        ConjuntoDados conjuntoAtacante = new ConjuntoDados();
        conjuntoAtacante.generar(3, randomMock);
        when(randomMock.generar(1,6)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count == 1){
                    count++;
                    return 6;
                }
                else if (count == 2){
                    count++;
                    return 1;
                }
                count++;
                return 6;
            }
        });
        ConjuntoDados conjuntoDefensor = new ConjuntoDados();
        conjuntoDefensor.generar(3, randomMock);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertEquals(resultado.get(0), 2);
        assertEquals(resultado.get(1), 1);
    }

}
