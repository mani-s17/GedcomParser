package com.mani.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class GedcomParser extends AbstractParser
{
	public GedcomParser(String filePath)
	{
		super(filePath);
	}

	@Override
	public List<Node> doParseTags(List<String> lines)
	{
		List<Node> nodes = new ArrayList<Node>();
		for(String line : lines)
		{
			if(!line.isEmpty())
			{
				String[] tokens = line.split("\\s+", 3);
				Node node = new Node();
				node.setLevel(Integer.parseInt(tokens[0]));
				if(tokens[1].startsWith("@") && tokens[1].endsWith("@"))
				{
					node.setId(tokens[1]);
					node.setTag(tokens[2].toLowerCase());
				}
				else if(tokens.length == 3)
				{
					node.setTag(tokens[1].toLowerCase());
					node.setValue(tokens[2]);
				}
				else
				{
					node.setTag(tokens[1].toLowerCase());
				}
				nodes.add(node);
			}
		}
		return nodes;
	}


}
