package TeamProject;

import java.io.IOException;

public class TestIndex {

	public static void main(String[] args)
	{
		Index de=new Index();
		//Indexsfilepath is the path of Folder that is used to store index;
		String Indexfilepath="/Users/wangzehui/Documents/Solr/TestIndex";
		//Filesfilepath is the path of Folder that is used to store files which will be indexed
		String Filesfilepath="/Users/wangzehui/Documents/Solr/Test";
		try {
			de.IndexUtil(Indexfilepath,Filesfilepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
