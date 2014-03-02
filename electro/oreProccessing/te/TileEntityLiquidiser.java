package assets.electrolysm.electro.oreProccessing.te;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import assets.electrolysm.api.powerSystem.meter.IMeterable;
import assets.electrolysm.api.powerSystem.usageMachine.TileEntityEnergyMachine;

public class TileEntityLiquidiser extends TileEntityEnergyMachine implements IMeterable, IInventory//, ISidedInventory
{
    private ItemStack[] inventory;
    public boolean isOpen;
        
	public TileEntityLiquidiser()
	{
		super();
		
		this.activationEnergy = 67;
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
    public String getInvName()
    {
        // TODO Auto-generated method stub
        return "Research Desk";
    }

    @Override
    public boolean isInvNameLocalized()
    {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void openChest()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void closeChest()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void updateEntity()
    {
    	
    }

}