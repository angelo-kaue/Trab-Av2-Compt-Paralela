import pandas as pd
import matplotlib.pyplot as plt
import os

# Lista de arquivos e títulos
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

    plt.figure(figsize=(8, 5))
    for thread in sorted(df["Threads"].unique()):
        subset = df[df["Threads"] == thread]
        plt.plot(subset["Tamanho"], subset["TempoParalelo(ms)"], marker='o', label=f"{int(thread)} Threads")

    plt.title(f"{titulo} - Tempo Paralelo vs Tamanho")
    plt.xlabel("Tamanho do Vetor")
    plt.ylabel("Tempo (ms)")
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.tight_layout()
    plt.savefig(f"{nome_arquivo}_grafico.png")
    plt.close()

