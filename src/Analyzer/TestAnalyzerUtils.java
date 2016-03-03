package Analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class TestAnalyzerUtils {

	public static void main(String[] args)
	{
		Analyzer a1=new StandardAnalyzer(Version.LUCENE_35);
		Analyzer a2=new StopAnalyzer(Version.LUCENE_35);
        Analyzer a3=new SimpleAnalyzer(Version.LUCENE_35);
        Analyzer a4=new WhitespaceAnalyzer(Version.LUCENE_35);
        String text="this is my house,i come from china,my email is mariatravelling@hotmail.com";
//        AnalyzerUtils.displayToken(text, a1);
//        AnalyzerUtils.displayToken(text, a2);
//        AnalyzerUtils.displayToken(text, a3);
//        AnalyzerUtils.displayToken(text, a4);
        
        AnalyzerUtils.displayAllTokenStream(text, a1);
//        AnalyzerUtils.displayAllTokenStream(text, a2);
//        AnalyzerUtils.displayAllTokenStream(text, a3);
//        AnalyzerUtils.displayAllTokenStream(text, a4);
        
	}
}
