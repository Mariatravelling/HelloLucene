package TeamProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;


public class Index {

	public void IndexUtil(String Indexfilepath,String Filesfilepath) throws IOException
	{
		Directory directory=FSDirectory.open(new File(Indexfilepath));
		IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35));
		IndexWriter writer=null;
		int j=0;
		try {
			writer=new IndexWriter(directory,iwc);
			Document doc=null;
			File f=new File(Filesfilepath);
			Tika tika=new Tika();
			for(File file:f.listFiles())
			{
				
				j=j+1;
				String i=""+j;
				System.out.println(j);
				doc=new Document();
				Metadata metadata=new Metadata();
				doc.add(new Field("id",i,Field.Store.YES,Field.Index.NOT_ANALYZED));
				doc.add(new Field("content",tika.parse(new FileInputStream(file),metadata)));
				doc.add(new Field("filename",file.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED));
				doc.add(new Field("title", FilenameUtils.getBaseName(file.getName()),Field.Store.YES,Field.Index.ANALYZED));
				doc.add(new Field("type", FilenameUtils.getExtension(file.getName()),Field.Store.YES,Field.Index.NOT_ANALYZED));
				doc.add(new Field("path",file.getAbsolutePath(),Field.Store.YES,Field.Index.NOT_ANALYZED));
				doc.add(new NumericField("date",Field.Store.YES,true).setLongValue(file.lastModified()));
				doc.add(new NumericField("size",Field.Store.YES,true).setIntValue((int) file.length()));
				writer.addDocument(doc);
				//String str=doc.get("title")+"+"+doc.get("date");			
			}
			
			System.out.println("Totally "+j+" Files has been indexed successfully! ");
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
}
