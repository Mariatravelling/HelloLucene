package HalloLucene;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class Search {
	
	private Directory directory=null;
	private IndexWriter writer=null;
	private static IndexReader reader=null;
	private Date[] dates=null;
	private Map<String,Float> scores=new HashMap<String,Float>();
	
	public Search()//Constructor function
	{
		try {
			//setDates();
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
	
	public void Searcher()throws IOException
	{
	try {
		IndexSearcher searcher=getSearcher();
		QueryParser parser=new QueryParser(Version.LUCENE_35,"content",new StandardAnalyzer(Version.LUCENE_35));
		//TermQuery query=new TermQuery(new Term("content","love"));
		try {
			Query query=parser.parse("love");
			TopDocs tds=searcher.search(query, 3);
			ScoreDoc[] sds=tds.scoreDocs;
			for(ScoreDoc sd:sds)
			{
				Document d=searcher.doc(sd.doc);
				System.out.println(d.get("filename")+d.get("path")+d.get("content"));
			}
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searcher.close();
		//reader.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void Delete() throws CorruptIndexException, LockObtainFailedException, IOException
	{
		
		writer.forceMergeDeletes();
		System.out.println("love");
	}
	
	public void Merge()
	{
		
		try {
			writer.forceMerge(1);
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(writer!=null)
				try {
					writer.close();
				} catch (CorruptIndexException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	public void Query()
	{
			IndexReader reader;
			try {
				reader = IndexReader.open(directory);
				System.out.println("maxDocs:"+reader.maxDoc());
				System.out.println("numDocs:"+reader.numDocs());
				System.out.println("deleteDocs:"+reader.numDeletedDocs());
				reader.close();
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}	
}
	

