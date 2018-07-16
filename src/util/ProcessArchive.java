package util;

import java.util.Arrays;
import java.util.List;

import poker.Main.Card;

public class ProcessArchive {

	private static long four = 0;
	private static long nothing = 0;
	private static long differents = 0;
	private static long sequences = 0;
	
	public void verifyHands(List<String> hands) {
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
	
	public long getFour() {
		return four;
	}

	public void setFour(long four) {
		ProcessArchive.four = four;
	}

	public long getNothing() {
		return nothing;
	}

	public void setNothing(long nothing) {
		ProcessArchive.nothing = nothing;
	}

	public long getDifferents() {
		return differents;
	}

	public void setDifferents(long differents) {
		ProcessArchive.differents = differents;
	}

	public long getSequences() {
		return sequences;
	}

	public void setSequences(long sequences) {
		ProcessArchive.sequences = sequences;
	}

}
