package me.fedee.itemmeta.lore;

import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import org.bukkit.inventory.ItemStack;

public class CompareItemsLore extends Element {
    public CompareItemsLore(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Compare Items Lore";
    }

    public String getInternalName() {
        return "compare-items-lore";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.STICK;
    }

    public String[] getDescription() {
        return new String[] { "Compare the item lore of 2 items" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("item1", "Item1", DataType.ITEM, elementInfo),
                new Argument("item2", "Item2", DataType.ITEM, elementInfo) };
    }

    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    public Child[] getConnectors(final ElementInfo elementInfo) {
        return new Child[] { new Child(elementInfo, "yes") {
            public String getName() {
                return "The lore are equals";
            }

            public String[] getDescription() {
                return new String[] { "Will be executed if the item lore", "are equals" };
            }

            public XMaterial getIcon() {
                return XMaterial.LIME_STAINED_GLASS_PANE;
            }
        }, new Child(elementInfo, "no") {
            public String getName() {
                return "The lore are not equals";
            }

            public String[] getDescription() {
                return new String[] { "Will be executed if the item lore", "are not equals" };
            }

            public XMaterial getIcon() {
                return XMaterial.RED_STAINED_GLASS_PANE;
            }
        } };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        ItemStack item = (ItemStack) getArguments(info)[0].getValue(instance);
        ItemStack item2 = (ItemStack) getArguments(info)[1].getValue(instance);

        if(item.getItemMeta().getLore().equals(item2.getItemMeta().getLore())) {
            getConnectors(info)[0].run(instance);
        } else {
            getConnectors(info)[1].run(instance);
        }


    }
}
