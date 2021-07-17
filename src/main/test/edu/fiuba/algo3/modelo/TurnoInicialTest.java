package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.controlador.EleccionInicial;
import edu.fiuba.algo3.modelo.flujoDeJuego.*;
import edu.fiuba.algo3.modelo.jugador.*;
import edu.fiuba.algo3.modelo.general.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class  TurnoInicialTest {

    Jugador jugador;
    Pais unPais;
    Tablero tablero;
    EleccionInicial eleccion;

    public void setUp(){
        jugador = mock(Jugador.class);
        unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        Tablero tablero = mock(Tablero.class);
        eleccion = mock(EleccionInicial.class);
    }

    @Test
    public void test01SeCreaUnTurnoInicialCon5EjercitosYLosColocaTodosEnUnPais(){
        setUp();
        when(eleccion.colocacionDeEjercitos()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add(true);
                lista.add(unPais);
                lista.add(1);
                return lista;
            }
        });
        TurnoInicial turno = new TurnoInicial(jugador, 5, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(7, unPais.getCantidadEjercitos());
    }

    @Test
    public void test02SeCreaUnTurnoInicialCon3EjercitosYLosColocaTodosEnUnPais(){
        setUp();
        when(eleccion.colocacionDeEjercitos()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add(true);
                lista.add(unPais);
                lista.add(1);
                return lista;
            }
        });
        TurnoInicial turno = new TurnoInicial(jugador, 3, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(5, unPais.getCantidadEjercitos());
    }

    @Test
    public void test03SeCreaUnTurnoInicialCon5EjercitosYLosColocaEnTresPaises(){
        setUp();
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        Pais otroPaisMas = new Pais("Bolivia");
        otroPaisMas.colocarEjercitos(2);
        when(eleccion.colocacionDeEjercitos()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add(true);
                if(count == 0){
                    lista.add(unPais);
                }
                else if(count == 1){
                    lista.add(otroPais);
                }
                else{
                    lista.add(otroPaisMas);
                }
                lista.add(1);
                count++;
                return lista;
            }
        });
        TurnoInicial turno = new TurnoInicial(jugador, 5, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(3, unPais.getCantidadEjercitos());
        assertEquals(3, otroPais.getCantidadEjercitos());
        assertEquals(5, otroPaisMas.getCantidadEjercitos());
    }

    @Test
    public void test04SeCreaUnTurnoInicialCon5EjercitosIntentaColocarDeMasEIgnoraLaPeticion(){
        setUp();
        when(eleccion.colocacionDeEjercitos()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add(true);
                lista.add(unPais);
                if(count == 0){
                    lista.add(6);
                }
                else{
                    lista.add(5);
                }
                count++;
                return lista;
            }
        });
        TurnoInicial turno = new TurnoInicial(jugador, 5, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(7, unPais.getCantidadEjercitos());
    }

}

//TODO: crear la prueba en la que se cumple el objetivo apenas se colocan los paises en el turno inicial