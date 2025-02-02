package com.brahim;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileClient {
    private FileService service;
    private Scanner scanner;
    private List<FileMetadata> currentFileList;

    public FileClient() {
        scanner = new Scanner(System.in);
        currentFileList = new ArrayList<>();
    }

    public void connect() throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        service = (FileService) registry.lookup("FileService");
    }

    private void downloadFile() throws Exception {
        // First show the list of files
        listFiles();

        if (currentFileList.isEmpty()) {
            System.out.println("No files available to download.");
            return;
        }

        System.out.print("\nEnter the number of the file you want to download (1-" + currentFileList.size() + "): ");
        int fileNo = getValidFileNumber();
        if (fileNo == -1)
            return;

        FileMetadata selectedFile = currentFileList.get(fileNo - 1);
        System.out.println("Selected file: " + selectedFile.getFileName());

        System.out.print("Enter local save path: ");
        String savePath = scanner.nextLine().replace("\"", "");

        FileTransferObject fileTransfer = service.downloadFile(selectedFile.getFileId());

        if (fileTransfer.hasError()) {
            System.out.println("Error: " + fileTransfer.getErrorMessage());
            return;
        }

        java.io.File saveDir = new java.io.File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        java.io.File outputFile = new java.io.File(saveDir, selectedFile.getFileName());

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(fileTransfer.getData());
        }
        System.out.println("File downloaded successfully to: " + outputFile.getAbsolutePath());
    }

    private void uploadFile() throws Exception {
        System.out.print("Enter local file path: ");
        String path = scanner.nextLine().replace("\"", "");
        byte[] content = Files.readAllBytes(Paths.get(path));
        String fileName = Paths.get(path).getFileName().toString();

        FileTransferObject fileTransfer = new FileTransferObject(content, fileName);
        String fileId = service.uploadFile(fileName, fileTransfer);
        System.out.println("File uploaded successfully. File ID: " + fileId);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== File Sharing System ===");
            System.out.println("1. List files");
            System.out.println("2. Upload file");
            System.out.println("3. Download file");
            System.out.println("4. Delete file");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        listFiles();
                        break;
                    case 2:
                        uploadFile();
                        break;
                    case 3:
                        downloadFile();
                        break;
                    case 4:
                        deleteFile();
                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                scanner.nextLine(); // Consume bad input
            }
        }
    }

    private void listFiles() throws Exception {
        System.out.println("\nFiles in Google Drive:");
        System.out.println("----------------------------------------");
        System.out.printf("%-4s | %-50s%n", "No.", "File Name");
        System.out.println("----------------------------------------");

        currentFileList = service.getFilesList(); // Get updated list of files
        int fileNo = 1;

        for (FileMetadata file : currentFileList) {
            System.out.printf("%-4d | %-50s%n", fileNo++, file.getFileName());
        }
        System.out.println("----------------------------------------");
    }

    private void deleteFile() throws Exception {
        // First show the list of files
        listFiles();

        if (currentFileList.isEmpty()) {
            System.out.println("No files available to delete.");
            return;
        }

        System.out.print("\nEnter the number of the file you want to delete (1-" + currentFileList.size() + "): ");
        int fileNo = getValidFileNumber();
        if (fileNo == -1)
            return;

        FileMetadata selectedFile = currentFileList.get(fileNo - 1);
        System.out.println("Selected file: " + selectedFile.getFileName());

        System.out.print("Are you sure you want to delete this file? (yes/no): ");
        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("yes")) {
            service.deleteFile(selectedFile.getFileId());
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Delete operation cancelled");
        }
    }

    private int getValidFileNumber() {
        try {
            int fileNo = Integer.parseInt(scanner.nextLine());
            if (fileNo < 1 || fileNo > currentFileList.size()) {
                System.out
                        .println("Invalid file number. Please enter a number between 1 and " + currentFileList.size());
                return -1;
            }
            return fileNo;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }
}
