package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaisTest {

        @Test
        public void test01paisColocaTresFichasYTieneTresFichas() {

            Pais unPais = new Pais("Argentina");
            unPais.colocarFichas(3);
            assertEquals(unPais.getCantidadFichas(), 3);
        }

        @Test
        public void test02paisColocaCinchoFichasYTiene5Fichas() {
            Pais unPais = new Pais("Brasil");
            unPais.colocarFichas(5);
            assertEquals(unPais.getCantidadFichas(), 5);
        }


        @Test
        public void test03paisCon5FichasElimina2YTiene3(){
            Pais unPais = new Pais("Chile");
            unPais.colocarFichas(5);
            unPais.eliminarFichas(2);
            assertEquals(unPais.getCantidadFichas(), 3);
        }

    @Test
    public void test04paisCon3FichasElimina3YTiene0Fichas(){
        Pais unPais = new Pais("Chile");
        unPais.colocarFichas(3);
        unPais.eliminarFichas(3);
        assertEquals(unPais.getCantidadFichas(), 0);
    }

    @Test
    public void test05paisCon1FichaElimina2ElevaException() {
            Pais unPais = new Pais("Argentina");
            unPais.colocarFichas(1);
            assertThrows(EjercitosInsuficientesException.class,
                ()->{
                    unPais.eliminarFichas(2);
                });
    }

    @Test
    public void test06paisCon2FichasElimina1NoElevaExcepcion() {
            Pais unPais = new Pais("Argentina");
            unPais.colocarFichas(2);
            boolean error = false;
            try {
                unPais.eliminarFichas(1);
            } catch (EjercitosInsuficientesException e) {
                error = true;
            }
            assertFalse(error);
    }

}