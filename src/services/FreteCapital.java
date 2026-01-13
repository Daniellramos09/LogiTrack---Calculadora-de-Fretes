package services;

import exceptions.PesoExcedidoException;

public class FreteCapital implements FreteStrateg{
    @Override
    public double calcularPreco(double peso) {
        if (peso < 20) {
            return 10.0 + peso;
        }else{
            throw new PesoExcedidoException("Error: Peso Excedido!" );
        }
    }
}
