package electro.block.machines.gui;

import java.util.Random;

import electro.Electrolysm;
import electro.handlers.helpers.ColourEnumHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.InventoryPlayer;

import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import electro.block.machines.container.ContainerResearchDesk;
import electro.block.machines.tile.TileEntityResearchDesk;
import electro.common.CommonProxy;

public class GUIResearchDesk extends GuiContainer
{
    private TileEntityResearchDesk entity;

    public GUIResearchDesk(TileEntityResearchDesk entity,	InventoryPlayer inventory)
    {
        super(new ContainerResearchDesk(entity, inventory));
        this.entity = entity;
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        if(entity.renderActiveScreen) {
            this.mc.renderEngine.bindTexture(CommonProxy.RESEARCH_DESK_ACTIVE_GUI);
        } else {
            this.mc.renderEngine.bindTexture(CommonProxy.RESEARCH_DESK_GUI);
        }
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        int progress = 0;
        int heat;

        if (entity.errorRequirement)
        {
            int xMod = 12;
            int yMod = 50;

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1F, 1F, 1F, 0.999999F);
            this.drawTexturedModalRect(x + xMod, y + yMod, 0, 166, 140, 42);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1f, 1f, 1f, 1f);

            String error = "You don't have the";
            String error2 = "correct requirements to";
            String error3 = "continue.";
            fontRendererObj.drawString(ColourEnumHelper.WHITE + error, x + 20 + xMod, y + 6 + yMod, 4210752);
            fontRendererObj.drawString(ColourEnumHelper.WHITE + error2, x + 8 + xMod, y + 16 + yMod, 4210752);
            fontRendererObj.drawString(ColourEnumHelper.WHITE + error3, x + 50 + xMod, y + 26 + yMod, 4210752);
        }

        this.drawTexturedModalRect(x + 150, y + 25, 176, 14, 24, progress);
        this.buttonList.add(new GuiButton(0, x + 66, y + 116, 60, 20, "Research"));

    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        entity.actionPerformed(button);
    }

    @Override
    public void onGuiClosed()
    {
        entity.renderActiveScreen = false;
        entity.errorRequirement = false;
        entity.requirements = false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
        fontRendererObj.drawString(entity.getInventoryName(), 40, 6, 4210752);
        /*
        fontRenderer.drawString(matrix(), 10, 40, 4210752);
        fontRenderer.drawString(matrix(), 10, 48, 4210752);
        fontRenderer.drawString(matrix(), 10, 56, 4210752);
        fontRenderer.drawString(matrix(), 10, 62, 4210752);
        fontRenderer.drawString(matrix(), 10, 70, 4210752);
        fontRenderer.drawString(matrix(), 10, 78, 4210752);
        fontRenderer.drawString(matrix(), 10, 86, 4210752);
        fontRenderer.drawString(matrix(), 10, 94, 4210752);
        fontRenderer.drawString(matrix(), 10, 102, 4210752);
        fontRenderer.drawString(matrix(), 10, 110, 4210752);
        */
    }
    public String matrix()
    {
        String[] matrix = { "0010101001000011010101" ,
                            "1011010101011101010010",
                            "0100101010100110101101",
                            "1011101001011100101000"
                          };
        Random rand = new Random();
        int randNo = rand.nextInt(3);
        return matrix[randNo];
    }
}
