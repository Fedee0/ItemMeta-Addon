package me.fedee.itemmeta.customodeldata;

import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemGetCustomModelData extends Element {
    public ItemGetCustomModelData(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Item Get Custom Model Data";
    }

    public String getInternalName() {
        return "item-get-custom-model-data";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.ENCHANTED_GOLDEN_APPLE;
    }

    public String[] getDescription() {
        return new String[] { "Get the ID of an item custom model data" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("item", "Item", DataType.ITEM, elementInfo) };
    }

    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("id", "ID", DataType.NUMBER, elementInfo) };
    }

    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        ItemStack item = (ItemStack) getArguments(info)[0].getValue(instance);

        getOutcomingVariables(info)[0].register(instance, new DataRequester() {
            public Object request() {

                return Objects.requireNonNull(item.getItemMeta()).getCustomModelData();
            }
        });

        getConnectors(info)[0].run(instance);
    }
}
