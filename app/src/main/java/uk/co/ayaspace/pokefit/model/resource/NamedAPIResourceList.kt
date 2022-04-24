package uk.co.ayaspace.pokefit.model.resource

import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class NamedAPIResourceList {
    // The total number of resources available from this API.
    var count = 0
        private set

    // The URL for the next page in the list.
    var next: String? = null
        private set

    // The URL for the previous page in the list.
    var previous = false
        private set

    // A list of named API resources.
    var results: ArrayList<NamedAPIResource>? = null
        private set

    fun setCount(count: Int): NamedAPIResourceList {
        this.count = count
        return this
    }

    fun setNext(next: String?): NamedAPIResourceList {
        this.next = next
        return this
    }

    fun setPrevious(previous: Boolean): NamedAPIResourceList {
        this.previous = previous
        return this
    }

    fun setResults(results: ArrayList<NamedAPIResource>?): NamedAPIResourceList {
        this.results = results
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        private operator fun get(url: String): NamedAPIResourceList {
            return Gson().fromJson(Information().fromInternet(url), NamedAPIResourceList::class.java)
        }

        fun getList(endpoint: String, limit: Int, offset: Int): NamedAPIResourceList {
            val json: String = Information().fromInternet("https://pokeapi.co/api/v2/" + endpoint + "/?limit=" + Math.abs(limit) + "&offset=" + Math.abs(offset))
            return Gson().fromJson(json, NamedAPIResourceList::class.java)
        }

        fun getByEndpoint(endpoint: String): NamedAPIResourceList {
            return Companion["https://pokeapi.co/api/v2/$endpoint"]
        }
    }
}