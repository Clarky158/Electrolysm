package assets.electrolysm.electro.handlers.nei;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import assets.electrolysm.electro.common.CommonProxy;
import assets.electrolysm.electro.oreProccessing.gui.GUILiquidiser;
import assets.electrolysm.electro.oreProccessing.recipes.LiquidiserRecipes;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class LiquidiserRecipeHandler extends TemplateRecipeHandler {

	public class SmeltingPair extends CachedRecipe
    {
        public SmeltingPair(ItemStack ingred, ItemStack result)
        {
            ingred.stackSize = 1;
            //											x	y
            this.ingred = new PositionedStack(ingred, 75, 15 - 16 + 3);
            this.result = new PositionedStack(result, 75, 50 + 1 - 2);
        }
        
        public PositionedStack getIngredient()
        {
            int cycle = cycleticks / 48;
            if(ingred.item.getItemDamage() == -1)
            {
                PositionedStack stack = ingred.copy();
                int maxDamage = 0;
                do
                {
                    maxDamage++;
                    stack.item.setItemDamage(maxDamage);
                }
                while(NEIClientUtils.isValidItem(stack.item));
                
                stack.item.setItemDamage(cycle % maxDamage);
                return stack;
            }
            return ingred;
        }
        
        public PositionedStack getResult()
        {
            return result;
        }
        
        
        PositionedStack ingred;
        PositionedStack result;
    }
    
        
        public PositionedStack stack;
        public int burnTime;
    

    @Override
    public void loadTransferRects()
    {
        //transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "liquidising"));
        transferRects.add(new RecipeTransferRect(new Rectangle(80, 80, 80, 80), "liquidising"));

    }
    
    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GUILiquidiser.class;
    }
    
    @Override
    public String getRecipeName()
    {
        return "Liquidising";
    }
    
    @Override
    public TemplateRecipeHandler newInstance()
    {
        return super.newInstance();
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if(outputId.equals("liquidising") && getClass() == LiquidiserRecipeHandler.class)//don't want subclasses getting a hold of this
        {
        	HashMap<List<Integer>, ItemStack> recipes = (HashMap<List<Integer>, ItemStack>) LiquidiserRecipes.liquidising().getLiquidsMap();
            
            for(Entry<List<Integer>, ItemStack> recipe : recipes.entrySet())
            {
                ItemStack item = recipe.getValue();
                arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)), item));
            }
        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }
    }
    
    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
    	HashMap<List<Integer>, ItemStack> recipes = (HashMap<List<Integer>, ItemStack>) LiquidiserRecipes.liquidising().getLiquidsMap();
        
        for(Entry<List<Integer>, ItemStack> recipe : recipes.entrySet())
        {
            ItemStack item = recipe.getValue();
            if(NEIServerUtils.areStacksSameType(item, result))
            {
                arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)), item));
            }
        }
    }
    
    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients)
    {
        if(inputId.equals("fuel") && getClass() == LiquidiserRecipeHandler.class)//don't want subclasses getting a hold of this
        {
            loadCraftingRecipes("liquidising");
        }
        else
        {
            super.loadUsageRecipes(inputId, ingredients);
        }
    }
    
    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
    	HashMap<List<Integer>, ItemStack> recipes = (HashMap<List<Integer>, ItemStack>) LiquidiserRecipes.liquidising().getLiquidsMap();
        
        for(Entry<List<Integer>, ItemStack> recipe : recipes.entrySet())
        {
            ItemStack item = recipe.getValue();
            if(NEIServerUtils.areStacksSameType(item, ingredient))
            {
                arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)), item));
            }
        }
    }
    
    @Override
    public String getGuiTexture()
    {
        return CommonProxy.LIQUIDISER_GUI.toString();
    }

    @Override
    public void drawExtras(int recipe)
    {
        //drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
        drawProgressBar(64 - 5, 20 - 11, 0, 166, 90, 16, 90, 0);
    }
    
    @Override
    public String getOverlayIdentifier()
    {
        return "Liquidiser";
    }
    
    @Override
    public int recipiesPerPage()
    {
    	return 1;
    }
}
