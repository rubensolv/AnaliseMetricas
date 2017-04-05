package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Metric;
import model.ResultRawLine;

public class GravadorResultRaw {

	
	
	public static void gravarListagemResultRaw(ArrayList<ResultRawLine> results, File caminho) {
		try {
			FileWriter arq = new FileWriter(caminho.getAbsolutePath().concat("/summaryRaw"));
			PrintWriter gravarArq = new PrintWriter(arq);

			for (ResultRawLine r : results) {
				gravarArq.println(r.getStringSimple());
			}

			gravarArq.flush();
			gravarArq.close();
			arq.close();
		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo Summary");
			e.printStackTrace();
		}
	}
}
