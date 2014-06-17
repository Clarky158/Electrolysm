package electro.oreProccessing;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import assets.electrolysm.electro.electrolysmCore;
import electro.oreProccessing.te.TileEntityCrusher;
import electro.oreProccessing.te.TileEntitySmeltory;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class smeltory extends oreProcessMachineBase
{
    public String className = "" + this.getClass();
    public String unlocalName = className.replace("assets.electrolysm.electro", "");
    public String textureName = unlocalName.replace(".", "/");

    public smeltory(int par1, Material par2Material, boolean isActive)
    {
        super(par1, Material.iron, isActive);
        this.setCreativeTab(electrolysmCore.TabElectrolysm);
        this.setUnlocalizedName("smeltory");
        this.setHardness(6.0F);
        this.active = isActive;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        this.frontIcon = reg.registerIcon("electrolysm:oreProcessMachines/" + "smeltory_Front");
        this.frontActive = reg.registerIcon("electrolysm:oreProcessMachines/" + "smeltory_Front_Active");
        this.blockIcon = reg.registerIcon("electrolysm:oreProcessMachines/" + "sidePanels");
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        // TODO Auto-generated method stub
        return new TileEntitySmeltory();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            player.openGui(electrolysmCore.GUIInstance, 0, world, x, y, z);
            return true;
        }
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int block)
    {
        TileEntitySmeltory te = (TileEntitySmeltory)world.getBlockTileEntity(x, y, z);

        if (this.isRecievingRedstonePower(world, x, y, z))
        {
            te.setRedstonePower(true);
        }
        else
        {
        	te.setRedstonePower(false);
        }
    }
    
    public boolean isRecievingRedstonePower(World world, int x, int y, int z)
    {
        return (world.isBlockIndirectlyGettingPowered(x, y, z));
    }

	/**
     * Update which block ID the furnace is using depending on whether or not it is burning
     */
    public static void updateFurnaceBlockState(boolean active, World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getBlockTileEntity(x, y, z);
        keepInventory = true;

        if (active)
        {
            world.setBlock(x, y, z, electrolysmCore.smeltoryActive.blockID);
        }
        else
        {
            world.setBlock(x, y, z, electrolysmCore.smeltory.blockID);
        }

        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setBlockTileEntity(x, y, z, tileentity);

        }
    }
    
Random furnaceRand = new Random();
    
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        TileEntitySmeltory tileentityfurnace = (TileEntitySmeltory)par1World.getBlockTileEntity(par2, par3, par4);

        if (tileentityfurnace != null && !(keepInventory))
        {
            for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1)
            {
                ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);

                if (itemstack != null)
                {
                    float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int k1 = this.furnaceRand.nextInt(21) + 10;

                        if (k1 > itemstack.stackSize)
                        {
                            k1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= k1;
                        EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.furnaceRand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.furnaceRand.nextGaussian() * f3);
                        par1World.spawnEntityInWorld(entityitem);
                    }
                }
            }

            par1World.func_96440_m(par2, par3, par4, par5);
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
}
