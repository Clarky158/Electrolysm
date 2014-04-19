package assets.electrolysm.electro.world;

import java.util.Random;

import assets.electrolysm.electro.electrolysmCore;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
public class WorldGenOres implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        //Make sure it's not generating in the end or nether
        if (world.provider.dimensionId != 1 && world.provider.dimensionId != -1)
        {
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
        }
        else
        {
            generateEndNether(world, random, chunkX * 16, chunkZ * 16);
        }
    }
    private void generateEndNether(World world, Random random, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 10; i++)
        {
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(256);
            int zCoord = chunkZ + random.nextInt(16);
        }
    }
    //Graphite Ore Generation
    private void generateSurface(World world, Random random, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 10; i++)
        {
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(11);
            int zCoord = chunkZ + random.nextInt(16);
            (new WorldGenMinable(electrolysmCore.graphite.blockID, 6)).generate(world, random, xCoord, yCoord, zCoord);
        }

        for (int i = 0; i < 10; i++)
        {
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(65);
            int zCoord = chunkZ + random.nextInt(16);
            (new WorldGenMinable(electrolysmCore.copperOre.blockID, 10)).generate(world, random, xCoord, yCoord, zCoord);
        }

        for (int i = 0; i < 10; i++)
        {
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(20);
            int zCoord = chunkZ + random.nextInt(16);
            (new WorldGenMinable(electrolysmCore.sulphurOre.blockID, 15)).generate(world, random, xCoord, yCoord, zCoord);
        }
        
        for (int i = 0; i < 4; i++)
        {
            int xCoord = chunkX + random.nextInt(16);
            int yCoord = random.nextInt(10);
            int zCoord = chunkZ + random.nextInt(16);
            (new WorldGenMinable(electrolysmCore.Yttrium.blockID, 5)).generate(world, random, xCoord, yCoord, zCoord);
        }
    }
}