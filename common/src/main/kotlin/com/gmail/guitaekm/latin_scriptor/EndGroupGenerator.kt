package com.gmail.guitaekm.latin_scriptor

import com.google.common.reflect.TypeToken
import java.lang.reflect.Type
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.ssblur.scriptor.helpers.generators.TokenGenerator
import com.ssblur.scriptor.registry.TokenGeneratorRegistry
import java.util.*

class EndGroupGenerator(obj: JsonObject?): TokenGenerator() {
    data class Group(
        val syllables: Array<String>
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Group

            return syllables.contentEquals(other.syllables)
        }

        override fun hashCode(): Int {
            return syllables.contentHashCode()
        }

        fun choice(random: Random): String {
            return this.syllables.get(random.nextInt(this.syllables.size))
        }
    }

    data class EndGroupParameters(
        val minTokens: Int,
        val maxTokens: Int,
        val middleGroup: Group,
        val endGroup: Group
    )

    private val parameters: EndGroupParameters = GSON.fromJson(obj, END_GROUP_PARAMETERS )

    companion object {
        val GSON: Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val END_GROUP_PARAMETERS: Type = object: TypeToken<EndGroupParameters>() {}.type
        val RANDOM: Random = Random()
        fun register() {
            TokenGeneratorRegistry.registerGeneratorGenerator("end_group") { EndGroupGenerator(it) }
        }
    }

    override fun canBeDefault(): Boolean {
        return true
    }

    fun generateNrMiddleTokens(overrideParameters: JsonObject?): Int {
        var minTokens = this.parameters.minTokens
        var maxTokens = this.parameters.maxTokens
        if (overrideParameters != null) {
            if (overrideParameters.has("min_tokens")) {
                minTokens = overrideParameters["min_tokens"].asInt
            }
            if (overrideParameters.has("max_tokens")) {
                maxTokens = overrideParameters["max_tokens"].asInt
            }
        }
        return RANDOM.nextInt(minTokens, maxTokens + 1)
    }

    override fun generateToken(key: String, overrideParameters: JsonObject?): String {
        val nrMiddleTokens: Int = generateNrMiddleTokens(overrideParameters)
        val n = this.parameters.middleGroup.syllables
        val result = (1..nrMiddleTokens).map { this.parameters.middleGroup.choice(RANDOM) }.toMutableList()
        result.add(this.parameters.endGroup.choice(RANDOM))
        return result.joinToString("")
    }
}