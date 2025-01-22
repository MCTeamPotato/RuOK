package team.teampotato.ruok.util.clazz;

import net.minecraft.network.chat.Component;
import team.teampotato.ruok.RuOKMod;
import team.teampotato.ruok.util.ToastUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassManager {
    private static final ClassLoadingMXBean clb = ManagementFactory.getClassLoadingMXBean();
    public static void saveToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("class_tracker.log"))) {
            writer.write("ClassLoadCount:\n");
            writer.write("TotalLoadedClassCount " + clb.getTotalLoadedClassCount() + "\n");
            writer.write("LoadedClassCount " + clb.getLoadedClassCount() + "\n");
            writer.write("UnloadedClassCount " + clb.getUnloadedClassCount() + "\n");
        } catch (IOException e) {
            RuOKMod.LOGGER.error("Failed to write class tracking info to file", e);
        }
        ToastUtil.send(Component.translatable("ruok.setting.debug.tracker.save.file.title"),Component.translatable("ruok.setting.debug.tracker.save.file.info"));
    }
}
