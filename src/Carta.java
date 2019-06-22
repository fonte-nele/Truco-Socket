import java.io.Serializable;

public class Carta implements Serializable{
	private String nipe;
	private String numero;
	private int peso;
	
	public String getNipe(){
		return nipe;
	}
	
	public String getNumero(){
		return numero;
	}
	
	public int getPeso(){
		return peso;
	}
	
	public void setNipe(String n){
		nipe = n;
	}
	
	public void setNumero(int nu){
		if(nu == 1)
			numero = "A";
		else if(nu == 8)
			numero = "Dama";
		else if(nu == 9)
			numero = "Valete";
		else if(nu == 10)
			numero = "Rei";
		else
			numero = Integer.toString(nu);
	}
	
	public void setPeso(int p){
		peso = p;
	}
}
