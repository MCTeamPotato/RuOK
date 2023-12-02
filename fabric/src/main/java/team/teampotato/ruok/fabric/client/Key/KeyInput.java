package team.teampotato.ruok.fabric.client.Key;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import team.teampotato.ruok.fabric.config.RuOK;
import team.teampotato.ruok.fabric.mixins.Minecraft.WorldRendererInvoker;

import static team.teampotato.ruok.fabric.client.Key.RegisterKey.*;

public class KeyInput {
    public static MinecraftClient minecraftClient = MinecraftClient.getInstance();
    public static void onClientKeyInputTick() {
        ClientPlayerEntity clientPlayer = minecraftClient.player;
        if (clientPlayer == null) return;
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(KeyReduceDistance.wasPressed()) {
                if (RuOK.get().Render_Entities_Distance > 50) {
                    RuOK.get().Render_Entities_Distance -= 50;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.add")
                                    + I18n.translate("ruok.key.reducedistance")
                            ));
                } else {
                    RuOK.get().Render_Entities_Distance = 50;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.error.max"))
                    );
                }
            }
            if(KeyAddDistance.wasPressed()) {
                if (RuOK.get().Render_Entities_Distance < 1000) {
                    RuOK.get().Render_Entities_Distance += 50;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.add")
                                    + I18n.translate("ruok.key.adddistance")
                            ));
                } else {
                    RuOK.get().Render_Entities_Distance = 1000;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.error.max"))
                    );
                }
            }
            if(KeyAddMaxEntities.wasPressed()) {
                if (RuOK.get().Max_Rendered_LivingEntities < 500) {
                    RuOK.get().Max_Rendered_LivingEntities += 30;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.add")
                                    + I18n.translate("ruok.key.reducemaxentities")
                            ));
                } else {
                    RuOK.get().Max_Rendered_LivingEntities = 500;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.error.max"))
                    );
                }
            }
            if(KeyReduceMaxEntities.wasPressed()) {
                if(RuOK.get().Max_Rendered_LivingEntities > 30) {
                    RuOK.get().Max_Rendered_LivingEntities -= 30;
                    minecraftClient.player.sendMessage(
                            Text.of(I18n.translate("ruok.key.value.add")
                                    + I18n.translate("ruok.key.reducemaxentities")
                            ));
                } else {
                    RuOK.get().Max_Rendered_LivingEntities = 30;
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

        });
    }
}