package team.teampotato.ruok.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;

public class ToastUtil {
    private static final Minecraft mci = Minecraft.getInstance();
    private static final ToastComponent tom = mci.getToasts();
    public static void send(Component title, Component Component) {
        if(mci!=null)return;
        SystemToast.addOrUpdate(tom,SystemToast.SystemToastId.PERIODIC_NOTIFICATION,title,Component);
    }
    public static void send(Component title, Component Component,SystemToast.SystemToastId type) {
        if(mci!=null)return;
        SystemToast.addOrUpdate(tom,type,title,Component);
    }
}
