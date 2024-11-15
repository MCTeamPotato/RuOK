package team.teampotato.ruok.gui.vanilla.options.widget.list;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import team.teampotato.ruok.gui.vanilla.options.widget.list.entry.BlackTypeEntry;
import team.teampotato.ruok.gui.vanilla.screen.list.BlackListScreen;

import java.util.Collection;

public class BlackEntityListWidget extends ContainerObjectSelectionList<BlackTypeEntry> {

    public static Minecraft client;
    final BlackListScreen config;

    public static Collection<EntityType<?>> entityTypes = BuiltInRegistries.ENTITY_TYPE.stream().toList();
    public static EntityType<?>[] types = entityTypes.toArray(new EntityType<?>[0]);

    public BlackEntityListWidget(@NotNull BlackListScreen cfgs, Minecraft Minecraft) {
        super(Minecraft, cfgs.width , cfgs.layout.getContentHeight(), cfgs.height - 32, 20);
        this.config = cfgs;
        client = Minecraft.getInstance();

        for(EntityType<?> type:types){
            this.addEntry(new BlackTypeEntry(type));
        }
    }

    public static Minecraft getClient() {
        return client;
    }

    public BlackTypeEntry createEntry(EntityType<?> type){
        return new BlackTypeEntry(type);
    }

    public void add(BlackTypeEntry blackTypeEntry){
        this.addEntry(blackTypeEntry);

    }

    public boolean has(BlackTypeEntry entry){
        for(BlackTypeEntry etr:this.children()){
            if(etr.name.equalsIgnoreCase(entry.name)){
                return true;
            }
        }
        return false;
    }


}