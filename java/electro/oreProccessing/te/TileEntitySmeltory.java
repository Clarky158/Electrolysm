package electro.oreProccessing.te;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import api.powerSystem.meter.IMeterable;
import electro.electrolysmCore;
import electro.oreProccessing.recipes.SmeltoryRecipes;

public class TileEntitySmeltory extends TileEntity implements IInventory, ISidedInventory, IMeterable
{
    private ItemStack[] inventory;
    public boolean isOpen;

    public TileEntitySmeltory()
    {
        this.inventory = new ItemStack[2];
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack stack = getStackInSlot(slot);

        if (stack != null)
        {
            if (stack.stackSize <= amount)
            {
                setInventorySlotContents(slot, null);
            }
            else
            {
                stack = stack.splitStack(amount);

                if (stack.stackSize == 0)
                {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack stack = getStackInSlot(slot);

        if (stack != null)
        {
            setInventorySlotContents(slot, null);
        }

        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        // TODO Auto-generated method stub
        inventory[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        // TODO Auto-generated method stub
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
    	if(stack != null)
    	{
	    	ItemStack recipe = FurnaceRecipes.smelting().getSmeltingResult(stack);
	    	if(slot == 0)
	    	{
	    		if(recipe != null)
	    		{
	    			return true;
	    		}
	    		else
	    		{
	    			return false;
	    		}
	    	}
	    	else
	    	{
	    		return false;
	    	}
    	}
    	else 
    	{
    		return false;
    	}
    }
    
    public void setRedstonePower(boolean powered)
    {
    	this.redstonePower = powered;
    }

    public int time = 0;
    public int maxSmeltTime = 60;
    public int smeltTime = 60;
    
    public int temp = 0;
    public int maxTemp = 100;
    public int maxMaxTemp = 100;
    boolean redstonePower;
    
    @Override
    public void updateEntity()
    {
    	this.markDirty();

    	ItemStack inStack = getStackInSlot(0);
        ItemStack output = getStackInSlot(1);
        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inStack);
        ItemStack result2 = SmeltoryRecipes.smelting().getSmeltingResult(inStack);
        Random rand = new Random();
    	int inputPersent = this.getInputPersent(inStack);
    	redstonePower = (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord));
    	
    	/*
    	if(redstonePower && ((temp + 1) <= maxTemp))
    	{
    		if(rand.nextInt(15) == 1)
    		{
    			temp = temp + 1;
    		}
    	}*/
    	
        if (inStack != null)
        {
            if (result != null)
            {
            	if(temp == maxTemp)
            	{
	                if (output == null)
	                {
	                    int outputSize = 0;
	                    int resultSize = result.stackSize;
	
	                    if (((resultSize + outputSize) <= 64))
	                    {
	                    	if(time == smeltTime)
	                    	{
	                    		time = 0;
	                    		this.decrStackSize(0, 1);
		                        this.setInventorySlotContents(1, result);
		                        this.markDirty();
	                    	}
	                    	else
	                    	{
	                    		time = time + 1;
	                    	}
	                    }
	                }
	                else
	                {
	                    int outputSize = output.stackSize;
	                    int resultSize = result.stackSize;
	
	                    if (((resultSize + outputSize) < 64))
	                    {
	                    	if(time == smeltTime)
	                    	{
	                    		time = 0;
		                        this.decrStackSize(0, 1);
		                        this.setInventorySlotContents(1, new ItemStack(result.getItem(), 
		                        		(result.stackSize + output.stackSize), result.getItemDamage()));
		                        this.markDirty();
	                    	}
	                    	else
	                    	{
	                    		time = time + 1;
	                    	}
	                    }
	                }
	                
                }
            	else
                {
            		if(rand.nextInt(5) == 1)
            		{
            			temp = temp + 1;
            		}
                }
           	}
            else
            {
            	time = 0;
            	if((temp - 1) > 0 && rand.nextInt(10) == 1)
            	{
            		if(!(redstonePower))
            		{
            			temp = temp - 1;
            		}
            	}
            }
        }
        else
        {
        	time = 0;
        	if((temp - 1) > 0 && rand.nextInt(10) == 1)
        	{
        		if(!(redstonePower))
        		{
        			temp = temp - 1;
        		}        	
        	}
        }
    }

	private int getInputPersent(ItemStack input) 
	{
		if(input != null)
		{
			String unlocalName = input.getUnlocalizedName();
			
			if(unlocalName.contains("dust"))
			{
				return 75;
			}
			else if(unlocalName.contains("ingot"))
			{
				return 139;
			}
			else
			{
				return 100;
			}
		}
		else
		{
			return 100;
		}
	}

	int[] slots_bottom = {1, 1};
	int[] slots_top = {0, 0};
	int[] slots_sides = {1, 1};
	
    public int[] getAccessibleSlotsFromSide(int side)
    {
        if(side == 0)
        {
        	return slots_bottom;
        }
        else if(side == 1)
        {
        	return slots_top;
        }
        else
        {
        	return slots_sides;
        }
    }

    public boolean canInsertItem(int slot, ItemStack item, int side)
    {
        return this.isItemValidForSlot(slot, item);
    }

    public boolean canExtractItem(int slot, ItemStack item, int side)
    {
        return side != 0 || item.getItem() == electrolysmCore.ingots;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            NBTTagList tagList = tagCompound.getTagList("Inventory", 0);
            for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                    byte slot = tag.getByte("Slot");
                    if (slot >= 0 && slot < inventory.length) {
                            inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
                    }
            }
            
            time = tagCompound.getInteger("time");
            temp = tagCompound.getInteger("temp123456");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                            
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < inventory.length; i++) {
                    ItemStack stack = inventory[i];
                    if (stack != null) {
                            NBTTagCompound tag = new NBTTagCompound();
                            tag.setByte("Slot", (byte) i);
                            stack.writeToNBT(tag);
                            itemList.appendTag(tag);
                    }
            }
            tagCompound.setTag("Inventory", itemList);
            tagCompound.setInteger("time", time);
            tagCompound.setInteger("temp123456", temp);
    }

	public void setGuiDisplayName(String displayName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Block getBlock() 
	{
		return electrolysmCore.smeltory;
	}

    @Override
    public void closeInventory() { }

    @Override
    public boolean hasCustomInventoryName() { return true; }

    @Override
    public String getInventoryName() { return "Smeltory"; }

    @Override
    public void openInventory() { }


}
