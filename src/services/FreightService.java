package services;

import entities.Delivery;
import exceptions.WeightExceededException;
import exceptions.InvalidWeightException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FreightService {

    public Map<String, Double> processFile(){

        Set<Delivery> set = new HashSet<>();
        Set<String>  idProcessados = new HashSet<>();
        Map<String,Double> map = new HashMap<>();

        try(BufferedReader br  = new BufferedReader(new FileReader("C:\\temp\\entregas.txt"))){

            String line = br.readLine();

            while(line != null){
                try {
                    String[] split = line.split(",");
                    String id = split[0];
                    LocalDate date = LocalDate.parse(split[1]);
                    String capital = split[2];
                    Double kg = Double.parseDouble(split[3]);
                    String name = split[4];

                    set.add(new Delivery(id, date, capital, kg,name));

                    if(!idProcessados.add(id)){
                        System.err.println("Id: " + id + " Duplicado");
                        line = br.readLine();
                        continue;
                    }

                    FreteStrateg frete;
                    if(capital.equalsIgnoreCase("CAPITAL")){
                        frete = new CapitalFreight();
                    }else{
                        frete = new InlandFreight();
                    }

                    double valorRecebido = frete.calcularPreco(kg);

                    map.merge(name, valorRecebido, Double::sum);

                } catch (NumberFormatException | WeightExceededException | InvalidWeightException e ){
                    System.err.println(e.getMessage() + " -- " + line);

                }

                 line = br.readLine();
            }

            System.out.println("---------------PAYMENT REPORT-------------");

            map.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .forEach(p -> {
                        System.out.println( p.getKey() + ": " + p.getValue());
                    });



        }catch (Exception e ){
            System.err.printf(e.getMessage());
        }

        return Map.of();
    }

}
