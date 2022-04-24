package uk.co.ayaspace.pokefit.model.item

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.Gson

class ItemSprites {
    // The default depiction of this item.
    @JsonProperty("default")
    var default: String? = null
        private set

    fun setDefault(default_val: String?): ItemSprites {
        default = default_val
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}