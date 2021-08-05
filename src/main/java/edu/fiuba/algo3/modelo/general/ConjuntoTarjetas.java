package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.ataque.CantidadInvalidaDeDadosError;
import edu.fiuba.algo3.modelo.flujoDeJuego.TarjetasRepetidasException;

import java.util.ArrayList;

public class ConjuntoTarjetas {
    private Tarjeta tarjeta1;
    private Tarjeta tarjeta2;
    private Tarjeta tarjeta3;
    private Mazo mazo;

    public ConjuntoTarjetas(Tarjeta tarjeta1, Tarjeta tarjeta2, Tarjeta tarjeta3, Mazo mazo){
        this.tarjeta1 = tarjeta1;
        this.tarjeta2 = tarjeta2;
        this.tarjeta3 = tarjeta3;
        this.mazo = mazo;
    }

    public static boolean sonCanjeables(Tarjeta tarjeta1, Tarjeta tarjeta2, Tarjeta tarjeta3){
        if(tarjeta1 == tarjeta2 || tarjeta1 == tarjeta3 || tarjeta2 == tarjeta3)
            return false;
        boolean sonDeMismoSigno = (tarjeta1.mismoSignoQue(tarjeta2) && tarjeta1.mismoSignoQue(tarjeta3));
        boolean sonDeSignosDistintos = (!tarjeta1.mismoSignoQue(tarjeta2) && !tarjeta1.mismoSignoQue(tarjeta3) && !tarjeta2.mismoSignoQue(tarjeta3));

        return (sonDeMismoSigno || sonDeSignosDistintos);
    }

    public boolean sePudoCanjear(){
        if(ConjuntoTarjetas.sonCanjeables(this.tarjeta1, this.tarjeta2, this.tarjeta3)){
            this.mazo.agregarTarjetas(this.tarjeta1, this.tarjeta2, this.tarjeta3);
            return true;
        }
        return false;
    }

    public ArrayList<Tarjeta> getTarjetas() {
        ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
        tarjetas.add(this.tarjeta1);
        tarjetas.add(this.tarjeta2);
        tarjetas.add(this.tarjeta3);
        return tarjetas;
    }

}
