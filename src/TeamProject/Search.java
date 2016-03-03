package TeamProject;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Search {
	
	private Directory directory=null;
	private IndexWriter writer=null;
	private static IndexReader reader=null;
	
	public Search()
	{
		try {
			//scores.put("love", 2.0f);
			directory=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
			writer=new IndexWriter(directory,new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35)));
		    reader=IndexReader.open(directory);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public IndexSearcher getSearcher()
	{
		if(reader==null) 
		{
			try {
				reader=IndexReader.open(directory);
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				IndexReader tr=IndexReader.openIfChanged(reader);
				if(tr!=null) 
					{
					reader.close();
					reader=tr;
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		return new IndexSearcher(reader);
	}
		return null;
	}
	
	public void executeSearch(int FieldNum,String keywords,String[] fields) throws IOException
	{
		IndexSearcher searcher=getSearcher();
		BooleanClause.Occur[] flags=new BooleanClause.Occur[FieldNum];
		try {
			QueryParser queryparser=new MultiFieldQueryParser(Version.LUCENE_35,fields,new StandardAnalyzer(Version.LUCENE_35));
			queryparser.setDefaultOperator(Operator.AND);
			Query query=queryparser.parse(keywords);
			queryparser.getFuzzyMinSim();
		    //Query query1=new FuzzyQuery(new Term(keywords));
			//Query query=MultiFieldQueryParser.parse(Version.LUCENE_35,keywords,fields,flags,new StandardAnalyzer(Version.LUCENE_35));
			TopDocs tds=searcher.search(query,100);
			int ResultNum=tds.totalHits;
			if(ResultNum==0)
			{
				System.out.println("Sorry,there is no result about what you want,please try other words");
			}
			else
			{
			System.out.println("There are "+ResultNum+" results that contains what you search");
			}
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document doc=searcher.doc(sd.doc);
				System.out.println(doc.get("filename"));
				//System.out.println(doc.get("content"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
