package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import model.Metric;
import model.Unit;

public class GravadorMetricas {

	public void gravarListagemMetrics(ArrayList<Metric> metricas, File caminho) {
		try {
			FileWriter arq = new FileWriter(caminho.getAbsolutePath().concat("/summary"));
			PrintWriter gravarArq = new PrintWriter(arq);

			for (Metric m : metricas) {
				gravarArq.println(m.getString());
			}

			gravarArq.flush();
			gravarArq.close();
			arq.close();
		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo Summary");
			e.printStackTrace();
		}
	}

	public void gravarListaCombinacoes(HashMap<Integer, ArrayList<Unit>> combina, File caminho) {

		try {
			FileWriter arq = new FileWriter(caminho.getAbsolutePath().concat("/combinacoes"));
			PrintWriter gravarArq = new PrintWriter(arq);

			for (Integer x : combina.keySet()) {
				gravarArq.println("Combinação "+ x);
				for(Unit un : combina.get(x)){
					gravarArq.println(un.getString());
				}
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
