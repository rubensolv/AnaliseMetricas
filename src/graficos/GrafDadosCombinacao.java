package graficos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import model.Combinacao;
import model.Summary;

public class GrafDadosCombinacao {
	private ArrayList<Combinacao> combinacoes;

	public GrafDadosCombinacao() {
		combinacoes = new ArrayList<>();
	}

	public void gravarDadosCombinacao(ArrayList<Combinacao> combinacoes, ArrayList<Summary> summaries, File diretorio) {
		this.combinacoes = combinacoes;
		// preencher dados das combinações
		preencherDadosCombinacao(summaries);
		try {
			FileWriter arq = new FileWriter(diretorio.getAbsolutePath().concat("/graficoDadosCombinacao"));
			PrintWriter gravarArq = new PrintWriter(arq);
			
			gravarArq.println(montarCabecalho());
			
			for(Combinacao comb : combinacoes){
				gravarArq.println(comb.getGrafDadosCombinacao());
			}
			
			gravarArq.flush();
			gravarArq.close();
			arq.close();
		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo GrafDadosCombinacao");
			e.printStackTrace();
		}
		
	}
	
	protected String montarCabecalho(){
		String cabecalho = "NumComb;DistanciaMediaUnidadesNaBat;MediaLTD2;DistanciaMediaInicialUnidades;";
		cabecalho = cabecalho.concat("TotalVitorias;").concat("TotalDerrotas;").concat("TotalUnidadesRestantesEmVitorias;");
		cabecalho = cabecalho.concat("TotalUnidadesRestantesEmDerrotas");
		
		return cabecalho;
	}
	
	protected void preencherDadosCombinacao(ArrayList<Summary> summaries){
		for (Combinacao comb : this.combinacoes) {
			comb.setDistMediaInicialUnits(comb.calcularDistanciaMediaInicialUnidades());
			comb.setMediaLTD2(calcularMediaLTD2(comb.getNumComb(), summaries));
			comb.setDistMediaUnits(calcularMediaDistUnits(comb.getNumComb(), summaries));
			comb.setTotalVitoria(calcularTotalVitorias(comb.getNumComb(), summaries));
			comb.setTotalDerrotas(calcularTotalDerrotas(comb.getNumComb(), summaries));
			comb.setTotalUnitsRestVitoria(calcularTotalUnitsRestVitoria(comb.getNumComb(), summaries));
			comb.setTotalUnitsRestDerrota(calcularTotalUnitsRestDerrota(comb.getNumComb(), summaries));
		}
	}

	protected Integer calcularTotalUnitsRestDerrota(Integer numComb, ArrayList<Summary> summaries){
		int totalUnDerrota = 0;
		
		for (Summary summary : summaries) {
			if (summary.getNumAbst() == numComb) {
				if(summary.getLTD2().compareTo(new BigDecimal(0)) < 0){
					totalUnDerrota += summary.getNumUnitsInimigas();
				}
			}
		}
		
		return totalUnDerrota;
	}

	protected Integer calcularTotalUnitsRestVitoria(Integer numComb, ArrayList<Summary> summaries){
		int totalUnVitoria = 0;
		
		for (Summary summary : summaries) {
			if (summary.getNumAbst() == numComb) {
				if(summary.getLTD2().compareTo(new BigDecimal(0)) >= 0){
					totalUnVitoria += summary.getNumUnitsAliadas();
				}
			}
		}
		
		return totalUnVitoria;
		
		
	}

	protected Integer calcularTotalDerrotas(Integer numComb, ArrayList<Summary> summaries) {
		int totalDerrotas = 0;

		for (Summary summary : summaries) {
			if (summary.getNumAbst() == numComb) {
				if (summary.getLTD2().compareTo(new BigDecimal(0)) < 0) {
					totalDerrotas++;
				}
			}
		}

		return totalDerrotas;
	}

	protected Integer calcularTotalVitorias(Integer numComb, ArrayList<Summary> summaries) {
		int totalVitorias = 0;

		for (Summary summary : summaries) {
			if (summary.getNumAbst() == numComb) {
				if (summary.getLTD2().compareTo(new BigDecimal(0)) >= 0) {
					totalVitorias++;
				}
			}
		}

		return totalVitorias;
	}

	protected BigDecimal calcularMediaDistUnits(Integer numComb, ArrayList<Summary> summaries) {
		BigDecimal sumMedia = BigDecimal.ZERO;
		int cont = 0;
		for (Summary sum : summaries) {
			if (sum.getNumAbst() == numComb) {
				cont++;
				sumMedia = sumMedia.add(sum.getDistMedia());
			}
		}

		return sumMedia.divide(new BigDecimal(cont));
	}

	protected BigDecimal calcularMediaLTD2(Integer numComb, ArrayList<Summary> summaries) {
		BigDecimal sumLTD2 = BigDecimal.ZERO;
		int cont = 0;
		for (Summary sum : summaries) {
			if (sum.getNumAbst() == numComb) {
				cont++;
				sumLTD2 = sumLTD2.add(sum.getLTD2());
			}
		}
		return sumLTD2.divide(new BigDecimal(cont));
	}
}
