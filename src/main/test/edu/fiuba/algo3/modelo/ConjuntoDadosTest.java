package edu.fiuba.algo3.modelo;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConjuntoDadosTest {

    @Test
    public void ConjuntoGeneraLaCantidadDeDadosPedidosEntre1Y3(){
        ConjuntoDados conjunto;
        try {
            conjunto = new ConjuntoDados(2);
        } catch(CantidadInvalidaDeDadosError e){
            assertTrue(1==2);
            return;
        }
        assertTrue(conjunto.size() == 2);
    }

    @Test
    public void ConjuntoTiraErrorAlGenerarConjuntoConMenosDe1Dado() {
        assertThrows(CantidadInvalidaDeDadosError.class, () -> {
            ConjuntoDados conjunto = new ConjuntoDados(0);
        });
    }

    @Test
    public void ConjuntoTiraErrorAlGenerarConjuntoConMasDe3Dados() {
        assertThrows(CantidadInvalidaDeDadosError.class, () -> {
        ConjuntoDados conjunto = new ConjuntoDados(4);
        });
    }

    @Test
    public void CunjuntoSeGeneraConLosDadosOrdenadosDeMayorAMenor() {
        ConjuntoDados conjunto;
        try {
            conjunto = new ConjuntoDados(3);
        } catch (CantidadInvalidaDeDadosError e) {
            assertTrue(1==2);
            return;
        }
        boolean ordenados = true;
        ArrayList<Dado> lista = conjunto.obtenerDados();

        for(int i=0;i<lista.size()-1;i++){
            if(lista.get(i).compareTo(lista.get(i+1)) == Dado.DADO_PERDIO)
                ordenados = false;
        }
        assertTrue(ordenados);
    }



}
