package kr.goldenmine.inumodelloader.inumodelloader.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TUTORIAL_TAB = new CreativeModeTab("tutorial_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TITANIUM_INGOT.get());
        }
    };

    public static final CreativeModeTab INU_MODELS_TAB = new CreativeModeTab("inu_models") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.INU_ITEM.get());
        }
    };
}
