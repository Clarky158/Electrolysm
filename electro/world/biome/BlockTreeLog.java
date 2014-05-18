package assets.electrolysm.electro.world.biome;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import assets.electrolysm.electro.electrolysmCore;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTreeLog extends BlockLog {

	public static int type;
	public String name;
	@SideOnly(Side.CLIENT)
	private static Icon[] icon = new Icon[3];
	
	public BlockTreeLog(int par1, int type1, String unlocalName) {
		super(par1);
		
		this.setCreativeTab(electrolysmCore.TabElectrolysm);
		this.setUnlocalizedName(unlocalName);
		this.setStepSound(soundWoodFootstep);
		this.setHardness(2.0F);
		this.type = type1;
		name = unlocalName;
		GameRegistry.registerBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
		if(type == 0 || name == "treeLog")
		{
			if(side == 0 || side == 1)
			{
				 return icon[2];
			}
			else
			{
				return icon[1];
			}
		}
		else
		{
			return icon[0];
		}
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
		String MOD_ID = "electrolysm:";
		icon[0] = reg.registerIcon(MOD_ID + "treeLeaves");
		icon[1] = reg.registerIcon(MOD_ID + "treeLog1");
		icon[2] = reg.registerIcon(MOD_ID + "treeLog2");
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return WorldGenDiseasedTree.treeLog.blockID;
    }

}
