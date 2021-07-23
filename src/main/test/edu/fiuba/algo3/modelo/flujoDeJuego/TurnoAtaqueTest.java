package edu.fiuba.algo3.modelo.flujoDeJuego;

public class TurnoAtaqueTest {
/*
    @Test
    public void test01sePideAtacarYElTurnoDelegaEnElTableroCorrectamente(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count=0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(2);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        Jugador atacante = mock(Jugador.class);
        Tablero tablero = mock(Tablero.class);
        TurnoAtaque turno = new TurnoAtaque(atacante, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        verify(tablero).conquisto(eq(unPais), eq(otroPais), eq(2));
    }

    @Test
    public void test02elAtaqueConquistaYElTurnoTransfiereLasFichasPedidas(){
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.asignarJugador(otroJugador);
        otroPais.colocarEjercitos(2);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        Tablero tablero = mock(Tablero.class);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(2);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        when(eleccion.cantidadAMover()).thenReturn(2);
        when(tablero.conquisto(eq(unPais), eq(otroPais), eq(2))).thenAnswer( new Answer() {
            public Object answer(InvocationOnMock invocation) {
                otroPais.serConquistadoPor(unJugador);
                return true;
            }
        });
        Jugador atacante = mock(Jugador.class);
        TurnoAtaque turno = new TurnoAtaque(atacante, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(3, unPais.getCantidadEjercitos());
    }

    @Test
    public void test03elAtaqueNoConquistaYElTurnoNoTransfiereLasFichasPedidas(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        Tablero tablero = mock(Tablero.class);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(3);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        when(eleccion.cantidadAMover()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return 2;
            }
        });
        when(tablero.conquisto(eq(unPais), eq(otroPais), eq(2))).thenReturn(false);
        Jugador atacante = mock(Jugador.class);
        TurnoAtaque turno = new TurnoAtaque(atacante, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(5, unPais.getCantidadEjercitos());
    }

    @Test
    public void test04sePideAtacarYElJugadorCumplioUnObjetivo(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        Tablero tablero = mock(Tablero.class);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(3);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        when(eleccion.cantidadAMover()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return 2;
            }
        });
        when(tablero.conquisto(eq(unPais), eq(otroPais), eq(3))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return true;
            }
        });
        Jugador atacante = mock(Jugador.class);
        when(atacante.jugadorGano()).thenReturn(true);
        TurnoAtaque turno = new TurnoAtaque(atacante, eleccion);
        assertFalse(turno.realizarTurnoYContinuar(tablero));
    }
*/
}
