package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadoTest {

    public boolean dadoEnRango(Dado dado){
        return (dado.obtenerValor() >= 1 && dado.obtenerValor() <= 6);
    }

    @Test
    public void dadoGeneradoEnRangoEsperado(){
        for(int i=0;i<50;i++){
            Dado dado = new Dado(new Randomizador());
            assertTrue(dadoEnRango(dado));
        }
    }

    @Test
    public void dadoComparaConOtroDadoYSoloPierdeSiEsMenor(){
        IRandomizador randomizador = mock(Randomizador.class);
        when(randomizador.generar(1,6)).thenReturn(2);
        Dado dadoAtacante = new Dado(randomizador);
        when(randomizador.generar(1,6)).thenReturn(4);
        Dado dadoDefensor = new Dado(randomizador);
        assertTrue(dadoAtacante.compareTo(dadoDefensor) == Dado.DADO_PERDIO);
    }

    @Test
    public void dadoComparaConOtroDadoYSoloGanaSiEsMayor(){
        IRandomizador randomizador = mock(Randomizador.class);
        when(randomizador.generar(1,6)).thenReturn(6);
        Dado dadoAtacante = new Dado(randomizador);
        when(randomizador.generar(1,6)).thenReturn(5);
        Dado dadoDefensor = new Dado(randomizador);
        assertTrue(dadoAtacante.compareTo(dadoDefensor) == Dado.DADO_GANO);
    }

    @Test
    public void dadoComparaConOtroDadoYEmpataSiEsIgual(){
        IRandomizador randomizador = mock(Randomizador.class);
        when(randomizador.generar(1,6)).thenReturn(5);
        Dado dadoAtacante = new Dado(randomizador);
        when(randomizador.generar(1,6)).thenReturn(5);
        Dado dadoDefensor = new Dado(randomizador);
        assertTrue(dadoAtacante.compareTo(dadoDefensor) == Dado.DADO_EMPATO);
    }

}
