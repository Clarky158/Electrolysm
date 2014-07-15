package electro.misc.crafting.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electro.Electrolysm;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class crystalBase extends Item
{
    public crystalBase()
    {
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setUnlocalizedName("crystalBase");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
        this.itemIcon = reg.registerIcon("electrolysm:" + "crystal");
    }
}
