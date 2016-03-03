package HalloLucene;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearchSort {

	private Directory directory;
	private IndexReader reader;
	
	public SearchSort()
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
	
	public void SearchSort(String query,Sort sort)throws IOException
	{
	try {
		Directory directory=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
		IndexReader reader=IndexReader.open(directory);
		IndexSearcher searcher=new IndexSearcher(reader);
		QueryParser parser=new QueryParser(Version.LUCENE_35,"content",new StandardAnalyzer(Version.LUCENE_35));
		try {
			Query q = parser.parse(query);
			TopDocs tds=searcher.search(q, 5,sort);
			int i=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			for(ScoreDoc sd:tds.scoreDocs)
			{
				Document d=searcher.doc(sd.doc);
				i=i+1;
				System.out.println(d.get("filename")+d.get("path")+d.get("size")+i);
				System.out.println(sdf.format(new Date(Long.valueOf(d.get("date")))));
			}
			searcher.close();
			//reader.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}	
}
