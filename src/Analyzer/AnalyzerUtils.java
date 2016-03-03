package Analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

public class AnalyzerUtils {

	public static void displayToken(String str,Analyzer a)
	{
		TokenStream stream=a.tokenStream("content", new StringReader(str));
		CharTermAttribute cta=stream.addAttribute(CharTermAttribute.class);
		try {
			while(stream.incrementToken())
			{
				System.out.println("["+cta+"]");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void displayAllTokenStream(String str,Analyzer a)
	{
		TokenStream stream=a.tokenStream("content", new StringReader(str));
		CharTermAttribute cta=stream.addAttribute(CharTermAttribute.class);
		PositionIncrementAttribute pia=stream.addAttribute(PositionIncrementAttribute.class);
		OffsetAttribute oa=stream.addAttribute(OffsetAttribute.class);
		try {
			while(stream.incrementToken())
			{
				if(cta.toString().equals("china"))
				{
					cta.setEmpty();
					cta.append("Chinese");
					//System.out.print("Yeah");
				}
				
				System.out.println("["+cta+"]");
				//System.out.print("["+cta+"]"+"["+pia+"]"+"["+oa+"]");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
