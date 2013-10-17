package com.mani.parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
	public void xmlMarshaller(List<Node> nodes, BufferedWriter bw) throws IOException
	{
		Stack<Node> nodeStack = new Stack<Node>();
		Queue<Node> nodeQueue = new LinkedList<Node>();

		bw.append(getOpenNode(root));
		Node current, previous = root;

		for(Node node : nodes)
		{
			current = node;
			if(nodeStack.isEmpty() || current.getLevel() > previous.getLevel())
			{
				nodeQueue.add(current);
				nodeStack.push(current);
			}
			else if(current.getLevel() == previous.getLevel())
			{
				while (!nodeQueue.isEmpty())
				{
					if(current.getLevel() != nodeQueue.peek().getLevel())
						bw.append(getOpenNode(nodeQueue.remove()));
					else
					{
						bw.append(getChildNode(nodeQueue.remove()));
						nodeStack.pop();
					}
				}

				bw.append(getChildNode(current));
			}
			else if(current.getLevel() < previous.getLevel())
			{
				while (!nodeStack.isEmpty() && current.getLevel() <= nodeStack.peek().getLevel())
				{
					bw.append(getCloseNode(nodeStack.pop()));
				}
				bw.append(getChildNode(current));
			}
			previous = current;
		}

		while (!nodeStack.isEmpty())
		{
			bw.append(getCloseNode(nodeStack.pop()));
		}

		bw.append(getCloseNode(root));
	}

	private String getOpenNode(Node node)
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

	private String getCloseNode(Node node)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getIntention(node));
		stringBuilder.append("</" + node.getTag() + ">\n");
		return stringBuilder.toString();
	}

	private String getChildNode(Node node)
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
}
