package com.gmail.guitaekm.latin_scriptor.fabric;

import net.fabricmc.api.ModInitializer;

import com.gmail.guitaekm.latin_scriptor.LatinScriptorMod;

public final class LatinScriptorModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        LatinScriptorMod.INSTANCE.init();
    }
}
