package com.brahim;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FileService extends Remote {
    List<FileMetadata> getFilesList() throws RemoteException;

    FileTransferObject downloadFile(String fileId) throws RemoteException;

    String uploadFile(String fileName, FileTransferObject fileData) throws RemoteException;

    void deleteFile(String fileId) throws RemoteException;
}
