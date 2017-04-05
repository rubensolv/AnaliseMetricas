package principal;

import java.io.File;
import java.util.ArrayList;

import graficos.GrafDadosCombinacao;
import graficos.GrafLTD2PorCombinacao;
import model.Combinacao;
import model.Summary;
import utils.LeitorMetricas;

public class MainTestes {

	public static void main(String[] args) {

		for (int i = 1; i < 2; i++) {

			File diretorio = new File("/media/rubens/Dados/MetricDados/SimAmostragem/Simulacao50UnAmostra3/EstadoNumber" + i);
			LeitorMetricas leitor = new LeitorMetricas();
			ArrayList<Combinacao> combinacoes = leitor.lerDadosCombinacao(diretorio);
			System.out.println("Total de Combinacoes=" + combinacoes.size());

			ArrayList<Summary> summaries = leitor.lerDadosSummary(diretorio);
			System.out.println("Total de registros lidos =" + summaries.size());

			gerarGrafLTD2PorCombinacao(summaries, diretorio);
			gerarDadosCombinacao(combinacoes, summaries, diretorio);
		}

		System.out.println("Gráficos gerados!");

	}

	public static void gerarDadosCombinacao(ArrayList<Combinacao> combinacoes, ArrayList<Summary> summaries,
			File diretorio) {
		GrafDadosCombinacao grafDados = new GrafDadosCombinacao();
		grafDados.gravarDadosCombinacao(combinacoes, summaries, diretorio);
	}

	public static void gerarGrafLTD2PorCombinacao(ArrayList<Summary> summaries, File diretorio) {
		GrafLTD2PorCombinacao grafLTD2 = new GrafLTD2PorCombinacao();
		grafLTD2.lerListSummary(summaries);
		grafLTD2.gravarGrafLTD2(diretorio);

	}

}
