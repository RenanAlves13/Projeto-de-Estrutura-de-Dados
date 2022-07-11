package Transformacoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Funcoes.Metodos;

public class Transformacao3 {
	
	double media;
	
	public Transformacao3() throws FileNotFoundException {
		double sum = 0D;
		int count = 0;
		File metroTrips = new File("LAMetroTrips.csv");
		Scanner metroTime = null;
		String linha;
		metroTime = new Scanner(metroTrips);
		metroTime.nextLine(); //Lendo a primeira linha respectiva com os nomes da coluna
		
		while(metroTime.hasNextLine()) {
			linha = metroTime.nextLine();
			sum += Integer.parseInt(Metodos.readLinha(linha, 1, 2));//searchTime(linha);
			++count;
		}
		this.media = sum / count;
	}
	
	public void newFile2() {
		int number;
		
		try (FileWriter writer = new FileWriter("LAMetroTrips_F2.csv", true)){
			File metroTrips = new File("LAMetroTrips.csv");
			Scanner metroTime = null;
			String linha;
			metroTime = new Scanner(metroTrips);
			linha = metroTime.nextLine(); //Lendo a primeira linha respectiva com os nomes da coluna
			writer.write(linha + "\n");
    		
    		while(metroTime.hasNextLine()) {
    			linha = metroTime.nextLine();
				number = Integer.parseInt(Metodos.readLinha(linha, 1, 2));//searchTime(linha);
				if(number > media) {
					writer.write(linha + "\n");
				}
				
			} //Fim do loop while
    		writer.close();
		}
		catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}


