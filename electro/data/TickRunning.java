package mods.Electrolysm.electro.data;

import java.util.EnumSet;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.Electrolysm.electro.electrolysmCore;
import mods.Electrolysm.electro.advAtomics.lasers.TileEntity.TileEntityLaserGen;
import mods.Electrolysm.electro.machines.platFurnace;
import net.minecraft.entity.player.EntityPlayerMP;

public class TickRunning implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		data.tick = data.tick + 1;
		if(data.tick > 20){
			data.tick = 0;
		}
		
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}


}