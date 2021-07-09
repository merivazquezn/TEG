package edu.fiuba.algo3.modelo;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import org.mockito.invocation.*;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConjuntoDadosTest {

    @Test
    public void ConjuntoGeneraLaCantidadDeDadosPedidosEntre1Y3(){
        ConjuntoDados conjunto = new ConjuntoDados(2, new Randomizador());
        assertTrue(conjunto.size() == 2);
    }

    @Test
    public void ConjuntoTiraErrorAlGenerarConjuntoConMenosDe1Dado() {
        assertThrows(CantidadInvalidaDeDadosError.class, () -> {
            ConjuntoDados conjunto = new ConjuntoDados(0, new Randomizador());
        });
    }

    @Test
    public void ConjuntoTiraErrorAlGenerarConjuntoConMasDe3Dados() {
        assertThrows(CantidadInvalidaDeDadosError.class, () -> {
        ConjuntoDados conjunto = new ConjuntoDados(4, new Randomizador());
        });
    }

    @Test
    public void CunjuntoSeGeneraConLosDadosOrdenadosDeMayorAMenor() {
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
    public void ConjuntoDadoComparaConOtroConjuntoYDeberiaPerder1AtacanteY2Defensor() {
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
        ConjuntoDados conjuntoAtacante = new ConjuntoDados(3, random);
        when(random.generar(1,6)).thenReturn(1);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados(3, random);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertTrue(resultado.get(0) == 1);
        assertTrue(resultado.get(1) == 2);
    }

    @Test
    public void ConjuntoDadoComparaConOtroConjuntoYDeberiaPerder1AtacanteY1Defensor() {
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
        ConjuntoDados conjuntoAtacante = new ConjuntoDados(2, random);
        when(random.generar(1,6)).thenReturn(5);
        ConjuntoDados conjuntoDefensor = new ConjuntoDados(3, random);
        ArrayList<Integer> resultado = conjuntoAtacante.compararCon(conjuntoDefensor);
        assertTrue(resultado.get(0) == 1);
        assertTrue(resultado.get(1) == 1);
    }

}
