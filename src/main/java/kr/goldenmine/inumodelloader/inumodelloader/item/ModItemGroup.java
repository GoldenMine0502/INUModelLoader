package kr.goldenmine.inumodelloader.inumodelloader.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

        @Override
        public void fill(NonNullList<ItemStack> items) {
            super.fill(items);
//            List<Item> registryItems = new ArrayList<Item>();
//            for(Item item : Registry.ITEM) {
//                registryItems.add(item);
//            }
//
//            registryItems.sort(Comparator.comparing(o -> o.getItem().getName().toString()));
//
//            for(Item item : registryItems) {
//                item.fillItemGroup(this, items);
//            }

            items.sort(Comparator.comparing(o -> o.getItem().getName().toString()));
        }
    };
}
