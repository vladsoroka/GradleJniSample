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
        InputStream inputStream = HelloWorld.class.getResourceAsStream("/libs/" + libraryName + "." + getLibraryExtension());
        File tempFile = null;
        try {
            tempFile = File.createTempFile(libraryName, "." + getLibraryExtension());
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

    private static String getLibraryExtension() {
        String osName = System.getProperty("os.name").toLowerCase(Locale.US);
        if (osName.contains("mac")) {
            return "dylib";
        } else if (osName.contains("linux")) {
            return "so";
        } else if (osName.contains("windows")) {
            return "dll";
        } else {
            throw new IllegalStateException("Unsupported platform: " + osName);
        }
    }

    public static void main(String[] args) {
        new HelloWorld().print();
    }
}