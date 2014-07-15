package electro.powerSystem;

import cpw.mods.fml.common.registry.LanguageRegistry;
import electro.Electrolysm;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class basicEnergyStorage extends Block
{
    public basicEnergyStorage()
    {
        super(Material.iron);
        this.setCreativeTab(Electrolysm.TabElectrolysm);
        this.setHardness(5F);
        LanguageRegistry.addName(this, "Energy Storage");
        setHarvestLevel("pickaxe",2);
        setStepSound(soundTypeMetal);
    }
}