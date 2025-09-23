package com.farmtracker;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.Set;

/**
 * To-Do:
 * Tracking Start-End Runs of Patch Type
 * Panel:
 * Graph -> Profit Table || GE Table || Both (Different Colored Lines)
 * Profit Table<Time, Date, Diseased, Dead, Run#, Item, Quantity, Price, Total, Used Seeds> (ALL Table, <-> All Products of Patch Type Table, Up/Down Specified Products of Patch Type Table)
 * GE Table<Time, Date, #, Item, Quantity, Cost, Total>
 *
 */

@Slf4j
@PluginDescriptor(
	name = "Farm Tracker"
)
public class FarmTrackerPlugin extends Plugin
{
	// Regions for each patch
	private static final Set<Integer> AllRegions = ImmutableSet.of(4922, 5021, 5421, 5423, 5427, 5684, 6192, 6702, 6967, 7223, 9265, 9777, 9781, 10288, 10290, 10300, 10548, 10551, 11056, 11058, 11060, 11062, 11317, 11321, 11325, 11570, 11573, 11828, 12083, 12340, 12594, 12596, 12851, 12854, 13106, 13151, 13878, 14391, 14651, 14907, 15008, 15148);
	private static final Set<Integer> AllotmentRegions = ImmutableSet.of(4922, 6192, 6967, 10548, 11062, 12083, 13151, 14391, 15148);
	private static final Set<Integer> FlowerRegions = ImmutableSet.of(4922, 5423, 6192, 6967, 10548, 11062, 12083, 13151, 14391);
	private static final Set<Integer> HerbRegions = ImmutableSet.of(4922, 6192, 6967, 10548, 11062, 11321, 11325, 12083, 14391, 15148);
	private static final Set<Integer> HopsRegions = ImmutableSet.of(5421, 10288, 10551, 11060, 12851);
	private static final Set<Integer> BushRegions = ImmutableSet.of(4922, 10290, 10300, 11570, 12596);
	private static final Set<Integer> TreeRegions = ImmutableSet.of(4922, 5427, 9781, 11573, 11828, 12594, 12854);
	private static final Set<Integer> FruitTreeRegions = ImmutableSet.of(4922, 5423, 9265, 9777, 9781, 11058, 11317);
	private static final Set<Integer> SpecialRegions = ImmutableSet.of(4922, 5021, 5684, 7223, 12340, 13878, 15008);
	private static final Set<Integer> SpecialTreeRegions = ImmutableSet.of(4922, 5423, 6702, 11056, 13151, 14651, 14907);
	private static final Set<Integer> CactusRegions = ImmutableSet.of(4922, 13106);
	//
 	// Seeds for each patch

	//
 	// Products for each patch
	private static final Set<Integer> Allotments = ImmutableSet.of(ItemID.POTATO, ItemID.ONION, ItemID.CABBAGE, ItemID.TOMATO, ItemID.SWEETCORN, ItemID.STRAWBERRY, ItemID.WATERMELON, ItemID.SNAPE_GRASS);
	private static final Set<Integer> Flowers = ImmutableSet.of(ItemID.MARIGOLDS, ItemID.ROSEMARY, ItemID.NASTURTIUMS, ItemID.WOAD_LEAF, ItemID.LIMPWURT_ROOT, ItemID.WHITE_LILY);
	private static final Set<Integer> Herbs = ImmutableSet.of(ItemID.GRIMY_GUAM_LEAF, ItemID.GRIMY_MARRENTILL, ItemID.GRIMY_TARROMIN, ItemID.GRIMY_HARRALANDER, ItemID.GOUTWEED, ItemID.GRIMY_RANARR_WEED, ItemID.GRIMY_TOADFLAX, ItemID.GRIMY_IRIT_LEAF, ItemID.GRIMY_AVANTOE, ItemID.GRIMY_KWUARM, ItemID.GRIMY_SNAPDRAGON, ItemID.GRIMY_HUASCA, ItemID.GRIMY_CADANTINE, ItemID.GRIMY_LANTADYME, ItemID.GRIMY_DWARF_WEED, ItemID.GRIMY_TORSTOL);
	private static final Set<Integer> Hops = ImmutableSet.of(ItemID.BARLEY, ItemID.HAMMERSTONE_HOPS, ItemID.ASGARNIAN_HOPS, ItemID.JUTE_FIBRE, ItemID.YANILLIAN_HOPS, ItemID.KRANDORIAN_HOPS, ItemID.WILDBLOOD_HOPS);
	private static final Set<Integer> Bushes = ImmutableSet.of(ItemID.REDBERRIES, ItemID.CADAVA_BERRIES, ItemID.DWELLBERRIES, ItemID.JANGERBERRIES, ItemID.WHITE_BERRIES, ItemID.POISON_IVY_BERRIES);
	// Trees can also get: Roots, As well as willow being able to get Branch
	private static final Set<Integer> Trees = ImmutableSet.of(ItemID.OAK_LOGS, ItemID.WILLOW_LOGS, ItemID.MAPLE_LOGS, ItemID.YEW_LOGS, ItemID.MAGIC_LOGS);
	private static final Set<Integer> FruitTrees = ImmutableSet.of(ItemID.COOKING_APPLE, ItemID.BANANA, ItemID.ORANGE, ItemID.CURRY_LEAF, ItemID.PINEAPPLE, ItemID.PAPAYA_FRUIT, ItemID.COCONUT, ItemID.DRAGONFRUIT);
	private static final Set<Integer> Specials = ImmutableSet.of(ItemID.GIANT_SEAWEED, ItemID.GRAPES, ItemID.ZAMORAKS_GRAPES, ItemID.MUSHROOM, ItemID.NIGHTSHADE);
	private static final Set<Integer> SpecialTrees = ImmutableSet.of(ItemID.TEAK_LOGS, ItemID.MAHOGANY_LOGS, ItemID.CALQUAT_FRUIT, ItemID.CRYSTAL_SHARD, ItemID.CELASTRUS_BARK, ItemID.REDWOOD_LOGS);
	private static final Set<Integer> Cactus = ImmutableSet.of(ItemID.CACTUS_SPINE, ItemID.POTATO_CACTUS);
	//
 	// Dynamic Values
	private Item[] LastInventory = null;
	//

	private FarmTrackerPanel panel;

	@Inject
	private ItemManager itemManager;

	@Inject
	private Client client;

	@Inject
	private FarmTrackerConfig config;

	private boolean isInFarmingRegion() {
		Player local = client.getLocalPlayer();
		if (local == null)
		{
			return false;
		}

		WorldPoint location = local.getWorldLocation();
		return AllRegions.contains(location.getRegionID());
	}
	private boolean isInRegion(Set<Integer> set) {
		Player local = client.getLocalPlayer();
		if (local == null)
		{
			return false;
		}

		WorldPoint location = local.getWorldLocation();
		return set.contains(location.getRegionID());
	}
	private Item[] getDifference(Item[] CurrentInventory) {
		Item[] cCopy = CurrentInventory.clone();
		Item[] lCopy = LastInventory.clone();
		int nns = 0;

		for (int i = 0; i < cCopy.length-1; i++) {
			Item cItem = cCopy[i];
			for (int j = 0; j < lCopy.length-1; j++) {
				Item lItem = lCopy[j];
				if(cItem == null) break;
				else if(lItem == null) continue;
				if(cItem.getId() == lItem.getId() && cItem.getQuantity() == lItem.getQuantity()) {
					cCopy[i] = null;
					lCopy[j] = null;
					break;
				}
			}
			if(cCopy[i] != null) nns++;
		}

		Item[] newArr = new Item[nns];
		int current = 0;
		for (int i = 0; i < cCopy.length-1; i++) {
			Item cItem = cCopy[i];
			if(cItem != null) {
				newArr[current] = cItem;
				current++;
			}
		}

		return newArr;
	}
	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event) {
		Item[] CurrentInventory = event.getItemContainer().getItems();
		if(LastInventory == null) {
			// Better method would be to set *LastInventory on (Farming Region Entered)
			LastInventory = client.getItemContainer(InventoryID.INVENTORY).getItems();
		}
		else if(!isInFarmingRegion()) {
			// Better method would be to set *LastInventory on (Farming Region Entered)
			LastInventory = CurrentInventory;
			return;
		}
		if (event.getItemContainer() != client.getItemContainer(InventoryID.INVENTORY)) {
			return;
		}
		Item[] Difference = getDifference(CurrentInventory);
		if(config.trackAllotmentPatches() && isInRegion(AllotmentRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(Allotments.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Allotment) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackFlowerPatches() && isInRegion(FlowerRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(Flowers.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Flowers) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackHerbPatches() && isInRegion(HerbRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(Herbs.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Herbs) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackHopsPatches() && isInRegion(HopsRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(Hops.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Hops) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackBushPatches() && isInRegion(BushRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(Bushes.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Bushes) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackTreePatches() && isInRegion(TreeRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(Trees.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Trees) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackFruitTreePatches() && isInRegion(FruitTreeRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(FruitTrees.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Fruit Trees) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackSpecialPatches() && isInRegion(SpecialRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(Specials.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Specials) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackSpecialTreePatches() && isInRegion(SpecialTreeRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(SpecialTrees.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Special Trees) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		if(config.trackCactusPatches() && isInRegion(CactusRegions)) {
			for (Item item : Difference) {
				int id = item.getId();
				if(Cactus.contains(id)) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", String.format("(Cactus) Inventory Added: %d and quantity: %c!", id, item.getQuantity()), null);
				}
			}
		}
		LastInventory = CurrentInventory;
	}

	@Override
	protected void startUp() throws Exception
	{
		panel = new FarmTrackerPanel(this, itemManager, config);
		/*
		 * Load Data
		 */

	}

	@Override
	protected void shutDown() throws Exception
	{
		/*
		 * Save Data
		 */

	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{

			/*
			 * Load Data
			 */
		}
		else if (gameStateChanged.getGameState() == GameState.LOGIN_SCREEN) {
			/*
			 * Save Data
			 * Clear Data?
			 */
			LastInventory = null;
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{

	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{

	}

	@Provides
	FarmTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FarmTrackerConfig.class);
	}
}
