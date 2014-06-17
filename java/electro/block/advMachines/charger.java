package electro.block.advMachines;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import electro.electrolysmCore;
import electro.block.advMachines.te.TileEntityCharger;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class charger extends BlockContainer
{
    public charger()
    {
        super(Material.iron);
        this.setHardness(4F);
        this.setCreativeTab(electrolysmCore.TabElectrolysm);
        this.setHardness(4F);
        this.setResistance(7F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityCharger();
    }

    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
