package Ordenacoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import Funcoes.Metodos;

public class Ordenacao3 {

	String[] array = new String[1250835];
	String coluna;
	
	public Ordenacao3() throws FileNotFoundException {
		File metroTrips = new File("LAMetroTrips.csv");
		Scanner read = new Scanner(metroTrips);
		String linha;
		int n = 0;
		coluna = read.nextLine();
		
		while(read.hasNextLine()) {
			linha = read.nextLine().toString();
			array[n] = linha;	
			++n;
		} //Fim do loop while
		read.close();
	}
	
	public void allMediumCases() {
		
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_insertionSort_medioCaso.csv");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_selectionSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_quickSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_mergeSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_heapSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_quickSortMedianaDe3_medioCaso");
	}
	
	public static long converteData(String linha) {
		
		try {
			
			String dataNaoFormatada = Metodos.readLinha(linha, 2, 3).substring(0,2);
			String dataNaoFormatada2 = Metodos.readLinha(linha, 2, 3).substring(3,5);
			dataNaoFormatada = dataNaoFormatada.replace("/","");
			dataNaoFormatada2 = dataNaoFormatada2.replace("/","");
			int num1 = Integer.parseInt(dataNaoFormatada);
			int num2 = Integer.parseInt(dataNaoFormatada2);
			
			if(num1 < num2) {
				SimpleDateFormat formatoOrigem = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		       	String data1 = Metodos.readLinha(linha, 2, 3);
		    	Date data = formatoOrigem.parse(data1); // string com a data no formato dia-mês-ano

		    	SimpleDateFormat formatoDestino = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    	String formatoAnoMesDia = formatoDestino.format(data);
		    	
		    	formatoAnoMesDia = formatoAnoMesDia.replace("/","");
		    	formatoAnoMesDia = formatoAnoMesDia.replace(":","");
		    	formatoAnoMesDia = formatoAnoMesDia.replace(" ","");
		    	return Long.parseLong(formatoAnoMesDia);
			}
			else {
				String formatoAnoMesDia = Metodos.readLinha(linha, 2, 3);
				formatoAnoMesDia = formatoAnoMesDia.replace("/","");
		    	formatoAnoMesDia = formatoAnoMesDia.replace(":","");
		    	formatoAnoMesDia = formatoAnoMesDia.replace(" ","");
		    	return Long.parseLong(formatoAnoMesDia);
			}
		
		}
	    catch(ParseException e){
	    	System.out.println("Error: " + e.getMessage());
	    	return -1;
	    }
    }

	private void insertionSort(int n){
	
		long key1, key2;
		int j;
		String aux1, aux2;
		for(int i = 1; i < 2000; i++) {
			key1 = converteData(array[i]);//chave = vetor[i];
			aux1 = array[i];
			j = i - 1;
			key2 = converteData(array[j]);
			aux2 = array[j];
		
			while((j >= 0) && (key2 > key1)) {
				array[j + 1] = array[j];
				j = j - 1;
				if(j >= 0) {
					key2 = converteData(array[j]);
					aux2 = array[j];
				}
			}
			array[j + 1] = aux1;
		}//Fim do laço for
		
	} //Fim do método insertionSort
	
	public void insertionFiles(int n) {
		
		//Gerando o melhor caso
		insertionSort(n); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_insertionSort_melhorCaso.csv");
		
		//Gerando o pior caso
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_insertionSort_piorCaso.csv");
		} //Fim do método insertionFiles
	
	private void selectionSort(int n) {	
		
		int index, j;
		double aux1 = 0D, aux2 = 0D;
		String linha;
		for (int i = 0; i < n; i++) {
			index = i;
			for (j = i + 1; j < n; j++) {
				aux1 = converteData(array[index]);
				aux2 = converteData(array[j]);;
				if (aux2 < aux1)
					index= j;
			
			linha = array[index];
			array[index] = array[i];
			array[i] = linha;
			}
		}		
	} //Fim do método selectionSort
	
	public void selectionFiles(int n) {
		//Gerando o melhor caso
		selectionSort(n); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_selectionSort_melhorCaso");
				
		//Gerando o pior caso
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_selectionSort_piorCaso");
	} //Fim do método selectionFiles
	
	private void heapSort(int len)
    {
        int n = len;//array.length;
  
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);
  
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            String temp = array[0];
            array[0] = array[i];
            array[i] = temp;
  
            // call max heapify on the reduced heap
            heapify(array, i, 0);
        }
    } //Fim do método heapSort
  
    private static void heapify(String array[], int n, int i)
    {
        int largest = i; 
        int l = 2 * i + 1;
        int r = 2 * i + 2; 
  
        if (l < n && converteData(array[l]) > converteData(array[largest]))
            largest = l;
  
        if (r < n && converteData(array[r]) > converteData(array[largest]))
            largest = r;
  
        if (largest != i) {
            String swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
  
            heapify(array, n, largest);
        }
    } //Fim do método heapify
    
    public void heapSortFiles(int n) {
		
		//Gerando o melhor caso
		heapSort(n); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_heapSort_melhorCaso");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_heapSort_piorCaso");
		
	} //Fim do método heapSortFiles
    
    public static int partition1(String arr[], int low, int high)
    {
        long pivot = converteData(arr[high]);
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (converteData(arr[j]) <= pivot)
            {
                i++;
 
                // swap arr[i] and arr[j]
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
 
        String temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
 
        return i + 1;
    }
 
    private static void quicksort(String arr[], int low, int high)
    {
        if (low < high)
        {

            int pi = partition1(arr, low, high);

            quicksort(arr, low, pi-1);
            quicksort(arr, pi+1, high);
        }
    }
    
    public void quickSortFiles(int n) {
		
		//Gerando o melhor caso
		quicksort(this.array, 0, n); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_quickSort_melhorCaso");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_quickSort_piorCaso");
		
	} //Fim do método quickSortFiles
    
    private static int medianPivot(String array[], int low, int high) {
    	
		long first = converteData(array[low]);//arr[low]
		long last = converteData(array[array.length - 1]);//arr[arr.length - 1];
		int mid = (high) / 2;

		String[] sortingArr = {array[low], array[mid], array[high]};

		Arrays.sort(sortingArr);

		String middleValue = sortingArr[1];

		long temp = converteData(array[high]);//array[high];
		String auxTemp = array[high];
		String linha = array[high];
		array[high] = middleValue;
		long aux1 = converteData(array[mid]);
		
		if (converteData(middleValue) == converteData(array[low])) {
			array[low] = linha;
		} else if (converteData(middleValue) == (aux1)) {
			array[mid] = auxTemp;
		}
		
		return partition(array, low, high);

	}

	private static void medianQuickSort(String array[], int low, int high) {
		if (low >= high)
			return;

		if (low < high) {

			int pi = medianPivot(array, low, high);

			QuickSort(array, low, high);

		}
	} //Fim do método medianQuickSort

	private static void QuickSort(String array[], int low, int high) {

		if (low < high) {
			int pi = partition(array, low, high);

			QuickSort(array, low, pi - 1);
			QuickSort(array, pi + 1, high);
		}
	} //Fim do método QuickSort
	
	private static int partition(String array[], int low, int high) {
		long pivot = converteData(array[high]);//array[high];
		int i = (low - 1);
		long aux1;
		int count = 0;

		for (int j = low; j < high; j++) {
			aux1 = converteData(array[j]);
			if (aux1 <= pivot) {
				i++;

				String linha = array[i];
				array[i] = array[j];
				array[j] = linha;
				count++;
			}
			count++;
		}
		
		String linha = array[i + 1];
		array[i + 1] = array[high];
		array[high] = linha;
		count++;
		
		return i + 1;
	} //Fim do método partition
	
	public void quickSort3tFiles(int n) {
		
		//Gerando o melhor caso
		medianQuickSort(this.array, 0, n);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_quickSortMedianaDe3_melhorCaso");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_quickSortMedianaDe3_piorCaso");
		
	} //Fim do método quickSort3Files
	
	private static void merge(String array[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
 
        String L[] = new String[n1];
        String R[] = new String[n2];
 
        for (int i = 0; i < n1; ++i)
            L[i] = array[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[m + 1 + j];
        
        int i = 0, j = 0;
 
        int k = l;
        long aux1, aux2;
        while (i < n1 && j < n2) {
        	aux1 = converteData(L[i]);
        	aux2 = converteData(R[j]);
            if (aux1 <= aux2) {
                array[k] = L[i];
                i++;
            }
            else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
 
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }
 
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    } //Fim do método merge
    
    private static void mergeSort(String array[], int l, int r)
    {
        if (l < r) {
        	
            int m = l+ (r-l)/2;
 
            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);
 
            merge(array, l, m, r);
        }
    } //Fim do método mergeSort
    
    public void mergeSortFiles(int n) {
		
		//Gerando o melhor caso
    	mergeSort(this.array, 0, n); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_mergeSort_melhorCaso");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_start_time_mergeSort_piorCaso");
		
	} //Fim do método selectionFiles
}