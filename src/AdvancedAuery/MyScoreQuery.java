package AdvancedAuery;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.Weight;
import org.apache.lucene.search.function.CustomScoreProvider;
import org.apache.lucene.search.function.CustomScoreQuery;
import org.apache.lucene.search.function.FieldScoreQuery;
import org.apache.lucene.search.function.FieldScoreQuery.Type;
import org.apache.lucene.search.function.ValueSourceQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.ScoreDoc;
public class MyScoreQuery {
 
	private Directory dir;
	public MyScoreQuery()
	{
		try {
			dir=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void searchByScoreQuery()
	{
		IndexReader reader;
		try {
			reader = IndexReader.open(dir);
			IndexSearcher searcher=new IndexSearcher(reader);
			Query q=new TermQuery(new Term("content","love"));
			FieldScoreQuery fd=new FieldScoreQuery("score",Type.INT);
			MyCustomScoreQuery query=new MyCustomScoreQuery(q,fd);
			TopDocs tds=null;
			tds=searcher.search(query, 50);
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document d=searcher.doc(sd.doc);
				System.out.println(d.get("filename")+d.get("score"));
				
			}
			reader.close();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void searchByFileScoreQuery()

	{
		IndexReader reader;
		try {
			reader = IndexReader.open(dir);
			IndexSearcher searcher=new IndexSearcher(reader);
			Query q=new TermQuery(new Term("content","love"));
			FilenameScoreQuery query=new FilenameScoreQuery(q);
			TopDocs tds=null;
			tds=searcher.search(query, 50);
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document d=searcher.doc(sd.doc);
				System.out.println(d.get("filename")+d.get("score"));
				
			}
			reader.close();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private class FilenameScoreQuery extends CustomScoreQuery
	{
		public FilenameScoreQuery(Query subQuery) {
			super(subQuery);
			// TODO Auto-generated constructor stub
		}
		@Override
		protected CustomScoreProvider getCustomScoreProvider(IndexReader reader)
				throws IOException {
			// TODO Auto-generated method stub
			return new FilenameScoreProvider(reader);
		}
	}
	
	private class DateScoreProvider extends CustomScoreProvider
	{
        long[] dates=null;
		public DateScoreProvider(IndexReader reader) {
			super(reader);
			try {
				dates=FieldCache.DEFAULT.getLongs(reader, "date");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated constructor stub
		}
		
		public float customScore(int doc, float subQueryScore, float valSrcScore)
				throws IOException {
			// TODO Auto-generated method stub
			long date=dates[doc];
			long today=new Date().getTime();
			long year=1000*60*60*24*365;
			if(today-date<year)
			{
				return subQueryScore*15f;
			}
			return subQueryScore;
		}
		
		
	}
	private class FilenameScoreProvider extends CustomScoreProvider
	{
		String[] filenames=null;
		public FilenameScoreProvider(IndexReader reader) {
			super(reader);
			try {
				filenames=FieldCache.DEFAULT.getStrings(reader, "filename");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// TODO Auto-generated constructor stub
		}

		@Override
		public float customScore(int doc, float subQueryScore, float valSrcScore)
				throws IOException {
			// TODO Auto-generated method stub
			float score=subQueryScore;
			String filename=filenames[doc];
	        if(filename.endsWith(".h") || filename.endsWith(".txt") )
			{
				return score*1.5f;
			}
			return score;
		}
	}
	
	public class MyCustomScoreQuery extends CustomScoreQuery
	{
		
		public MyCustomScoreQuery(Query subQuery, ValueSourceQuery valSrcQuery) {
			super(subQuery, valSrcQuery);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected CustomScoreProvider getCustomScoreProvider(IndexReader reader)
				throws IOException {
			// TODO Auto-generated method stub
			return super.getCustomScoreProvider(reader);
		}

		
	}
	
	
}

