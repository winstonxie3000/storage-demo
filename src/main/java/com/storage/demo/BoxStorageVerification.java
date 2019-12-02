package com.storage.demo;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import util.FileUtil;

import java.io.*;
import java.security.MessageDigest;

public class BoxStorageVerification {

    public static void main(String[] args) throws IOException {

        BoxAPIConnection api = new BoxAPIConnection("mhvmexLbBmzY7FKCaggHuBSFPpOuaqVE");
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        FileInputStream stream = null;
        String fileName = "song.mp3";
        String folderName = "Test";
        try {
            stream = new FileInputStream("song.mp3");
            byte[] bytes = IOUtils.toByteArray(stream);

            BoxFolder boxFolder = getSubFolder(rootFolder, folderName);
            if (boxFolder == null) {
                boxFolder = rootFolder.createFolder(folderName).getResource();
            }
            boxFolder.canUpload(fileName, bytes.length);
            BoxFile.Info newFileInfo = boxFolder.uploadFile(new ByteArrayInputStream(bytes), fileName);
            String hex = DigestUtils.sha1Hex(bytes);
            System.out.println("file checksum: " + newFileInfo.getSha1().equals(hex));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }

    private static BoxFolder getSubFolder(BoxFolder parentFolder, String subFolderName) {

        BoxFolder boxFolder = null;
        for (BoxItem.Info itemInfo : parentFolder) {
            if (itemInfo instanceof BoxFolder.Info && StringUtils.equals(itemInfo.getName().toLowerCase(), subFolderName.toLowerCase())) {
                BoxFolder.Info folderInfo = (BoxFolder.Info) itemInfo;
                boxFolder = folderInfo.getResource();
                break;
            }
        }
        return boxFolder;
    }

}
