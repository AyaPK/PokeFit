package uk.co.ayaspace.pokefit.model.utility

import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class Language : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // Whether or not the games are published in this language.
    var official = false
        private set

    // The two-letter code of the country where this language is spoken. Note that it is not unique.
    var iso639: String? = null
        private set

    // The two-letter code of the language. Note that it is not unique.
    var iso3166: String? = null
        private set

    // The name of this resource listed in different languages.
    private var names: ArrayList<Name>? = null
    fun setId(id: Int): Language {
        this.id = id
        return this
    }

    fun setOfficial(official: Boolean): Language {
        this.official = official
        return this
    }

    fun setIso639(iso639: String?): Language {
        this.iso639 = iso639
        return this
    }

    fun setIso3166(iso3166: String?): Language {
        this.iso3166 = iso3166
        return this
    }

    fun getNames(): ArrayList<Name>? {
        return names
    }

    fun setNames(names: ArrayList<Name>?): Language {
        this.names = names
        return this
    }

}