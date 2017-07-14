package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import model.ResultRawLine;
import model.Unit;

public class LeitorResultRaw {

	public static void lerResultRaw(String caminhoArquivo, ArrayList<ResultRawLine> resultados) {
		String linha = "";
		try {
			FileReader arq = new FileReader(caminhoArquivo);
			BufferedReader learArq = new BufferedReader(arq);

			linha = learArq.readLine();
			linha = learArq.readLine();
			ResultRawLine result = null;
			while (linha != null) {
				result = new ResultRawLine();
				/*
				 * System.out.print(linha.substring(0, 6)+ "  "); // P1
				 * System.out.print(linha.substring(6, 12)+ "  "); // P2
				 * System.out.print(linha.substring(12, 18)+ "  "); // ST
				 * System.out.print(linha.substring(18, 24)+ "  "); // Unit
				 * System.out.print(linha.substring(24, 35)+ "  "); // EVAL
				 * System.out.print(linha.substring(35, 42)+ "  "); // RND
				 * System.out.print(linha.substring(42, 55)+ "  "); // MS
				 * System.out.print(linha.substring(57, linha.length())+ "  ");
				 * // units
				 * 
				 * System.out.println(" ");
				 */
				result.setNumCombinacao(Integer.valueOf(linha.substring(12, 18).trim()) + 1);
				result.setLTD2(new BigDecimal(linha.substring(24, 35).trim()));
				if (result.getLTD2().compareTo(BigDecimal.ZERO) != 0) {
					String units = linha.substring(57, linha.length()).trim();
					units = units.replace("|", ";");
					String[] unitsSplit = units.split(";");
					for (String un : unitsSplit) {
						String[] unT = un.trim().split(" ");
						
						Unit temp = new Unit();
						temp.setTipo(unT[0]);
						temp.setPlayer(Integer.valueOf(unT[1].trim()));
						temp.setLife(Integer.valueOf(unT[2]));
						temp.setX(Integer.valueOf(unT[3]));
						temp.setY(Integer.valueOf(unT[4]));
						result.addUnit(temp);
						
						if (unT[1].equals("0")) {
							result.incrementUnitsAlly();
						} else {
							result.incrementUnitsEnemy();
						}
					}
				}
				resultados.add(result);
				linha = learArq.readLine();
			}

			arq.close();

		} catch (Exception e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			System.out.println(e.toString());
			System.out.println(linha);
		}
	}

}
