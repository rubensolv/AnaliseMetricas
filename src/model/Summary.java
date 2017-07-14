package model;

import java.math.BigDecimal;

public class Summary {
	private int numAbst, numUnitsAliadas, numUnitsInimigas;
	private BigDecimal LTD2, distMedia;
	
	public Summary(){
		LTD2 = BigDecimal.ZERO;
		distMedia = BigDecimal.ZERO;
	}

	public int getNumAbst() {
		return numAbst;
	}

	public void setNumAbst(int numAbst) {
		this.numAbst = numAbst;
	}

	public int getNumUnitsAliadas() {
		return numUnitsAliadas;
	}

	public void setNumUnitsAliadas(int numUnitsAliadas) {
		this.numUnitsAliadas = numUnitsAliadas;
	}

	public int getNumUnitsInimigas() {
		return numUnitsInimigas;
	}

	public void setNumUnitsInimigas(int numUnitsInimigas) {
		this.numUnitsInimigas = numUnitsInimigas;
	}

	public BigDecimal getLTD2() {
		return LTD2;
	}

	public void setLTD2(BigDecimal lTD2) {
		LTD2 = lTD2;
	}

	public BigDecimal getDistMedia() {
		return distMedia;
	}

	public void setDistMedia(BigDecimal distMedia) {
		this.distMedia = distMedia;
	}
	
	
	
}
