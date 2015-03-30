package lab3.v16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SentimentAnalyzer {
	private ArrayList<String> getText(String path) throws IOException
	{
		File file = new File(path);
		BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ASCII"));
		ArrayList<String> words = new ArrayList<String>();
        while(true)
        {
            String line = fr.readLine();
            if(line==null)
                break;
            String[] tempWords = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");;//those are your words
            for (String word : tempWords)
            {
            	words.add(word);
            }
        }
        fr.close();
		return words;
	}
	
	public Integer analyze(String path) throws IOException
	{
		Integer sentiment = new Integer(0);
		ArrayList<String> text = getText(path);
		ArrayList<String> negw = getText("negw.list");
		ArrayList<String> posw = getText("posw.list");
		ArrayList<String> neuw = getText("neuw.list");
		for (String word : text)
		{
			boolean cont = true;
			for (String neu : neuw)
				if (neu.equals(word))
				{
					cont = false;
					break;
				}
			if (cont)
				for (String neg : negw)
					if (neg.equals(word))
					{
						sentiment--;
						cont = false;
						break;
					}
			if (cont)
				for (String pos : posw)
					if (pos.equals(word))
					{
						sentiment++;
						cont = false;
						break;
					}
		}
		return sentiment;
	}
}
