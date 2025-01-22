package team.teampotato.ruok.neoforge.event;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.util.render.TextRender;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
public class RenderHudEvent {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void HudRender(RenderGuiEvent.@NotNull Pre event) { // 方法需要为 static
        TextRender.draw(event.getGuiGraphics(), Minecraft.getInstance());
    }
}

