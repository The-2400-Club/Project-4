import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class HeapDriver 
{
	public static void main(String[] args) throws FileNotFoundException
	{	
		MaxHeap sequential = new MaxHeap(); //new heap
	
		File data = new File("data_sorted.txt"); //new input file 
		
		Scanner inputData = new Scanner(data);	//read data from input file
		
		int i = 0; 
		int[] tempArray = new int[100];
		
		while (inputData.hasNextInt()) //loop used to store data
		{
			int number = Integer.parseInt(inputData.nextLine());
			tempArray[i] = number; //data into array
			sequential.add(number); //data sequentially added to heap
			i++;
		}
		
		inputData.close(); //file closed
		
		MaxHeap optimal = new MaxHeap(tempArray);	//new heap 
		
		PrintWriter outputFile = new PrintWriter("output.txt"); //output file created
		
		outputFile.println("=====================================================================");
		outputFile.println("Heap built using sequential insertions: " + sequential.topTen() + "...");				
		outputFile.println("Number of swaps in the heap creation: " + sequential.getSwaps());
		outputFile.println("Heap after 10 removals: " + sequential.removeTop() + "...");
		outputFile.println();
		outputFile.println("Heap built using optimal method: " + optimal.topTen() + "...");
		outputFile.println("Number of swaps in the heap creation: " + optimal.getSwaps());
		outputFile.println("Heap after 10 removals: " + optimal.removeTop() + "...");
		outputFile.println("=====================================================================");
		
		outputFile.close(); //output file closed
	}

}
