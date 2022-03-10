package me.fedee.itemmeta.enchant;

import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemHasEnchantment extends Element {
    public ItemHasEnchantment(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Item Has Enchantment";
    }

    public String getInternalName() {
        return "item-has-enchantment";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.ENCHANTED_BOOK;
    }

    public String[] getDescription() {
        return new String[] { "Check if an item has any enchantment" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo),
                new Argument("item", "Item", DataType.ITEM, elementInfo) };
    }


    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[]{new OutcomingVariable("result", "Result", DataType.BOOLEAN, elementInfo)};
    }

    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] {new DefaultChild(elementInfo, "next") };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        Player player = (Player) getArguments(info)[0].getValue(instance);
        ItemStack Item = (ItemStack) getArguments(info)[1].getValue(instance);

        getOutcomingVariables(info)[0].register(instance, new DataRequester() {
            public Object request() {

                if (player.getInventory().containsAtLeast(Item, 1)) {
                    return Item.getEnchantments().size() > 0;
                }
                return false;
            }
        });

        getConnectors(info)[0].run(instance);

    }
}
