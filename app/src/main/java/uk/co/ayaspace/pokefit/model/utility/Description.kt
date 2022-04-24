package uk.co.ayaspace.pokefit.model.utility

import com.google.gson.Gson

class Description {
    // The localized description for an API resource in a specific language.
    var description: String? = null
        private set

    // The language this name is in.
    private var language: Language? = null
    fun setDescription(description: String?): Description {
        this.description = description
        return this
    }

    fun getLanguage(): Language? {
        return language
    }

    fun setLanguage(language: Language?): Description {
        this.language = language
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}