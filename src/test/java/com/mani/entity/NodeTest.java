package com.mani.entity;

import com.mani.helper.Constants;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class NodeTest extends TestCase
{
	Node node;

	public void setUp() throws Exception
	{
		super.setUp();
		node = new Node(Constants.ROOT_LEVEL, "ID", "TAG", "VALUE");
	}

	public void testGetterSetter() throws Exception
	{
		assertEquals(Constants.ROOT_LEVEL, node.getLevel());
		assertEquals("ID", node.getId());
		assertEquals("TAG", node.getTag());
		assertEquals("VALUE", node.getValue());
		assertNotSame(false, node.isRootNode());
	}
}
