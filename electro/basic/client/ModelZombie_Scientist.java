package mods.Electrolysm.electro.basic.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelZombie_Scientist extends ModelBiped
{
    public ModelZombie_Scientist()
    {
        this(0.0F, false);
    }

    protected ModelZombie_Scientist(float par1, float par2, int par3, int par4)
    {
        super(par1, par2, par3, par4);
    }

    public ModelZombie_Scientist(float par1, boolean par2)
    {
        super(par1, 0.0F, 64, par2 ? 32 : 64);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        float f6 = MathHelper.sin(this.onGround * (float)Math.PI);
        float f7 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * (float)Math.PI);
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
        this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
        this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F);
        this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F);
        this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
    }
}