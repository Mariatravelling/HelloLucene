package HalloLucene;

import java.io.IOException;

import org.apache.commons.io.filefilter.WildcardFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermRangeFilter;

public class TestSearcherFilter {

	public static void main(String[] args)
	{
		
		try {
			Filter tr=new TermRangeFilter("content","i","Beijing",true,true);
//			tr=NumericRangeFilter.newIntRange("size", "1000", "1200", true, true);
//			tr=new QueryWrapperFilter(new WildcardQuery(new Term("filename",".test")));
			SearcherFilter sf=new SearcherFilter();
			sf.SearchFilter("test1", tr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
