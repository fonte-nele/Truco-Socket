import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Mesa {
	
	private static Vector<Carta> baralho = new Vector<Carta>();
	
	private static void preencheBaralho(){
		for(int i=0; i<4; i++){
			for(int j=1; j<11; j++){
				Carta aux = new Carta();
				if(i==0){
					aux.setNipe("Copas");
					aux.setNumero(j);
					if(j==1)
						aux.setPeso(8);
					if(j==2)
						aux.setPeso(9);
					if(j==3)
						aux.setPeso(10);
					if(j==4)
						aux.setPeso(1);
					if(j==5)
						aux.setPeso(2);
					if(j==6)
						aux.setPeso(3);
					if(j==7)
						aux.setPeso(13);
					if(j==8)
						aux.setPeso(5);
					if(j==9)
						aux.setPeso(6);
					if(j==10)
						aux.setPeso(7);
				}
				if(i==1){
					aux.setNipe("Ouro");
					aux.setNumero(j);
					if(j==1)
						aux.setPeso(8);
					if(j==2)
						aux.setPeso(9);
					if(j==3)
						aux.setPeso(10);
					if(j==4)
						aux.setPeso(1);
					if(j==5)
						aux.setPeso(2);
					if(j==6)
						aux.setPeso(3);
					if(j==7)
						aux.setPeso(11);
					if(j==8)
						aux.setPeso(5);
					if(j==9)
						aux.setPeso(6);
					if(j==10)
						aux.setPeso(7);
				}
				if(i==2){
					aux.setNipe("Espada");
					aux.setNumero(j);
					if(j==1)
						aux.setPeso(12);
					if(j==2)
						aux.setPeso(9);
					if(j==3)
						aux.setPeso(10);
					if(j==4)
						aux.setPeso(1);
					if(j==5)
						aux.setPeso(2);
					if(j==6)
						aux.setPeso(3);
					if(j==7)
						aux.setPeso(4);
					if(j==8)
						aux.setPeso(5);
					if(j==9)
						aux.setPeso(6);
					if(j==10)
						aux.setPeso(7);
				}
				if(i==3){
					aux.setNipe("Paus");
					aux.setNumero(j);
					if(j==1)
						aux.setPeso(8);
					if(j==2)
						aux.setPeso(9);
					if(j==3)
						aux.setPeso(10);
					if(j==4)
						aux.setPeso(14);
					if(j==5)
						aux.setPeso(2);
					if(j==6)
						aux.setPeso(3);
					if(j==7)
						aux.setPeso(4);
					if(j==8)
						aux.setPeso(5);
					if(j==9)
						aux.setPeso(6);
					if(j==10)
						aux.setPeso(7);
				}
				baralho.add(aux);
			}
		}
	}
	
	@SuppressWarnings("null")
	private static void distribuicaoCartas() throws IOException, ClassNotFoundException{
		ServerSocket serverSocket = new ServerSocket(6968);
	    Socket socket = serverSocket.accept();
	    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	    Random gerador = new Random();
	    Vector<Carta> jogador1 = new Vector<Carta>(); 
	    Vector<Carta> jogador2 = new Vector<Carta>();
	    int controle[] = new int[40];
	    int num = 0;
	    int ponto_jogador1 = 0, ponto_jogador2 = 0, ponto_rodada_j1 = 0, ponto_rodada_j2 = 0;
	    String fim_jogo = "jogando", resposta, fim_rodada = "jogando";
	    @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
	    Boolean minha_vez = true;
	    Carta carta_j1 = new Carta();
	    Carta carta_j2 = new Carta();
	    
	    //Criar um while q repete rodadas ate algum dos jogadores atingir 12 pontos
	    while(ponto_jogador1 <= 12 && ponto_jogador2 <= 12){
		    jogador1.clear(); jogador2.clear();
		    
		    for(int i=0; i<40; i++){
		    	controle[i] = 0;
		    }
		    
		    for(int i=0; i<3; i++){
		    	num = gerador.nextInt(40);
		    	if(controle[num] == 0){
		    		controle[num] = 1;
		    		jogador1.add(baralho.get(num));
		    	}
		    	else{
		    		num = gerador.nextInt(40);
		    		i--;
		    	}
		    }
		    
		    for(int i=0; i<3; i++){
		    	num = gerador.nextInt(40);
		    	if(controle[num] == 0){
		    		controle[num] = 1;
		    		jogador2.add(baralho.get(num));
		    	}
		    	else{
		    		num = gerador.nextInt(40);
		    		i--;
		    	}
		    }
		    
		    //////////////
		    
		    System.out.println(" Testando para ver as cartas ");
		    for(int i=0; i<jogador1.size(); i++){
		    	System.out.println(jogador1.get(i).getNumero() + " de " + jogador1.get(i).getNipe());
		    }
		    System.out.println(" ");
		    for(int i=0; i<jogador2.size(); i++){
		    	System.out.println(jogador2.get(i).getNumero() + " de " + jogador2.get(i).getNipe());
		    }
		    System.out.println(" ----------------- ");
		    System.out.println(" ");
		  
		    /////////////
		    
		    oos.writeObject(jogador2);
		    //oos.writeObject(jogador2.get(0));
		    //oos.writeObject(jogador2.get(1));
		    //oos.writeObject(jogador2.get(2));
		    
		    ponto_rodada_j1 = 0; ponto_rodada_j2 = 0;
		    while((jogador1.size() > 0 || jogador2.size() > 0) && ponto_rodada_j1 < 2 && ponto_rodada_j2 < 2){
		    	System.out.println("-------------------");
		    	System.out.println("Cartas disponíveis:");
		    	System.out.println(" ");
		    	for(int i=0; i<jogador1.size(); i++){
			    	System.out.println(jogador1.get(i).getNumero() + " de " + jogador1.get(i).getNipe());
			    }
		    	System.out.println(" ");
		    	
		    	if(minha_vez){
				    System.out.print("Jogue uma carta: ");
					resposta = scanner.nextLine();
					
					carta_j1 = jogador1.get(Integer.parseInt(resposta) - 1);
					jogador1.remove(Integer.parseInt(resposta) - 1);
					
					oos.writeObject(carta_j1);
					carta_j2 = (Carta) ois.readObject();
					System.out.println("O outro jogador jogou a carta: " + carta_j2.getNumero() + " de " + carta_j2.getNipe());
		    	}
		    	else{
		    		carta_j2 = (Carta) ois.readObject();
		    		System.out.println("O outro jogador jogou a carta: " + carta_j2.getNumero() + " de " + carta_j2.getNipe());
		    		
		    		System.out.print("Jogue uma carta: ");
					resposta = scanner.nextLine();
					
					carta_j1 = jogador1.get(Integer.parseInt(resposta) - 1);
					jogador1.remove(Integer.parseInt(resposta) - 1);
					
					oos.writeObject(carta_j1);
		    	}
		    	System.out.println(" ");
			    
			    if(carta_j1.getPeso() > carta_j2.getPeso()){
			    	minha_vez = true;
			    	ponto_rodada_j1++;
			    }
			    else if(carta_j1.getPeso() < carta_j2.getPeso()){
			    	minha_vez = false;
			    	ponto_rodada_j2++;
			    }
			    else{
			    	minha_vez = true;
			    }
			    
			    if(!((jogador1.size() > 0 || jogador2.size() > 0) && ponto_rodada_j1 < 2 && ponto_rodada_j2 < 2)){
			    	fim_rodada = "fim";
			    	oos.writeObject(fim_rodada);
			    }
			    else{
			    	oos.writeObject(fim_rodada);
			    }
		    }
		    
		    if(ponto_rodada_j1 > ponto_rodada_j2){
		    	ponto_jogador1 += 2;
		    	minha_vez = true;
		    }
		    else if(ponto_rodada_j1 < ponto_rodada_j2){
		    	ponto_jogador2 += 2;
		    	minha_vez = false;
		    }
		    else{
		    	ponto_jogador1 += 2;
		    	ponto_jogador2 += 2;
		    	minha_vez = true;
		    }
		    
		    oos.writeObject(fim_jogo);
	    }
	    
	    fim_jogo = "fim";
	    oos.writeObject(fim_jogo);
	    
	    oos.close();
	    ois.close();
	    socket.close();
	    serverSocket.close();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		preencheBaralho();
		distribuicaoCartas();
	}

}
