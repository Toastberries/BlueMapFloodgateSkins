package xyz.toastberries.bluemap.floodgateskins;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.bluecolored.bluemap.api.plugin.SkinProvider;
import de.bluecolored.bluemap.core.logger.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

/*
 * Modeled after BlueMap's default SkinProvider: de.bluecolored.bluemap.common.plugin.skins.MojangSkinProvider.
 * We could use the same logic of decoding the texture URL from the value field,
 * but Geyser's REST API also supplies the texture ID directly,
 * so we can just use that instead to construct the same URL.
 */
public class GeyserSkinProvider implements SkinProvider {

    @Override
    public Optional<BufferedImage> load(UUID playerUUID) {
        try (Reader reader = requestProfileJson(playerUUID)) {
            String textureUrl = readTextureUrl(JsonParser.parseReader(reader));
            return Optional.of(ImageIO.read(URI.create(textureUrl).toURL()));
        } catch (Exception e) {
            Logger.global.logDebug("Failed to load skin for floodgate player: '" + playerUUID + "' - " + e);
            return Optional.empty();
        }
    }

    private Reader requestProfileJson(UUID playerUUID) throws IOException {
        // A non-linked player's XUID is stored in the least significant bits of their UUID.
        long playerXUID = playerUUID.getLeastSignificantBits();
        URL url = URI.create("https://api.geysermc.org/v2/skin/" + playerXUID).toURL();
        return new InputStreamReader(url.openStream());
    }

    private String readTextureUrl(JsonElement json) {
        String textureId = json.getAsJsonObject().get("texture_id").getAsString();
        return "https://textures.minecraft.net/texture/" + textureId;
    }

}
