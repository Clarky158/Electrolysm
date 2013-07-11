package mods.Electrolysm.electro.physics.lasers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.Electrolysm.electro.electrolysmCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class laserLight extends Item {
	
	public String nameBefore = "adv." + this.getClass();
	public String name = nameBefore.replace("adv.class mods.Electrolysm.electro.advAtomics.lasers.", "");

	public laserLight(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	
	this.setUnlocalizedName(name);
	this.setCreativeTab(electrolysmCore.TabElectrolysmPhysics);

	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("Electrolysm:" + name);	
	}
	
}
