package Analyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

public class MySameTokenFilter extends TokenFilter {
	
	private CharTermAttribute cta=null;
	private PositionIncrementAttribute pia=null;
	private AttributeSource.State current;
	private Stack<String> sames=null;

	protected MySameTokenFilter(TokenStream input) {
		super(input);
		// TODO Auto-generated constructor stub
		cta=this.addAttribute(CharTermAttribute.class);
		pia=this.addAttribute(PositionIncrementAttribute.class);
		sames=new Stack<String>();
	}

	@Override
	public boolean incrementToken() throws IOException {
		// TODO Auto-generated method stub
		if(!this.input.incrementToken()) return false;
		while(sames.size()>0)
		{
			String str=sames.pop();
			restoreState(current);
			cta.setEmpty();
			cta.append(str);
			pia.setPositionIncrement(0);
			return true;
		}
		if(getSameWords(cta.toString()))
		{
			current=captureState();
		}
		/*String[] sws=getSameWords(cta.toString());
		if(sws!=null)
		{
			current=captureState();
			for(String s:sws)
			{
				cta.setEmpty();
				cta.append(s);
			}
		}*/
		return true;
	}

	public boolean getSameWords(String name)
	{
		Map<String,String[]> maps=new HashMap<String,String[]>();
		maps.put("love",new String[] {"like","would like"});
		String[] sws=maps.get(name);
		if(sws!=null)
		{
			for(String str:sws)
			{
				sames.push(str);
			}
			return true;
		}
		return false;
	}
}
