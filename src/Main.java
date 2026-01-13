import services.FreteServices;

import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        FreteServices entrega = new FreteServices();
        Map<String ,Double> dadosProntos = entrega.processFile();

        System.out.println(dadosProntos);


    }
}