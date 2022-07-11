package Transformacoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Funcoes.Metodos;

public class Transformacao1{
	
	//Variáveis necessárias
	Scanner stationsInput = null;
	int arrayIds[] = new int[350];
	String arrayNomes[] = new String[350];
	int i = 0;
	String recebedor;
	String colunas;
	String[] arquivo = new String[1250835];
	
	//Lendo o arquivo CSV principal
	public Transformacao1() throws IOException{
		File bikeSharing = new File("LA_Metro_BikeSharing_CLEANED_2016quater3-2021q3.csv");
		Scanner read = new Scanner(bikeSharing);
		this.colunas = read.nextLine().toString();
		int n = 0;
		String linha;
		
		while(read.hasNextLine()) {
			linha = read.nextLine().toString();
			this.arquivo[n] = linha;	
			++n;
		} //Fim do loop while
	}
	
	public void leStations() throws IOException{
		/*
		 * Lendo o arquivo referente com os nomes e IDs das estações
		 */
		File stations = new File("stations.csv");
		this.stationsInput = new Scanner(stations);
		Metodos.readLinha(this.stationsInput.nextLine(), 0, 2);
		while (this.stationsInput.hasNextLine()) {;
			//Deve ser retornado o id e o nome
			this.recebedor = Metodos.readLinha(this.stationsInput.nextLine(), 0, 2);
			//Separar o id e a senha da String recebedor
			this.arrayIds[i] = Integer.parseInt(this.recebedor.substring(0, 4));
			this.arrayNomes[i] = this.recebedor.substring(4);
			++i;
		}
	}
	
	public void leMetroTrips() throws IOException{
		String recebedor;
		String linha;
		int startId, endId;
		int j = 0;
		substitui(colunas, "start_station", "end_station");
		
		while(j < 1250835) {
			linha = arquivo[j];
			recebedor = Metodos.readLinha(linha, 9, 11);
			
			//Separacao dos IDs de inicio e fim
			startId = Integer.parseInt(recebedor.substring(0, 4));
			endId = Integer.parseInt(recebedor.substring(4));
			++j;
			
			//Fazer a comparacao dos IDs
			String start = "", end = "";
			//Procurando o ID no array de IDs
			for(i = 0; i < 350; i++) { 
				if((this.arrayIds[i] == startId) || (this.arrayIds[i] == endId)) {
					
					if((this.arrayIds[i] == startId) && (this.arrayIds[i] == endId)) {
						//ir no arrayNomes[i]
						start = this.arrayNomes[i];
						end = this.arrayNomes[i];
						i = 351; //Parando a execução do for
					}
					else if(this.arrayIds[i] == startId) {
						start = this.arrayNomes[i];
					}
					else if(this.arrayIds[i] == endId){
						end = this.arrayNomes[i];
					}
				}
				//Se já tiver percorrido todo o array de Ids e não encontrar nenhuma Id correspondente
				//é associado o Id ao invés do nome da estação
				else if (i == 349){
					if(start.equals("") || end.equals("")) {
						if(start.equals("") && end.equals("")) {
							start = "" + startId;
							end = "" + endId;
						}
						else if(start.equals("")) start = "" + startId;
						else if(end.equals("")) end = "" + endId;
					}
				}
			} //Fim do loop for
			substitui(linha, start, end);
		} //Fim do laço while
	}
    
    public void substitui(String line, String start, String end){ // id, (id, id)
    	
    	int n = 0;
    	try (FileWriter writer = new FileWriter("LAMetroTrips.csv", true)){
    		StringBuilder escreve = new StringBuilder();
    		
    		Scanner rowScanner = new Scanner(line);
    		rowScanner.useDelimiter(",");
    		while(rowScanner.hasNext() && (n < 16)) {
    			if((n == 9) || (n == 10)) {
    				if(n == 9) {
    					escreve.append(start);
    					rowScanner.next();
    					escreve.append(",");
    				}
    				else {
    					escreve.append(end);
    					rowScanner.next();
    					escreve.append(",");
    				}
    			}
    			else {
    				escreve.append(rowScanner.next());
    				if(n != 15) {
    					escreve.append(",");
    				}
    			}
    			++n;
    		}
    		escreve.append("\n");
    		writer.write(escreve.toString());
            writer.close();
            rowScanner.close();
    	}catch (IOException e) {
            System.out.println(e.getMessage());
        }
    	
    } //fim do metodo substitui
    
}//Fim da classe passo1}

