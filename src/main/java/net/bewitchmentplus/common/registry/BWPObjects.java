package net.bewitchmentplus.common.registry;

import moriyashiine.bewitchment.common.item.util.BWAxeItem;
import moriyashiine.bewitchment.common.item.util.BWHoeItem;
import moriyashiine.bewitchment.common.item.util.BWPickaxeItem;
import moriyashiine.bewitchment.common.registry.BWMaterials;
import net.bewitchmentplus.BewitchmentPlus;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class BWPObjects {
	private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
	private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

	public static final Item DRUDEN_SPAWN_EGG = create("druden_spawn_egg", new SpawnEggItem(BWPEntityTypes.DRUDEN, 0x006400, 0x989898, gen()));

	private static <T extends Block> T create(String name, T block, boolean createItem) {
		BLOCKS.put(block, new Identifier(BewitchmentPlus.MODID, name));
		if (createItem) {
			ITEMS.put(new BlockItem(block, gen()), BLOCKS.get(block));
		}
		return block;
	}

	private static <T extends Item> T create(String name, T item) {
		ITEMS.put(item, new Identifier(BewitchmentPlus.MODID, name));
		return item;
	}

	private static Item.Settings gen() {
		return new Item.Settings().group(BewitchmentPlus.BEWITCHMENT_PLUS_MOBS_GROUP);
	}

	public static void init() {
		BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
		ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
	}
}