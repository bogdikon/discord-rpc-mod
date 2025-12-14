package ru.bogdikon.discordRpc.client;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;


public class RPCManager {
    private static Core core;
    private static Thread callbackThread;

    public static boolean isSingleplayer() {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.isInSingleplayer();
    }

    public static void start() {
        try {
            // Core parameters
            CreateParams params = new CreateParams();
            params.setClientID(899013550759100478L);
            params.setFlags(CreateParams.getDefaultFlags());

            // initializing Core
            core = new Core(params);

            // making Activity
            Activity activity = new Activity();

            MinecraftClient client = MinecraftClient.getInstance();

            Thread rpcThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    ClientWorld world = client.world;
                    PlayerEntity player = client.player;

                    if (world != null && player != null) {
                        String dimension = world.getRegistryKey().getValue().toString();
                        String dimensionFriendly = "<idk>";
                        // Checking dimension
                        RegistryEntry<Biome> biome = world.getBiome(player.getBlockPos());

                        activity.assets().setSmallImage("barrier"); // In case it cant be detected
                        activity.assets().setSmallText("couldn't detect");

                        BiomeData biomeData = BiomeMapper.getCurrentBiome();

                        switch (dimension) {
                            case "minecraft:overworld" -> {
                                dimensionFriendly = "overworld";
                                activity.assets().setLargeImage("grass_block");
                                activity.assets().setLargeText("Overworld");
                                // Checking biome
                                if (biomeData != null) {
                                    activity.assets().setSmallImage(biomeData.image());
                                    activity.assets().setSmallText(biomeData.text());
                                }
                            }
                            case "minecraft:the_end" -> {
                                dimensionFriendly = "the end";
                                activity.assets().setLargeImage("end_stone");
                                activity.assets().setLargeText("The End");
                                if (biomeData != null) {
                                    activity.assets().setSmallImage(biomeData.image());
                                    activity.assets().setSmallText(biomeData.text());
                                }
                            }
                            case "minecraft:the_nether" -> {
                                dimensionFriendly = "the nether";
                                activity.assets().setLargeImage("netherrack");
                                activity.assets().setLargeText("The Nether");
                                // Checking biome
                                if (biomeData != null) {
                                    activity.assets().setSmallImage(biomeData.image());
                                    activity.assets().setSmallText(biomeData.text());
                                }
                            }
                        }
                        String details = String.format("In %s", dimensionFriendly);

                        activity.setDetails(details);

                        if (isSingleplayer()) {
                            activity.setState("Playing singleplayer");
                        } else {
                            activity.setState("Playing multiplayer");
                        }
                        core.activityManager().updateActivity(activity);
                    } else { // assuming this means main menu???
                        activity.setDetails("In main menu");
                        activity.assets().setLargeImage("workbench");
                        activity.assets().setLargeText("Main menu");
                        activity.assets().setSmallImage(null);
                        activity.setState(null);
                        core.activityManager().updateActivity(activity);
                    }
                    core.runCallbacks();

                    try {
                        Thread.sleep(1000); // update every second
                    } catch (InterruptedException ignored) {}
                }
            }, "Discord-RPC-Thread");

            rpcThread.start();
            callbackThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    core.runCallbacks();
                    try { Thread.sleep(16); } catch (InterruptedException ignored) {}
                }
            }, "Discord-Callback-Thread");
            callbackThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void stop() {
        if(callbackThread != null) callbackThread.interrupt();
        if (core != null) core.close();
    }
}
