package com.mani.helper;

import java.util.List;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class InputFileReaderTest extends TestCase
{
	InputFileReader inputFileReader;

	/*
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
		inputFileReader = new InputFileReader();
	}

	public void testReadFile() throws Exception
	{
		List<String> rawDataList = inputFileReader.readFile(this.getClass().getResource("/SampleData.txt").getPath());
		assertEquals("0 @I1@ INDI", rawDataList.get(0));
		assertEquals("2 SURN Buck", rawDataList.get(2));
		assertNotSame("2 SURN Buck", rawDataList.get(3));

		try
		{
			rawDataList = inputFileReader.readFile(this.getClass().getResource("/noFile.txt").getPath());
		}
		catch (NullPointerException e)
		{
			assertTrue(true);
		}
	}
}
