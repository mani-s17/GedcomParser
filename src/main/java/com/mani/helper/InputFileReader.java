package com.mani.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */

/**
 * Contains helper methods to load raw input data from file to memory
 */
public class InputFileReader
{

	/**
	 * Method for reading input file and load each line of file into list.
	 * File content should not be greater than Maxvalue of Integer (2147483647)
	 */
	public List<String> readFile(String filePath)
	{
		List<String> fileStrings = new ArrayList<String>();

		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(filePath));
			String line;
			int lineCount = 0;
			while ((line = br.readLine()) != null && lineCount < Integer.MAX_VALUE)
			{
				fileStrings.add(line);
				lineCount++;
			}
		}
		catch (IOException e)
		{
			System.err.println("Unable to read file " + filePath);
			e.printStackTrace();
			System.exit(0);
		}
		catch (NullPointerException e)
		{
			System.err.println("File not found. Check file path " + filePath);
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					System.err.println("Unable to close file " + filePath);
					e.printStackTrace();
				}
			}
		}
		return fileStrings;
	}
}
