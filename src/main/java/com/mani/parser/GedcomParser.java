package com.mani.parser;

import java.util.ArrayList;
import java.util.List;

import com.mani.entity.Node;
import com.mani.exceptions.TreeException;
import com.mani.helper.Constants;
import com.mani.helper.NodeMarshaller;
import com.mani.helper.TreeCreator;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */

/**
 * GEDCOM is the "GEnealogical Data COMmunication" file format. It is a plain-text
 * electronic format used to transfer genealogical data.
* */
public class GedcomParser extends AbstractParser
{
	Node root;

	public GedcomParser()
	{
		root = new Node();
		root.setLevel(Constants.ROOT_LEVEL);
		root.setTag("gedcom");
	}

	@Override
	public List<Node> convertRawDataToNode(List<String> lines)
	{
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(root);
		for(String line : lines)
		{
			if(line != null && line.trim().compareTo("") != 0)
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

	@Override
	public String xmlMarshaller(List<Node> nodes) throws TreeException
	{
		Node root = TreeCreator.create(nodes);
		return doMarshall(root);
	}

	private String doMarshall(Node node)
	{
		StringBuilder xml = new StringBuilder();
		List<Node> childNodes = node.getChildNodes();

		if (childNodes.isEmpty())
		{
			xml.append(NodeMarshaller.createChildTag(node));
		}
		else
		{
			xml.append(NodeMarshaller.createOpenTag(node));

			for (Node childNode : childNodes)
			{
				String childXml = doMarshall(childNode);
				xml.append(childXml);
			}
			xml.append(NodeMarshaller.createCloseTag(node));
		}
		return xml.toString();
	}
}
