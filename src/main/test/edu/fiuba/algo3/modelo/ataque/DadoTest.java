package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadoTest {

    public boolean dadoEnRango(Dado dado){
        return (dado.obtenerValor() >= 1 && dado.obtenerValor() <= 6);
    }

    @Test
    public void test01dadoGeneradoEnRangoEsperado(){
        for(int i=0;i<50;i++){
            Dado dado = new Dado(new Randomizador());
            assertTrue(dadoEnRango(dado));
        }
    }

    @Test
    public void test02dadoComparaConOtroDadoYSoloPierdeSiEsMenor(){
        IRandomizador randomizador = mock(Randomizador.class);
        when(randomizador.generar(1,6)).thenReturn(2);
        Dado dadoAtacante = new Dado(randomizador);
        when(randomizador.generar(1,6)).thenReturn(4);
        Dado dadoDefensor = new Dado(randomizador);
        assertTrue(dadoAtacante.compareTo(dadoDefensor) == Dado.DADO_PERDIO);
    }

    @Test
    public void test03dadoComparaConOtroDadoYSoloGanaSiEsMayor(){
        IRandomizador randomizador = mock(Randomizador.class);
        when(randomizador.generar(1,6)).thenReturn(6);
        Dado dadoAtacante = new Dado(randomizador);
        when(randomizador.generar(1,6)).thenReturn(5);
        Dado dadoDefensor = new Dado(randomizador);
        assertTrue(dadoAtacante.compareTo(dadoDefensor) == Dado.DADO_GANO);
    }

    @Test
    public void test04dadoComparaConOtroDadoYEmpataSiEsIgual(){
        IRandomizador randomizador = mock(Randomizador.class);
        when(randomizador.generar(1,6)).thenReturn(5);
        Dado dadoAtacante = new Dado(randomizador);
        when(randomizador.generar(1,6)).thenReturn(5);
        Dado dadoDefensor = new Dado(randomizador);
        assertTrue(dadoAtacante.compareTo(dadoDefensor) == Dado.DADO_EMPATO);
    }

}
