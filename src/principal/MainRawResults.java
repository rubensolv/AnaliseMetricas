package principal;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

import model.ResultRawLine;
import model.Unit;
import utils.BuscadorArquivos;
import utils.GravadorResultRaw;
import utils.LeitorResultRaw;

public class MainRawResults {

	public static void main(String[] args) {
		System.out.println("Iniciando busca de arquivos..." + Calendar.getInstance().getTime().toString());
		for (int i = 2; i < 3; i++) {
			File diretorio = new File(
					"/media/rubens/Dados/MetricDados/Simulacao/EstadoNumber" + i + "/");
					//"/media/rubens/Dados/MetricDados/SimAmostragem/Simulacao50UnAmostra3/EstadoNumber" + i + "/");
			ArrayList<String> listaArquivos = new ArrayList<>();

			for (int j = 1; j < 101; j++) {
				File diretorioTest = new File(diretorio.getAbsolutePath() + "/Teste" + j + "/bin");
				BuscadorArquivos.buscarParcial(diretorioTest, "_results_raw.txt", listaArquivos);
			}

			// listaArquivos.add("/media/rubens/Dados/MetricDados/SimAmostragem/Resulta_Raw_Teste.txt");
			// listaArquivos.add("/media/rubens/Dados/MetricDados/SimAmostragem/Resulta_Raw_Teste2.txt");

			System.out.println("Total de arquivo encontrados:" + listaArquivos.size() + " "
					+ Calendar.getInstance().getTime().toString());

			ArrayList<ResultRawLine> resultados = new ArrayList<>();

			for (String caminho : listaArquivos) {
				//System.out.println(caminho);
				LeitorResultRaw.lerResultRaw(caminho, resultados);

			}

			Collections.sort(resultados);
			// GravadorResultRaw.gravarListagemResultRaw(resultados, diretorio);

			HashMap<Integer, ArrayList<ResultRawLine>> mapAbstract = new HashMap<>();
			for (ResultRawLine r : resultados) {
				if (mapAbstract.containsKey(r.getNumCombinacao())) {
					mapAbstract.get(r.getNumCombinacao()).add(r);
				} else {
					ArrayList<ResultRawLine> temp = new ArrayList<>();
					temp.add(r);
					mapAbstract.put(r.getNumCombinacao(), temp);
				}

			}
			System.out.print("Abstracao;PlayerGAB;qtdVitorias;MediaUnits;MediaLifeDragoon;MediaLifeZealots;");
			System.out.println(        "PlayerPGS;qtdVitorias;MediaUnits;MediaLifeDragoon;MediaLifeZealots");
			for (Integer key : mapAbstract.keySet()) {
				
				BigDecimal PGSSumZea = BigDecimal.ZERO, GABSumZea = BigDecimal.ZERO, PGSSumDrag = BigDecimal.ZERO,
						GABSumDrag = BigDecimal.ZERO;
				int PGSqtd = 0, GABqtd = 0;
				int PGSTotUn = 0,  GABTotUn = 0;

				for (ResultRawLine rs : mapAbstract.get(key)) {
					PGSTotUn += rs.getTotalUnitsEnemy();
					GABTotUn += rs.getTotalUnitsAlly();
					
					if (rs.getLTD2().signum() == -1) {
						PGSqtd++;
						PGSSumDrag = PGSSumDrag.add(rs.getMediaLifeDragoons(1));
						PGSSumZea = PGSSumZea.add(rs.getMediaLifeZealots(1));
					}
					if (rs.getLTD2().signum() == 1) {
						GABqtd++;
						GABSumDrag = GABSumDrag.add(rs.getMediaLifeDragoons(0));
						GABSumZea = GABSumZea.add(rs.getMediaLifeZealots(0));
					}
				}
				
				System.out.print(key+";");
				
				BigDecimal calc = BigDecimal.ZERO;
				calc.setScale(3, RoundingMode.CEILING);
				if(GABqtd == 0){
					calc = new BigDecimal(GABTotUn).divide(BigDecimal.ONE,3,RoundingMode.CEILING);
					System.out.print("GAB;" +GABqtd+";"+calc.toString() +";" +GABSumDrag.divide(new BigDecimal(1), RoundingMode.CEILING).toString() + ";" + GABSumZea.divide(new BigDecimal(1), RoundingMode.CEILING).toString() + ";");
				}else{
					calc = new BigDecimal(GABTotUn).divide(new BigDecimal(GABqtd),3,RoundingMode.CEILING);
					System.out.print("GAB;" +GABqtd+";"+calc.toString() +";" +GABSumDrag.divide(new BigDecimal(GABqtd), RoundingMode.CEILING).toString() + ";" + GABSumZea.divide(new BigDecimal(GABqtd), RoundingMode.CEILING).toString() + ";");
				}
				
				if(PGSqtd == 0){
					calc = new BigDecimal(PGSTotUn).divide(BigDecimal.ONE,3,RoundingMode.CEILING);
					System.out.println("PGS;"+PGSqtd+";"+calc.toString() +";" + PGSSumDrag.divide(new BigDecimal(1), RoundingMode.CEILING).toString() + ";" + PGSSumZea.divide(new BigDecimal(1), RoundingMode.CEILING).toString());	
				}else{
					calc = new BigDecimal(PGSTotUn).divide(new BigDecimal(PGSqtd),3,RoundingMode.CEILING);
					System.out.println("PGS;"+PGSqtd+";"+calc.toString() +";" + PGSSumDrag.divide(new BigDecimal(PGSqtd), RoundingMode.CEILING).toString() + ";" + PGSSumZea.divide(new BigDecimal(PGSqtd), RoundingMode.CEILING).toString());
				}
				
				
				
				
			}
		}

		System.out.println("Dados gravados!");
	}

}
