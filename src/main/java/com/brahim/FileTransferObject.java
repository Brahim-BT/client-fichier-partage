package com.brahim;
import java.io.Serializable;

public class FileTransferObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private byte[] data;
    private String fileName;
    private String errorMessage;

    public FileTransferObject(byte[] data, String fileName) {
        this.data = data;
        this.fileName = fileName;
    }

    public FileTransferObject(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public byte[] getData() {
        return data;
    }

    public String getFileName() {
        return fileName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean hasError() {
        return errorMessage != null;
    }
}