package mods.Electrolysm.electro.physics.robotics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.Electrolysm.electro.electrolysmCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class servo extends Item {

	public servo(int id) {
		super(id);
		this.setCreativeTab(electrolysmCore.TabElectrolysmPhysics);
		this.setUnlocalizedName("servo");
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("Electrolysm:" + this.getUnlocalizedName().replace("item.", ""));	
	}

}
