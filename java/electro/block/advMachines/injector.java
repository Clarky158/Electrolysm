package electro.block.advMachines;

import electro.Electrolysm;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import electro.block.advMachines.te.TileEntityInjector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class injector extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconFront;
    private boolean active;

    public injector()
    {
        super(Material.iron);
        // TODO Auto-generated constructor stub
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setHardness(3);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        // TODO Auto-generated method stub
        return new TileEntityInjector();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
                                    int par6, float par7, float par8, float par9)
    {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te == null || player.isSneaking())
        {
            return false;
        }

        player.openGui(Electrolysm.GUIInstance, 0, world, x, y, z);
        return true;
    }

    public static void updateFurnaceBlockState(boolean b, World worldObj,
            int xCoord, int yCoord, int zCoord)
    {
        // TODO Auto-generated method stub
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }
    }

    public static void updateState(boolean active, World world, int x, int y, int z)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        TileEntityInjector tile = (TileEntityInjector)world.getTileEntity(x, y, z);

        if (active)
        {
        }
        else
        {
        }

        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);

        if (tile != null)
        {
            tile.validate();
            world.setTileEntity(x, y, z, tile);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.furnaceIconTop : (par1 == 0 ? this.furnaceIconTop : (par1 != par2 ? this.furnaceIconFront : this.furnaceIconFront));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("electrolysm:" + "injector_side");
        this.furnaceIconFront = par1IconRegister.registerIcon("Electrolysm:" + "injector_Front");
        this.furnaceIconTop = par1IconRegister.registerIcon("Electrolysm:" + "injector_side");
    }
}