package poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final String FILENAME = "/Users/matheus.carvalho/Developer/effetive/Poker_Game/teste.txt";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> fileRead = readFile();
		
		if (fileRead != null && fileRead.size() > 0) {
			
		}
	}
	
	public static List<String> readFile() {
		BufferedReader br = null;
		FileReader fr = null;
		List<String> hands= new ArrayList<>();

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;			
			
			while ((sCurrentLine = br.readLine()) != null) {
				hands.add(sCurrentLine);
			}


		} catch (IOException e) {

			e.printStackTrace();
			return null;
		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
				
				return hands;
			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return null;
	}

	
}
	
