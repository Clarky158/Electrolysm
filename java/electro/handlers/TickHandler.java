package electro.handlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Random;

/**
 * Created by Clarky158 19/06/2014.
 */
public class TickHandler
{/*
    public GuiResearchNotify guiNotify = null;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTickRender(RenderGameOverlayEvent event)
    {
        System.out.println("render");

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player;

        if ((Minecraft.getMinecraft().thePlayer instanceof EntityPlayer))
        {
            player = (EntityPlayer)Minecraft.getMinecraft().thePlayer;
            long time = System.currentTimeMillis();
            if (guiNotify == null) { guiNotify = new GuiResearchNotify(mc); }

            guiNotify.updateResearchWindow();
            //System.out.println("rendering");

            boolean hasWorld = mc.theWorld != null;
            GuiScreen gui = mc.currentScreen;
        }
    }
*/
    int times = 0;

    @SubscribeEvent
    public void checkUpdate(TickEvent.ClientTickEvent event)
    {
        //System.out.println("tick");
        //VersionCheck.check();

        if (FMLClientHandler.instance().getClient().inGameHasFocus) {
            if (times <= 0) {
                if (VersionCheck.chatMessage != null) {
                    this.printChatMessage(VersionCheck.chatMessage);
                    times = 100;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void printChatMessage(String message)
    {
        FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentTranslation(message));
    }

    public static float rotation = 0;

    @SubscribeEvent
    public void renderTicks(TickEvent.ClientTickEvent event)
    {
        Random rand = new Random();
        float[] value = new float[] {0.021F, 0.0125F, 0.0197F, 0.0158F, 0.0638F, 0.015F};
        if (!Minecraft.getMinecraft().isGamePaused()) {
            if(rotation >= 360)
            {
                rotation = 0;
            } else {
                rotation = rotation + 0.015F;
            }

        }
    }
}
