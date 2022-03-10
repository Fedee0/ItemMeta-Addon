package me.fedee.itemmeta.enchant;

import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemRemoveEnchantment extends Element {
    public ItemRemoveEnchantment(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Item Remove Enchantment";
    }

    public String getInternalName() {
        return "item-remove-enchantment";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.ENCHANTED_BOOK;
    }

    public String[] getDescription() {
        return new String[] { "Remove an enchantment to an item", "(Name must be uppercase)" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("item", "Item", DataType.ITEM, elementInfo),
                new Argument("enchantment", "Enchantment", DataType.STRING, elementInfo)};
    }


    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] {new DefaultChild(elementInfo, "next") };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        ItemStack Item = (ItemStack) getArguments(info)[0].getValue(instance);
        String EnchantmentName = (String) getArguments(info)[1].getValue(instance);


        Enchantment EnchantmentObj = Enchantment.getByName(EnchantmentName);
        Item.removeEnchantment(EnchantmentObj);


        getConnectors(info)[0].run(instance);

    }
}
