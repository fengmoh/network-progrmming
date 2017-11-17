package hangman;

import java.io.*;
import java.util.*;

public class getword {
	public static String randomWord(){
		//static might be an error when run multithread!!!
		String rdmword = null;
		try {
			//List<String> list = new ArrayList<String>();
			FileReader fr=new FileReader("E:\\javaworkspace\\tst\\bin\\words.txt");
			BufferedReader reader = new BufferedReader(fr);
		    String line = reader.readLine();
		    List<String> words = new ArrayList<String>();
	    while(line != null) {
	        String[] wordsLine = line.split("\n");
	        for(String word : wordsLine) {
	            words.add(word);
	        }
	        line = reader.readLine();
	    }

	    Random rand = new Random(System.currentTimeMillis());
        rdmword = words.get(rand.nextInt(words.size()));
      
	}catch(Exception e)
		{
		e.getStackTrace();
	}
		return rdmword;
	}

}
