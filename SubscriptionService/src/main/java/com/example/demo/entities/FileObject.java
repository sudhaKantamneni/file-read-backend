package com.example.demo.entities;

public class FileObject {
	
	private String fileFullPath;
	private Long fileSize;
	private FILEENUM fileType;
	public String getFileFullPath() {
		return fileFullPath;
	}
	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}
	
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public FILEENUM getFileType() {
		return fileType;
	}
	public void setFileType(FILEENUM fileType) {
		this.fileType = fileType;
	}

}
