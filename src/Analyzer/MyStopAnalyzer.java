package Analyzer;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;

public class MyStopAnalyzer extends Analyzer {

	//private Set stops;
	Set stops1=StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	Set stops;
	public MyStopAnalyzer()
	{
		
		//System.out.println(stops1);
	}
	public MyStopAnalyzer(String[] sws)
	{
		stops=StopFilter.makeStopSet(Version.LUCENE_35,sws,true);
		//stops1=StopAnalyzer.ENGLISH_STOP_WORDS_SET;
		System.out.println(stops1);
		stops.addAll(stops1);
	}
	@Override
	public TokenStream tokenStream(String arg0, Reader arg1) {
		// TODO Auto-generated method stub
		return new StopFilter(Version.LUCENE_35,
				new LetterTokenizer(Version.LUCENE_35,arg1),stops);
	}

	
}
