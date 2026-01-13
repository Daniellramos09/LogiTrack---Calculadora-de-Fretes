package services;

import exceptions.WeightExceededException;

public class CapitalFreight implements FreteStrateg{
    @Override
    public double calcularPreco(double peso) {
        if (peso < 20) {
            return 10.0 + peso;
        }else{
            throw new WeightExceededException("Error: Excess weight!" );
        }
    }
}
