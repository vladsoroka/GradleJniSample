import java.util.Locale;

public class PlatformUtils {
    public static String getLibraryExtension() {
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
}
