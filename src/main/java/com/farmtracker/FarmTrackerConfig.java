package com.farmtracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

/*
Time Options:
Regional/British
24hr/12hr
Track Options:
GE Buy
GE Sell
Allotment
Flower
Herb
Hops
Bush
Tree
Fruit Tree
Special
Special Trees
Cactus
 */

@ConfigGroup("farmtracker")
public interface FarmTrackerConfig extends Config
{
	/**
	 * Time Options
	 */
	@ConfigSection(
			name = "Time",
			description = "Settings for Time.",
			position = 0
	)
	String timeSection = "Time";

	@ConfigItem(
			keyName = "useRegionalTime",
			name = "Use Regional Time",
			description = "Uses your current Time/Date. (If not will default to standard time)",
			position = 0,
			section = timeSection
	)
	default boolean useRegionalTime()
	{
		return true;
	}

	@ConfigItem(
			keyName = "useTwelveFormat",
			name = "Use 12-Hour Format",
			description = "Uses 12-Hour Format EX: 1:39 PM. (24-Hour Format EX: 13:39)",
			position = 1,
			section = timeSection
	)
	default boolean useTwelveFormat()
	{
		return true;
	}

	/**
	 * GE Section Options
	 */
	@ConfigSection(
			name = "GE",
			description = "Settings for GE buy/sell.",
			position = 1
	)
	String geSection = "GE";

	@ConfigItem(
			keyName = "trackGESeedBuys",
			name = "Track GE Seed Buys",
			description = "Tracks seeds bought from the GE.",
			position = 0,
			section = geSection
	)
	default boolean trackGESeedBuys()
	{
		return true;
	}

	//Bought Color? (RED)

	@ConfigItem(
			keyName = "trackGEHerbSells",
			name = "Track GE Herb Sells",
			description = "Tracks herbs sold to the GE.",
			position = 1,
			section = geSection
	)
	default boolean trackGEHerbSells()
	{
		return true;
	}

	//Sells Color? (GREEN)

	/**
	 * Patch Section Options
	 */
	@ConfigSection(
			name = "Patches",
			description = "Settings for Patches.",
			position = 2
	)
	String patchSection = "Patches";

	@ConfigItem(
			keyName = "trackAllotmentPatches",
			name = "Track Allotment Patches",
			description = "Tracks allotment patches for amount harvested, disease, or dead.",
			position = 0,
			section = patchSection
	)
	default boolean trackAllotmentPatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackFlowerPatches",
			name = "Track Flower Patches",
			description = "Tracks Flower patches for amount harvested, disease, or dead.",
			position = 1,
			section = patchSection
	)
	default boolean trackFlowerPatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackHerbPatches",
			name = "Track Herb Patches",
			description = "Tracks Herb patches for amount harvested, disease, or dead.",
			position = 2,
			section = patchSection
	)
	default boolean trackHerbPatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackHopsPatches",
			name = "Track Hops Patches",
			description = "Tracks Hops patches for amount harvested, disease, or dead.",
			position = 3,
			section = patchSection
	)
	default boolean trackHopsPatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackBushPatches",
			name = "Track Bush Patches",
			description = "Tracks Bush patches for amount harvested, disease, or dead.",
			position = 4,
			section = patchSection
	)
	default boolean trackBushPatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackTreePatches",
			name = "Track Tree Patches",
			description = "Tracks Tree patches for amount harvested, disease, or dead.",
			position = 5,
			section = patchSection
	)
	default boolean trackTreePatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackFruitTreePatches",
			name = "Track Fruit Tree Patches",
			description = "Tracks Fruit Tree patches for amount harvested, disease, or dead.",
			position = 6,
			section = patchSection
	)
	default boolean trackFruitTreePatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackSpecialPatches",
			name = "Track Special Patches",
			description = "Tracks Special patches for amount harvested, disease, or dead.",
			position = 7,
			section = patchSection
	)
	default boolean trackSpecialPatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackSpecialTreePatches",
			name = "Track Special Tree Patches",
			description = "Tracks Special Tree patches for amount harvested, disease, or dead.",
			position = 8,
			section = patchSection
	)
	default boolean trackSpecialTreePatches()
	{
		return true;
	}

	@ConfigItem(
			keyName = "trackCactusPatches",
			name = "Track Cactus Patches",
			description = "Tracks Cactus patches for amount harvested, disease, or dead.",
			position = 9,
			section = patchSection
	)
	default boolean trackCactusPatches()
	{
		return true;
	}
}
