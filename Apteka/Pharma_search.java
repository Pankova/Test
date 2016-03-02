import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Мария on 28.02.16.
 */
public class Pharma_search
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Pharma_search obj = new Pharma_search();
		obj.search(sc.nextLine());
	}

	static Comparator<Pharmacy> comp = new Comparator<Pharmacy>()
	{
		@Override
		public int compare(Pharmacy o1, Pharmacy o2)
		{
			return o1.getDistance().compareTo(o2.getDistance());
		}
	};

	public void search(String in)
	{
		String[] data = in.split(" ");
		String filename = data[0];
		BufferedReader reader = null;
		String line = "";
		String splitElem = "\\|";

		Double x = Double.parseDouble(data[1]); //широта
		Double y = Double.parseDouble(data[2]); //долгота

		try{
			reader = new BufferedReader(new FileReader(filename));
			List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();

			line = reader.readLine();
			while(( line = reader.readLine()) != null){
				String[] newLine = line.split(splitElem);
				if(newLine.length == 4){
					Pharmacy pharm = new Pharmacy(newLine[0],newLine[1],Double.parseDouble(newLine[2]),Double.parseDouble(newLine[3]));
					pharmacies.add(pharm);
				}
				else
					System.out.println("Incorrect data: " + line);
			}
			reader.close();

			//нашли расстояние от введенной координаты до каждой из аптек
			for(int i = 0; i < pharmacies.size(); i++)
				pharmacies.get(i).setDistance(x, y);


			Collections.sort(pharmacies, comp);

			for(int i = 0; i < 3; i++)
				System.out.println(pharmacies.get(i));

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
