package electro.block.advMachines.te;

import electro.crafting.InjectorRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityInjector extends TileEntity implements IInventory
{
    private ItemStack[] inventory = new ItemStack[4];

    public static final int INGOT_SLOT_1_INDEX = 0;
    public static final int INGOT_SLOT_2_INDEX = 1;
    public static final int FUEL_INVENTORY_INDEX = 2;
    public static final int OUTPUT_INVENTORY_INDEX = 3;

    public int furnaceBurnTime = 0;
    public int currentItemBurnTime = 0;
    public int furnaceCookTime = 0;

    @Override
    public int getSizeInventory()
    {
        return this.inventory.length;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return this.inventory[slot];
    }

    @SuppressWarnings("null")
    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack itemStack = getStackInSlot(slot);

        if (itemStack != null)
        {
            setInventorySlotContents(slot, null);
        }
        else
        {
            itemStack = itemStack.splitStack(amount);

            if (itemStack.stackSize == 0)
            {
                setInventorySlotContents(slot, null);
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);

        if (itemStack != null)
        {
            setInventorySlotContents(slot, null);
        }

        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.inventory[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items", 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }

        this.furnaceBurnTime = nbtTagCompound.getShort("BurnTime");
        this.furnaceCookTime = nbtTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(inventory[1]);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
    }

    @Override
    public void updateEntity()
    {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;

        if (this.furnaceBurnTime > 0)
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.furnaceBurnTime == 0)
            {
                if (this.canDoubleSmelt())
                {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.inventory[2]);

                    if (this.furnaceBurnTime > 0)
                    {
                        flag1 = true;

                        if (this.inventory[2] != null)
                        {
                            --this.inventory[2].stackSize;

                            if (this.inventory[2].stackSize == 0)
                            {
                                this.inventory[2] = this.inventory[2].getItem().getContainerItem(inventory[2]);
                            }
                        }
                    }
                }
            }

            if (/*this.isBurning() && */this.canDoubleSmelt())
            {
                ++this.furnaceCookTime;

                if (this.furnaceCookTime == 400)
                {
                    this.furnaceCookTime = 0;
                    this.smeltDoubleItem();
                    flag1 = true;
                }
            }
            else
            {
                this.furnaceCookTime = 0;
            }

            if (flag != this.furnaceBurnTime > 0)
            {
                flag1 = true;
            }

            if (flag1)
            {
                this.markDirty();
                //platFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 400;
        }

        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }

    public int getCookProgressScaled(int par1)
    {
        return this.furnaceCookTime * par1 / 400;
    }

    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    private boolean canDoubleSmelt()
    {
        if (this.inventory[0] == null)
        {
            return false;
        }
        else if (this.inventory[1] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = InjectorRecipes.smelting().getDoubleSmeltingResult(this.inventory[0], this.inventory[1]);

            if (itemstack == null)
            {
                return false;
            }

            if (this.inventory[0].stackSize < InjectorRecipes.smelting().getSlot1ReduceAmount(this.inventory[0]).stackSize)
            {
                return false;
            }

            if (this.inventory[1].stackSize < InjectorRecipes.smelting().getSlot2ReduceAmount(this.inventory[1]).stackSize)
            {
                return false;
            }

            if (this.inventory[3] == null)
            {
                return true;
            }

            if (!this.inventory[3].isItemEqual(itemstack))
            {
                return false;
            }

            int result = inventory[3].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    public void smeltDoubleItem()
    {
        if (this.canDoubleSmelt())
        {
            ItemStack itemstack = InjectorRecipes.smelting().getDoubleSmeltingResult(this.inventory[0], this.inventory[1]);

            if (this.inventory[3] == null)
            {
                this.inventory[3] = itemstack.copy();
            }
            else if (this.inventory[3].isItemEqual(itemstack))
            {
                inventory[3].stackSize += itemstack.stackSize;
            }

            this.inventory[0].stackSize -= InjectorRecipes.smelting().getSlot1ReduceAmount(this.inventory[0]).stackSize;
            this.inventory[1].stackSize -= InjectorRecipes.smelting().getSlot2ReduceAmount(this.inventory[1]).stackSize;

            if (this.inventory[0].stackSize <= 0)
            {
                this.inventory[0] = null;
            }

            if (this.inventory[1].stackSize <= 0)
            {
                this.inventory[1] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack itemStack)
    {
        if (itemStack == null)
        {
            return 0;
        }
        else
        {
            Item item = itemStack.getItem();

            if (itemStack.getItem() instanceof ItemBlock)
            {
                return 126;
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
            {
                return 200;
            }

            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
            {
                return 200;
            }

            if (item == Items.lava_bucket)
            {
                return 1000;
            }

            return GameRegistry.getFuelValue(itemStack);
        }
    }

    public static boolean isItemFuel(ItemStack itemStack)
    {
        return getItemBurnTime(itemStack) > 0;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        if (i == 3)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void closeInventory() { }

    @Override
    public boolean hasCustomInventoryName() { return true; }

    @Override
    public String getInventoryName() { return "Injector"; }

    @Override
    public void openInventory() { }
}