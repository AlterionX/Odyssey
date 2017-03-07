package gui;

import java.io.IOException;
import java.nio.file.*;

/**
 *
 * @author AlterionX
 */
public class FileCreator implements Runnable{
	String[] printToFile;
	public FileCreator(String[] g){
		printToFile = g;
	}
	@Override
	public void run(){
		Path outputPath = Paths.get("./output");
		
		try {
			Files.createFile(outputPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}