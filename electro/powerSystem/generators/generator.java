package assets.electrolysm.electro.powerSystem.generators;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import assets.electrolysm.electro.electrolysmCore;
import assets.electrolysm.electro.powerSystem.TeslaTransmittingServer;
import assets.electrolysm.electro.powerSystem.generators.te.TileEntityGenerator;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class generator extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon frontActive;
	@SideOnly(Side.CLIENT)
	private Icon front;
	
	private Random furnaceRand = new Random();
	private Map name = new HashMap();
	
	public generator(int id, Material mat) {
		super(id, Material.iron);

		this.setCreativeTab(electrolysmCore.TabElectrolysm);
		this.setUnlocalizedName("basicGenerator");
		this.setHardness(5.2165F);
		GameRegistry.registerBlock(this);
		LanguageRegistry.addName(this, "Coal Generator");
	}

	@Override
	public void registerIcons(IconRegister reg)
	{
		String modID = "electrolysm:";
		this.blockIcon = reg.registerIcon(modID + "generatorSide");
		this.front = reg.registerIcon(modID + "generatorFront");
		this.frontActive = reg.registerIcon(modID + "generatorFrontActive");
	}
	
	@Override
	public Icon getIcon(int side, int meta)
	{
		TileEntityGenerator te = new TileEntityGenerator();
		if(side == meta)
		{
			/*if(te.isWorking(te.worldObj, te.xCoord, te.yCoord, te.zCoord))
			{
				return frontActive;
			}
			else
			{*/
				return front;
			//}
		}
		else
		{
			return blockIcon;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new TileEntityGenerator();
	}
	
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
    	TeslaTransmittingServer.clearAll();
    }

    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
    {
    	TeslaTransmittingServer.clearAll();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, 
    		int par6, float par7, float par8, float par9)
    {
    	ItemStack meterStack = new ItemStack(electrolysmCore.energyMeter, 1, 0);
    	if(player.isSneaking())
    	{
	    	if(player.getHeldItem().isItemEqual(meterStack))
	    	{
	    		this.printChatMessage(world, x, y, z);
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
    	}
    	else
    	{
            player.openGui(electrolysmCore.GUIInstance, 0, world, x, y, z);
    		return true;
    	}
    }
    
    @SideOnly(Side.CLIENT)
    private void printChatMessage(World world, int x, int y, int z) 
    {
    	if(world.isRemote)
    	{
    		TileEntityGenerator te = (TileEntityGenerator)world.getBlockTileEntity(x, y, z);
    		String message = "This generator is producing " + String.valueOf(te.getSendTeU(world, x, y, z)) + " TeU";
    		
    		FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(message);	
    	}
    	else
    	{
    		
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
            ((TileEntityGenerator)par1World.getBlockTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
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
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }
    
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
            TileEntityGenerator tileentityfurnace = (TileEntityGenerator)par1World.getBlockTileEntity(par2, par3, par4);

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
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	TileEntityGenerator te = (TileEntityGenerator)par1World.getBlockTileEntity(par2, par3, par4);
    	
        if (te.isWorking(par1World, par2, par3, par4))
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            float f = (float)par2 + 0.5F;
            float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)par4 + 0.5F;
            float f3 = 0.52F;
            float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                par1World.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                par1World.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

}
