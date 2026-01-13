package services;

import entities.Entrega;
import exceptions.PesoExcedidoException;
import exceptions.PesoInvalidoException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FreteServices {

    public Map<String, Double> processFile(){

        Set<Entrega> set = new HashSet<>();
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

                    set.add(new Entrega(id, date, capital, kg,name));

                    if(!idProcessados.add(id)){
                        System.err.println("Id: " + id + " Duplicado");
                        line = br.readLine();
                        continue;
                    }

                    FreteStrateg frete;
                    if(capital.equalsIgnoreCase("CAPITAL")){
                        frete = new FreteCapital();
                    }else{
                        frete = new FreteInterior();
                    }

                    double valorRecebido = frete.calcularPreco(kg);

                    map.merge(name, valorRecebido, Double::sum);

                } catch (NumberFormatException | PesoExcedidoException | PesoInvalidoException e ){
                    System.err.println(e.getMessage() + " -- " + line);

                }

                 line = br.readLine();
            }

            System.out.println("---------------RELATÓRIO DE PAGAMENTO-------------");

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
