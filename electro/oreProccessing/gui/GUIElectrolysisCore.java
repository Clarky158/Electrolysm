package assets.electrolysm.electro.oreProccessing.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import assets.electrolysm.electro.common.CommonProxy;
import assets.electrolysm.electro.oreProccessing.container.ContainerElectrolysis;
import assets.electrolysm.electro.oreProccessing.te.TileEntityElectrolisisCore;

public class GUIElectrolysisCore extends GuiContainer
{
    private TileEntityElectrolisisCore entity;

    public GUIElectrolysisCore(TileEntityElectrolisisCore entity,	InventoryPlayer inventory)
    {
        super(new ContainerElectrolysis(entity, inventory));
        this.entity = entity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(CommonProxy.ELECTROLYSIS_CORE_GUI);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        
        if (entity.active)
        {
            this.drawTexturedModalRect(x + 63, y + 27 + 12, 176, 4, 44, 10);
        }
        int progress = (entity.time);
        int progress2 = ((progress * 29) / entity.electroTime);
        int progress1 = ((progress * 37) / entity.electroTime);
        /**
         * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
         */
        this.drawTexturedModalRect(x + 27, y + 33, 176, 16, 26 + 6, 37 - progress1);
        this.drawTexturedModalRect(x + 82, y + 23, 226, 19, 16, 29 - progress2);
        //this.drawTexturedModalRect(x + 82, y + 23, 176, 14, 80, progress - 50);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
        fontRenderer.drawString(entity.getInvName(), 40, 6, 4210752);
    }
}
