package model;

public class Unit implements Comparable<Unit> {
	private String tipo;
	private int id, player, x, y, life;
	
	public Unit(){
		player = 0;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public String getString(){
		String retorno = "";
		retorno = Integer.toString(id).concat(";") + tipo.concat(";") + Integer.toString(life).concat(";");
		retorno = retorno.concat(Integer.toString(x)).concat(";").concat(Integer.toString(y));
		
		return retorno;
	}
	
	public void decodeGetString(String unitString){
		String[] split = unitString.trim().split(";");
		this.id = Integer.valueOf(split[0]);
		this.tipo = split[1];
		this.life = Integer.valueOf(split[2]);
		this.x = Integer.valueOf(split[3]);
		this.y = Integer.valueOf(split[4]);
	}
	
	public void leDadosUnidade(String dados){
		//System.out.println(dados);
		String[] split = dados.split(" ");
		setId(Integer.valueOf(split[0]));
		setTipo(split[1]);
		setLife(Integer.valueOf(split[2]));
		
		String xy = split[3];
		
		split = xy.split(",");
		setX(Integer.valueOf(split[0].replace("(", "").trim()));
		setY(Integer.valueOf(split[1].replace(")", "").trim()));
	}
	
	public void print(){
		System.out.println(getString());
	}
	@Override
	public int compareTo(Unit outraUnidade) {
		if(this.player < outraUnidade.player){
			return -1;
		}
		if(this.player > outraUnidade.player){
			return 1;
		}
		return 0;
	}
	
	public void changePlayer(){
		if(this.player == 0){
			this.player = 1;
		}else{
			this.player = 0;
		}
	}
}
