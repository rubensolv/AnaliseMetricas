package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import model.Combinacao;
import model.Metric;
import model.Summary;
import model.Unit;

public class LeitorMetricas {

	public void lerArquivoDeTesteMetrica(ArrayList<Metric> metricas, String nomeArq,
			HashMap<Integer, ArrayList<Unit>> unitsCombinacoes, ArrayList<Unit> unidades) {

		Metric metrica = new Metric();
		metrica = new Metric();
		metricas.add(metrica);
		try {
			FileReader arq = new FileReader(nomeArq);
			BufferedReader learArq = new BufferedReader(arq);

			String linha = learArq.readLine();

			while (linha != null) {
				// System.out.println(linha);
				if (linha.contains("Número da Abstração =")) {
					// System.out.println(linha.substring(23));
					metrica.setNumAbstracao(Integer.valueOf(linha.substring(23, linha.length()).trim()));
				}
				if (linha.contains("Distancia Media das Unidades na Abstração =")) {
					BigDecimal val = new BigDecimal(linha.substring(45).trim());
					if (!val.equals(BigDecimal.ZERO)) {
						metrica.addValueMedia(val);
						metrica.incrementQtd();
					}
				}
				if (linha.contains("LTD2 =")) {
					// System.out.println(linha.substring(7).trim());
					metrica.setLTD2(new BigDecimal(linha.substring(7).trim()));
				}
				if (linha.contains("Número de Unidades =")) {
					metrica.setNumTotalUnits(Integer.valueOf(linha.substring(21).trim()));
				}
				if (linha.contains("Número de Unidades Inimigas =")) {
					metrica.setNumUnidadesInimigas(Integer.valueOf(linha.substring(30).trim()));
				}
				if (unidades.size() < 7) {
					if (linha.contains("Protoss_Dragoon") || linha.contains("Protoss_Zealot")) {
						Unit newUnit = new Unit();
						newUnit.leDadosUnidade(linha.trim());
						unidades.add(newUnit);
					}
				}

				linha = learArq.readLine();
			}
			unitsCombinacoes.put(metrica.getNumAbstracao(), unidades);
			unidades = new ArrayList<>();
			arq.close();

		} catch (Exception e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			System.out.println(e.toString());
		}
	}

	public ArrayList<Combinacao> lerDadosCombinacao(File caminho) {
		ArrayList<Combinacao> combinacoes = new ArrayList<>();

		try {
			FileReader arq = new FileReader(caminho.getAbsolutePath().concat("/combinacoes"));
			BufferedReader learArq = new BufferedReader(arq);

			String linha = learArq.readLine();
			Combinacao combTemp = new Combinacao();
			while (linha != null) {
				if (linha.contains("Combinação")) {
					combTemp = new Combinacao();
					combTemp.setNumComb(Integer.valueOf(linha.substring(11).trim()));
					combinacoes.add(combTemp);
				} else {
					Unit newUnit = new Unit();
					newUnit.decodeGetString(linha.trim());
					combTemp.addUnit(newUnit);
				}

				linha = learArq.readLine();
			}

			arq.close();

		} catch (Exception e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			System.out.println(e.toString());
		}

		return combinacoes;
	}

	public ArrayList<Summary> lerDadosSummary(File caminho) {
		ArrayList<Summary> summaries = new ArrayList<>();

		try {
			FileReader arq = new FileReader(caminho.getAbsolutePath().concat("/summary"));
			BufferedReader learArq = new BufferedReader(arq);

			String linha = learArq.readLine();
			Summary sumTemp;
			while (linha != null) {
				sumTemp = new Summary();
				
				String[] split = linha.trim().split(",");
				sumTemp.setNumAbst(Integer.valueOf(split[0]));
				sumTemp.setNumUnitsAliadas(Integer.valueOf(split[1]));
				sumTemp.setNumUnitsInimigas(Integer.valueOf(split[2]));
				sumTemp.setDistMedia(new BigDecimal(split[3]));
				sumTemp.setLTD2(new BigDecimal(split[4]));
				
				summaries.add(sumTemp);
				
				linha = learArq.readLine();
				
			}

			arq.close();

		} catch (Exception e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			System.out.println(e.toString());
		}

		return summaries;
	}

}
