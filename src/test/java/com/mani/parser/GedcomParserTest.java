package com.mani.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.mani.helper.Constants;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class GedcomParserTest extends TestCase
{
	Parser parser;
	File inputFile, outputFile, expectedFile;

	String inputFileName1 = this.getClass().getResource("/SampleData.txt").getPath();
	String inputFileName2 = this.getClass().getResource("/CompleteData.txt").getPath();

	public void setUp() throws Exception
	{
		super.setUp();
		parser = new GedcomParser();
		inputFile = new File(inputFileName1);
		outputFile = new File(inputFileName1.split(Constants.FILE_EXTENSION_SEPARATOR)[0] + ".xml");
		expectedFile = new File(inputFileName1.split(Constants.FILE_EXTENSION_SEPARATOR)[0] + "_Expected.xml");
	}

	public void testDoParse() throws Exception
	{
		parser.doParse(inputFileName1);
		parser.doParse(inputFileName2);
		assertEquals(true, compareFiles(expectedFile, outputFile));
	}

	private boolean compareFiles(File expectedFile, File actualFile) throws IOException
	{
		BufferedReader expFileReader = new BufferedReader(new FileReader(expectedFile));
		BufferedReader actFileReader = new BufferedReader(new FileReader(actualFile));

		boolean isEqual = true;

		char[] chars1 = new char[1024];
		char[] chars2 = new char[1024];

		while (true)
		{
			int len1 = expFileReader.read(chars1);
			int len2 = actFileReader.read(chars2);

			if (len1 != len2)
			{
				isEqual = false;
				break;
			}

			if (len1 < 0)
			{
				break;
			}

			for (int i = 0; i < chars1.length; i++)
			{
				if (chars1[i] != chars2[i])
				{
					isEqual = false;
					break;
				}
			}
		}
		actFileReader.close();
		expFileReader.close();

		return isEqual;
	}
}
