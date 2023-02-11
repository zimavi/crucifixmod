package net.zimavi.crucifixmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTab {
    public static final CreativeModeTab CRUCIFIX_TAB = new CreativeModeTab("crucifixtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CRUCIFIX.get());
        }
    };
}
