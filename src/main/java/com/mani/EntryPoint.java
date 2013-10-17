package com.mani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mani.exceptions.TreeException;
import com.mani.parser.GedcomParser;
import com.mani.parser.Parser;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */

/**
 * Entry point for Gedcom Parser / Any type of Parser
 */
public class EntryPoint
{
	public static void main(String[] args) throws TreeException, IOException
	{
		System.out.println("Enter absolute path of GEDCOM File : ");
		String inputFilePath = "";
		try
		{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			inputFilePath = bufferRead.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Parser parser = new GedcomParser();
		String outputFilePath = parser.doParse(inputFilePath);
		System.out.print("Output XML is stored in : " + outputFilePath);
	}
}
