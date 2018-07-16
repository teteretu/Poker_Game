package view;

import control.Archive;

public class MainV2 {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Informe 3 arquivos para serem lidos.");
			System.exit(1);
		}
		
		String line = new String();
		
		for (int x = 0; x < 3; x++) {
			Archive.readFileAndCalculate(args[x]);
			line += String.valueOf(Archive.getDatePassed()) + " " + String.valueOf(Archive.getFour()) +
					" " + String.valueOf(Archive.getDifferents()) + " " + String.valueOf(Archive.getSequences()) + "\n";
		}
		
		Archive.writeFile(line);
		
		System.exit(0);
	}

}
