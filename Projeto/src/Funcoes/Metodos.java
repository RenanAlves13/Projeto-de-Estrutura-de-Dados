package Funcoes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Metodos {

	public static String readLinha(String line, int n, int m){ 
    	
    	String linhaStations = "";
    	int count = 0;
        try (Scanner rowScanner = new Scanner(line)) { 
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext() && (count <= m)) {
            	if((count >= n) && (count < m)) {
            		linhaStations += rowScanner.next().toString();
                    ++n;
                    ++count;
            	}
            	else {
            		rowScanner.next();
            		++count;
            	}
            } //Fim do loop while
        }
        //implementar o catch
        return linhaStations;
    }//Fim do metodo
	
	public static void renameFile(String name) {
		// TODO Auto-generated method stub
		File oldName = new File("arquivo.csv");
        File newName =  new File("" + name + ".csv");
        oldName.renameTo(newName);
	}
	
	public static void GeraCsv(String array[], String coluna) {
		String line;
		int i = 0;
		try (FileWriter writer = new FileWriter("arquivo.csv", true)){
			writer.write(coluna + "\n");
			
    		for(i = 1; i < array.length; i++) {
    			line = array[i];
    			writer.write(line + "\n");
    		}
    		
    		writer.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void reverse(String array[], int n) {
		
		int j = n - 1;
		String linha;
		
		for(int i = 0; i < 625417 && j >= 0; i++) {
			linha = array[i];
			array[i] = array[j];
			array[j] = linha;
			--j;
		}
	}
}


