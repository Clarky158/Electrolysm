package electro.sciences.robotics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electro.Electrolysm;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class partAssemb extends Block
{
    public partAssemb(int id, Material mat)
    {
        super(Material.iron);
        this.setCreativeTab(Electrolysm.TabElectrolysm);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("Electrolysm:" + this.getUnlocalizedName().replace("item.", ""));
    }
}
