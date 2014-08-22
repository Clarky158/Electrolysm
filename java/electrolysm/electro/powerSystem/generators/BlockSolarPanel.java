package electrolysm.electro.powerSystem.generators;

import electrolysm.api.powerSystem.prefab.BlockEnergy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrolysm.electro.Electrolysm;
import electrolysm.electro.powerSystem.generators.te.TileEntitySolarPanel;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Ben on 19/07/2014.
 */
public class BlockSolarPanel extends BlockEnergy
{
    @SideOnly(Side.CLIENT)
    public IIcon top;
    @SideOnly(Side.CLIENT)
    public IIcon bottom;

    public BlockSolarPanel()
    {
        super();
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setHardness(3.14159F);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("electrolysm:powerGenSide");
        this.bottom = reg.registerIcon("electrolysm:powerGenTop");
        this.top = reg.registerIcon("electrolysm:solarPanel");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return top;
        } else if (side == 0) {
            return bottom;
        } else {
            return blockIcon;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySolarPanel();
    }
}
