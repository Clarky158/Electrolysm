package assets.electrolysm.electro.research;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import assets.electrolysm.electro.electrolysmCore;
import assets.electrolysm.electro.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class researchPaper extends Item{

	private String message = "Research Complete!";
	private boolean finished;
	private int numberOfSub = 1; 
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	
	public researchPaper(int id) {
		super(id);
		this.setCreativeTab(electrolysmCore.TabElectrolysm);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(!player.isSneaking())
		{
			finished = true;
		}
		if(player.isSneaking())
		{
			finished = false;
		}
        return stack;
    }
	
	public String getUnlocalizedName(ItemStack stack)
	{
		return "researchNote" + stack.getItemDamage();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister red)
	{
		icons = new Icon[CommonProxy.RESEARCH_NOTES.length];
		for(int i = 0; i < icons.length; i++)
		{
			icons[i] = red.registerIcon("electrolysm:" + CommonProxy.RESEARCH_NOTES[i]);
		}
	}
   
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int dmg)
	{
		if(finished)
		{
			return icons[1];
		}
		if(!finished)
		{
			return icons[0];
		}
		return icons[100];
	}
	
	
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) 
    {
    	for(int i = 0; i < Research.researchNames1.length; i++)
    	{
    		if(stack.getItemDamage() == i)
    		{
    			list.add("Research: " + Research.researchNames1[i]);
    		}
    	}
    }

}