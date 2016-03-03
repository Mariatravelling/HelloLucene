package TeamProject;

import java.io.IOException;
public class TestSearch {

	public static void main(String[] args)
	{
	Search a=new Search();
	//you can type whatever you want to search!
	String query="Deloitte Talk";
	String[] fields={"title","content"};
	int FieldNum=fields.length;
	try {
	   a.executeSearch(FieldNum,query,fields);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
