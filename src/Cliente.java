import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Cliente {

	@SuppressWarnings("null")
	public static void client() throws ClassNotFoundException, IOException{
		Socket client = new Socket("localhost", 6968);		
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
	    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
	    Vector<Carta> jogador2 = new Vector<Carta>();
	    String fim_jogo = "jogando", resposta, fim_rodada = "jogando";
	    @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
	    Boolean minha_vez = false;
	    Carta carta_j1 = new Carta();
	    Carta carta_j2 = new Carta();
	    int ponto_rodada_j1 = 0, ponto_rodada_j2 = 0;
	    
	    while(fim_jogo != "fim"){
	    	jogador2.clear();
			jogador2 = (Vector<Carta>) ois.readObject();
	    	//jogador2.add((Carta) ois.readObject());
	    	//jogador2.add((Carta) ois.readObject());
	    	//jogador2.add((Carta) ois.readObject());
		    fim_rodada = "jogando";
			
		    while(fim_rodada != "fim"){
		    	System.out.println("-------------------");
		    	System.out.println("Cartas disponíveis:");
		    	System.out.println(" ");
		    	for(int i=0; i<jogador2.size(); i++){
			    	System.out.println(jogador2.get(i).getNumero() + " de " + jogador2.get(i).getNipe());
			    }
		    	System.out.println(" ");
		    	
		    	if(minha_vez){
				    System.out.print("Jogue uma carta: ");
					resposta = scanner.nextLine();
					
					carta_j2 = jogador2.get(Integer.parseInt(resposta) - 1);
					jogador2.remove(Integer.parseInt(resposta) - 1);
					
					oos.writeObject(carta_j2);
					carta_j1 = (Carta) ois.readObject();
					System.out.println("O outro jogador jogou a carta: " + carta_j1.getNumero() + " de " + carta_j1.getNipe());
		    	}
		    	else{
		    		carta_j1 = (Carta) ois.readObject();
		    		System.out.println("O outro jogador jogou a carta: " + carta_j1.getNumero() + " de " + carta_j1.getNipe());
		    		
		    		System.out.print("Jogue uma carta: ");
					resposta = scanner.nextLine(); 
					
					carta_j2 = jogador2.get(Integer.parseInt(resposta) - 1);
					jogador2.remove(Integer.parseInt(resposta) - 1);
					
					oos.writeObject(carta_j2);
		    	}
		    	System.out.println(" ");
		    	
		    	if(carta_j1.getPeso() > carta_j2.getPeso()){
			    	minha_vez = false;
			    }
			    else if(carta_j1.getPeso() < carta_j2.getPeso()){
			    	minha_vez = true;
			    }
			    else{
			    	minha_vez = false;
			    }
		    	
		    	fim_rodada = (String) ois.readObject();
		    }
		    
		    fim_jogo = (String) ois.readObject();
	    }
		// fim do while

		oos.close();
	    ois.close();
	    client.close();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		client();
	}

}
