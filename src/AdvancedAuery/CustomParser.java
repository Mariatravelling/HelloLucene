package AdvancedAuery;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.function.FieldScoreQuery;
import org.apache.lucene.search.function.FieldScoreQuery.Type;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class CustomParser extends QueryParser {

	public CustomParser(Version matchVersion, String f, Analyzer a) {
		super(matchVersion, f, a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected org.apache.lucene.search.Query getFuzzyQuery(String field,
			String termStr, float minSimilarity) throws ParseException {
		// TODO Auto-generated method stub
		throw new ParseException("Becasause of performance,this function has been forbidden");
	}

	@Override
	protected org.apache.lucene.search.Query getWildcardQuery(String field,
			String termStr) throws ParseException {
		// TODO Auto-generated method stub
		throw new ParseException("Becasause of performance,this function has been forbidden");
	}

	@Override
	protected org.apache.lucene.search.Query getRangeQuery(String arg0,
			String arg1, String arg2, boolean arg3) throws ParseException {
		// TODO Auto-generated method stub
		if(arg0.equals("size"))
		{
			return NumericRangeQuery.newIntRange(arg0, Integer.parseInt(arg1),Integer.parseInt(arg2), arg3, arg3);
		}
		else if(arg0.equals("date"))
		{
			String dateType="yyyy-mm-dd";
			Pattern pattern=Pattern.compile("\\d(4)-\\d(2)-\\d(2)");
			if(pattern.matcher(arg1).matches()&&pattern.matcher(arg2).matches())
			{
				SimpleDateFormat sdf=new SimpleDateFormat(dateType);
				try {
					long start=sdf.parse(arg1).getTime();
					long end=sdf.parse(arg2).getTime();
					return NumericRangeQuery.newLongRange(arg0,start,end,arg3,arg3);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
			
		}
		return super.newRangeQuery(arg0, arg1, arg2, arg3);
	}


	

	
	
}
