package assets.electrolysm.electro.oreProccessing.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import assets.electrolysm.electro.common.CommonProxy;
import assets.electrolysm.electro.oreProccessing.container.ContainerCrusher;
import assets.electrolysm.electro.oreProccessing.te.TileEntityCrusher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUICrusher extends GuiContainer
{
    private TileEntityCrusher inventoryFurnace;

    public GUICrusher(InventoryPlayer inventoryPlayer, TileEntityCrusher tileFurnace)
    {
        super(new ContainerCrusher(inventoryPlayer, tileFurnace));
        this.inventoryFurnace = tileFurnace;
        xSize = 176;
        ySize = 167;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = inventoryFurnace.isInvNameLocalized() ? inventoryFurnace.getInvName() : StatCollector.translateToLocal(inventoryFurnace.getInvName());
        fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 5, 4210752);
        fontRenderer.drawString("Inventory", 8, ySize - 93, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(CommonProxy.ENERGISER_GUI);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int i1;
        //Flames Code
        // if (this.inventoryFurnace.isBurning()) {
        //	i1 = this.inventoryFurnace.getBurnTimeRemainingScaled(12);
        //     this.drawTexturedModalRect(xStart + 46, yStart + 65 - i1, 176, 12 - i1, 14, i1 + 2);
        // }
        //Progress Code
        //i1 = this.inventoryFurnace.getCookProgressScaled(49);
        // this.drawTexturedModalRect(xStart + 65, yStart + 35, 176, 14, i1 + 1, 41);
    }
}