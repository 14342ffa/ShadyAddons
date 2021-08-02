package shady.shady.shady.features;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import shady.shady.shady.config.Config;
import shady.shady.shady.config.Setting;
import shady.shady.shady.utils.KeybindUtils;
import shady.shady.shady.utils.Utils;

public class GhostBlockKeybind {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public GhostBlockKeybind() {
        KeybindUtils.register("Create Ghost Block", Keyboard.KEY_G);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(Config.isEnabled(Setting.GHOST_BLOCK_KEYBIND) && KeybindUtils.get("Create Ghost Block").isPressed()) {
            BlockPos lookingAtPos = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1).getBlockPos();
            if(lookingAtPos != null) {
                Block lookingAtblock = mc.theWorld.getBlockState(lookingAtPos).getBlock();
                if(!Utils.isInteractable(lookingAtblock)) {
                    mc.theWorld.setBlockToAir(lookingAtPos);
                }
            }
        }
    }

}
