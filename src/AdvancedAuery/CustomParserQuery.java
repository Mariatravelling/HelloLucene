package AdvancedAuery;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class CustomParserQuery {

	public void searchByCustomParser(String value)
	{
		IndexReader reader;
		try {
			Directory dir=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
			reader = IndexReader.open(dir);
			IndexSearcher searcher=new IndexSearcher(reader);
			CustomParser parser=new CustomParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
			Query q;
			try {
				q = parser.parse(value);
				TopDocs tds=null;
				tds=searcher.search(q, 50);
				for(ScoreDoc sd:tds.scoreDocs)
				{
					Document d=searcher.doc(sd.doc);
					System.out.println(d.get("filename")+d.get("score"));
					
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
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
}
