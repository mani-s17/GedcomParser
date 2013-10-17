package com.mani.parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.mani.exceptions.MarshalException;
import com.mani.exceptions.TreeException;
import com.mani.helper.TreeCreator;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class GedcomParser extends AbstractParser
{
	Node root;

	public GedcomParser(String filePath)
	{
		super(filePath);
		root = new Node();
		root.setLevel(-1);
		root.setTag("gedcom");
	}

	@Override
	public List<Node> doParseNodes(List<String> lines)
	{
		List<Node> nodes = new ArrayList<Node>();
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
	public void xmlMarshaller(List<Node> nodes, BufferedWriter bw) throws IOException, TreeException, MarshalException
	{
		Node root = TreeCreator.create(this.root, nodes);

		if (!isRootNode(root))
			throw new MarshalException("No root node found");

		bw.write(encodeNode(root));
	}

	private String encodeNode(Node node)
	{
		StringBuilder xml = new StringBuilder();
		List<Node> childNodes = node.getChildNodes();

		if (childNodes.isEmpty())
		{
			xml.append(createChildTag(node));
		}
		else
		{
			xml.append(createOpenTag(node));

			for (Node childNode : childNodes)
			{
				String childXml = encodeNode(childNode);
				xml.append(childXml);
			}

			xml.append(createCloseTag(node));
		}

		return xml.toString();
	}

	private String createOpenTag(Node node)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getIntention(node));
		stringBuilder.append("<" + node.getTag());

		if(node.getId() != null)
			stringBuilder.append(" id=\"" + node.getId() + "\"");
		if(node.getValue() != null)
			stringBuilder.append(" value=\"" + node.getValue() + "\"");

		stringBuilder.append(">\n");
		return stringBuilder.toString();
	}

	private String createCloseTag(Node node)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getIntention(node));
		stringBuilder.append("</" + node.getTag() + ">\n");
		return stringBuilder.toString();
	}

	private String createChildTag(Node node)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getIntention(node));
		stringBuilder.append("<" + node.getTag() + ">");
		if(node.getValue() != null)
			stringBuilder.append(node.getValue());
		stringBuilder.append("</" + node.getTag() + ">\n");
		return stringBuilder.toString();
	}

	private String getIntention(Node node)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (int i=0; i< node.getLevel()+1; i++)
		{
			stringBuilder.append("\t");
		}
		return stringBuilder.toString();
	}

	private boolean isRootNode(Node node)
	{
		return node.getLevel() == -1;
	}
}
