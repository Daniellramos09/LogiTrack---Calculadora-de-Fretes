package services;

import exceptions.PesoExcedidoException;
import exceptions.PesoInvalidoException;

public class FreteInterior implements FreteStrateg{
    @Override
    public double calcularPreco(double peso) {
        if (peso < 0) {
            throw new PesoInvalidoException("Error: peso negativaddo!");

        } else {
                return 20.0 + 0.5 * peso;
        }
    }
}
