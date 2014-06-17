package electro.block;

import java.util.List;

import electro.configHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 *
 *  @author Ben
 *
 */

public class GlassBlockConnectedMeta extends GlassBlockConnected
{
    public String[] textures;
    public IIcon[][] icons;
    boolean ignoreMetaForConnectedGlass = configHandler.connectedTexturesMode == 2;

    public GlassBlockConnectedMeta(String location, boolean hasAlpha, String... textures)
    {
        super(location, hasAlpha);
        this.textures = textures;
        this.icons = new IIcon[textures.length][16];
    }

    @Override
    public IIcon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        if (meta < icons.length)
        {
            return getConnectedBlockTexture(par1IBlockAccess, par2, par3, par4, par5, icons[meta]);
        }
        else
        {
            return getConnectedBlockTexture(par1IBlockAccess, par2, par3, par4, par5, icons[0]);
        }
    }

    @Override
    public boolean shouldConnectToBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, Block block, int par6)
    {
        return block instanceof GlassBlockConnectedMeta && (par6 == par1IBlockAccess.getBlockMetadata(par2, par3, par4) || ignoreMetaForConnectedGlass);
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return icons[par2][0];
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs par2CreativeTabs, List list)
    {
        list.add(new ItemStack(this, 1, 0));
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        for (int i = 0; i < textures.length; i++)
        {
            icons[i][0] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass");
            icons[i][1] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_1_d");
            icons[i][2] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_1_u");
            icons[i][3] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_1_l");
            icons[i][4] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_1_r");
            icons[i][5] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_2_h");
            icons[i][6] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_2_v");
            icons[i][7] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_2_dl");
            icons[i][8] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_2_dr");
            icons[i][9] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_2_ul");
            icons[i][10] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_2_ur");
            icons[i][11] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_3_d");
            icons[i][12] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_3_u");
            icons[i][13] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_3_l");
            icons[i][14] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_3_r");
            icons[i][15] = par1IconRegister.registerIcon("electrolysm:glass/" + folder + "/glass_4");
        }
    }

    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
        return true;
    }
}
