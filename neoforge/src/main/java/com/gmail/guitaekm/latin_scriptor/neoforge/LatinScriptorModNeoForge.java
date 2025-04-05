package com.gmail.guitaekm.latin_scriptor.neoforge;

import net.neoforged.fml.common.Mod;

import com.gmail.guitaekm.latin_scriptor.LatinScriptorMod;

@Mod(LatinScriptorMod.MOD_ID)
public final class LatinScriptorModNeoForge {
    public LatinScriptorModNeoForge() {
        // Run our common setup.
        LatinScriptorMod.INSTANCE.init();
    }
}
