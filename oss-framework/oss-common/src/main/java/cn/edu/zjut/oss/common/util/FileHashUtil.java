package cn.edu.zjut.oss.common.util;

import ky.korins.blake3.Blake3;
import ky.korins.blake3.Hasher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


public class FileHashUtil {

    private final static int byteSize = 64;

    public static String hashMultipartFile(MultipartFile multiPartFile) throws IOException {
        try ( InputStream inputStream = multiPartFile.getInputStream()){
            Hasher hasher = Blake3.newHasher();
            hasher.update(inputStream);
            return hasher.doneHex(byteSize);
        }
    }

}
