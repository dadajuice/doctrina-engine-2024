package Viking;

public class GameConfig {
    private static boolean debugEnabled;

    public static boolean isDebugEnabled() {
        return debugEnabled;
    }

    public static void setDebugEnabled(boolean debugEnabled) {
        GameConfig.debugEnabled = debugEnabled;
    }
}
