package team.teampotato.ruok.gui.vanilla.options.widget.list;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.gui.vanilla.options.widget.list.entry.WhiteTypeEntry;
import team.teampotato.ruok.gui.vanilla.screen.list.WhiteListScreen;

import java.util.Collection;

public class WhiteEntityListWidget extends ContainerObjectSelectionList<WhiteTypeEntry> {

    public static Minecraft client;
    final WhiteListScreen config;

    public static Collection<EntityType<?>> entityTypes = BuiltInRegistries.ENTITY_TYPE.stream().toList();
    public static EntityType<?>[] types = entityTypes.toArray(new EntityType<?>[0]);

    public WhiteEntityListWidget(@NotNull WhiteListScreen cfgs, Minecraft Minecraft) {
        super(Minecraft,cfgs.width , cfgs.layout.getContentHeight(), cfgs.height - 32, 20);
        this.config = cfgs;
        client = Minecraft.getInstance();

        for(EntityType<?> type:types){
            this.addEntry(new WhiteTypeEntry(type));
        }
    }

    public static Minecraft getClient() {
        return client;
    }

    public WhiteTypeEntry createEntry(EntityType<?> type){
        return new WhiteTypeEntry(type);
    }

    public void add(WhiteTypeEntry type){
        this.addEntry(type);

    }

    public boolean has(WhiteTypeEntry entry){
        for(WhiteTypeEntry etr:this.children()){
            if(etr.name.equalsIgnoreCase(entry.name)){
                return true;
            }
        }
        return false;
    }


}