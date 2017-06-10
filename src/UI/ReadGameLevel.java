package UI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadGameLevel {

	public static char[][] gameLevel(String fileName)
	{
		ArrayList<Character> level = new ArrayList<>();
		char[][] l = new char[20][20];
		BufferedReader reader = null;
		
		try {
		
			char c;
			int readValue;
			reader = new BufferedReader(new FileReader(fileName));
			while((readValue = reader.read()) != -1)
			{
				c = (char)readValue;
				if((c == '0') || (c == '1') || (c== '2') || (c=='3'))
				{
					level.add(c);
				}
				
			}
			
			int index = 0;
			for(int x = 0; x < 20; x++)
			{
				for(int y = 0; y < 20; y++)
				{
					l[x][y] = level.get(index);
					index++;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return l;
	}
}
