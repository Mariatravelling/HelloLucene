package HalloLucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class Index {
	
	private Map<String,Float> Scores=new HashMap<String,Float>();
	
	public void IndexUtil() throws IOException
	{
		//Scores.put("love", 2.0f);
		Directory directory=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
		IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35));
		IndexWriter writer=null;
		int j=0;
		try {
			writer=new IndexWriter(directory,iwc);
			Document doc=null;
			File f=new File("/Users/wangzehui/Documents/Solr/Test");
			Random ran=new Random();
			for(File file:f.listFiles())
			{
				int score=ran.nextInt(400);
				j=j+1;
				System.out.println(j);
				doc=new Document();
				doc.add(new Field("content",new FileReader(file)));
				doc.add(new Field("filename",file.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED));
				doc.add(new Field("path",file.getAbsolutePath(),Field.Store.YES,Field.Index.NOT_ANALYZED));
				doc.add(new NumericField("date",Field.Store.YES,true).setLongValue(f.lastModified()));
				doc.add(new NumericField("size",Field.Store.YES,true).setIntValue((int) f.length()));
				doc.add(new NumericField("score",Field.Store.YES,true).setIntValue(score));
				writer.addDocument(doc);
				String str=doc.get("filename")+"+++++"+doc.get("score");
				
				//i=content.indexOf("love");
				System.out.println(str);
				
			}
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
	/*public void Search()
	{
		File f=new File("/Users/wangzehui/Documents/Solr/Test");
		int j=0;
		Document doc=null;
		for(File file:f.listFiles())
		{
			j=j+1;
			System.out.println(j);
			doc=new Document();
			String str=doc.get("filename");
			//i=content.indexOf("love");
			System.out.println(str);
		}
	}*/
	
	private ScoreDoc getLastScoreDoc(int pageIndex,int pageSize,Query query,IndexSearcher searcher)
	{
		if(pageIndex==1) return null;
		int num=pageSize*(pageIndex-1);
		TopDocs tds = null;
		try {
			tds = searcher.search(query,num);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tds.scoreDocs[num-1];
	}
	public void SearchPageByAfter(String query,int pageIndex,int pageSize)throws IOException
	{
	try {
		Directory directory=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
		IndexReader reader=IndexReader.open(directory);
		IndexSearcher searcher=new IndexSearcher(reader);
		QueryParser parser=new QueryParser(Version.LUCENE_35,"content",new StandardAnalyzer(Version.LUCENE_35));
		Query q;
		try {
			q = parser.parse(query);
			TopDocs tds=searcher.search(q, 50);
			//ScoreDoc[] sds=tds.scoreDocs;
			ScoreDoc last=getLastScoreDoc(pageIndex,pageSize,q,searcher);
			tds=searcher.searchAfter(last, q, pageSize);
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
