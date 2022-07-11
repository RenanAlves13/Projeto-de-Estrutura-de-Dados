package Transformacoes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Funcoes.Metodos;

public class Transformacao2 {
	
	String station[] = new String[31];

	public Transformacao2() throws IOException{
		//Abrindo o arquivo stations_pasadena.csv
		File pasadena = new File("stations_pasadena.csv");
		Scanner pasadenaInput = null;
				
		pasadenaInput = new Scanner(pasadena);
		pasadenaInput.nextLine(); //Lendo a primeira linha respectiva com os nomes da coluna
		int i = 0;
					
		while(pasadenaInput.hasNext()) {
			this.station[i] = pasadenaInput.nextLine().toString(); 
			++i;
		}				
	}
	
	public void newFile() {
    	File trips = new File("LAMetroTrips.csv");
		Scanner metroTrips = null;
		String linha, recebedor;
		
		try (FileWriter writer = new FileWriter("LAMetroTrips_F1.csv", true)){
			metroTrips = new Scanner(trips); 
    		linha = metroTrips.nextLine();
    		writer.write(linha + "\n");
    		String[] estacoes;
    		
    		while(metroTrips.hasNextLine()) {
    			
				linha = metroTrips.nextLine();
				recebedor = getStations(linha, 9, 11);
				estacoes = recebedor.split(","); 
				
				for(int i = 0; i < this.station.length; i++) {
					if((estacoes[0].equals(this.station[i])) || (estacoes[1].equals(this.station[i]))) {
						writer.write(linha + "\n");
						break;
					}
					
				} //Fim do loop for
			} //Fim do loop while
    		writer.close();
		}
		catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
    } //Fim do método newFile
	
    private static String getStations(String line, int n, int m) {
    	
    	String linhaStations = "";
    	int count = 0;
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext() && (count <= m)) {
            	if((count >= n) && (count < m)) {
            		linhaStations += rowScanner.next().toString();
            		linhaStations += ",";
                    ++n;
                    ++count;
            	}
            	else {
            		rowScanner.next();
            		++count;
            	}
            } //Fim do laço while
        }
        //implementar o catch
        return linhaStations;
    }//Fim do metodo
}
