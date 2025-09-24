/*
 * Copyright (c) 2018 Abex
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.farmtracker.util;

import lombok.Getter;
import net.runelite.api.ItemID;

@Getter
public enum Tab
{
	OVERVIEW(0, "Overview", ItemID.OLD_NOTES, new String[]{}),
	ALLOTMENT(1, "Allotment Patches", ItemID.CABBAGE, new String[]{"All", "Potato", "Onion", "Cabbage", "Tomato", "Sweetcorn", "Strawberry", "Watermelon", "Snape grass"}),
	FLOWER(2, "Flower Patches", ItemID.RED_FLOWERS, new String[]{"All", "Marigold", "Rosemary", "Nasturtium", "Woad", "Limpwurt", "White lily"}),
	HERB(3, "Herb Patches", ItemID.GRIMY_RANARR_WEED, new String[]{"All", "Guam", "Marrentill", "Tarromin", "Harralander", "Goutweed", "Ranarr", "Toadflax", "Irit", "Avantoe", "Kwuarm", "Snapedragon", "Huasca", "Cadantine", "Lantadyme", "Dwarf weed", "Torstol"}),
	TREE(4, "Tree Patches", ItemID.YEW_LOGS, new String[]{"All", "Oak", "Willow", "Maple", "Yew", "Magic"}),
	FRUIT_TREE(5, "Fruit Tree Patches", ItemID.PINEAPPLE, new String[]{"All", "Apple", "Banana", "Orange", "Curry", "Pineapple", "Papaya", "Palm", "Dragonfruit"}),
	HOPS(6, "Hops Patches", ItemID.BARLEY, new String[]{"All", "Barley", "Hammerstone", "Asgarnian", "Jute", "Yanillian", "Krandorian", "Wildblood"}),
	BUSH(7, "Bush Patches", ItemID.POISON_IVY_BERRIES, new String[]{"All", "Redberries", "Cadavaberry", "Dwellberry", "Jangerberry", "Whiteberry", "Poison ivy"}),
	GRAPE(8, "Grape Patches", ItemID.GRAPES, new String[]{"All", "Grapes", "Zamorak's grapes"}),
	SPECIAL(9, "Special Patches", ItemID.MUSHROOM, new String[]{"All", "Seaweed", "Mushroom", "Nightshade", "Kronos", "Iasor", "Attas"}),
	SPECIAL_TREE(10, "Special Tree Patches", ItemID.MAHOGANY_LOGS, new String[]{"All", "Teak", "Mahogany", "Calquat", "Crystal", "Celastrus", "Redwood"}),
	CACTUS(11, "Cactus Patches", ItemID.CACTUS_SPINE, new String[]{"All", "Cactus spine", "Potato cactus"});

	public static final Tab[] FARMING_TABS = {HERB, TREE, FRUIT_TREE, SPECIAL, FLOWER, ALLOTMENT, BUSH, GRAPE, HOPS, SPECIAL_TREE, CACTUS};

	public final int uid;
	public final String name;
	public final int itemID;
	public  final String[] filters;

    Tab(int uid, String name, int itemID, String[] filters) {
        this.uid = uid;
		this.name = name;
        this.itemID = itemID;
		this.filters = filters;
    }
}
