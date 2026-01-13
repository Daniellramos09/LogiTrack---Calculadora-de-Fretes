# Calculadora de Fretes

<p> 
 ### Cenário

Você foi contratado por uma transportadora. Eles recebem um arquivo `.csv` sujo com tentativas de entrega. Seu sistema precisa ler isso, ignorar duplicatas, calcular o valor do frete baseado na região (Capital ou Interior) e gerar um relatório de **Quanto cada Motorista faturou**.

### 1. O Arquivo (`entregas.txt`)

Crie este arquivo. Ele contém armadilhas.

Colunas: ID_PACOTE, DATA, REGIAO, PESO, MOTORISTA

Snippet de código

`P001,2026-01-10,CAPITAL,5.0,Joao
P002,2026-01-10,INTERIOR,10.0,Maria
P003,2026-01-10,CAPITAL,50.0,Joao
P001,2026-01-10,CAPITAL,5.0,Joao
P004,2026-01-10,INTERIOR,2.0,Carlos
P005,2026-01-10,CAPITAL,abc,Maria
P006,2026-01-10,INTERIOR,-5.0,Carlos`

**As Armadilhas:**

1. **P001:** Duplicado (O segundo deve ser ignorado).
2. **P003:** Peso de 50kg na Capital (Viola regra de negócio).
3. **P005:** Peso "abc" (Erro de formato).
4. **P006:** Peso negativo (Erro de lógica/negócio).

---

### 2. As Regras de Negócio (Interfaces e Classes)

Crie uma interface chamada FreteStrategy (ou RegraFrete) com um método:

double calcularPreco(double peso);

Implemente duas classes que usam essa interface:

**A. Classe `FreteCapital`**

- **Regra:** O preço é **R$ 10,00 fixo + R$ 1,00 por quilo**.
- **Restrição:** Se o peso for maior que **20kg**, lance uma Exception personalizada `PesoExcedidoException` ("Pacote muito pesado para moto na capital"). A entrega deve ser rejeitada.

**B. Classe `FreteInterior`**

- **Regra:** O preço é **R$ 20,00 fixo + R$ 0,50 por quilo**.
- **Restrição:** Não tem limite de peso superior, mas se o peso for **negativo**, lance uma `PesoInvalidoException` (crie essa também ou use a mesma mudando a mensagem).

---

### 3. O Fluxo (Service)

Seu método `processarEntregas` deve:

1. **Ler o arquivo** linha a linha.
2. **Set:** Ignorar IDs de pacotes duplicados.
3. **Try-Catch:**
    - Se o peso for "abc", capture `NumberFormatException` e pule a linha.
    - Se a regra de negócio lançar Exception (peso > 20kg na capital), capture, logue o erro e pule a linha.
4. **Lógica:**
    - Ler a coluna `REGIAO`. Se for "CAPITAL", instancie a classe `FreteCapital`. Se for "INTERIOR", instancie `FreteInterior`.
    - Chamar `calcularPreco(peso)`.
5. **Map:** Acumular o valor ganho por motorista.
    - Chave: Nome do Motorista (`String`).
    - Valor: Soma dos fretes calculados (`Double`).

---

### 4. O Relatório Final (Console)

No final, use **Stream** para imprimir os motoristas ordenados de quem ganhou mais para quem ganhou menos.

**Saída esperada (Exemplo):**

Plaintext

# 

`Log: Pacote duplicado P001...
Erro: Formato inválido na linha P005...
Erro: Peso excedido na Capital: 50.0kg (P003)

--- RELATÓRIO DE PAGAMENTO ---
Maria: R$ 25.00  (Frete do P002: 20 + 10*0.5)
Carlos: R$ 21.00 (Frete do P004: 20 + 2*0.5)
Joao: R$ 15.00   (Frete do P001: 10 + 5*1.0)`
</p>
