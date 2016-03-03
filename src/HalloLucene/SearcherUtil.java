package HalloLucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SearcherUtil {

	private Directory directory;
	private IndexReader reader;
	
	public SearcherUtil()
	{
		try {
			directory=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IndexSearcher getSearcher()
	{
		if(reader==null)
			try {
				reader=IndexReader.open(directory);
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
		{
			IndexReader tr;
			try {
				tr = IndexReader.openIfChanged(reader);
				if(tr!=null)
				{
					reader.close();
					reader=tr;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return new IndexSearcher(reader);
	}
	
	public void searcherByTerm(String field,String name,int num)
	{
		IndexSearcher searcher=getSearcher();
		Query query=new TermQuery(new Term(field,name));
		try {
			TopDocs tds=searcher.search(query, num);
			System.out.println("total queries"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document doc=searcher.doc(sd.doc);
				System.out.println(doc.get("filename"));
				System.out.println(doc.get("content"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searcherByTermRange(String field,String start,String end,int num)
	{
		IndexSearcher searcher=getSearcher();
		Query query=new TermRangeQuery(field,start,end,true,true);
		try {
			TopDocs tds=searcher.search(query,num);
			System.out.println("total queries"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document doc=searcher.doc(sd.doc);
				System.out.println(doc.get("filename"));
				//System.out.println(doc.get("content"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searcherByNumericRange(String field,int start,int end,int num)
	{
		IndexSearcher searcher=getSearcher();
		Query query=NumericRangeQuery.newIntRange(field,start,end,true,true);
		try {
			TopDocs tds=searcher.search(query,num);
			System.out.println("total queries"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document doc=searcher.doc(sd.doc);
				System.out.println(doc.get("filename"));
				//System.out.println(doc.get("content"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searcherByPrefix(String field,String value,int num)
	{
		IndexSearcher searcher=getSearcher();
		Query query=new PrefixQuery(new Term(field,value));
		try {
			TopDocs tds=searcher.search(query,num);
			System.out.println("total queries"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document doc=searcher.doc(sd.doc);
				System.out.println(doc.get("filename"));
				//System.out.println(doc.get("content"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searcherByWildcard(String field,String value,int num)
	{
		IndexSearcher searcher=getSearcher();
		Query query=new WildcardQuery(new Term(field,value));
		try {
			TopDocs tds=searcher.search(query,num);
			System.out.println("total queries"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document doc=searcher.doc(sd.doc);
				System.out.println(doc.get("filename"));
				//System.out.println(doc.get("content"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searcherByBoolean(int num)
	{
		IndexSearcher searcher=getSearcher();
	    BooleanQuery query=new BooleanQuery();
	    query.add(new TermQuery(new Term("filename","test1")), Occur.MUST);
		try {
			TopDocs tds=searcher.search(query,num);
			System.out.println("total queries"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document doc=searcher.doc(sd.doc);
				System.out.println(doc.get("filename"));
				//System.out.println(doc.get("content"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
