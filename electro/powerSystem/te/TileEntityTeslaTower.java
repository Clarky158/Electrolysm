package assets.electrolysm.electro.powerSystem.te;

import java.util.Random;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import assets.electrolysm.electro.electrolysmCore;
import assets.electrolysm.electro.block.te.TileEntityIronFrame;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTeslaTower extends TileEntity {

	private float field_82138_c;
	private long field_82137_b;

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
	
	public boolean canDistribute(World world, int x, int y, int z)
	{
		if(this.isTowerFormed(world, x, y, z))
		{
			return true;
		}
		else if(this.isBaseRecievingPower(world, x, y, z))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void zapPlayer(World world, int x, int y, int z)
	{
		EntityPlayer player;
		int distanceTooClosestPlayer;
		Random rand = new Random();
		int transmitRange = this.getTransmitDistance(world, x, y, z);
		
		player = world.getClosestPlayer(x, y, z, (this.getTransmitDistance(world, x, y, z) * 5));
		distanceTooClosestPlayer = (int) player.getDistance(x, y, z);
		
		int playerX = (int)player.lastTickPosX; 
		int playerY = (int)player.lastTickPosY;
		int playerZ = (int)player.lastTickPosZ;
		
		if(rand.nextInt(this.getZapChance(distanceTooClosestPlayer, transmitRange)) == 1)
		{

			this.spawnLighningBolt(world, playerX, playerY, playerZ);
			player.performHurtAnimation();
        	player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 200, true));
		}
	}
	
    public int getZapChance(int distanceTooClosestPlayer, int transmitRange)
    {
    	int distance = distanceTooClosestPlayer;
    	int range = transmitRange;
    	int chance = distance + range;
    	
    	chance = chance * (distance/range);
    	chance = (int) (chance + (range/Math.PI + 7));
    	chance = (int) ((int)chance * (Math.sqrt(chance)));
    	System.out.println(chance);
    	if(distance < 7)
    	{
    		return (int)(chance / 4);
    	}
    	else
    	{
    		return chance;
    	}
	}

	public int getTransmitDistance(World world, int x, int y, int z)
    {
    	return 20;
	}

	public void updateEntity() 
    {
    	worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	
    	this.isTowerFormed(worldObj, xCoord, yCoord, zCoord);
    	this.zapPlayer(worldObj, xCoord, yCoord, zCoord);
    	this.func_82125_v_();
    }
	
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


}