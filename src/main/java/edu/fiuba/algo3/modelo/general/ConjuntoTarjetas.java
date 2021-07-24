package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.ataque.CantidadInvalidaDeDadosError;

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
    private boolean sonCanjeables(){
        if(this.tarjeta1.mismoSignoQue(this.tarjeta2) && this.tarjeta1.mismoSignoQue(this.tarjeta3))
            return true;
        else if(!this.tarjeta1.mismoSignoQue(this.tarjeta2) && !this.tarjeta1.mismoSignoQue(this.tarjeta3) && !this.tarjeta2.mismoSignoQue(this.tarjeta3))
            return true;
        else
            throw new TarjetasNoCanjeablesError();
    }

    public boolean sePudoCanjear(){
        if(this.sonCanjeables()){
            this.mazo.agregarTarjetas(this.tarjeta1, this.tarjeta2, this.tarjeta3);
            return true;
        }
        return false;
    }
}
