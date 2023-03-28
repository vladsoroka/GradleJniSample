import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class HelloWorld {
    public native void print();

    static {
        // Load the native library from the JAR file
        String libraryName = "libhello";
        String extension = PlatformUtils.getLibraryExtension();
        InputStream inputStream = HelloWorld.class.getResourceAsStream("/libs/" + libraryName + "." + extension);
        File tempFile = null;
        try {
            tempFile = File.createTempFile(libraryName, "." + extension);
            tempFile.deleteOnExit();
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.load(tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load native library from JAR file", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }

    public static void main(String[] args) {
        new HelloWorld().print();
    }
}