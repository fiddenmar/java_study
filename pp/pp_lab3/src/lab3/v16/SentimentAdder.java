package lab3.v16;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SentimentAdder {
	void add(String word, WordType type)
	{
		String path = new String();
		if (type == WordType.Negative)
			path = "negw.list";
		if (type == WordType.Positive)
			path = "posw.list";
		if (type == WordType.Neutral)
			path = "neuw.list";
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
		    out.println(word+" ");
		    out.close();
		} catch (IOException e) {
		    ;
		}
	}
}
