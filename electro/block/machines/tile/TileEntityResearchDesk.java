package assets.electrolysm.electro.block.machines.tile;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import assets.electrolysm.electro.electrolysmCore;
import assets.electrolysm.electro.research.IResearch;
import assets.electrolysm.electro.research.Research;

public class TileEntityResearchDesk extends TileEntity implements IInventory {

	private ItemStack[] inventory;

	public TileEntityResearchDesk() {
		this.inventory = new ItemStack[3];
	}

	@Override
	public int getSizeInventory() 
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);

		if (stack != null) {
			if (stack.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amount);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);

		if (stack != null) {
			setInventorySlotContents(slot, null);
		}

		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		// TODO Auto-generated method stub
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "Research Desk";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
    public void updateEntity() 
	{
		
		ItemStack inStack = getStackInSlot(0);
		ItemStack card = getStackInSlot(2);
		ItemStack output = Research.research().getResearch(inStack);
	}
/*
	@Override
	public int getResearch(ItemStack input, ItemStack card, ItemStack output)
	{
		int inputID = input.itemID;
		int cardLevel = card.getItemDamage();
		
		try{
			if(cardLevel == 1)
			{
				for(int IDList = 0; IDList < 2; IDList++)
				{
					if(inputID == Research.levelIDs1[IDList])
					{
						System.out.println("ItemIsCorrect");
						return Research.researchID1[IDList];
					}
				}
			}
		}finally{
			
	}
		return 0;
	}*/
}

