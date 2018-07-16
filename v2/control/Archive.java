package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.ProcessArchive;

public class Archive {

	private static long four = 0;
	private static long nothing = 0;
	private static long differents = 0;
	private static long sequences = 0;
	private static long datePassed = 0;
	
	public static List<String> readFileAndCalculate(String fileName) {
		Long dateBeggin = System.currentTimeMillis();
		
		four = 0;
		differents = 0;
		sequences = 0;
		datePassed = 0;
		BufferedReader br = null;
		FileReader fr = null;
		List<String> hands = new ArrayList<>();
		ProcessArchive processArquive = new ProcessArchive();

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			String sCurrentLine;
			int thousand = 0;
			
			while ((sCurrentLine = br.readLine()) != null) {
				hands.add(sCurrentLine);
				
				if (thousand > 5000) {
					if (hands != null && hands.size() > 0) {
//						System.out.println("calculando");
						processArquive.verifyHands(hands);
						hands = null;
						hands = new ArrayList<>();
					}
					thousand = 0;
				} else
					thousand++;
				
			}
			
			if (hands != null && hands.size() > 0) {
				
				processArquive.verifyHands(hands);
			}
			
			setFour(processArquive.getFour());
			setNothing(processArquive.getNothing());
			setDifferents(processArquive.getDifferents());
			setSequences(processArquive.getSequences());
			setDatePassed(System.currentTimeMillis() - dateBeggin);

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
	
	public static void writeFile (String line) {
		try {
			File file = new File("saida.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(line);
			
			bw.close();
			fw.close();
		}
		
		catch (IOException e) {
			 e.printStackTrace();
		}
	}

	public static long getFour() {
		return four;
	}

	public static void setFour(long four) {
		Archive.four = four;
	}

	public static long getNothing() {
		return nothing;
	}

	public static void setNothing(long nothing) {
		Archive.nothing = nothing;
	}

	public static long getDifferents() {
		return differents;
	}

	public static void setDifferents(long differents) {
		Archive.differents = differents;
	}

	public static long getSequences() {
		return sequences;
	}

	public static void setSequences(long sequences) {
		Archive.sequences = sequences;
	}

	public static long getDatePassed() {
		return datePassed;
	}

	public static void setDatePassed(long datePassed) {
		Archive.datePassed = datePassed;
	}
}
