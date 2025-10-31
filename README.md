# SIMULADOR PARA ANÁLISE DE DESEMPENHO DE ALGORITMOS DE SUBSTITUIÇÃO DE PÁGINAS

### Autor 1: Erfon Spanos  
### Autor 2: Rodrigo Cirino  

**Palavras-chave:** Memória Virtual • Sistemas Operacionais • Algoritmos de Substituição de Páginas • Simulador • Java Swing  

---

## Resumo

Este trabalho propõe o desenvolvimento de um simulador em **Java**, com interface gráfica (**GUI**) utilizando a biblioteca **Swing**, para avaliar o desempenho de diferentes algoritmos de substituição de páginas.  
O objetivo é analisar e comparar a contagem de **faltas de página (page faults)** dos quatro algoritmos implementados (**FIFO**, **LRU**, **Relógio** e **NFU**) com base em uma cadeia de referência de páginas fornecida pelo usuário.  

O simulador oferece uma interface interativa e uma visualização gráfica dos resultados, permitindo uma análise comparativa direta da eficiência de cada método no gerenciamento de memória virtual.

---

## Introdução

O gerenciamento eficiente da memória virtual é crucial para o desempenho dos sistemas operacionais.  
A alocação e substituição de páginas são tarefas complexas que afetam diretamente a experiência do usuário.  
Neste contexto, os **algoritmos de substituição de páginas** desempenham um papel fundamental na otimização do uso da memória.

Existem diversos algoritmos propostos para essa finalidade, cada um com suas características, complexidade e eficiência.  
Os 6 algoritmos propostos para serem implementados neste trabalho são (Sabendo que o aluno deve escolher 4 desses 6 para implementar):

- **FIFO (First In, First Out):** Substitui a página mais antiga.  
- **LRU (Least Recently Used):** Substitui a página menos recentemente usada.  
- **Relógio (Clock):** Uma implementação de "segunda chance" que se aproxima do LRU.
- **Algoritmo Ótimo:** Substitui a página que será usada mais tarde no futuro (ótima previsão). 
- **NFU (Not Frequently Used):** Substitui a página menos referenciada com base em um contador de frequência.
- **Envelhecimento (Aging):** Usa bits de envelhecimento para rastrear o tempo desde a última referência a cada página. As páginas com menor tempo de referência são substituídas.

A escolha do algoritmo impacta diretamente a quantidade de **faltas de página** e, consequentemente, a performance geral do sistema.

---

## Metodologia

Para a construção do simulador, foi utilizada a linguagem de programação **Java**.  
O sistema foi projetado para receber como entrada principal:

- Uma cadeia de números inteiros (representando a sequência de páginas)  
- O número de quadros de memória (**frames**)  

Foram implementados quatro métodos distintos, cada um correspondendo a um algoritmo de substituição de página.  
Cada método processa a mesma cadeia de referência de entrada, simulando o comportamento da memória e contabilizando o número de faltas de página.

---

## Evolução da Interface

Inicialmente, uma prova de conceito da interface foi desenvolvida como uma aplicação **web moderna (Full-Stack)** utilizando **React** e **Tailwind CSS** para o frontend, e **Spring Boot** para o backend (API).  
Embora totalmente funcional, essa abordagem exigiria que o professor executasse dois ambientes separados (um servidor Java e um servidor Node.js).

Para facilitar os testes e a correção, o projeto foi **refatorado** para uma **aplicação desktop única**, utilizando a biblioteca nativa **Java Swing** para a interface gráfica (GUI).  
Isso permite que o simulador seja executado como um único arquivo `.jar` ou diretamente pela IDE, simplificando a verificação.

A interface Swing permite ao usuário:

- Inserir a cadeia de páginas  
- Definir o número de quadros de memória  
- Executar a simulação  
- Visualizar os resultados e gráficos comparativos em barras  

---

## Como Executar

Existem duas formas de executar o simulador:

### Executando pela IDE (Recomendado)

1. Clone o repositório.  
2. Abra o projeto em sua **IDE Java** preferida (IntelliJ, Eclipse, etc.).  
3. Localize a classe principal da UI que contém o método `SimulationUI`.  
4. Execute o arquivo. A interface Swing será iniciada já utilizando nossos Algoritmos implementados.

