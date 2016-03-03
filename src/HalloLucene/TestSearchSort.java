package HalloLucene;

import java.io.IOException;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

public class TestSearchSort {

	public static void main(String[] args)
	{
	SearchSort s=new SearchSort();
	try {
		//通过默认评分排序
		s.SearchSort("love", Sort.RELEVANCE);
		//通过doc的id排序
		s.SearchSort("love", Sort.INDEXORDER);
		//通过文件大小排序
		s.SearchSort("love", new Sort(new SortField("size",SortField.INT)));
		//通过文件名排序
		s.SearchSort("love", new Sort(new SortField("filename",SortField.STRING)));
		//通过日期排序
		s.SearchSort("love", new Sort(new SortField("date",SortField.LONG)));
		//通过设置SortField最后一个参数设置是否反转排序
		s.SearchSort("love", new Sort(new SortField("filename",SortField.STRING,true)));
		//先通过文件大小排序，在文件大小一样时再通过评分排序
		s.SearchSort("love", new Sort(new SortField("size",SortField.INT),SortField.FIELD_SCORE));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
