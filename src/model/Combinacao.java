package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Combinacao {
	private int numComb;
	private ArrayList<Unit> unidades;
	private BigDecimal distMediaUnits, mediaLTD2, distMediaInicialUnits;
	private int totalVitoria, totalDerrotas, totalUnitsRestVitoria, totalUnitsRestDerrota;
	
	public Combinacao(){
		unidades = new ArrayList<>();
		distMediaUnits = BigDecimal.ZERO;
		mediaLTD2 = BigDecimal.ZERO;
		distMediaInicialUnits = BigDecimal.ZERO;
		totalDerrotas =  0;
		totalVitoria = 0;
		totalUnitsRestVitoria = 0;
		totalUnitsRestDerrota = 0;
	}
	
	
	public void incrTotalVitoria(){
		this.totalVitoria++;
	}
	
	public void incrTotalDerrotas(){
		this.totalDerrotas++;
	}
	
	public void addTotalUnitsRestVitoria(int numUnits){
		this.totalUnitsRestVitoria += numUnits;
	}
	
	public void addTotalUnitsRestDerrota(int numUnits){
		this.totalUnitsRestDerrota += numUnits;
	}
	
	public int getTotalBatalhas(){
		return totalDerrotas + totalVitoria;
	}

	public int getNumComb() {
		return numComb;
	}

	public void setNumComb(int numComb) {
		this.numComb = numComb;
	}

	public ArrayList<Unit> getUnidades() {
		return unidades;
	}

	public void addUnit(Unit un){
		this.unidades.add(un);
	}

	public BigDecimal getDistMediaUnits() {
		return distMediaUnits.setScale(4, RoundingMode.HALF_UP);
	}

	public void setDistMediaUnits(BigDecimal distMediaUnits) {
		this.distMediaUnits = distMediaUnits;
	}

	public BigDecimal getMediaLTD2() {
		return mediaLTD2;
	}

	public void setMediaLTD2(BigDecimal mediaLTD2) {
		this.mediaLTD2 = mediaLTD2;
	}

	public BigDecimal getDistMediaInicialUnits() {
		return distMediaInicialUnits;
	}

	public void setDistMediaInicialUnits(BigDecimal distMediaInicialUnits) {
		this.distMediaInicialUnits = distMediaInicialUnits;
	}

	public int getTotalVitoria() {
		return totalVitoria;
	}

	public void setTotalVitoria(int totalVitoria) {
		this.totalVitoria = totalVitoria;
	}

	public int getTotalDerrotas() {
		return totalDerrotas;
	}

	public void setTotalDerrotas(int totalDerrotas) {
		this.totalDerrotas = totalDerrotas;
	}

	public int getTotalUnitsRestVitoria() {
		return totalUnitsRestVitoria;
	}

	public void setTotalUnitsRestVitoria(int totalUnitsRestVitoria) {
		this.totalUnitsRestVitoria = totalUnitsRestVitoria;
	}

	public int getTotalUnitsRestDerrota() {
		return totalUnitsRestDerrota;
	}

	public void setTotalUnitsRestDerrota(int totalUnitsRestDerrota) {
		this.totalUnitsRestDerrota = totalUnitsRestDerrota;
	}

	public void setUnidades(ArrayList<Unit> unidades) {
		this.unidades = unidades;
	}
	
	
	public BigDecimal calcularDistanciaMediaInicialUnidades(){
		//calcular a distância média entre as unidades que estão inicialmente na composição da combinação
		BigDecimal posMedia = BigDecimal.ZERO;
		int qtd = 0;
		for(int i = 0; i < unidades.size(); i++){
			for(int j = 0; j < unidades.size(); j++){
				if(i != j){
					posMedia = posMedia.add(getDistanciaEuclidiana(unidades.get(i), unidades.get(j)));
					qtd++;
				}
			}
			
		}
		posMedia = posMedia.divide(new BigDecimal(qtd), RoundingMode.HALF_UP); 
		return posMedia.setScale(4, RoundingMode.HALF_UP);
	}
	
	protected BigDecimal getDistanciaEuclidiana(Unit base, Unit secundaria){
		return new BigDecimal( Math.sqrt(((base.getX() - secundaria.getX())*(base.getX() - secundaria.getX()) +
				(base.getY() - secundaria.getY())*(base.getY() - secundaria.getY())
				)));
	}
	
	/**
	 * Gera uma String com os dados da combinacao para ser gravado
	 * @return String contendo os dados separados por ;
	 */
	public String getGrafDadosCombinacao(){
		String retorno = "";
		retorno = retorno.concat(Integer.toString(this.getNumComb())               ).concat(";");
		retorno = retorno.concat(this.getDistMediaUnits().toString()               ).concat(";");
		retorno = retorno.concat(this.getMediaLTD2().toString()                    ).concat(";");
		retorno = retorno.concat(this.getDistMediaInicialUnits().toString()        ).concat(";");
		retorno = retorno.concat(Integer.toString(this.getTotalVitoria())          ).concat(";");
		retorno = retorno.concat(Integer.toString(this.getTotalDerrotas())         ).concat(";");
		retorno = retorno.concat(Integer.toString(this.getTotalUnitsRestVitoria()) ).concat(";");
		retorno = retorno.concat(Integer.toString(this.getTotalUnitsRestDerrota()) );
		
		return retorno;
	}

}
