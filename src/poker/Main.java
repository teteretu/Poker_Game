package poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
	private static final String FILENAME2k = "C:\\Users\\win7\\Documents\\git-repositories\\Poker_Game\\poker2K.txt";
	private static final String FILENAME2M = "C:\\Users\\win7\\Documents\\git-repositories\\Poker_Game\\poker2M.txt";
	private static final String FILENAME200M = "C:\\Users\\win7\\Documents\\git-repositories\\Poker_Game\\poker200M.txt";

	private static long four;
	private static long nothing;
	private static long differents;
	private static long sequences;
	private static long datePassed;
	
	
	public enum Card {
	    T(10),J(11),Q(12),K(13),A(14);
	 
	    public Integer cardValue;
	    Card(Integer value) {
	        cardValue = (Integer) value;
	    }
	    
	    public Integer getCard() {
	    	return this.cardValue;
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calculate2k();
		calculate2M();
		calculate200M();
		
	}
	
	public static void calculate2k () {
		Long dateBeggin = System.currentTimeMillis();
		
		readFileAndCalculate(FILENAME2k);
		
		datePassed = System.currentTimeMillis() - dateBeggin;
		System.out.println("2 mil: ");
		System.out.print("date Passed: " + datePassed);
		System.out.println(" four: " + four + " diferentes: " + differents + " sequências: " + sequences);
		System.out.println("nothing: " + nothing);
		
		four = 0;
		nothing = 0;
		differents = 0;
		sequences = 0;
		datePassed = 0;
	}

	public static void calculate2M () {
		Long dateBeggin = System.currentTimeMillis();
		
		readFileAndCalculate(FILENAME2M);
		
		datePassed = System.currentTimeMillis() - dateBeggin;
		System.out.println("2 milhões: ");
		System.out.print("date Passed: " + datePassed);
		System.out.println(" four: " + four + " diferentes: " + differents + " sequências: " + sequences);
		System.out.println("nothing: " + nothing);

		four = 0;
		nothing = 0;
		differents = 0;
		sequences = 0;
		datePassed = 0;
	}
	

	public static void calculate200M () {
		Long dateBeggin = System.currentTimeMillis();
		
		readFileAndCalculate(FILENAME200M);
		
		datePassed = System.currentTimeMillis() - dateBeggin;
		System.out.println("200 milhões: ");
		System.out.println("date Passed: " + datePassed);
		System.out.println("four: " + four + " diferentes: " + differents + " sequências: " + sequences);
		System.out.println("nothing: " + nothing);

		four = 0;
		nothing = 0;
		differents = 0;
		sequences = 0;
		datePassed = 0;
	}
	
	public static List<String> readFileAndCalculate(String fileName) {
		BufferedReader br = null;
		FileReader fr = null;
		List<String> hands = new ArrayList<>();

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
						System.out.println("calculando");
						verifyHands(hands);
						hands = null;
						hands = new ArrayList<>();
					}
					thousand = 0;
				} else
					thousand++;
				
			}
			
			if (hands != null && hands.size() > 0) {
				
				verifyHands(hands);
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

	public static void verifyHands(List<String> hands) {
		//percorre todos os conjuntos de cartas
		for (String hand : hands) {
			
			String result = isFourEquals(hand);
			
			//se existem quatro cartas iguais
			if(result.equalsIgnoreCase("four")) {
				four++;
			} else if (result.equalsIgnoreCase("nada")) { //se existe pares
				nothing++;
			} else if (isSequency(hand)) { //sequência
				sequences++;
			}else {		//todos diferentes
				differents++;
			}
			
		}
	}
	
	public static String isFourEquals(String hand) {
		hand = hand.replaceAll("\\s+","");
		String tmp = new String(hand);
		short i = 0;
		while(i < tmp.length()-1) {
			hand = hand.replace(tmp.charAt(i++) + "", "");
			
			if (hand.length() > 3)
				hand = tmp;
			else if(hand.length() > 1) //se encontrou pelo menos um par de cartas
				return "nada";
			else
				return "four";	
		}
		
		return "";
	}
	
	public static boolean isSequency(String hand ) {
		/*sequencis
		 * TQJKA
		 * 23456
		 * */
		String[] hands = hand.split(("\\s+"));
		int[] handsNumber = new int[5];
		
		short i = 0;
		//transforma string em aray de números
		while (i < hands.length) {
			if(hands[i].equalsIgnoreCase("T")) {
				handsNumber[i] = Card.T.getCard();
			} else if (hands[i].equalsIgnoreCase("J")) {
				handsNumber[i] = Card.J.getCard();
			} else if (hands[i].equalsIgnoreCase("Q")) {
				handsNumber[i] = Card.Q.getCard();
			} else if (hands[i].equalsIgnoreCase("K")) {
				handsNumber[i] = Card.K.getCard();
			} else if (hands[i].equalsIgnoreCase("A")) {
				handsNumber[i] = Card.A.getCard();
			} else {
				handsNumber[i] = Integer.parseInt(hands[i]);
			}
			i++;
		}
		//ordena o array
		Arrays.sort(handsNumber);
		
		i = 0;
		short isSequence = 0;
		//conta os que estão em sequencia
		while(i < (handsNumber.length - 1)) {
			
			if ((handsNumber[i] + 1) == handsNumber[i+1])
				isSequence++;
			i++;
		}
		//quer dizer que são sequencia
		if (isSequence > 3)
			return true;
		else
			return false;	
	}
	
}
	
