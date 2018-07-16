package view;

import control.Archive;

public class MainV2 {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Informe 3 arquivos para serem lidos.");
			System.exit(1);
		}

		for (String arg : args) {
			Archive file = new Archive();
			
			file.readFileAndCalculate(arg);
		}
		
		System.exit(0);
	}

}
