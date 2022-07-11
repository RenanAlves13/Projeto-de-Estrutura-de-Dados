package Ordenacoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Funcoes.Metodos;

public class Ordenacao1 {
	
	String colunas;
	String array[] = new String[1250835];
	public Ordenacao1() throws FileNotFoundException {
		
		File bikeSharing = new File("LAMetroTrips.csv");
		Scanner bikeInput = null;
		bikeInput = new Scanner(bikeSharing); 
		this.colunas = bikeInput.nextLine();
		String linha;
		int j = 0;
		
		while(bikeInput.hasNextLine()) {
			linha = bikeInput.nextLine().toString();
		    array[j] = linha;
		    j++;
		} //Fim do loop while
		
	} //Fim do construtor
	
	public void allMediumCases() {
		//Gerando todos os arquivos de médio caso
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_insertionSort_medioCaso.csv");
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_selectionSort_medioCaso.csv");
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_mergeSort_medioCaso.csv");
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_quickSort_medioCaso.csv");
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_heapSort_medioCaso.csv");
	}
	
	private void insertionSort(int n) {
		int i,j;  
        String x;
        for (j = 1; j < n; j++) {       
            x = this.array[j]; 
            i = j - 1;

            while (i >= 0) {
                if (Metodos.readLinha(x, 9, 10).compareTo(Metodos.readLinha(array[i], 9, 10)) > 0) {
                    break;
                }
                this.array[i + 1] = this.array[i];
                i--;
            }
            this.array[i + 1] = x;
        }
	}
	
	public void insertionFiles(int n) {
		
		//Gerando o melhor caso
		insertionSort(n); //Ordenando
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_insertionSort_melhorCaso.csv");
		
		//Gerando o pior caso
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_insertionSort_piorCaso.csv");
	} //Fim do método insertionFiles
	
	private void selectionSort(int n){
		
		int index;
		String minStr;
		for(int i = 0; i < n - 1; i++){
	        index = i;
	        minStr = this.array[i];
	        for(int j = i + 1; j < n; j++){
	            if(Metodos.readLinha(this.array[j], 9, 10).compareTo(Metodos.readLinha(minStr, 9, 10)) < 0){
	                // Make arr[j] as minStr and update min_idx
	                minStr = this.array[j];
	                index = j;
	            }
	        }
	        if(index != i){
	            String temp = this.array[index];
	            this.array[index] = this.array[i];
	            this.array[i] = temp;
	        }
	    }
    } //Fim do método selectionSort
	
	public void selectionFiles(int n) {
		
		//Gerando o melhor caso
		selectionSort(n); //Ordenando
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_selectionSort_melhorCaso.csv");
		
		//Gerando o pior caso
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_selectionSort_piorCaso.csv");
		
	} //Fim do método selectionFiles
	
	private void mergeSort(String[] names) {
        if (names.length >= 2) {
            String[] left = new String[names.length / 2];
            String[] right = new String[names.length - names.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = names[i];
            }

            for (int i = 0; i < right.length; i++) {
                right[i] = names[i + names.length / 2];
            }

            mergeSort(left);
            mergeSort(right);
            merge(names, left, right);
        }
    }//Fim do método mergeSort

    private void merge(String[] names, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < names.length; i++) {
            if (b >= right.length || (a < left.length && Metodos.readLinha(left[a], 9, 10).compareToIgnoreCase(Metodos.readLinha(right[b], 9, 10)) < 0)) {
                names[i] = left[a];
                a++;
            } else {
                names[i] = right[b];
                b++;
            }
        }
    } //Fim do método merge
    
    public void mergeSortFiles() {
		
		//Gerando o melhor caso
		mergeSort(this.array); //Ordenando
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_mergeSort_melhorCaso.csv");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_mergeSort_piorCaso.csv");
		
	} //Fim do método mergeSortFiles
    
    private void quickSort(String array[], int start, int end) {
	    int i = start;
	    int k = end;
	    if (end - start >= 1) {
	        String pivot = array[start];
	        while (k > i) {
	            while (Metodos.readLinha(array[i], 9, 10).compareTo(Metodos.readLinha(pivot, 9, 10)) <= 0 && i <= end && k > i)
	                i++;
	            while (Metodos.readLinha(array[k], 9, 10).compareTo(Metodos.readLinha(pivot, 9, 10)) > 0 && k >= start && k >= i)
	                k--;
	            if (k > i)
	                swap(array, i, k);
	        }
	        swap(array, start, k);
	        quickSort(array, start, k - 1);
	        quickSort(array, k + 1, end);
	    }
	} //Fim do método quickSort
    
	private void swap(String array[], int index1, int index2) {
	    String temp = array[index1];
	    array[index1] = array[index2];
	    array[index2] = temp;
	} //Fim do método swap
	
	public void quickSortFiles() {
		
		//Gerando o melhor caso
		quickSort(this.array, 0, this.array.length - 1); //Ordenando
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_quickSort_melhorCaso.csv");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_quickSort_piorCaso.csv");
		
	} //Fim do método quickSortFiles
	
	private void heapSort( int i) {
        for (int j = i / 2; j >= 0; j--)
            fall(this.array, i, j);
        for (int k = i - 1; k > 0; k--) {
            String s = this.array[0];
            this.array[0] = this.array[k];
            this.array[k] = s;
            fall(this.array, k, 0);
        }
    } //Fim do método heapSort
	
    private static void fall(String as[], int i, int j) {
        int k = 2 * j + 1;
        if (k < i) {
            if (k + 1 < i && Metodos.readLinha(as[k], 9, 10).compareTo(Metodos.readLinha(as[k+1], 9, 10)) < 0)
                k = 2 * j + 2;
            if (Metodos.readLinha(as[j], 9, 10).compareTo(Metodos.readLinha(as[k], 9, 10)) < 0) {
                String s = as[j];
                as[j] = as[k];
                as[k] = s;
                fall(as, i, k);
            }
        }
    } //Fim do método fall
    
    public void heapSortFiles(int n) {
		
		//Gerando o melhor caso
		heapSort(n); //Ordenando
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_heapSort_melhorCaso.csv");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.colunas);
		Metodos.renameFile("LAMetroTrips_station_heapSort_piorCaso.csv");
		
	} //Fim do método heapSortFiles
}

