package com.mani.helper;

import com.mani.entity.Node;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 */

/**
 * Helper class to Marshal processed node to XML Tags
* */
public class NodeMarshaller
{
	public static String createOpenTag(Node node)
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

	public static String createCloseTag(Node node)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getIntention(node));
		stringBuilder.append("</" + node.getTag() + ">\n");
		return stringBuilder.toString();
	}

	public static String createChildTag(Node node)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getIntention(node));
		stringBuilder.append("<" + node.getTag() + ">");
		if(node.getValue() != null)
			stringBuilder.append(node.getValue());
		stringBuilder.append("</" + node.getTag() + ">\n");
		return stringBuilder.toString();
	}

	private static String getIntention(Node node)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (int i=0; i< node.getLevel()+1; i++)
		{
			stringBuilder.append("\t");
		}
		return stringBuilder.toString();
	}
}
