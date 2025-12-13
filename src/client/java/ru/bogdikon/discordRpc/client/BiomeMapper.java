package ru.bogdikon.discordRpc.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;

public final class BiomeMapper {
    private static final Map<String, BiomeData> BIOME_DATA = new HashMap<>();

    static {
        // =====================
        // OVERWORLD – PLAINS & GRASSLANDS
        // =====================
        BIOME_DATA.put("plains", new BiomeData("grass_block", "Plains"));
        BIOME_DATA.put("sunflower_plains", new BiomeData("sunflower", "Sunflower Plains"));
        BIOME_DATA.put("meadow", new BiomeData("oxeye_daisy", "Meadow"));
        BIOME_DATA.put("snowy_plains", new BiomeData("snowy_grass", "Snowy Plains"));
        BIOME_DATA.put("ice_spikes", new BiomeData("packed_ice", "Ice Spikes"));
        // Mushroom fields
        BIOME_DATA.put("mushroom_fields", new BiomeData("mycelium", "Mushroom Fields"));

        // =====================
        // FORESTS
        // =====================
        BIOME_DATA.put("forest", new BiomeData("oak_log", "Forest"));
        BIOME_DATA.put("flower_forest", new BiomeData("pink_tulip", "Flower Forest"));
        BIOME_DATA.put("birch_forest", new BiomeData("birch_log", "Birch Forest"));
        BIOME_DATA.put("old_growth_birch_forest", new BiomeData("birch_log", "Old Birch Forest"));
        BIOME_DATA.put("dark_forest", new BiomeData("dark_oak_log", "Dark Forest"));
        BIOME_DATA.put("cherry_grove", new BiomeData("cherry_leaves", "Cherry Grove"));
        BIOME_DATA.put("pale_garden", new BiomeData("pale_oak_log", "Pale Garden"));

        // =====================
        // TAIGA
        // =====================
        BIOME_DATA.put("taiga", new BiomeData("spruce_log", "Taiga"));
        BIOME_DATA.put("snowy_taiga", new BiomeData("spruce_log", "Snowy Taiga"));
        BIOME_DATA.put("old_growth_pine_taiga", new BiomeData("spruce_log", "Old Pine Taiga"));
        BIOME_DATA.put("old_growth_spruce_taiga", new BiomeData("spruce_log", "Old Spruce Taiga"));

        // =====================
        // JUNGLES
        // =====================
        BIOME_DATA.put("jungle", new BiomeData("jungle_log", "Jungle"));
        BIOME_DATA.put("sparse_jungle", new BiomeData("jungle_log", "Sparse Jungle"));
        BIOME_DATA.put("bamboo_jungle", new BiomeData("bamboo", "Bamboo Jungle"));

        // =====================
        // SAVANNA
        // =====================
        BIOME_DATA.put("savanna", new BiomeData("acacia_log", "Savanna"));
        BIOME_DATA.put("savanna_plateau", new BiomeData("acacia_log", "Savanna Plateau"));
        BIOME_DATA.put("windswept_savanna", new BiomeData("acacia_log", "Windswept Savanna"));

        // =====================
        // DESERTS & BADLANDS
        // =====================
        BIOME_DATA.put("desert", new BiomeData("sand", "Desert"));
        BIOME_DATA.put("badlands", new BiomeData("red_sand", "Badlands"));
        BIOME_DATA.put("wooded_badlands", new BiomeData("red_sand", "Wooded Badlands"));
        BIOME_DATA.put("eroded_badlands", new BiomeData("red_sand", "Eroded Badlands"));

        // =====================
        // MOUNTAINS
        // =====================
        BIOME_DATA.put("windswept_hills", new BiomeData("stone", "Windswept Hills"));
        BIOME_DATA.put("windswept_gravelly_hills", new BiomeData("gravel", "Gravelly Hills"));
        BIOME_DATA.put("windswept_forest", new BiomeData("oak_log", "Windswept Forest"));
        BIOME_DATA.put("stony_peaks", new BiomeData("stone", "Stony Peaks"));
        BIOME_DATA.put("jagged_peaks", new BiomeData("packed_ice", "Jagged Peaks"));
        BIOME_DATA.put("frozen_peaks", new BiomeData("blue_ice", "Frozen Peaks"));
        BIOME_DATA.put("grove", new BiomeData("spruce_log", "Grove"));
        BIOME_DATA.put("snowy_slopes", new BiomeData("snow_block", "Snowy Slopes"));

        // =====================
        // SWAMPS
        // =====================
        BIOME_DATA.put("swamp", new BiomeData("lily_pad", "Swamp"));
        BIOME_DATA.put("mangrove_swamp", new BiomeData("mud_block", "Mangrove Swamp"));

        // =====================
        // RIVERS & OCEANS
        // =====================
        BIOME_DATA.put("river", new BiomeData("water", "River"));
        BIOME_DATA.put("frozen_river", new BiomeData("ice", "Frozen River"));

        BIOME_DATA.put("ocean", new BiomeData("water", "Ocean"));
        BIOME_DATA.put("cold_ocean", new BiomeData("water", "Cold Ocean"));
        BIOME_DATA.put("frozen_ocean", new BiomeData("ice", "Frozen Ocean"));
        BIOME_DATA.put("lukewarm_ocean", new BiomeData("water", "Lukewarm Ocean"));
        BIOME_DATA.put("warm_ocean", new BiomeData("water", "Warm Ocean"));

        BIOME_DATA.put("deep_ocean", new BiomeData("water", "Deep Ocean"));
        BIOME_DATA.put("deep_cold_ocean", new BiomeData("water", "Deep Cold Ocean"));
        BIOME_DATA.put("deep_frozen_ocean", new BiomeData("ice", "Deep Frozen Ocean"));
        BIOME_DATA.put("deep_lukewarm_ocean", new BiomeData("water", "Deep Lukewarm Ocean"));

        // =====================
        // CAVES
        // =====================
        BIOME_DATA.put("lush_caves", new BiomeData("flowering_azalea", "Lush Caves"));
        BIOME_DATA.put("dripstone_caves", new BiomeData("pointed_dripstone", "Dripstone Caves"));
        BIOME_DATA.put("deep_dark", new BiomeData("sculk_block", "Deep Dark"));

        // =====================
        // BEACHES & SHORES
        // =====================
        BIOME_DATA.put("beach", new BiomeData("sand", "Beach"));
        BIOME_DATA.put("snowy_beach", new BiomeData("sand", "Snowy Beach"));
        BIOME_DATA.put("stony_shore", new BiomeData("stone", "Stony Shore"));

        // =====================
        // NETHER
        // =====================
        BIOME_DATA.put("nether_wastes", new BiomeData("netherrack", "Nether Wastes"));
        BIOME_DATA.put("soul_sand_valley", new BiomeData("soul_sand", "Soul Sand Valley"));
        BIOME_DATA.put("crimson_forest", new BiomeData("crimson_nylium", "Crimson Forest"));
        BIOME_DATA.put("warped_forest", new BiomeData("warped_nylium", "Warped Forest"));
        BIOME_DATA.put("basalt_deltas", new BiomeData("magma_block", "Basalt Deltas"));

        // =====================
        // THE END
        // =====================
        BIOME_DATA.put("the_end", new BiomeData("end_stone", "The End"));
        BIOME_DATA.put("end_highlands", new BiomeData("end_stone", "End Highlands"));
        BIOME_DATA.put("end_midlands", new BiomeData("end_stone", "End Midlands"));
        BIOME_DATA.put("small_end_islands", new BiomeData("end_stone", "Small End Islands"));
        BIOME_DATA.put("end_barrens", new BiomeData("end_stone", "End Barrens"));

        // =====================
        // THE VOID
        // =====================
        BIOME_DATA.put("the_void", new BiomeData("barrier", "The Void"));
    }

    private BiomeMapper() {}

    public static BiomeData getCurrentBiome() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return null;

        RegistryEntry<Biome> biome = client.world.getBiome(client.player.getBlockPos());
        Identifier id = biome.getKey().orElseThrow().getValue();
        return BIOME_DATA.get(id.getPath());
    }

}