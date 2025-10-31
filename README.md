UNIVERSIDADE DE FORTALEZA
<br>
CENTRO DE CIÊNCIAS TECNOLÓGICAS
<br>
CURSO: CIÊNCIA DA COMPUTAÇÃO
<br>
<br>
<br>
<br>

## **SIMULADOR PARA ANÁLISE DE DESEMPENHO DE ALGORITMOS DE SUBSTITUIÇÃO DE PÁGINAS**

<br>
<br>

**Autor 1:** Rodrigo Cirino.
<br>
**Autor 2:** Erfon Spanos.

<br>
<br>

**Palavras-chave:**
Memória Virtual. Sistemas Operacionais. Algoritmos de Substituição de Páginas. Simulador. Java Swing.

<br>

## Resumo

Este trabalho propõe o desenvolvimento de um simulador em Java, com interface gráfica (GUI) utilizando a biblioteca Swing, para avaliar o desempenho de diferentes algoritmos de substituição de páginas. O objetivo é analisar e comparar a contagem de faltas de página (page faults) de quatro algoritmos selecionados (dentre FIFO, LRU, Relógio, Ótimo, NFU e Envelhecimento) com base em uma cadeia de referência de páginas fornecida pelo usuário. O simulador oferece uma interface interativa e uma visualização gráfica dos resultados, permitindo uma análise comparativa direta da eficiência de cada método no gerenciamento de memória virtual.

## Introdução

O gerenciamento eficiente da memória virtual é crucial para o desempenho dos sistemas operacionais. A alocação e substituição de páginas são tarefas complexas que afetam diretamente a experiência do usuário. Neste contexto, os algoritmos de substituição de páginas desempenham um papel fundamental na otimização do uso da memória. Existem diversos algoritmos propostos para essa finalidade, cada um com suas características, complexidade e eficiência. Os principais algoritmos estudados incluem o FIFO (First In, First Out), que substitui a página mais antiga; o LRU (Least Recently Used), que substitui a página menos recentemente usada; o Algoritmo do Relógio (ou Segunda Chance), uma aproximação do LRU; o Algoritmo Ótimo, que serve como base teórica de comparação; o NFU (Not Frequently Used) e o de Envelhecimento (Aging), que tentam simular o LRU via software. A escolha do algoritmo impacta diretamente a quantidade de faltas de página e, consequentemente, a performance geral do sistema.

## Metodologia

Para a construção do simulador, foi utilizada a linguagem de programação Java. A interface gráfica do usuário (GUI) foi desenvolvida com a biblioteca Java Swing, permitindo a interatividade do usuário com o programa. O sistema foi projetado para receber como entrada principal uma cadeia de números inteiros, que representa a sequência de páginas a serem acessadas. Foram implementados quatro métodos distintos, cada um correspondendo a um algoritmo de substituição de página (por exemplo: FIFO, LRU, Relógio e Ótimo). Cada método processa a mesma cadeia de referência de entrada, simulando o comportamento da memória e contabilizando o número de faltas de página. A interface gráfica permite ao usuário inserir a cadeia de páginas, definir o número de quadros de memória (frames) e executar a simulação. Ao final, a interface exibe os resultados e apresenta gráficos comparativos, conforme a funcionalidade extra solicitada.

## Resultados e Discussão

O simulador exibe como saída principal o número total de faltas de página (page faults) para cada um dos quatro algoritmos implementados. A interface gráfica apresenta esses dados de forma clara, permitindo uma comparação direta. A saída segue o formato:

  * Método 1 (ex: FIFO) - X faltas de página
  * Método 2 (ex: LRU) - Y faltas de página
  * Método 3 (ex: Relógio) - Z faltas de página
  * Método 4 (ex: Ótimo) - W faltas de página

Adicionalmente, gráficos comparativos em barras são gerados pela interface Swing para ilustrar visualmente qual algoritmo obteve o melhor desempenho (menor número de faltas) para uma determinada cadeia de entrada e configuração de quadros de memória. A discussão dos resultados permite observar como algoritmos mais simples, como o FIFO, tendem a ter um desempenho inferior (mais faltas de página) em comparação com algoritmos mais complexos como o LRU ou o Ótimo (que representa o limite ideal de eficiência).

## Conclusão

O simulador desenvolvido em Java e Swing provou ser uma ferramenta didática e funcional para o estudo e a análise comparativa de algoritmos de substituição de páginas. O software cumpre os objetivos propostos ao implementar quatro algoritmos distintos e ao fornecer uma interface gráfica interativa para a visualização dos resultados, incluindo a contagem de faltas de página e gráficos comparativos. O projeto permite a visualização prática de conceitos teóricos de gerenciamento de memória virtual, facilitando o entendimento sobre o impacto direto da escolha do algoritmo na performance de um sistema.

## Referências

DEVMEDIA. **Introdução à interface GUI no Java**. Disponível em: [https://www.devmedia.com.br/introducao-a-interface-gui-no-java/25646](https://www.devmedia.com.br/introducao-a-interface-gui-no-java/25646). Acesso em: 31 out. 2025.

PRIMEGUI. **PrimeFaces ShowCase**. Disponível em: [https://www.primefaces.org](https://www.primefaces.org). Acesso em: 31 out. 2025.

SDPM-SIMULATOR. **Page Replacement Algorithm Simulator**. Disponível em: [https://sdpm-simulator.netlify.app](https://sdpm-simulator.netlify.app). Acesso em: 31 out. 2025.

TANENBAUM, A. S.; BOS, H. **Sistemas Operacionais Modernos**. 4. ed. São Paulo: Pearson Prentice Hall, 2016.
