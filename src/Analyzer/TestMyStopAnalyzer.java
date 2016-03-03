package Analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class TestMyStopAnalyzer {

	public static void main(String[] args)
	{
		Analyzer a1=new MyStopAnalyzer(new String[] {"i","come"});
        String text="this is my house,i come from china,my email is mariatravelling@hotmail.com";
        AnalyzerUtils.displayToken(text, a1);
	}
}
