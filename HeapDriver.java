import java.io.*;
import java.util.*;

public class HeapDriver
{
    public static void main(String[] args)
    {
        MaxHeap<int> heap = new MaxHeap<int>();

        Scanner x;
        try
        {
            x = new Scanner(new File("data_sorted.txt"));

            while(x.hasNext())
            {
                System.out.println(x.next());
            }

            x.close();
        }
        catch(Exception e)
        {
            System.out.println("Could not find the file");
        }

    }

}