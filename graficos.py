import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import os

#para dar um estilo visual limpo
sns.set(style="whitegrid")

#dicionário com arquivos e seus respectivos títulos
arquivos = {
    "bubble_results.csv": "Bubble Sort",
    "merge_results.csv": "Merge Sort",
    "quick_results.csv": "Quick Sort",
    "selection_results.csv": "Selection Sort"
}

for nome_arquivo, titulo in arquivos.items():
    if not os.path.exists(nome_arquivo):
        print(f"[AVISO] Arquivo {nome_arquivo} não encontrado.")
        continue

    df = pd.read_csv(nome_arquivo, header=None)
    df.columns = ["Tamanho", "Threads", "TempoSerial(ms)", "TempoParalelo(ms)"]

    #agrupar os dados por Tamanho e Threads, calculando média e desvio
    agrupado = df.groupby(["Tamanho", "Threads"])["TempoParalelo(ms)"].agg(["mean", "std"]).reset_index()
    agrupado.columns = ["Tamanho", "Threads", "Media", "Desvio"]

    plt.figure(figsize=(10, 6))
    sns.barplot(
        data=agrupado,
        x="Tamanho",
        y="Media",
        hue="Threads",
        palette="Set2",
        capsize=0.1,
        errwidth=1.5,
        ci=None
    )

    #implementamos alguns ajustes visuais
    plt.title(f"{titulo} - Tempo Médio Paralelo por Tamanho", fontsize=14)
    plt.xlabel("Tamanho do Vetor", fontsize=12)
    plt.ylabel("Tempo Médio (ms)", fontsize=12)
    plt.legend(title="Threads")
    plt.tight_layout()

    #salvar gráfico
    nome_imagem = f"{nome_arquivo}_grafico.png"
    plt.savefig(nome_imagem)
    plt.close()

    print(f"[OK] Gráfico salvo: {nome_imagem}")

print("✅ Todos os gráficos foram gerados com sucesso!")
