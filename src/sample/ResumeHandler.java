package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;


/**
 * Created by Ken on 9/29/2015.
 */
public class ResumeHandler {

    /**
     *
     * @param currentFolder     Path to the selected document
     * @param pdfFileName       The PDF version of the resume
     * @return                  A Base64 version of the PDF resume
     * @throws IOException
     */

    public static String encodeFileToBase64Binary(File currentFolder, String pdfFileName)  throws IOException {

        String pathToBinaryData = currentFolder.getAbsolutePath() + "/" + pdfFileName;

        File file = new File(pathToBinaryData);
        byte[] bytes = loadFile(file);
        byte[] encoded = Base64.encodeBase64(bytes);

        return new String(encoded);
    }

    /**
     *
     * @param file          The file to load
     * @return              The file in a byte array
     * @throws IOException
     */

    public static byte[] loadFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // file is too large
            System.out.println("File is too large, please try again");
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead;
        while (offset < bytes.length && (numRead=fileInputStream.read(bytes, offset, bytes.length-offset)) >=0 ) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        fileInputStream.close();
        return bytes;
    }
}