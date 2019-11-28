package com.storage.demo;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BoxStorageVerification {

    public static void main(String[] args) throws IOException {

        BoxAPIConnection api = new BoxAPIConnection("LSUCYCfOu0iP9PBS2AUItrqcPLfrvSur");
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("test.mp3");
            byte[] bytes = IOUtils.toByteArray(stream);
            BoxFile.Info newFileInfo = rootFolder.uploadFile(stream, "test5.mp3");
            System.out.println("file Info " + newFileInfo.getSize());
            System.out.println("byte length " + bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }
}
