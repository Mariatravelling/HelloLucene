package HalloLucene;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class TestFile {

	public static void main(String[] args)
	{
		File file=new File("/Users/wangzehui/Documents/Solr/Test");
		for(File f:file.listFiles())
		{
			String destFilename=FilenameUtils.getFullPath(f.getAbsolutePath())
					+FilenameUtils.getBaseName(f.getName())+".avro";
			try {
				FileUtils.copyFile(f, new File(destFilename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
