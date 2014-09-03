package electro.powerSystem.tesla.gui;

import electro.common.CommonProxy;
import electro.handlers.helpers.ColourEnumHelper;
import electro.handlers.network.PacketHandler;
import electro.handlers.network.ReceivingCoreMessage;
import electro.handlers.network.TeslaCoreMessage;
import electro.powerSystem.tesla.te.TileEntityTeslaCore;
import electrolysm.api.powerSystem.tesla.TERecievingCore;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Clarky158 on 03/09/2014.
 */
public class GuiReceivingCore extends GuiContainer {

    public TERecievingCore entity;
    public int INCREASE_BUTTON_ID = 0;
    public int DECREASE_BUTTON_ID = 1;
    public String INCREASE_BUTTON_STRING_1 = "+1";
    public String DECREASE_BUTTON_STRING_1 = "-1";
    public String INCREASE_BUTTON_STRING_10 = "+10";
    public String DECREASE_BUTTON_STRING_10 = "-10";
    public String INCREASE_BUTTON_STRING_100 = "+100";
    public String DECREASE_BUTTON_STRING_100 = "-100";

    public String KEY = "%VALUE%";
    public String COLOUR = ColourEnumHelper.WHITE.toString();
    public String FREQUENCY = COLOUR + "Freq: %VALUE% Hz";
    public String POWER_VALUE = COLOUR + "Receiving Power: %VALUE% TeU";
    public String POWER_0 = POWER_VALUE.replace(KEY, String.valueOf(0));

    public GuiReceivingCore(TERecievingCore te, InventoryPlayer player) {
        super(new ContainerReceivingCore(te, player));
        entity = te;
    }

    @Override
    public void initGui() {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        buttonList.add(new GuiButton(INCREASE_BUTTON_ID, x + 69, y + 14 - 9, 40, 20, INCREASE_BUTTON_STRING_1));
        buttonList.add(new GuiButton(DECREASE_BUTTON_ID, x + 69, y + 49 - 7, 40, 20, DECREASE_BUTTON_STRING_1));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        if (isShiftKeyDown()) {
            if (isCtrlKeyDown()) {
                ((GuiButton) buttonList.get(INCREASE_BUTTON_ID)).displayString = INCREASE_BUTTON_STRING_100;
                ((GuiButton) buttonList.get(DECREASE_BUTTON_ID)).displayString = DECREASE_BUTTON_STRING_100;
            } else {
                ((GuiButton) buttonList.get(INCREASE_BUTTON_ID)).displayString = INCREASE_BUTTON_STRING_10;
                ((GuiButton) buttonList.get(DECREASE_BUTTON_ID)).displayString = DECREASE_BUTTON_STRING_10;
            }
        } else {
            ((GuiButton) buttonList.get(INCREASE_BUTTON_ID)).displayString = INCREASE_BUTTON_STRING_1;
            ((GuiButton) buttonList.get(DECREASE_BUTTON_ID)).displayString = DECREASE_BUTTON_STRING_1;
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(CommonProxy.TESLA_TOWER);
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        this.drawCenteredString(fontRendererObj, FREQUENCY.replace(KEY, String.valueOf(entity.getFrequency())), x + 89, y + 29, 155475);
        if (entity.getTower() != null && entity.getTeU() > 0) {
            this.drawCenteredString(fontRendererObj, POWER_VALUE.replace(KEY, String.valueOf(entity.getTeU())), x + 89, y + 66, 155475);
        } else {
            this.drawCenteredString(fontRendererObj, POWER_0, x + 89, y + 66, 155475);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        int AMOUNT;
        if (isShiftKeyDown()) {
            if (isCtrlKeyDown()) {
                AMOUNT = 100;
            } else {
                AMOUNT = 10;
            }
        } else {
            AMOUNT = 1;
        }
        if (button.id - INCREASE_BUTTON_ID == 0) {
            entity.setFrequency(entity.getFrequency() + AMOUNT);
        } else if (button.id - DECREASE_BUTTON_ID == 0) {
            if (entity.getFrequency() - AMOUNT >= 0) {
                entity.setFrequency(entity.getFrequency() - AMOUNT);
            } else {
                entity.setFrequency(0);
            }
        } else {
            super.actionPerformed(button);
        }
        this.sendPacket();
    }

    private void sendPacket() {
        PacketHandler.INSTANCE.sendToServer(new ReceivingCoreMessage(entity));
    }
}
