package MAIN;

import java.io.IOException;
import java.util.Scanner;

import Ordenacoes.Ordenacao1;
import Ordenacoes.Ordenacao2;
import Ordenacoes.Ordenacao3;
import Transformacoes.Transformacao1;
import Transformacoes.Transformacao2;
import Transformacoes.Transformacao3;

public class Main {
public static void main(String args[]) {
		
		try {
			//Gerando a transformação 1
			
			Scanner leitor = new Scanner(System.in);
			System.out.println("Olá!\nGerando os arquivos das transformações...");
			Transformacao1 trans1 = new Transformacao1();
			trans1.leStations();
			trans1.leMetroTrips();
			Transformacao2 trans2 = new Transformacao2();
			trans2.newFile();
			Transformacao3 trans3 = new Transformacao3();
			trans3.newFile2();
			
			System.out.println("Pronto! Você já pode checar sua pasta ao qual o projeto está presente.\n");
			
			int opc = 0;
			int tamanho = 0;
			do {
				System.out.println("Informe qual passo de ordenação você deseja executar: ");
				System.out.println("\n1 Passo 1-\n2 - Passo 2\n3 - Passo 3\n4 - Sair");
				opc = leitor.nextInt();
				System.out.println("Informe o a parcela/quantidade de linhas que será ordenada (obs: máximo é 1250836): ");
				tamanho = leitor.nextInt();
				
				
				switch(opc) {
					case 1:
						Ordenacao1 passo1 = new Ordenacao1();
						passo1.allMediumCases();
						passo1.insertionFiles(tamanho); //Limitando o tamanho do arquivo para ordenação
						passo1.selectionFiles(tamanho);
						passo1.mergeSortFiles();
						passo1.quickSortFiles();
						passo1.heapSortFiles(tamanho);
						break;
					case 2:
						Ordenacao2 passo2 = new Ordenacao2();
						passo2.allMediumCases();
						passo2.countingFiles();
						passo2.mergeSortFiles();
						passo2.insertionFiles(tamanho);
						passo2.selectionFiles(tamanho);
						passo2.heapSortFiles();
						passo2.quickSort3tFiles(tamanho);
						passo2.quickSortFiles(tamanho);
						break;
					case 3:
						Ordenacao3 passo3 = new Ordenacao3();
						passo3.allMediumCases();
						passo3.heapSortFiles(tamanho);
						passo3.insertionFiles(tamanho);
						passo3.selectionFiles(tamanho);
						passo3.quickSortFiles(tamanho);
						passo3.quickSort3tFiles(tamanho);
						passo3.mergeSortFiles(tamanho - 1);
						break;
					case 4:
						System.out.println("Saindo...\n...\nPronto!");
						break;
					default:
						System.out.println("Não conseguimos entender essa opção!");
						break;
				}
			}while(opc < 4 && opc > 0);
		}
		catch(IOException e) {
			System.out.println("Algo deu errado  : (\nInicie o programa novamente.\n");
			System.out.println(e);
		}
	}
}