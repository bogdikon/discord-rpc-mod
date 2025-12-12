package ru.bogdikon.discordRpc.client;

import net.fabricmc.api.ClientModInitializer;
import ru.bogdikon.discordRpc.client.RPCManager;

public class DiscordRpcClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RPCManager.start();
        Runtime.getRuntime().addShutdownHook(new Thread(RPCManager::stop));    }
}
