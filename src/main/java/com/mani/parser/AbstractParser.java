package com.mani.parser;

import java.io.IOException;
import java.util.List;

import com.mani.entity.Node;
import com.mani.exceptions.MarshalException;
import com.mani.exceptions.TreeException;
import com.mani.helper.InputFileReader;
import com.mani.helper.OutputFileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public abstract class AbstractParser implements Parser
{
	InputFileReader fileReader = new InputFileReader();
	OutputFileWriter fileWriter = new OutputFileWriter();

	@Override
	public void doParse(String inputFilePath) throws MarshalException, TreeException, IOException
	{
		List<String> rawDataList = fileReader.readFile(inputFilePath, Integer.MAX_VALUE);
		List<Node> nodeList = convertRawDataToNode(rawDataList);
		String outputFilePath = inputFilePath.split("\\.(?=[^\\.]+$)")[0] + "_output.xml";
		String xmlContent = xmlMarshaller(nodeList);
		fileWriter.writeFile(outputFilePath, xmlContent);
	}

	public abstract List<Node> convertRawDataToNode(List<String> lines);

	public abstract String xmlMarshaller(List<Node> nodes) throws TreeException;
}
