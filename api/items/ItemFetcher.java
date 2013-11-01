package assets.electrolysm.api.items;

import net.minecraft.item.Item;

public class ItemFetcher {

    private static Class core;
    private static Item[] itemList;

    static {
            try {
                    core = Class.forName("assets.electrolysm.electro.electrolysmCore", false, ItemFetcher.class.getClassLoader());
                    itemList = (Item[])core.getField("basicItems").get(null);
            }
            catch (ClassNotFoundException e) {
                    System.out.println("electrolysmCore class not found!");
                    e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                    System.out.println("electrolysmCore class not read!");
                    e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                    System.out.println("electrolysmCore class not read!");
                    e.printStackTrace();
            }
            catch (NoSuchFieldException e) {
                    System.out.println("electrolysmCore class not read!");
                    e.printStackTrace();
            }
            catch (SecurityException e) {
                    System.out.println("electrolysmCore class not read!");
                    e.printStackTrace();
            }
    }


    /** For fetching items by enum ordinal */
    public static Item getItemByOrdinal(int ordinal) {
            return itemList[ordinal];
    }

    public static Item getItemByUnlocalizedName(String name) {
            for (int i = 0; i < itemList.length; i++) {
                    Item it = itemList[i];
                    String sg = it.getUnlocalizedName();
                    if (name.equals(sg))
                            return it;
            }
            return null;
    }

}
