package support.dto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class CVObject {
	private byte[] fileStream;
	private String fileName;
	private String filePath;
	
	public CVObject() {
		this.fileName = null;
		this.filePath = null;
		this.fileStream = null;
	}
	
	public CVObject(byte[] stream) throws IOException {
		FileOutputStream outputStream = new FileOutputStream("resources/pdf/cv.pdf");
		outputStream.write(stream);
		outputStream.close();
		File file = new File("resources/pdf/cv.pdf");
        this.filePath = file.getAbsolutePath();
        this.fileStream = stream;
	}
	
	
	public byte[] getStream() {
		return fileStream;
	}

	public String getFileName() {
		return fileName;
	}
	
	public CVObject setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
