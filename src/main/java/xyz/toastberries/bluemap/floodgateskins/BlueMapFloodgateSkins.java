package xyz.toastberries.bluemap.floodgateskins;

import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.plugin.SkinProvider;

@SuppressWarnings("unused")
public class BlueMapFloodgateSkins implements Runnable {

    @Override
    public void run() {
        BlueMapAPI.onEnable(api -> {
            final SkinProvider defaultSkinProvider = api.getPlugin().getSkinProvider();
            final SkinProvider geyserSkinProvider = new GeyserSkinProvider();

            // Define new SkinProvider that defers non-linked Floodgate players to GeyserSkinProvider.
            api.getPlugin().setSkinProvider(playerUUID -> {
                // A non-linked player can be identified by their UUID's most significant bits being packed with all zeros.
                // This is also how floodgate:core does it: org.geysermc.floodgate.api.SimpleFloodgateApi#isFloodgateId(UUID).
                if (playerUUID.getMostSignificantBits() == 0)
                    return geyserSkinProvider.load(playerUUID);
                return defaultSkinProvider.load(playerUUID);
            });
        });
    }

}
