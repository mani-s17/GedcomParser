package com.mani.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class InputFileReader
{

	/**
	 * Method to read input file and load each line into list
	 * @param filePath
	 * */
	public List<String> readFile(String filePath, int limitCount)
	{
		List<String> fileStrings = new ArrayList<String>();

		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(filePath));
			String line;
			int lineCount = 0;
			while ((line = br.readLine()) != null && lineCount < limitCount)
			{
				fileStrings.add(line);
				lineCount++;
			}
		}
		catch (IOException e)
		{
			System.err.println("Unable to read file " + filePath);
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			System.err.println("Unable to find file, check file path");
			e.printStackTrace();
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
