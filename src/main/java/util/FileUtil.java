package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * @author winston.xie
 */
public class FileUtil {

    public static String checksum(String filepath, MessageDigest md) throws IOException {

        return checksum(new FileInputStream(filepath), md);
    }

    public static String checksum(FileInputStream fileInputStream, MessageDigest md) throws IOException {

        // file hashing with DigestInputStream
        try (DigestInputStream dis = new DigestInputStream(fileInputStream, md)) {
//            while (dis.read() != -1) {
//                ; //empty loop to clear the data
//            }
            md = dis.getMessageDigest();
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();

    }
}
