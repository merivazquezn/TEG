package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadoTest {

    public boolean dadoEnRango(Dado dado){
        return (dado.obtenerValor() >= 1 && dado.obtenerValor() <= 6);
    }

    @Test
    public void dadoGeneradoEnRangoEsperado(){
        for(int i=0;i<50;i++){
            Dado dado = new Dado();
            assertTrue(dadoEnRango(dado));
        }
    }

    @Test
    public void dadoComparaConOtroDadoYSoloGanaSiEsMayor(){
        for(int i=0;i<50;i++){
            Dado dado1 = new Dado();
            Dado dado2 = new Dado();

            if(dado1.obtenerValor() > dado2.obtenerValor())
                assertTrue(dado1.compareTo(dado2) == Dado.DADO_GANO);
        }
    }

    @Test
    public void dadoComparaConOtroDadoYSoloPierdeSiEsMayor(){
        for(int i=0;i<50;i++){
            Dado dado1 = new Dado();
            Dado dado2 = new Dado();

            if(dado1.obtenerValor() < dado2.obtenerValor())
                assertTrue(dado1.compareTo(dado2) == Dado.DADO_PERDIO);
        }
    }

    @Test
    public void dadoComparaConOtroDadoYEmpataSiEsIgual(){
        Dado dado1 = new Dado();
        Dado dado2 = new Dado();

        while(dado1.obtenerValor() != dado2.obtenerValor()) {
            dado1 =new Dado();
        }

        assertTrue(dado1.compareTo(dado2) == Dado.DADO_EMPATO);
    }

}
