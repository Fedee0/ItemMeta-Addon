package me.fedee.itemmeta.lore;

import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.base.item.XMaterial;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetItemLore extends Element {
    public SetItemLore(UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "Set Item Lore";
    }

    public String getInternalName() {
        return "set-item-lore";
    }

    public boolean isHidingIfNotCompatible() {
        return false;
    }

    public XMaterial getMaterial() {
        return XMaterial.WRITABLE_BOOK;
    }

    public String[] getDescription() {
        return new String[] { "Set the name of an item" };
    }

    public Argument[] getArguments(ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo),
                new Argument("item", "Item", DataType.ITEM, elementInfo),
                new Argument("lore", "Lore", DataType.STRING, elementInfo) };
    }

    public OutcomingVariable[] getOutcomingVariables(ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    public void run(ElementInfo info, ScriptInstance instance) {

        Player player = (Player) getArguments(info)[0].getValue(instance);
        ItemStack item = (ItemStack) getArguments(info)[1].getValue(instance);
        String lore = (String) getArguments(info)[2].getValue(instance);
        lore = PlaceholderAPI.setPlaceholders(player, lore);

        List<String> LoreList = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();
        LoreList.add(Objects.requireNonNull(Objects.requireNonNull(meta).getLore()).toString().replaceAll("[\\\\[\\\\](\\\\)]", ""));
        LoreList.add(lore);
        System.out.println(LoreList);
        Objects.requireNonNull(meta).setLore(LoreList);
        item.setItemMeta(meta);

        getConnectors(info)[0].run(instance);

    }
}
