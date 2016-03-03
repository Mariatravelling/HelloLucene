package HalloLucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.CachingCollector;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.wordnet.Syns2Index;

public class indexSyns {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if(args.length!=2)
		{
			System.out.println("java org.apache.lucene.wordnet.SysLookup");
		}
		FSDirectory directory=FSDirectory.open(new File(args[0]));
		@SuppressWarnings("deprecation")
		IndexSearcher searcher=new IndexSearcher(directory);
		String word=args[1];
		Query query=new TermQuery(new Term(Syns2Index.F_WORD,word));
	//	Collector collector=new Collector();
	}

}
