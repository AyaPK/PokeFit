package uk.co.ayaspace.pokefit.model.utility

import com.google.gson.Gson

class Name {
    // The localized name for an API resource in a specific language.
    var name: String? = null
        private set

    // The language this name is in.
    private var language: Language? = null
    fun setName(name: String?): Name {
        this.name = name
        return this
    }

    fun getLanguage(): Language? {
        return language
    }

    fun setLanguage(language: Language?): Name {
        this.language = language
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}