package HalloLucene;

import java.io.IOException;

public class TestIndex {
	public static void main(String[] args)
	{
		Index h=new Index();
		try {
		h.IndexUtil();
		//h.SearchPageByAfter("love", 1, 10);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
