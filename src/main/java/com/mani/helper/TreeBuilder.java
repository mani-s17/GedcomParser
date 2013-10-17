package com.mani.helper;

import java.util.List;
import java.util.Stack;

import com.mani.entity.Node;
import com.mani.exceptions.TreeException;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 */

/**
 * Helper class to build a hierarchy tree based on levels of each node
 */
public class TreeBuilder
{
	/**
	 * @param nodes in order and links to them each other based on level of each node
	 * @return root node
	 */
	public static Node buildTree(List<Node> nodes) throws TreeException
	{
		if (nodes.isEmpty())
		{
			throw new TreeException("No nodes found to build tree");
		}

		if (!nodes.get(0).isRootNode())
		{
			throw new TreeException("First node should be root node with level -1");
		}

		Node rootNode = nodes.get(0);
		Stack<Node> rootStack = new Stack<Node>();

		Node previousNode = rootNode;
		int previousLevel = rootNode.getLevel();

		for (int i = 1; i < nodes.size(); i++)
		{
			Node currentNode = nodes.get(i);
			int currentLevel = currentNode.getLevel();

			if (currentLevel > previousLevel)
			{
				// if level increases push the previous node to root stack
				rootStack.push(previousNode);
			}
			else if (currentLevel < previousLevel)
			{
				// if level decreases, pop the current node
				while (currentLevel <= rootStack.peek().getLevel())
				{
					rootStack.pop();
				}
			}

			Node tempRoot = rootStack.peek();
			currentNode.setParentNode(tempRoot);
			tempRoot.addChildNode(currentNode);

			previousNode = currentNode;
			previousLevel = currentLevel;
		}
		return rootNode;
	}
}
