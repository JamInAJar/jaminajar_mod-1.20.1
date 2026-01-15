package io.github.jaminajar.jaminajarmod.screen;

import io.github.jaminajar.jaminajarmod.JamInAJarMod;

public class ModScreenHandlers {
//    public static final ScreenHandlerType<SoulBombScreenHandler> SOUL_BOMB_SCREEN_HANDLER =
//            Registry.register(Registries.SCREEN_HANDLER, new Identifier(JamInAJarMod.MOD_ID,"soul_bomb_fuelling"),
//                    new ExtendedScreenHandlerType<>(SoulBombScreenHandler::new));

    public static void registerScreenHandlers(){
        JamInAJarMod.LOGGER.info("Registering Screen Handlers for: "+JamInAJarMod.MOD_ID);
    }
}
