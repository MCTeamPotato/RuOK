package team.teampotato.ruok.util;

public class ClassCheck {
    public static boolean isLoad(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
