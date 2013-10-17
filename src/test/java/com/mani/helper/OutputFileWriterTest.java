package com.mani.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 */
public class OutputFileWriterTest extends TestCase
{
	OutputFileWriter outputFileWriter;
	File file;

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

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		outputFileWriter = new OutputFileWriter();
		file = new File(this.getClass().getResource("/SampleData.txt").getPath().split(Constants.FILE_EXTENSION_SEPARATOR)[0] + "_test.xml");
	}

	@Override
	public void tearDown() throws Exception
	{
		super.tearDown();
		file.deleteOnExit();
		;
	}

	public void testWriteFile() throws Exception
	{
		String expectedContent = "<gedcom>\n" +
				"\t<indi id=\"@I1@\">\n" +
				"\t\t<name value=\"Jamis Gordon /Buck/\">\n" +
				"\t\t\t<surn>Buck</surn>\n" +
				"\t\t\t<givn>Jamis Gordon</givn>\n" +
				"\t\t</name>\n" +
				"\t\t<sex>M</sex>\n" +
				"\t</indi>\n" +
				"</gedcom>";

		outputFileWriter.writeFile(file.getPath(), expectedContent);

		BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
		StringBuffer fileData = new StringBuffer(1000);
		String line;
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = br.read(buf)) != -1)
		{
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		assertEquals(expectedContent, fileData.toString());
	}
}
