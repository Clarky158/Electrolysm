package assets.electrolysm.electro.powerSystem.te;

import java.util.Random;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import assets.electrolysm.electro.electrolysmCore;
import assets.electrolysm.electro.block.te.TileEntityIronFrame;
import assets.electrolysm.electro.common.CommonProxy;
import assets.electrolysm.electro.powerSystem.TeslaTransmittingServer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTeslaTower extends TileEntity {

	private float field_82138_c;
	private long field_82137_b;

	/**
	 * @return
	 * does something for the beacon beam.
	 */
	@SideOnly(Side.CLIENT)
    public float func_82125_v_()
    {
        if (!this.isTowerFormed(worldObj, xCoord, yCoord, zCoord))
        {
            return 0.0F;
        }
        else
        {
            int i = (int)(this.worldObj.getTotalWorldTime() - this.field_82137_b);
            this.field_82137_b = this.worldObj.getTotalWorldTime();

            if (i > 1)
            {
                this.field_82138_c -= (float)i / 40.0F;

                if (this.field_82138_c < 0.0F)
                {
                    this.field_82138_c = 0.0F;
                }
            }

            this.field_82138_c += 0.025F;

            if (this.field_82138_c > 1.0F)
            {
                this.field_82138_c = 1.0F;
            }

            return this.field_82138_c;
        }
    }
	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * checks to see if the tower is formed.
	 */
	public boolean isTowerFormed(World world, int x, int y, int z)
	{
		int copperCoil = electrolysmCore.largeCopperCoil.blockID;
		int ironFrame = electrolysmCore.ironFrames.blockID;
		
		if(world.canBlockSeeTheSky(x, y + 1, z))
		{
			if(world.getBlockId(x, y - 1, z) == ironFrame)
			{
				if(world.getBlockId(x, y - 2, z) == ironFrame)
				{
					if(world.getBlockId(x, y - 3, z) == ironFrame)
					{
						if(world.getBlockId(x, y - 4, z) == ironFrame)
						{
							if(world.getBlockId(x, y - 5, z) == ironFrame)
							{
								for(int xx = -1; xx <= 1; xx++)
								{
									for(int zz = -1; zz <= 1; zz++)
									{
										if(zz == 0 && xx == 0)
										{}
										else
										{
											if(world.getBlockId(x + xx, y - 1, z + zz) == copperCoil)
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
	
	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * returns true if the block is recieving a redstone signal
	 */
	public boolean isRecievingRedstonePower(World world, int x, int y, int z)
	{
		return (world.isBlockIndirectlyGettingPowered(x, y, z));
	}
	
	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * checks to see if the base of the tower is connected to a power source.
	 */
	public boolean isBaseRecievingPower(World world, int x, int y, int z)
	{
		int baseY = y - 5;
		
		TileEntityIronFrame teFrame = (TileEntityIronFrame)world.getBlockTileEntity(x, baseY, z);
			
		if(teFrame != null)
		{
			if(teFrame.isRecieving())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return boolean if it can distribute power
	 * decides if the tower can distribute power to the local area.
	 */
	public boolean canDistribute(World world, int x, int y, int z)
	{
		if(this.isTowerFormed(world, x, y, z))
		{
			if(this.isBaseRecievingPower(world, x, y, z))
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
	
	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * decides if the tower can distribute power to the local area and that it isn't recieving redstone power.
	 */
	public boolean canDistributeRedstone(World world, int x, int y, int z)
	{
		if(this.canDistribute(world, x, y, z))
		{
			if(!(this.isRecievingRedstonePower(world, x, y, z)))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * zaps the player depending on closeness to the tower
	 */
	public void zapPlayer(World world, int x, int y, int z)
	{
		EntityPlayer player;
		int distanceTooClosestPlayer;
		Random rand = new Random();
		int transmitRange = this.getTransmitDistance(world, x, y, z);
		
		player = world.getClosestPlayer(x, y, z, (this.getTransmitDistance(world, x, y, z) * 5));
		
		if(player != null)
		{
			distanceTooClosestPlayer = (int) player.getDistance(x, y, z);
		
			int playerX = (int)player.lastTickPosX; 
			int playerY = (int)player.lastTickPosY;
			int playerZ = (int)player.lastTickPosZ;
		
			if(this.canDistributeRedstone(world, x, y, z))
			{
				if(rand.nextInt(this.getZapChance(distanceTooClosestPlayer, transmitRange)) == 1)
				{
					this.spawnLighningBolt(world, playerX, playerY, playerZ);
					player.performHurtAnimation();
					player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 200, true));
				}
			}
		}
	}
	/**
	 * @param distanceTooClosestPlayer
	 * @param transmitRange
	 * @return chance
	 * Uses distance too the closest player and the transmit range of the tower to calculate
	 * the chance of the player being zapped by lightning
	 */
    public int getZapChance(int distanceTooClosestPlayer, int transmitRange)
    {
    	int distance = distanceTooClosestPlayer;
    	int range = transmitRange;
    	int chance = distance + range;
    	
    	chance = chance * (distance/range);
    	chance = (int) (chance + (range/Math.PI + 7));
    	chance = (int) ((int)chance * (Math.sqrt(chance)));
    	if(distance < 2)
    	{
    		return (int)chance + 4;
    	}
    	else
    	{
    		return (int)(chance * 4);
    	}
	}
    /**
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     * returns the distance the tower can transmit power, based on tier.
     */
	public int getTransmitDistance(World world, int x, int y, int z)
    {
		int h = y;
		int r = CommonProxy.RANGE_TIER[(this.getTier(world, x, y, z))];
		
		return (int)(r + (Math.sqrt(h * 3)));
	}

	/**
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * gets the current tier of the crystal in the inventory
	 */
	private int getTier(World world, int x, int y, int z) 
	{
		int tier = 1;
		return tier - 1;
	}

	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param te
	 * This should keep the the transmitter (Tesla Tower) chunk loaded!
	 */
	public static void keepChunkLoaded(World world, int x, int y, int z, TileEntity te)
	{
	}
	
	/**
	 * updates the whole tile entity
	 */
	public void updateEntity() 
    {
    	worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	
    	this.transmitPower(worldObj, xCoord, yCoord, zCoord, this.getTransmitDistance(worldObj, xCoord,
    			yCoord, zCoord), 1, "username", this.getRecievingTeU(worldObj, xCoord, yCoord, zCoord));
    	//Temporary (For images)
    	//this.zapPlayer(worldObj, xCoord, yCoord, zCoord);
    	
    	if(!(this.isRecievingRedstonePower(worldObj, xCoord, yCoord, zCoord)) && 
    			this.canDistribute(worldObj, xCoord, yCoord, zCoord))
    	{
    		//0 = active = no redstone
    		//1 = deactive = redstone
    		
    		if(this.blockMetadata == 1)
    		{
    			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 0);
    			TeslaTransmittingServer.clearAll();
    		}
    	}
    	else
    	{
    		if(this.blockMetadata == 0)
    		{
    			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 0);
    			TeslaTransmittingServer.clearAll();
    		}
    	}
    }
	
	public int getRecievingTeU(World world, int x, int y, int z)
	{
		int TeU = 0;
		if(this.canDistributeRedstone(world, x, y, z))
		{
			TileEntityIronFrame te = (TileEntityIronFrame) world.getBlockTileEntity(x, y - 5, z);
			TeU = te.getRecievingTeU(world, x, y - 5, z);
		}
		return TeU;
	}
	/**
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * spawns lightning bolt.
	 */
	public EntityLightningBolt spawnLighningBolt(World world, double x, double y, double z) 
	{
		Random rand = new Random();
		EntityLightningBolt bolt = new EntityLightningBolt(world, x, y, z);

		EntityLightningBolt entity = bolt;
		bolt.setLocationAndAngles(x, y, z, MathHelper
				.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
		world.spawnEntityInWorld(bolt);
		this.worldObj.playSoundEffect(x, y, z, "ambient.weather.thunder1",
				10000.0F, 0.8F + rand.nextFloat() * 0.2F);
		
        this.worldObj.playSoundEffect(x, y, z, "random.explode1",
        		2.0F, 0.5F + rand.nextFloat() * 0.2F);		return bolt;
	}

	public int transmitPower(World world, int x, int y, int z, int range, int freq, String username, int TeU)
	{
		if(this.canDistributeRedstone(world, x, y, z))
		{
	    	this.zapPlayer(worldObj, xCoord, yCoord, zCoord);
	    	if(world.isRemote)
	    	{
	    		this.func_82125_v_();
	    	}
	    	this.keepChunkLoaded(world, x, y, z, this);
			TeslaTransmittingServer.saveTransmition(world.provider.getDimensionName(), x, y, z, range, 
					freq, username, TeU);
			
			return TeU;
		}
		
		return 0;
	}

}
