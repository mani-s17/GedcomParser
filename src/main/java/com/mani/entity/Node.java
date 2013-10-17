package com.mani.entity;

import java.util.ArrayList;
import java.util.List;

import com.mani.helper.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */

/**
 * Model which holds all information related to xml file
 */
public class Node
{
	private int level;
	private String id;
	private String tag;
	private String value;

	private Node parentNode;
	private List<Node> childNodes = new ArrayList<Node>();

	public Node()
	{
	}

	public Node(int level, String id, String tag, String value)
	{
		this.level = level;
		this.id = id;
		this.tag = tag;
		this.value = value;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Node getParentNode()
	{
		return parentNode;
	}

	public void setParentNode(Node parentNode)
	{
		this.parentNode = parentNode;
	}

	public List<Node> getChildNodes()
	{
		return childNodes;
	}

	public void addChildNode(Node nodes)
	{
		this.childNodes.add(nodes);
	}

	public boolean isRootNode()
	{
		return this.level == Constants.ROOT_LEVEL;
	}
}
