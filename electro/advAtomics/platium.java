package mods.Electrolysm.electro.advAtomics;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.Electrolysm.electro.electrolysmCore;
import mods.Electrolysm.electro.data.data;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class platium extends Block {

	private static final String itemIDName = "platinum";
	private static final int breaking = 150;

	public platium(int par1) {
		super(par1, Material.iron);
		// TODO Auto-generated constructor stub
	
	this.setCreativeTab(electrolysmCore.TabElectrolysm);
	this.setUnlocalizedName(itemIDName);
	this.setHardness(breaking);
	this.setResistance(breaking);
	}

	
	@Override
	public void registerIcons(IconRegister reg)
	{
			this.blockIcon = reg.registerIcon("Electrolysm:" + itemIDName);
	}


}