package electrolysm.electro.world.biome;

import java.util.List;
import java.util.Random;

import electrolysm.electro.Electrolysm;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class treeSapling extends BlockSapling {

	public treeSapling() {
		super();

		this.setCreativeTab(Electrolysm.TabElectrolysm);
		this.setTickRandomly(true);
        this.setBlockName("diseasedSapling");
       // this.getUnlocalizedName("diseasedSapling");
	}

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        super.registerBlockIcons(reg);
        this.blockIcon = reg.registerIcon("electrolysm:diseasedSapling");
    }

    public void updateTick(World world, int x, int y, int z, Random random)
	{
		new WorldGenDiseasedTree(true, 6).generate(world, random, x, y, z);
	}
	
	@Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(Electrolysm.diseasedSapling, 1, 0));
    }

}
