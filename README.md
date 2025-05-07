# Trab-Av2-Compt-Paralela
=======
# ANÁLISE DE DESEMPENHO DE ALGORITMOS DE ORDENAÇÃO EM AMBIENTES CONCORRENTES E PARALELOS USANDO JAVA

**Autores:**
- Ângelo Kauê
- Guilherme Silva

**Palavras-chave:** Paralelismo. Ordenação. Java. Desempenho. Multithreading.

---

## Resumo

Esse artigo apresenta um estudo comparativo sobre o desempenho de quatro algoritmos de ordenação implementados em Java, tanto em versões sequenciais quanto paralelas. Foram utilizados os algoritmos Bubble Sort, Merge Sort, Quick Sort e Selection Sort, aplicados a diferentes tamanhos de vetores e com variações no número de threads (2 e 4). Os tempos de execução foram registrados em arquivos CSV e os resultados, posteriormente, representados em gráficos gerados automaticamente por meio de um script Python, acionado diretamente pelo código Java. O objetivo do estudo é observar se a paralelização realmente contribui para ganho de desempenho nos algoritmos escolhidos. Os testes revelaram que, apesar de alguns algoritmos se beneficiarem do paralelismo, o ganho nem sempre é garantido, especialmente para algoritmos mais simples ou para tamanhos pequenos de entrada.

---

## Introdução

O aumento da demanda por performance em aplicações computacionais tem impulsionado o estudo de técnicas de paralelização. Algoritmos de ordenação são uma das estruturas mais estudadas em ciência da computação, e entender como eles se comportam em ambientes concorrentes pode ser essencial para otimizações reais. Esse trabalho tem como objetivo analisar o comportamento de diferentes algoritmos de ordenação, comparando suas execuções em versões sequenciais e paralelas, utilizando a linguagem Java e ferramentas da própria plataforma para gerenciar as threads.

---

## Metodologia

Implementamos quatro algoritmos de ordenação em Java: Bubble Sort, Merge Sort, Quick Sort e Selection Sort. Cada um foi desenvolvido em duas versões: uma sequencial (serial) e outra paralela com controle de threads. A execução foi controlada por uma classe de testes que variava tanto o tamanho do vetor de entrada (1000, 5000, 10000 elementos) quanto a quantidade de threads (2 e 4). Para cada combinação, foram feitas cinco execuções, e os tempos médios foram registrados em arquivos CSV. Em seguida, um script Python foi acionado automaticamente pelo Java para ler os arquivos gerados e construir os gráficos com os resultados. Os gráficos foram salvos como imagens PNG e incluídos como parte da análise.

---

## Resultados e Discussão

Os resultados mostraram que a performance variou bastante entre os algoritmos. O Bubble Sort e o Selection Sort, por exemplo, não apresentaram ganhos relevantes com a paralelização. Em alguns casos, o uso de múltiplas threads até piorou o tempo final de execução, devido ao overhead de criação de threads e sincronização. Já os algoritmos Merge Sort e Quick Sort tiveram comportamentos mais interessantes. O Merge Sort, por sua estrutura naturalmente divisível, se beneficiou melhor da divisão em tarefas. O Quick Sort também apresentou bons resultados, mas com variações maiores por depender da escolha do pivô. Em geral, o paralelismo demonstrou ser mais eficiente para algoritmos com divisão natural de tarefas e entradas maiores. Os gráficos deixaram claro que o uso de múltiplas threads nem sempre resulta em melhor desempenho, e que existe um ponto de equilíbrio entre complexidade do algoritmo e custo da paralelização.

---

## Conclusão

Através da implementação e comparação entre versões sequenciais e paralelas dos algoritmos de ordenação, foi possível perceber que o uso de múltiplas threads nem sempre garante melhor desempenho. Algoritmos como Merge Sort se mostraram mais adaptáveis ao modelo paralelo, enquanto outros, como Bubble Sort, apresentaram pouca ou nenhuma vantagem. A automatização do processo, desde os testes até a geração dos gráficos, foi importante para dar maior confiabilidade aos resultados. Esse trabalho reforça a importância de analisar caso a caso antes de aplicar paralelismo em busca de performance, e mostra como Java pode ser uma ferramenta prática para experimentos desse tipo.

---

## Referências

CORMEN, T. H.; LEISERSON, C. E.; RIVEST, R. L.; STEIN, C. *Algoritmos: Teoria e Prática*. Rio de Janeiro: Elsevier, 3ª ed., 2012.

ORACLE. *Java Platform, Standard Edition Documentation*. Disponível em: https://docs.oracle.com/javase. Acesso em: 25 abr. 2025.

TANENBAUM, A. S.; BOS, H. *Modern Operating Systems*. 4th ed. Pearson, 2014.

---

## Instruções de Execução

### Requisitos
- Java 11 ou superior
- Python 3.11 (com `seaborn`, `pandas`, `matplotlib` instalados)

### Instalar dependências Python (caso necessário):
```bash
pip install pandas matplotlib seaborn
```

### Como executar o projeto
1. Compile o projeto Java:
```bash
javac Main.java
```

2. Execute o programa:
```bash
java Main
```

Durante a execução:
- O programa irá realizar os testes com todos os algoritmos.
- Os dados serão salvos em arquivos `.csv`.
- O script `graficos.py` será chamado automaticamente pelo Java.
- Os gráficos serão gerados como imagens `.png` na pasta do projeto.

### Observação1
Se o script Python não abrir ou não gerar os gráficos corretamente, verifique se o caminho do interpretador Python está correto dentro da `Main.java`, ou utilize o caminho absoluto para garantir a execução.
Se necessário, altere o ProcessBuilder no Main.java para usar o caminho completo do interpretador.

### Observação2
Deixamos os arquivos CSV's salvos, caso queira testar, pode apagar a executar mais uma vez. Eles serão criados novamente.

>>>>>>> [Link](https://github.com/angelo-kaue/Trab-Av2-Compt-Paralela)
