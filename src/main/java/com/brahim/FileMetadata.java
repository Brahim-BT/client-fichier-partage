package com.brahim;

import java.io.Serializable;

public class FileMetadata implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fileId;
    private String fileName;
    private String mimeType;

    public FileMetadata(String fileId, String fileName, String mimeType) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.mimeType = mimeType;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMimeType() {
        return mimeType;
    }
}