package com.mani.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public abstract class AbstractParser implements Parser
{
	private final String filePath;

	AbstractParser(String filePath)
	{
		this.filePath = filePath;
	}

	@Override
	public List<Node> doParse()
	{
		return doParseTags(readFile(filePath, Integer.MAX_VALUE));
	}

	public abstract List<Node> doParseTags(List<String> lines);

	private List<String> readFile(String filePath, int limitCount)
	{
		List<String> fileStrings = new ArrayList<String>();

		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filePath)));
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
			System.out.println("Unable to read file " + filePath);
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			System.out.println("Unable to find file, check file path");
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
					System.out.println("Unable to close file " + filePath);
					e.printStackTrace();
				}
			}
		}
		return fileStrings;
	}
}
