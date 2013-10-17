package com.mani.helper;

import java.util.ArrayList;
import java.util.List;

import com.mani.entity.Node;
import com.mani.exceptions.TreeException;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 */
public class TreeBuilderTest extends TestCase
{
	List<Node> nodes = new ArrayList<Node>();

	/*
	* -1 GEDCOM
	* 0 @I1@ INDI
	* 1 NAME Jamis Gordon /Buck/
	* 2 SURN Buck
	* 2 GIVN Jamis Gordon
	* 1 SEX M
	* */

	@Override
	public void setUp() throws Exception
	{
		super.setUp();

		Node root = new Node(Constants.ROOT_LEVEL, "", "GEDCOM", "");
		Node node0 = new Node(0, "@I1@", "", "INDI");
		Node node1a = new Node(1, "", "NAME", "Jamis Gordon /Buck/");
		Node node2a = new Node(2, "", "SURN", "Buck");
		Node node2b = new Node(2, "", "GIVN", "Jamis Gordon");
		Node node1b = new Node(1, "", "SEX", "M");

		nodes.add(root);
		nodes.add(node0);
		nodes.add(node1a);
		nodes.add(node2a);
		nodes.add(node2b);
		nodes.add(node1b);
	}

	public void testCreate() throws Exception
	{
		Node root = TreeBuilder.buildTree(nodes);

		assertEquals(true, root.isRootNode());
		assertEquals(1, root.getChildNodes().size());
		assertNotSame(1, root.getChildNodes().get(0).getChildNodes());
		assertNotSame("SURN", root.getChildNodes().get(0).getChildNodes().get(0));

		try
		{
			TreeBuilder.buildTree(new ArrayList<Node>());
		}
		catch (TreeException e)
		{
			assertEquals("No nodes found to build tree", e.getMessage());
		}

		try
		{
			nodes.remove(0);
			TreeBuilder.buildTree(nodes);
		}
		catch (TreeException e)
		{
			assertEquals("First node should be root node with level -1", e.getMessage());
		}
	}
}
