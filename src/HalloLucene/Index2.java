package HalloLucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Index2 {

	public static void main(String[] args) throws IOException 
	{
		Directory directory=FSDirectory.open(new File("/Users/wangzehui/Documents/Solr/TestIndex"));
		IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35));
		IndexWriter writer=null;
		File f=new File("/Users/wangzehui/Documents/Solr/Test/testData");
		try {
			writer=new IndexWriter(directory,iwc);
		InputStreamReader isr=null;
		for(File file:f.listFiles())
		{
			Document doc=new Document();
			doc.add(new Field("Content",new FileReader(file)));
			doc.add(new Field("Name",file.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED));
			doc.add(new Field("Path",file.getAbsolutePath(),Field.Store.YES,Field.Index.NOT_ANALYZED));
			doc.add(new NumericField("Date",Field.Store.YES,true).setIntValue((int)file.lastModified()));
			doc.add(new NumericField("Size",Field.Store.YES,true).setIntValue((int)(file.length())));
			writer.addDocument(doc);
			System.out.println(doc.get("Content"));
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(writer!=null)
			{
				writer.close();
			}
		}
	}
}
