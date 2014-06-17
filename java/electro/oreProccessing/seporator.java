package electro.oreProccessing;

import assets.electrolysm.electro.electrolysmCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class seporator extends oreProcessMachineBase
{
    public String className = "" + this.getClass();
    public String unlocalName = className.replace("assets.electrolysm.electro", "");
    public String textureName = unlocalName.replace(".", "/");

    public seporator(int par1, Material par2Material, boolean isActive)
    {
        super(par1, Material.iron, isActive);
        this.setCreativeTab(electrolysmCore.TabElectrolysm);
        this.setUnlocalizedName(unlocalName);
        this.setHardness(6.0F);
        this.active = isActive;
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            player.openGui(electrolysmCore.GUIInstance, 0, world, x, y, z);
            return true;
        }
    }
}
