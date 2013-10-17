package com.mani.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.mani.exceptions.MarshalException;
import com.mani.exceptions.TreeException;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public abstract class AbstractParser implements Parser
{
	private final String inputFilePath;

	AbstractParser(String inputFilePath)
	{
 		this.inputFilePath = inputFilePath;
	}

	@Override
	public List<Node> doParse()
	{
		return doParseNodes(readFile(inputFilePath, Integer.MAX_VALUE));
	}

	@Override
	public boolean marshaller(List<Node> nodes) throws TreeException, MarshalException
	{
		String outputFilePath = this.getClass().getResource(inputFilePath).getFile().split("\\.(?=[^\\.]+$)")[0] + "_output.xml";
		File outputFile = new File(outputFilePath);
		BufferedWriter bw = null;
		try
		{
			if (!outputFile.exists())
				outputFile.createNewFile();
			bw = new BufferedWriter(new FileWriter(outputFile.getAbsoluteFile()));

			//XMLMarshaller
			xmlMarshaller(nodes, bw);
		}
		catch (IOException e)
		{
			System.err.println("Unable to write file " + outputFile.getAbsoluteFile());
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			System.err.println("Unable to find file, check file path");
			e.printStackTrace();
		}
		finally
		{
			if (bw != null)
			{
				try
				{
					bw.close();
				}
				catch (IOException e)
				{
					System.err.println("Unable to close file " + outputFile.getAbsoluteFile());
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	public abstract List<Node> doParseNodes(List<String> lines);

	public abstract void xmlMarshaller(List<Node> nodes, BufferedWriter bw) throws IOException, TreeException, MarshalException;

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
