package electro.powerSystem.tesla;

import api.powerSystem.tesla.TETeslaTower;
import electro.Electrolysm;
import electro.misc.crafting.items.BlockCrafting;
import electro.powerSystem.tesla.te.TileEntityTeslaCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Clarky158 on 30/08/2014.
 */
public class BlockTeslaCore extends BlockContainer {
    public BlockTeslaCore(String name) {
        super(Material.iron);
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setBlockName(name);
        this.setHardness(9.1576F);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityTeslaCore();
    }

    private IIcon topActiveIcon;
    private IIcon topIcon;

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon("electrolysm:" + "teslaTowerCore/sides");
        topActiveIcon = reg.registerIcon("electrolysm:" + "teslaTowerCore/tier1Top");
        topIcon = reg.registerIcon("electrolysm:" + "teslaTowerCore/tier1TopOff");
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if(world.getTileEntity(x, y, z) instanceof TETeslaTower){
            if(side == 1 && ((TETeslaTower) world.getTileEntity(x, y, z)).canWork
                    (((TETeslaTower) world.getTileEntity(x, y, z)).getTransmitPower())){
                return topActiveIcon;
            }
            else if(side == 1){
                return topIcon;
            }
        }
        return blockIcon;
    }
}
