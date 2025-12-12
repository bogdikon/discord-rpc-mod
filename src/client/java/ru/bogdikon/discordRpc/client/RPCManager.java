package ru.bogdikon.discordRpc.client;
import com.jcraft.jorbis.Block;
import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;

import java.time.Instant;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;


public class RPCManager {
    private static Core core;
    private static Thread callbackThread;

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
            activity.timestamps().setStart(Instant.now());

            MinecraftClient client = MinecraftClient.getInstance();

            Thread rpcThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    ClientWorld world = client.world;
                    PlayerEntity player = client.player;

                    if (world != null && player != null) {
                        String dimension = world.getRegistryKey().getValue().toString();
                        String dimensionFriendly = "<idk>";
                        switch (dimension) {
                            case "minecraft:overworld" -> dimensionFriendly = "overworld";
                            case "minecraft:the_end" -> dimensionFriendly = "the end";
                            case "minecraft:the_nether" -> dimensionFriendly = "the nether";
                        }
                        String details = String.format("In %s", dimensionFriendly);
                        activity.setDetails(details);
                        switch (dimension) {
                            case "minecraft:the_nether" -> activity.assets().setLargeImage("netherrack");
                            case "minecraft:the_end" -> activity.assets().setLargeImage("end_stone");
                            default -> activity.assets().setLargeImage("grass_block");
                        }
                        core.activityManager().updateActivity(activity);
                    } else { // assuming this means main menu???
                        activity.setDetails("In main menu");
                        activity.assets().setLargeImage("grass_block");
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
