package HalloLucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher2 {
	
	public static void mian(String[] args) throws ParseException
	{
		try{
			Directory directory=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
			IndexWriterConfig iws=new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35));
			IndexWriter writer=new IndexWriter(directory,iws);
			IndexReader reader=IndexReader.open(directory);
			IndexSearcher searcher=new IndexSearcher(reader);
			QueryParser parser=new QueryParser(Version.LUCENE_35,"Content",new StandardAnalyzer(Version.LUCENE_35));
			Query query=parser.parse("love");
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
