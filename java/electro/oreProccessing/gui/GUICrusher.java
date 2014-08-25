package electro.oreProccessing.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;

import electro.common.CommonProxy;
import electro.oreProccessing.container.ContainerCrusher;
import electro.oreProccessing.te.TileEntityCrusher;

public class GUICrusher extends GuiContainer //implements INEIGuiHandler
{
    private TileEntityCrusher entity;

    public GUICrusher(TileEntityCrusher entity, InventoryPlayer inventory)
    {
        super(new ContainerCrusher(inventory, entity));
        this.entity = entity;
    }

    int coord;

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(CommonProxy.CRUSHER_GUI);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        
        int progress = ((int)((entity.time * 46) / entity.crushTime));

        if(progress <= 1)
        {
        	if(entity.getStackInSlot(2) != null)
        	{
        		int grindMeta = entity.getStackInSlot(2).getItemDamage();
        		int[] yCoord = {0, 46, 92};
        		coord = yCoord[grindMeta];
        	}
        	else
        	{
        		coord = 0;
        	}
        }
        
        if (progress > 0)
        {
            this.drawTexturedModalRect(x + 64, y + 20, 176, coord, progress + 1, 46);
        }

        //this.drawTexturedModalRect(x + 150, y + 25, 176, 14, 24, progress);
        //this.drawTexturedModelRectFromIcon()
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
    	//String RotationString = "Rotations: " + entity.rotations  + "RPM";
        
    	fontRendererObj.drawString(entity.getInventoryName(), 40, 6, 4210752);
        //fontRenderer.drawString(EnergyString, 13, 6 + 60 + 6 + 10, 4210752);
        //fontRenderer.drawString(RotationString, 13, 6 + 60 + 6, 4210752);
        
    }

}
