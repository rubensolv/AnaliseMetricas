package graficos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import model.Summary;

public class GrafLTD2PorCombinacao {
	private HashMap<Integer, BigDecimal> dadosFinais;
	private HashMap<Integer, ArrayList<Summary>> mapSummaries;
	
	
	public GrafLTD2PorCombinacao(){
		this.dadosFinais = new HashMap<>();
		this.mapSummaries = new HashMap<>();
	}
	
	public void addNewSummary(Integer numCombinacao, Summary summary){
		if(mapSummaries.containsKey(numCombinacao)){
			mapSummaries.get(numCombinacao).add(summary);
		}else{
			ArrayList<Summary> temp = new ArrayList<>();
			temp.add(summary);
			mapSummaries.put(numCombinacao, temp);
		}
	}
	
	
	protected void calcularDadosFinais(){
		dadosFinais.clear();
		for(Integer combinacao : mapSummaries.keySet()){
			BigDecimal sumSummary = BigDecimal.ZERO;
			int contador = 0;
			for(Summary sum : mapSummaries.get(combinacao)){
				sumSummary = sumSummary.add(sum.getLTD2());
				contador++;
			}
			dadosFinais.put(combinacao, sumSummary.divide(new BigDecimal(contador)));
		}
		
	}
	
	public void gravarGrafLTD2(File diretorio){
		calcularDadosFinais();
		try {
			FileWriter arq = new FileWriter(diretorio.getAbsolutePath().concat("/graficoLTD2PorCombinacao"));
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println("NumCombincao;LTD2");
			for(Integer combin : dadosFinais.keySet()){
				gravarArq.println(combin +";"+dadosFinais.get(combin).toString());
			}
			
			gravarArq.flush();
			gravarArq.close();
			arq.close();
		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo graficoLTD2PorCombinacao");
			e.printStackTrace();
		}
	}
	
	public void lerListSummary(ArrayList<Summary> summaries){
		for (Summary summary : summaries) {
			this.addNewSummary(summary.getNumAbst(), summary);
		}
	}
}
