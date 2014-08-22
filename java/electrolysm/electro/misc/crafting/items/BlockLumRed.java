package electrolysm.electro.misc.crafting.items;

import electrolysm.electro.Electrolysm;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import electrolysm.electro.misc.crafting.items.te.TileEntityLumRed;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLumRed extends BlockContainer
{
    public BlockLumRed()
    {
        super(Material.glass);
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setLightLevel(1.0F);
        this.setHardness(2F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.blockIcon = reg.registerIcon("electrolysm:" + "blockLumRed");
    }

    @Override
    public boolean canProvidePower()
    {
        return true;
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return 20;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityLumRed();
    }
}
