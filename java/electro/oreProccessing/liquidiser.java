package electro.oreProccessing;

import java.util.Random;

import electro.Electrolysm;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import electro.oreProccessing.te.TileEntityLiquidiser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class liquidiser extends oreProcessMachineBase
{
    public liquidiser(boolean isActive)
    {
        super(isActive);
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setHardness(6.0F);
        this.active = isActive;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityLiquidiser();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.frontIcon = reg.registerIcon("electrolysm:oreProcessMachines/" + "liquidizerFront");
        this.frontActive = reg.registerIcon("electrolysm:oreProcessMachines/" + "liquidizerFront");
        this.blockIcon = reg.registerIcon("electrolysm:oreProcessMachines/" + "sidePanels");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            player.openGui(Electrolysm.GUIInstance, 0, world, x, y, z);
            return true;
        }
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

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

        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityLiquidiser)par1World.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
        }
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            Block l = par1World.getBlock(par2, par3, par4 - 1);
            Block i1 = par1World.getBlock(par2, par3, par4 + 1);
            Block j1 = par1World.getBlock(par2 - 1, par3, par4);
            Block k1 = par1World.getBlock(par2 + 1, par3, par4);
            byte b0 = 3;

            if (l.isOpaqueCube() && !i1.isOpaqueCube())
            {
                b0 = 3;
            }

            if (i1.isOpaqueCube() && !l.isOpaqueCube())
            {
                b0 = 2;
            }

            if (j1.isOpaqueCube() && !k1.isOpaqueCube())
            {
                b0 = 5;
            }

            if (k1.isOpaqueCube() && !j1.isOpaqueCube())
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }
    
    Random furnaceRand = new Random();
    
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
        TileEntityLiquidiser tileentityfurnace = (TileEntityLiquidiser)par1World.getTileEntity(par2, par3, par4);

        if (tileentityfurnace != null)
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
                        EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

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

            par1World.scheduleBlockUpdate(par2, par3, par4, par5, 0);
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
}
