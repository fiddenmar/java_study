package lab3.v16;

import java.io.IOException;
import java.util.Scanner;

public class SentimentMe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the sentiment analyzer SentimentMe 0.0.1");
		System.out.println("Type ADD to add a word");
		System.out.println("Type ANALYZE to analyze a text");
		String answer = scanner.nextLine();
		if (answer.equals("ADD"))
		{
			boolean cont = true;
			while (cont)
			{
				WordType type = null;
				System.out.println("Enter a word:");
				String word = scanner.nextLine();
				while (type == null)
				{
					System.out.println("Enter a sentiment of this word (NEU/POS/NEG):");
					String sent = scanner.nextLine();
					
					if (sent.equals("NEU"))
						type = WordType.Neutral;
					else
						if (sent.equals("NEG"))
							type = WordType.Negative;
						else
							if (sent.equals("POS"))
								type = WordType.Positive;
							else
								System.out.println("Wrong type");
				}
				SentimentAdder adder = new SentimentAdder();
				adder.add(word, type);
				System.out.println("Done. Finish (Y/N)?");
				answer = scanner.nextLine();
				if (answer.equals("Y"))
				{
					cont = false;
				}
			}
		}
		else
			if (answer.equals("ANALYZE"))
			{
				System.out.println("Enter the path to the text:");
				String path = scanner.nextLine();
				SentimentAnalyzer analyzer = new SentimentAnalyzer();
				Integer res = new Integer(0);
				try {
					res = analyzer.analyze(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("The sentiment of this text is");
				System.out.println(res);
				
			}
			else
			{
				System.out.println("Wrong command");
			}
	}

}
