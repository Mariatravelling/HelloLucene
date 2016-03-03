package AdvancedAuery;

public class TestCustomParser {

	public static void main(String[] args)
	{
		
		CustomParserQuery cpq=new CustomParserQuery();
		//cpq.searchByCustomParser("*love");
		cpq.searchByCustomParser("content:[i TO j ]");
	}
}
