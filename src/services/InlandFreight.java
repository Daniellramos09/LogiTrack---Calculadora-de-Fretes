package services;

import exceptions.InvalidWeightException;

public class InlandFreight implements FreteStrateg{
    @Override
    public double calcularPreco(double peso) {
        if (peso < 0) {
            throw new InvalidWeightException("Error: invalid weight!");

        } else {
                return 20.0 + 0.5 * peso;
        }
    }
}
