package com.gmail.guitaekm.latin_scriptor

import com.ssblur.scriptor.ScriptorMod
import org.apache.logging.log4j.LogManager
import com.ssblur.unfocused.ModInitializer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.Configurator
import com.ssblur.scriptor.data.saved_data.DictionarySavedData

@Suppress("RedundantVisibilityModifier", "SpellCheckingInspection", "unused")
public object LatinScriptorMod: ModInitializer("latin_scriptor") {
    public const val MOD_ID = "latin_scriptor"
    public val LOGGER = LogManager.getLogger()!!
    fun init() {
        EndGroupGenerator.register()
    }
}