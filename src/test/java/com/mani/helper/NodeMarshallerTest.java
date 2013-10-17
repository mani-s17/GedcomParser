package com.mani.helper;

import com.mani.entity.Node;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 */
public class NodeMarshallerTest extends TestCase
{
	// SAMPLE DATA
	/*
	* -1 GEDCOM
	* 0 @I1@ INDI
	* 1 NAME Jamis Gordon /Buck/
	* 2 SURN Buck
	* 2 GIVN Jamis Gordon
	* 1 SEX M
	* */

	// EXPECTED RESULT
	/*
	<gedcom>
		<indi id="@I1@">
			<name value="Jamis Gordon /Buck/">
				<surn>Buck</surn>
				<givn>Jamis Gordon</givn>
			</name>
			<sex>M</sex>
		</indi>
	</gedcom>
	*/

	public void testCreateOpenTag() throws Exception
	{
		Node node = new Node();
		node.setLevel(Constants.ROOT_LEVEL);
		node.setTag("gedcom");
		assertEquals("<gedcom>\n", NodeMarshaller.createOpenTag(node));

		node = new Node();
		node.setLevel(0);
		node.setId("@I1@");
		node.setTag("indi");
		assertNotSame("<indi id=\"@I1@\">", NodeMarshaller.createOpenTag(node));
	}

	public void testCreateCloseTag() throws Exception
	{
		Node node = new Node();
		node.setLevel(Constants.ROOT_LEVEL);
		node.setTag("gedcom");
		assertEquals("</gedcom>\n", NodeMarshaller.createCloseTag(node));

		node = new Node();
		node.setLevel(0);
		node.setId("@I1@");
		node.setTag("indi");
		assertNotSame("</indi>", NodeMarshaller.createCloseTag(node));
	}

	public void testCreateChildTag() throws Exception
	{
		Node node = new Node();
		node.setLevel(2);
		node.setTag("surn");
		node.setValue("Buck");
		assertEquals("\t\t\t<surn>Buck</surn>\n", NodeMarshaller.createChildTag(node));
		assertNotSame("<surn>Buck</surn>", NodeMarshaller.createChildTag(node));
	}
}
