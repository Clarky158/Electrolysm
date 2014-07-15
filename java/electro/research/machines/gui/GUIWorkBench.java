package electro.research.machines.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import electro.research.machines.container.ContainerWorkBench;
import electro.research.machines.tile.TileEntityWorkBench;
import electro.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIWorkBench extends GuiContainer
{
    private TileEntityWorkBench entity;

    public GUIWorkBench(TileEntityWorkBench entity,	InventoryPlayer inventory)
    {
        super(new ContainerWorkBench(entity, inventory));
        this.entity = entity;
    }
    /**
    * Draw the foreground layer for the GuiContainer (everything in front of the items)
    */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Crafting", 28, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }
    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(CommonProxy.WORK_BENCH_GUI);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}