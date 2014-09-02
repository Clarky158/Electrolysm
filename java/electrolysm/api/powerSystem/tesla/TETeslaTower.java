package electrolysm.api.powerSystem.tesla;

import electrolysm.api.items.Fetcher;
import electrolysm.api.powerSystem.interfaces.IWorkableMachine;
import electrolysm.api.powerSystem.prefab.TEPowerCore;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Clarky158 on 01/08/2014.
 * <p/>
 * Electrolysm is an open source Minecraft mod
 * released under version 3 of the GNU Lesser
 * General Public License. This means that
 * the source of this mod is publicly available
 * and you have certain rights with respective
 * to the code.
 */
public class TETeslaTower extends TileEntity implements ITeslaTower, IWorkableMachine{

    private TEPowerCore powerCore = null;
    public boolean isTransmitting = false;
    private boolean goodFrequency = true;
    private int frequency = 0;

    @Override
    public int getTransmitPower()
    {
        return 100;
    }

    @Override
    public boolean isFormed(World world, int x, int y, int z) {
        Block copperCoil = Block.getBlockFromItem(Fetcher.getItem("copperCoil", 1).getItem());
        Block ironFrame = Block.getBlockFromItem(Fetcher.getItem("ironFrames", 1).getItem());

        if(world.canBlockSeeTheSky(x, y + 1, z))
        {
            if(world.getBlock(x, y - 1, z) == ironFrame)
            {
                if(world.getBlock(x, y - 2, z) == ironFrame)
                {
                    if(world.getBlock(x, y - 3, z) == ironFrame)
                    {
                        if(world.getBlock(x, y - 4, z) == ironFrame)
                        {
                            if(world.getTileEntity(x, y - 5, z) instanceof TEPowerCore)
                            {
                                for(int xx = -1; xx <= 1; xx++)
                                {
                                    for(int zz = -1; zz <= 1; zz++)
                                    {
                                        if(zz == 0 && xx == 0)
                                        {}
                                        else
                                        {
                                            if(world.getBlock(x + xx, y - 1, z + zz) == copperCoil)
                                            {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean canDistribute(World world, int x, int y, int z) {
        return isFormed(world, x, y, z) && this.canWork(getTransmitPower()) && this.getFrequency() > 0 && goodFrequency;
    }

    @Override
    public void doNegativeAffects(World world, int x, int y, int z) {
        Random rand = new Random();
        if(rand.nextInt(10) != 2) { return; }

        EntityPlayer player = world.getClosestPlayer(x, y, z, 100);
        if(player != null){
            String username = player.getDisplayName();
            if(username.replace(" ", "").toLowerCase().equals("ellio98")){
                player.setHealth(-10F);
                MinecraftServer.getServer().addChatMessage(new ChatComponentTranslation(player.getDisplayName() + " was killed by a tesla tower"));
                return;
            }
            float[] ratio = calculateNegativeRatio(world, x, y, z, player);
            if(rand.nextFloat() <= (ratio[0] / (ratio[1]))) {
                this.spawnLighningBolt(world, player.posX, player.posY - 1, player.posZ);
            }
        }
        //TODO
    }

    private float[] calculateNegativeRatio(World world, int x, int y, int z, EntityPlayer player) {
        //TODO
        float distance = TeslaTransmittingServer.calculateDistance(player.posX, player.posY, player.posY, x, y, z);
        int weight = calculatePlayerWeight(player.inventory);
        return new float[] {weight, (float)Math.pow(distance, 1.8)};
    }

    private int calculatePlayerWeight(InventoryPlayer inventory) {
        int weight = 1;
        for(int i =0; i < inventory.mainInventory.length; i++){
            if(inventory.mainInventory[i] != null) {
                weight = weight + (2 * inventory.mainInventory[i].stackSize);
            }
        }
        return weight;
    }

    public EntityLightningBolt spawnLighningBolt(World world, double x, double y, double z)
    {
        Random rand = new Random();
        EntityLightningBolt bolt = new EntityLightningBolt(world, x, y, z);
        EntityLightningBolt entity = bolt;
        bolt.setLocationAndAngles(x, y, z, MathHelper
                .wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
        //world.spawnEntityInWorld(bolt);
        if(world.isRemote) {
            this.worldObj.playSoundEffect(x, y, z, "ambient.weather.thunder1",
                    10000.0F, 0.8F + rand.nextFloat() * 0.2F);
            this.worldObj.playSoundEffect(x, y, z, "random.explode1",
                    2.0F, 0.5F + rand.nextFloat() * 0.2F);
        }
        return bolt;
    }


    @Override
    public void transmitPower(World world, int x, int y, int z, int power, int frequency) {
        world.markBlockForUpdate(x, y, z);
        TeslaTower tt = this.getCode();
        if (!getWorldObj().isRemote) {
            this.checkFrequency(this.getFrequency());
            if (this.canWork(getTransmitPower())) {
                if (this.canDistribute(world, x, y, z)) {
                    //this.doNegativeAffects(world, x, y, z);
                    this.isTransmitting = true;
                    work(getTransmitPower());
                    TeslaTransmittingServer.registerSendingTesla(tt);
                    return;
                }
            }
            TeslaTransmittingServer.removeTesla(tt);
            this.isTransmitting = false;
        }
        return;
    }

    private void checkFrequency(int frequency) {
        TeslaTower tt = TeslaTransmittingServer.doesFrequencyExist(frequency);
        if(tt != null && !tt.equals(getCode())){
            goodFrequency = false;
        } else {
            goodFrequency = true;
        }
    }

    public boolean isGoodFrequency() {
        return goodFrequency;
    }

    @Override
    public TeslaTower getCode() {
        return new TeslaTower(getWorld().provider.dimensionId, x(), y(), z(), getTransmitPower(), getFrequency());
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(int frequency) {
        TeslaTransmittingServer.towerList.clear();
        this.frequency = frequency;
    }

    @Override
    public boolean canWork(int teu) {
        if(powerCore != null) {
            return (powerCore.canDrain(teu));
        }

        return false;
    }

    @Override
    public void work(int teU) {
        if(powerCore != null && powerCore.canDrain(teU))
        {
            powerCore.drainPower(teU);
        }
    }

    @Override
    public World getWorld() {
        return this.getWorldObj();
    }

    @Override
    public int x() {
        return xCoord;
    }

    @Override
    public int y() {
        return yCoord;
    }

    @Override
    public int z() {
        return zCoord;
    }

    @Override
    public void updateEntity() {
        keepChunkLoaded();
        this.transmitPower(getWorld(), x(), y(), z(), getTransmitPower(), getFrequency());
        powerCore = getPowerCore();
    }

    public void keepChunkLoaded()
    {
        getWorld().getChunkProvider().loadChunk(x(), z());
    }

    public TEPowerCore getPowerCore() {
        if(getWorld().getTileEntity(x(), y() - 5, z()) instanceof TEPowerCore) {
            return (TEPowerCore)(getWorld().getTileEntity(x(), y() - 5, z()));
        }
        return null;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("isTransmitting", isTransmitting);
        tag.setBoolean("goodFrequency", goodFrequency);
        return new S35PacketUpdateTileEntity(x(), y(), z(), 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        isTransmitting = pkt.func_148857_g().getBoolean("isTransmitting");
        goodFrequency = pkt.func_148857_g().getBoolean("goodFrequency");
    }
}
