package team.teampotato.ruok.fabric.keyBind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import team.teampotato.ruok.config.RuOK;

import static team.teampotato.ruok.fabric.keyBind.RegisterKey.*;


public class InputEvent {
    private static final MinecraftClient minecraftClient = MinecraftClient.getInstance();
    private static final int MIN_DISTANCE = 50;
    private static final int MAX_DISTANCE = 1000;
    private static final int MIN_ENTITIES = 30;
    private static final int MAX_ENTITIES = 500;
    private static final int DISTANCE_STEP = 50;
    private static final int ENTITY_STEP = 30;

    public static void onClientKeyInputTick() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClientPlayerEntity clientPlayer = minecraftClient.player;
            if (clientPlayer == null) return;

            handleKeyPress(KeyReduceDistance, -DISTANCE_STEP, "ruok.key.reducedistance");
            handleKeyPress(KeyAddDistance, DISTANCE_STEP, "ruok.key.adddistance");
            handleKeyPress(KeyReduceMaxEntities, -ENTITY_STEP, "ruok.key.reducemaxentities");
            handleKeyPress(KeyAddMaxEntities, ENTITY_STEP, "ruok.key.addmaxentities");

            if (KeyReloadInvoker.wasPressed()) {
                minecraftClient.worldRenderer.reload();
                sendMessage("ruok.key.reloadinvoker");
            }
        });
    }

    private static void handleKeyPress(KeyBinding key, int step, String messageKey) {
        if (key.wasPressed()) {
            int currentValue;
            int newValue;

            if (key == KeyReduceDistance || key == KeyAddDistance) {
                currentValue = RuOK.get().entitiesDistance;
                newValue = clamp(currentValue + step, MIN_DISTANCE, MAX_DISTANCE);
                RuOK.get().entitiesDistance = newValue;
            } else {
                currentValue = RuOK.get().maxLivingEntities;
                newValue = clamp(currentValue + step, MIN_ENTITIES, MAX_ENTITIES);
                RuOK.get().maxLivingEntities = newValue;
            }

            if (newValue == currentValue) {
                sendMessage("ruok.key.value.error.max");
            } else {
                sendMessage(I18n.translate(messageKey));
            }
        }
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static void sendMessage(String messageKey) {
        if (minecraftClient.player != null) {
            minecraftClient.player.sendMessage(Text.of(I18n.translate(messageKey)));
        }
    }
}
