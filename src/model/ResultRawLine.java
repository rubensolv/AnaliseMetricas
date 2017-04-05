package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ResultRawLine implements Comparable<ResultRawLine>{
	
	private int numCombinacao, numUnitsAlly, numUnitsEnemy;
	private BigDecimal LTD2;
	private ArrayList<Unit> units;
	
	public ResultRawLine(){
		numCombinacao = -999;
		LTD2 = BigDecimal.ZERO;
		units = new ArrayList<>();
		numUnitsAlly = 0;
		numUnitsEnemy = 0;
	}
	
	public void incrementUnitsAlly(){
		this.numUnitsAlly++;
	}
	
	public void incrementUnitsEnemy(){
		this.numUnitsEnemy++;
	}
	
	public void addUnit(Unit un){
		units.add(un);
	}
	
	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
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
	
	public int getNumCombinacao() {
		return numCombinacao;
	}

	public void setNumCombinacao(int numCombinacao) {
		this.numCombinacao = numCombinacao;
	}

	public int getNumUnitsAlly() {
		return numUnitsAlly;
	}

	public void setNumUnitsAlly(int numUnitsAlly) {
		this.numUnitsAlly = numUnitsAlly;
	}

	public int getNumUnitsEnemy() {
		return numUnitsEnemy;
	}

	public void setNumUnitsEnemy(int numUnitsEnemy) {
		this.numUnitsEnemy = numUnitsEnemy;
	}

	public String getStringSimple(){
		String retorno ;
		retorno = getNumCombinacao() +","+getNumUnitsAlly() +","+getNumUnitsEnemy()+",";
		retorno += "0.0" +","+getLTD2().toString();
		
		return retorno;
	}
	
	public BigDecimal getMediaLifeZealots(int player){
		BigDecimal retorno = BigDecimal.ZERO;
		int cont = 0;
		for (Unit unit : units) {
			if( (unit.getPlayer() == player) && (unit.getTipo().equals("Protoss_Zealot")) ){
				cont++;
				retorno = retorno.add(new BigDecimal(unit.getLife()));
			}
		}
		if(cont > 0){
			retorno = retorno.divide(new BigDecimal(cont), RoundingMode.CEILING);
		}
		
		return retorno;
	}
	public BigDecimal getMediaLifeDragoons(int player){
		BigDecimal retorno = BigDecimal.ZERO;
		retorno.setScale(2, RoundingMode.CEILING);
		int cont = 0;
		for (Unit unit : units) {
			if( (unit.getPlayer() == player) && (unit.getTipo().equals("Protoss_Dragoon")) ){
				cont++;
				retorno = retorno.add(new BigDecimal(unit.getLife()));
			}
		}
		if(cont > 0){
			retorno = retorno.divide(new BigDecimal(cont),RoundingMode.CEILING);
		}
		
		return retorno;
	}
	
	
	public int getTotalUnitsAlly(){
		Integer total =0;
		
		for (Unit unit : units) {
			if(unit.getPlayer() == 0){
				total++;
			}
		}
		
		return total;
		
	}
	
	public int getTotalUnitsEnemy(){
		Integer total =0;
		
		for (Unit unit : units) {
			if(unit.getPlayer() == 1){
				total++;
			}
		}
		
		return total;
	}
	
	public String getInfoMedia(){
		String retorno = "";
		
		if(this.getLTD2().signum() == -1){
			//calculo PGS
			retorno += "PGS;P1;";
			retorno += this.getMediaLifeZealots(1).toString()+";";
			retorno += this.getMediaLifeDragoons(1).toString();
		}else{
			//calculo GAB
			retorno += "GAB;P0;";
			retorno += this.getMediaLifeZealots(0).toString()+";";
			retorno += this.getMediaLifeDragoons(0).toString();
		}
		
		return retorno;
	}

	@Override
	public int compareTo(ResultRawLine o) {
		if(this.numCombinacao > o.numCombinacao){
			return 1;
		}
		if(this.numCombinacao < o.numCombinacao){
			return -1;
		}
		return 0;
	}

}
