/**
 * 
 */
package principal;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import model.Metric;
import model.Unit;
import utils.BuscadorArquivos;
import utils.GravadorMetricas;
import utils.LeitorMetricas;

/**
 * @author rubens
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int numEst = 3; numEst < 9; numEst++) {
			
			File diretorio = new File("/media/rubens/Dados/MetricDados/Simulacao/EstadoNumber" + numEst);
			System.out.println("Lendo estado "+numEst);
			ArrayList<String> listaArquivos = new ArrayList<>();

			System.out.println("Iniciando busca dos arquivos...");
			for (int i = 1; i < 793; i++) {
				System.out.println("Buscando estado " + i);
				BuscadorArquivos.buscar(diretorio, "estado_" + i, listaArquivos);
			}

			System.out.println("Total de arquivo encontrados:" + listaArquivos.size() + " "
					+ Calendar.getInstance().getTime().toString());
			// iniciando leitura dos arquivos
			ArrayList<Metric> metricas = new ArrayList<>();
			HashMap<Integer, ArrayList<Unit>> unitsCombinacoes = new HashMap<>();
			ArrayList<Unit> unidades = new ArrayList<>();
			LeitorMetricas leitor = new LeitorMetricas();

			for (String nomeArq : listaArquivos) {
				// System.out.println(nomeArq);

				leitor.lerArquivoDeTesteMetrica(metricas, nomeArq, unitsCombinacoes, unidades);
				// break;
			}

			System.out.println("Fim acumulo métricas=" + Calendar.getInstance().getTime().toString());
			System.out.println("Total de registros:" + metricas.size());
			System.out.println("Total de combinacoes=" + unitsCombinacoes.size());

			GravadorMetricas gravador = new GravadorMetricas();
			gravador.gravarListagemMetrics(metricas, diretorio);
			gravador.gravarListaCombinacoes(unitsCombinacoes, diretorio);
			System.out.println("Dados do estado "+ numEst+ " gravados com sucesso.");
		}
		

	}

}
