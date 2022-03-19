package me.fedee.itemmeta.customodeldata;

import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemSetCustomModelData extends Element {
    public ItemSetCustomModelData(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Item Set Custom Model Data";
    }

    public String getInternalName() {
        return "item-set-custom-model-data";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.GLOWSTONE_DUST;
    }

    public String[] getDescription() {
        return new String[] { "Set the Custom Model Data of an item (1.13+)" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("item", "Item", DataType.ITEM, elementInfo),
               new Argument("id", "ID", DataType.NUMBER, elementInfo)};
    }

    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        ItemStack item = (ItemStack) getArguments(info)[0].getValue(instance);
        int id = (int) (long) getArguments(info)[1].getValue(instance);

        Objects.requireNonNull(item.getItemMeta()).setCustomModelData(id);

        getConnectors(info)[0].run(instance);
    }
}
