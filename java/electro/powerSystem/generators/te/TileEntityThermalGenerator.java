package electro.powerSystem.generators.te;

import electro.powerSystem.PowerUsage;
import api.powerSystem.prefab.TileEntityGenerator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by Ben on 19/07/2014.
 */
public class TileEntityThermalGenerator extends TileEntityGenerator implements IInventory, IFluidHandler//, ISidedInventory
{
    private ItemStack[] inventory;

    public TileEntityThermalGenerator()
    {
        this.inventory = new ItemStack[1];
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
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "Thermal Generator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
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
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return false;
    }


    //Fluid Tank Stuff
    public FluidTank tank = new FluidTank(100000);

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if(resource == null){ return 0; }
        if(!this.canBurnFluid(resource.getFluid())) { return 0; }
        if(tank.getFluid() != null && !(resource.getFluid() == tank.getFluid().getFluid())) { return 0; }
        if(tank.getFluid() == null)
        {
            tank.setFluid(resource);
        }
        else
        {
            Fluid fluid = resource.getFluid();
            int amount = resource.amount + tank.getFluidAmount();
            if(amount >= tank.getCapacity()) {
                tank.setFluid(new FluidStack(fluid, tank.getCapacity()));
                return 0;
            } else {
                tank.setFluid(new FluidStack(fluid, amount));
            }
        }
        return resource.amount;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] {tank.getInfo()};
    }

    public boolean canBurnFluid(Fluid fluid)
    {
        Block block = fluid.getBlock();
        if(block == Blocks.lava)
        {
            return true;
        }
        return false;
    }

    int timer = 0;
    int maxTimer = 100;
    public int amountFluid = 0;

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(worldObj.isRemote) { return; }

        amountFluid = this.tank.getFluidAmount();
        //System.out.println(amountFluid);

        int bucket = 500;
        int producingTeU = PowerUsage.THERMAL_GENERATOR;
        if(canProduce(producingTeU) && tank.getFluidAmount() >= bucket)
        {
            if(timer ==  maxTimer) {
                timer= 0;
                this.useFluid(bucket);
                this.produce(producingTeU);
            } else {
                this.produce(producingTeU);
                timer = timer + 1;
            }
        }
    }

    public void useFluid(int amount)
    {
        int tankAmount = tank.getFluidAmount();
        int set = tankAmount - amount;
        tank.setFluid(new FluidStack(tank.getFluid(), set));
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

        int amountInTank = nbtTagCompound.getInteger("amountTank");
        if(amountInTank > 0)
        {
            tank.setFluid(new FluidStack(FluidRegistry.LAVA, amountInTank));
        }
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

        nbtTagCompound.setInteger("amountTank", tank.getFluidAmount());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("amount", tank.getFluidAmount());
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        int amountInTank = pkt.func_148857_g().getInteger("amount");
        if(amountInTank > 0)
        {
            tank.setFluid(new FluidStack(FluidRegistry.LAVA, amountInTank));
        }
    }
}
