package service.impl;

import dao.FileListDao;
import dao.impl.FileListListDaoImpl;
import entity.FileList;
import service.FileListService;

import java.util.List;

public class FileListServiceImpl implements FileListService {
    private FileListDao fd = new FileListListDaoImpl();

    @Override
    public boolean uploadFile(String uuid, String filename, String filesize, String uploader, String uptime) {
        return fd.uploadFile(uuid, filename, filesize, uploader, uptime);
    }

    @Override
    public boolean deleteFile(String uuid, String filename) {
        return fd.deleteFile(uuid, filename);
    }

    @Override
    public List<FileList> showFile() {
        return fd.showFile();
    }

    @Override
    public List<String> getMacList() throws Exception {
        return fd.getMacList();
    }
}
