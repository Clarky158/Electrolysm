package electrolysm.electro;

import net.minecraft.item.Item;
import electrolysm.electro.oreProccessing.hammer;

public enum electrolysmItems
{
    HAMMER(hammer.unlocalName(), hammer.class);

    electrolysmItems[] itemList = electrolysmItems.values();

    private electrolysmItems(String someStuff, Class <? extends Item > itemClass)
    {
    }
}
