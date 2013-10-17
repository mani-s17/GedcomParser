package com.mani.helper;

import java.util.List;
import java.util.Stack;

import com.mani.exceptions.TreeException;
import com.mani.parser.Node;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 */
public class TreeCreator
{
	// TODO add more error handling like the level are not proper/ especially ordering etc.

	/**
	 * Takes the raw nodes (in order) and links to them each other based on the level.
	 * thus forming a tree.
	 * @param nodes
	 * @return RootNode of the tree.
	 */
	public static Node create(Node root, List<Node> nodes) throws TreeException
	{
		if (nodes.isEmpty())
			throw new TreeException("node list cannot be empty");

		Node rootNode = root;
		if (rootNode.getLevel() != -1)
			throw new TreeException("first node should be the root node");

		Stack<Node> rootStack = new Stack<Node>();

		Node previousNode = rootNode;
		int previousLevel = -1;

		for (int i = 0; i < nodes.size(); i++)
		{
			Node presentNode = nodes.get(i);
			int presentLevel = presentNode.getLevel();

				// if level increases push the previous element to root stack
			if (presentLevel > previousLevel) {
				rootStack.push(previousNode);
			}

			// if level decreases, pop the current root.
			else if (presentLevel < previousLevel)
			{
				while(presentLevel <= rootStack.peek().getLevel())
				{
					rootStack.pop();
				}
			}

			// get the current root.
			Node currentRoot = rootStack.peek();
			// set the root.
			presentNode.setParentNode(currentRoot);
			// add child to the root nodes.
			currentRoot.addChildNode(presentNode);

			previousLevel = presentLevel;
			previousNode = presentNode;
		}
		return rootNode;
	}
}
