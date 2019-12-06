package com.storage.demo;

import com.box.sdk.*;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author winston.xie
 */
public class DropBoxStorageVerification {

    public static void main(String[] args) throws IOException {

        FileInputStream stream = null;
        String filePath = "/RingCentral Archiver/Test/test6.mp3";
        try {
            stream = new FileInputStream("test.mp3");
            byte[] bytes = IOUtils.toByteArray(stream);

            DbxRequestConfig config = new DbxRequestConfig("DropBox PoC");
            DbxClientV2 client = new DbxClientV2(config, "1Jd2oEEP48IAAAAAAAAjboMzXfSK43WjEYhDfo7DtpgM0rYzYRAiT7yB4iqAa_DT");
            client.files().uploadBuilder(filePath).uploadAndFinish(new ByteArrayInputStream(bytes));
            client.files().deleteV2(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }

}
