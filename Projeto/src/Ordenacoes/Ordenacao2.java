package Ordenacoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import Funcoes.Metodos;

public class Ordenacao2 {
	
	String[] array = new String[1250835];
	String coluna;
	
	public Ordenacao2() throws FileNotFoundException {
		File metroTrips = new File("LAMetroTrips.csv");
		Scanner read = new Scanner(metroTrips);
		String linha;
		this.coluna = read.nextLine();
		int n = 0;
		
		while(read.hasNextLine()) {
			linha = read.nextLine().toString();
			this.array[n] = linha;	
			++n;
		} //Fim do loop while
		read.close();
	}
	
	public void allMediumCases() {
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_insertionSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_selectionSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_quickSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_countingSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_mergeSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_heapSort_medioCaso");
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_quickSortMedianaDe3_medioCaso");
	} //Fim do método que gera todos os casos médios
	
	private void insertionSort(int n){
		
		int key1, key2, j;
		String aux1, aux2;
		for(int i = 1; i < n; i++) {
			key1 = Integer.parseInt(Metodos.readLinha(this.array[i], 1, 2));//chave = vetor[i];
			aux1 = this.array[i];
			j = i - 1;
			key2 = Integer.parseInt(Metodos.readLinha(this.array[j], 1, 2));
			aux2 = this.array[j];
			
			while((j >= 0) && (key2 > key1)) {
				this.array[j + 1] = this.array[j];
				j = j - 1;
				if(j >= 0) {
					key2 = Integer.parseInt(Metodos.readLinha(this.array[j], 1, 2));
					aux2 = this.array[j];
				}
			}
			this.array[j + 1] = aux1;
		}//Fim do laço for
    }
	
	public void insertionFiles(int n) {
		
		//Gerando o melhor caso
		insertionSort(n); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_insertionSort_melhorCaso.csv");
		
		//Gerando o pior caso
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_insertionSort_piorCaso.csv");
	} //Fim do método insertionFiles
	
	private void selectionSort(int n) throws NumberFormatException {	
		
		int index, j;
		int aux1 = 0, aux2 = 0;
		String linha;
		for (int i = 0; i < n; i++) {
			index = i;
			for (j = i + 1; j < n; j++) {
				aux1 = Integer.parseInt(Metodos.readLinha(array[index], 1, 2));
				aux2 = Integer.parseInt(Metodos.readLinha(array[j], 1, 2));
				if (aux2 < aux1)
					index= j;
			
			linha = array[index];
			array[index] = array[i];
			array[i] = linha;
			}
		}		
	}
	
	public void selectionFiles(int n) {
		//Gerando o melhor caso
		selectionSort(n); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_selectionSort_melhorCaso");
				
		//Gerando o pior caso
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_selectionSort_piorCaso");
	} //Fim do método selectionFiles
	
	private void countingSort()
    {
        int n = array.length;
 
        String output[] = new String[n];
        int count[] = new int[n];
        
        for (int i = 0; i < n; ++i)
            ++count[Integer.parseInt(Metodos.readLinha(array[i], 1, 2))]; //array[i]
 
        for (int i = 1; i <= n - 1; ++i){
            count[i] += count[i - 1];
        }
 
        int aux;
        for (int i = n - 1; i >= 0; i--) {
        	aux = Integer.parseInt(Metodos.readLinha(array[i], 1, 2));
            output[count[aux] - 1] = array[i];
            --count[aux];
        }

        for (int i = 0; i < n; ++i){
            array[i] = output[i];
        }
    } //Fim do método countingSort
	
	public void countingFiles() {
		
		//Gerando o melhor caso
		countingSort(); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_countingSort_melhorCaso");
				
		//Gerando o pior caso
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_countingSort_piorCaso");
	} //Fim do método selectionFiles
	
	public static void merge(String array[], int l, int m, int r)
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
        int aux1, aux2;
        while (i < n1 && j < n2) {
        	aux1 = Integer.parseInt(Metodos.readLinha(L[i], 1, 2));
        	aux2 = Integer.parseInt(Metodos.readLinha(R[j], 1, 2));
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
    }
    
    public static void mergeSort(String array[], int l, int r)
    {
        if (l < r) {
        	
            int m = l+ (r-l)/2;
 
            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);
 
            merge(array, l, m, r);
        }
    }
    
    public void mergeSortFiles() {
		
		//Gerando o melhor caso
    	mergeSort(this.array, 0, 1250834); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_mergeSort_melhorCaso");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_mergeSort_piorCaso");
		
	} //Fim do método mergeSortFiles
    
    private void heapsort() {
		int n = array.length;
		  
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);
  
        for (int i = n - 1; i > 0; i--) {
            String temp = array[0];
            array[0] = array[i];
            array[i] = temp;
  
            heapify(array, i, 0);
        }
		
	} //Fim do método heapSort

	private static void heapify(String[] arr, int n, int i) {

		int largest = i;
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 
        
  
        if (l < n && Integer.parseInt(Metodos.readLinha(arr[l], 1, 2)) > Integer.parseInt(Metodos.readLinha(arr[largest], 1, 2)))
            largest = l;
  
        if (r < n && Integer.parseInt(Metodos.readLinha(arr[r], 1, 2)) > Integer.parseInt(Metodos.readLinha(arr[largest], 1, 2)))
            largest = r;
  
        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
  
            heapify(arr, n, largest);
	    }
		
	} //Fim do método heapify
	
	public void heapSortFiles() {
		
		//Gerando o melhor caso
		heapsort(); //Ordenando
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_heapSort_melhorCaso");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_heapSort_piorCaso");
		
	} //Fim do método heapSortFiles
	
    public static int medianPivot(String array[], int low, int high) {
    	
		int first = Integer.parseInt(Metodos.readLinha(array[low], 1, 2));//arr[low]
		int last = Integer.parseInt(Metodos.readLinha(array[array.length - 1], 1, 2));//arr[arr.length - 1];
		int mid = (high) / 2;

		String[] sortingArr = { array[low], array[mid], array[high] };

		Arrays.sort(sortingArr);

		String middleValue = sortingArr[1];

		int temp = Integer.parseInt(Metodos.readLinha(array[high], 1, 2));//array[high];
		String auxTemp = array[high];
		String linha = array[high];
		array[high] = middleValue;
		String aux1 = Metodos.readLinha(array[mid], 1, 2);
		
		if (middleValue.equals(array[low])) {
			array[low] = linha;
		} else if (middleValue.equals(aux1)) {
			array[mid] = auxTemp;
		}
		
		return partition(array, low, high);

	}

	public static void medianQuickSort(String array[], int low, int high) {
		if (low >= high)
			return;

		if (low < high) {

			int pi = medianPivot(array, low, high);

			QuickSort(array, low, high);

		}
	} //Fim do método medianQuickSort

	public static void QuickSort(String array[], int low, int high) {

		if (low < high) {
			int pi = partition(array, low, high);

			// Recursively sort elements before
			// partition and after partition
			QuickSort(array, low, pi - 1);
			QuickSort(array, pi + 1, high);
		}
	} //Fim do método QuickSort
	
	public static int partition(String array[], int low, int high) {
		int pivot = Integer.parseInt(Metodos.readLinha(array[high], 1, 2));//array[high];
		int i = (low - 1);
		int aux1;
		int count = 0;

		for (int j = low; j < high; j++) {
			aux1 = Integer.parseInt(Metodos.readLinha(array[j], 1, 2));
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
		Metodos.renameFile("LAMetroTrips_duration_quickSortMedianaDe3_melhorCaso");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_quickSortMedianaDe3_piorCaso");
		
	} //Fim do método quickSort3Files
	
	public static int partition1(String arr[], int low, int high)
    {
        int pivot = Integer.parseInt(Metodos.readLinha(arr[high], 1, 2));
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (Integer.parseInt(Metodos.readLinha(arr[high], 1, 2)) <= pivot)
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
		Metodos.renameFile("LAMetroTrips_duration_quickSort_melhorCaso");
		
		//Gerando o pior caso
		
		Metodos.reverse(array, array.length);
		Metodos.GeraCsv(this.array, this.coluna);
		Metodos.renameFile("LAMetroTrips_duration_quickSort_piorCaso");
		
	} //Fim do método quickSortFiles
}

