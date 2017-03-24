package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Metric {
	private int round, qtd;
	private int tempo;	
	private int numTotalUnits;
	private int numUnidadesInimigas;
	private int numAbstracao;
	private BigDecimal distMedia;
	private BigDecimal LTD2;
	private String tipoAlgoritmo;
	private ArrayList<Unit> unitsControl;
	
	public Metric(){
		round = 0;
		tempo = 0;		
		LTD2 = BigDecimal.ZERO;
		numTotalUnits = -999;
		numUnidadesInimigas = -999;
		numAbstracao = -1;
		distMedia = BigDecimal.ZERO;
		LTD2 = BigDecimal.ZERO;
		tipoAlgoritmo = "--";
		unitsControl = new ArrayList<>();
		qtd = 0;
	}
	
	public int getQtd() {
		return qtd;
	}
	public void incrementQtd() {
		this.qtd++;
	}
	
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public int getNumTotalUnits() {
		return numTotalUnits;
	}
	public void setNumTotalUnits(int numTotalUnits) {
		this.numTotalUnits = numTotalUnits;
	}
	public int getNumUnidadesInimigas() {
		return numUnidadesInimigas;
	}
	public void setNumUnidadesInimigas(int numUnidadesInimigas) {
		this.numUnidadesInimigas = numUnidadesInimigas;
	}
	public int getNumAbstracao() {
		return numAbstracao;
	}
	public void setNumAbstracao(int numAbstracao) {
		this.numAbstracao = numAbstracao;
	}
	public BigDecimal getDistMedia() {
		return distMedia;
	}
	public void setDistMedia(BigDecimal distMedia) {
		this.distMedia = distMedia;
	}
	public BigDecimal getLTD2() {
		return LTD2;
	}
	public void setLTD2(BigDecimal lTD2) {
		
		if(lTD2.compareTo(new BigDecimal(100000)) > 0    ){
			lTD2 = lTD2.subtract(new BigDecimal(100000));
		}
		if(lTD2.compareTo(new BigDecimal(-100000)) < 0    ){
			lTD2 = lTD2.subtract(new BigDecimal(-100000));
		}
		
		this.LTD2 = lTD2;
	}
	public String getTipoAlgoritmo() {
		return tipoAlgoritmo;
	}
	public void setTipoAlgoritmo(String tipoAlgoritmo) {
		this.tipoAlgoritmo = tipoAlgoritmo;
	}
	public ArrayList<Unit> getUnitsControl() {
		return unitsControl;
	}
	public void setUnitsControl(ArrayList<Unit> unitsControl) {
		this.unitsControl = unitsControl;
	}
	
	public void addNewUnit(Unit un){
		this.unitsControl.add(un);
	}
	
	public BigDecimal getMediaFinal() {
		return getDistMedia().divide(new BigDecimal(qtd), RoundingMode.HALF_UP);
	}
	
	public void addValueMedia(BigDecimal value){
		this.distMedia = this.distMedia.add(value);
	}
	
	public void printOrganized(){
		System.out.print(getNumAbstracao());
		System.out.print(",");
		System.out.print(getMediaFinal());
		System.out.print(",");
		System.out.println(getLTD2());
	}
	
	public String getString(){
		String retorno ;
		retorno = getNumAbstracao() +","+getNumTotalUnits()+","+getNumUnidadesInimigas()+",";
		retorno += getMediaFinal().toString() +","+getLTD2().toString();
		
		return retorno;
	}
	

}
