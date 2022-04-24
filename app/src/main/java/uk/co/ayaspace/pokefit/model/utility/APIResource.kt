package uk.co.ayaspace.pokefit.model.utility

import com.google.gson.Gson

open class APIResource {
    // The URL of the referenced resource.
    var url: String? = null
        private set

    // Check if this object has already been fetched from the API
    var isFetched = false

    fun setUrl(url: String?): APIResource {
        this.url = url
        return this
    }

    fun setIsFetched(is_fetched: Boolean): APIResource {
        isFetched = is_fetched
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}