package kr.goldenmine.inumodelloader.inumodelloader.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup  TUTORIAL_TAB = new ItemGroup ("tutorial_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TITANIUM_INGOT.get());
        }
    };

    public static final ItemGroup  INU_MODELS_TAB = new ItemGroup ("inu_models") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.INU_ITEM.get());
        }
    };
}
