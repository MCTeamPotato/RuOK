package team.teampotato.ruok.fix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;

public class GhostBlock {
    public static void execute() {
        Minecraft mc=Minecraft.getInstance();
        ClientPacketListener conn = mc.getConnection();
        if (conn == null && mc.player == null) return;
        BlockPos pos;
        if (mc.player != null) {
            pos = mc.player.blockPosition();
            for (int dx=-4; dx<=4; dx++)
                for (int dy=-4; dy<=4; dy++)
                    for (int dz=-4; dz<=4; dz++) {
                        ServerboundPlayerActionPacket packet;
                        if (pos != null) {
                            packet = new ServerboundPlayerActionPacket(
                                    ServerboundPlayerActionPacket.Action.ABORT_DESTROY_BLOCK,
                                    new BlockPos(pos.getX()+dx, pos.getY()+dy, pos.getZ()+dz),
                                    Direction.UP       // with ABORT_DESTROY_BLOCK, this value is unused
                            );
                            if (conn != null) conn.send(packet);
                        }
                    }
        }
    }

    public static void execute(int i) {
        Minecraft mc=Minecraft.getInstance();
        ClientPacketListener conn = mc.getConnection();
        if (conn == null && mc.player == null) return;
        BlockPos pos;
        if (mc.player != null) {
            pos = mc.player.blockPosition();
            for (int dx=-i; dx<=i; dx++)
                for (int dy=-i; dy<=i; dy++)
                    for (int dz=-i; dz<=i; dz++) {
                        ServerboundPlayerActionPacket packet;
                        if (pos != null) {
                            packet = new ServerboundPlayerActionPacket(
                                    ServerboundPlayerActionPacket.Action.ABORT_DESTROY_BLOCK,
                                    new BlockPos(pos.getX()+dx, pos.getY()+dy, pos.getZ()+dz),
                                    Direction.UP       // with ABORT_DESTROY_BLOCK, this value is unused
                            );
                            if (conn != null) conn.send(packet);
                        }
                    }
        }
    }
}
