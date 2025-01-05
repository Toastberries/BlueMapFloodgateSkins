# BlueMap Floodgate Skins Addon
A native addon for [BlueMap](https://bluemap.bluecolored.de/) that adds support for [GeyserMC](https://geysermc.org/)'s Floodgate plugin.
This project adds functionality to BlueMap to request Bedrock skins through the [GeyserMC Global REST API](https://geysermc.org/wiki/api/api.geysermc.org/global-api).
This fixes the issue of non-linked Bedrock players' icons all showing up as Steve on the BlueMap.

## Usage
- [Download the latest version here!](https://github.com/Toastberries/BlueMapFloodgateSkins/releases/latest)
- Place the jar file into the `packs` folder next to your BlueMap config files!
- Restart your server!
- If you see `Loading BlueMap Addon: bluemap-floodgate-skins (...)` in the console, everything should be up and running!
- Profit!

## FAQ

### Q: Can I use this on Paper/Fabric/NeoForge/etc.?
**A:** Yes. This is a native addon for BlueMap; it should work on any platform that BlueMap (and Floodgate) can run on.

### Q: My new Bedrock skin isn't appearing on the BlueMap. Is the addon broken?
**Short Answer:** Probably not. If it's not throwing any errors, it will update your skin eventually, it just takes time due to limitations and caches.

**Long Answer:** There are multiple things that could be going on simultaneously:
- Your skin could still be in Geyser's upload queue.
  - There's not much you can do about this, you'll just have to wait. Geyser's queue can be anywhere from a few seconds to multiple hours long.
    You'll know your skin has uploaded when Java players can see it in-game, but you can also use a tool like [MCProfile](https://mcprofile.io/) to check.
- Your previous skin could still be cached by BlueMap.
  - BlueMap only refreshes cached skins on login, and only if it's been at least an hour since your last refresh or if it's after a server restart. Try restarting your server to force a refresh.
- Your previous skin could still be cached by your web browser.
  - Try hitting Ctrl+Shift+R on your BlueMap's webpage to perform a hard refresh and clear your local cache.
- There might actually be something wrong! Please open an issue with the logs if you think it's my fault!
  - First, make sure `bluemap-floodgate-skins` is actually loaded as an addon (refer to Usage) and isn't throwing any errors in the console when trying to load.
  - Navigate to `/bluemap/logs/` from the root of your server, open `debug.log`, and make sure that it doesn't have any lines like `Failed to load skin for floodgate player: (...)`.
