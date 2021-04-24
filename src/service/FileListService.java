package service;

import entity.FileList;

import java.util.List;

public interface FileListService {
    public boolean uploadFile(String uuid, String filename, String filesize, String uploader, String uptime);
    public boolean deleteFile(String uuid, String filename);
    public List<FileList> showFile();
    public List<String> getMacList() throws Exception;
}
