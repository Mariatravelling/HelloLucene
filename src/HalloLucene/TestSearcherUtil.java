package HalloLucene;

public class TestSearcherUtil {

	public static void main(String[] args)
	
	{
	SearcherUtil b=new SearcherUtil();
	b.searcherByTerm("content", "love", 7);
	b.searcherByTermRange("filename","t", "3", 4);
	}
}
