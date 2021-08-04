package objcPleaseDontKillMe;

public class JNITest {

    public native String getCurrentSong();

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        System.setProperty("java.library.path","/Users/jannes/IdeaProjects/SkipMe/src/main/java/objcPleaseDontKillMe/");
        System.loadLibrary("native");
        JNITest j = new JNITest();
        System.out.println(j.getCurrentSong());

    }
}
