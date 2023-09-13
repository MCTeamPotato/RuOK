package team.teampotato.ruok.forge.client.Key;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import team.teampotato.ruok.forge.config.RuOK;
import team.teampotato.ruok.forge.mixins.Render.WorldRendererInvoker;

import static team.teampotato.ruok.forge.client.Key.RegisterKey.*;

public class KeyInput {
    public static MinecraftClient minecraftClient = MinecraftClient.getInstance();
    @SubscribeEvent
    public static void onClientEndTick(TickEvent.ClientTickEvent event) {
        ClientPlayerEntity clientPlayer = minecraftClient.player;
        if (clientPlayer == null) return;
        if (event.phase == TickEvent.Phase.END) {
            if(KeyReduceDistance.wasPressed()) {
                if (RuOK.get().RenderDistance > 50) {
                    RuOK.get().RenderDistance -= 50;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.add")
                                    + I18n.translate("ruok.key.reducedistance")
                            ));
                } else {
                    RuOK.get().RenderDistance = 50;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.error.max"))
                    );
                }
            }
            if(KeyAddDistance.wasPressed()) {
                if (RuOK.get().RenderDistance < 1000) {
                    RuOK.get().RenderDistance += 50;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.add")
                                    + I18n.translate("ruok.key.adddistance")
                            ));
                } else {
                    RuOK.get().RenderDistance = 1000;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.error.max"))
                    );
                }
            }
            if(KeyAddMaxEntities.wasPressed()) {
                if (RuOK.get().Max_Rendered_Entities < 500) {
                    RuOK.get().Max_Rendered_Entities += 30;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.add")
                                    + I18n.translate("ruok.key.reducemaxentities")
                            ));
                } else {
                    RuOK.get().Max_Rendered_Entities = 500;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.error.max"))
                    );
                }
            }
            if(KeyReduceMaxEntities.wasPressed()) {
                if(RuOK.get().Max_Rendered_Entities > 30) {
                    RuOK.get().Max_Rendered_Entities -= 30;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.add")
                                    + I18n.translate("ruok.key.reducemaxentities")
                            ));
                } else {
                    RuOK.get().Max_Rendered_Entities = 30;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.error.max"))
                    );
                }
            }
            if(KeyReloadInvoker.wasPressed()) {
                minecraftClient.worldRenderer.reload();
                WorldRendererInvoker worldRendererInvoker = (WorldRendererInvoker) MinecraftClient.getInstance().worldRenderer;
                worldRendererInvoker.invokeReload();
                minecraftClient.player.sendMessage(
                        Text.of(I18n.translate("ruok.key.value.add")
                                + I18n.translate("ruok.key.reloadinvoker")
                        ));
            }

        }
    }
}