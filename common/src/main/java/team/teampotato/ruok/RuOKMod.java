package team.teampotato.ruok;


public class RuOKMod {
    public static final String MOD_ID = "ruokmod";
    public static final String MOD_NAME = "RuOK";


    public static void init() {
        
        System.out.println(RuOKExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}