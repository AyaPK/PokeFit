package uk.co.ayaspace.pokefit.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.preference.PreferenceManager
import uk.co.ayaspace.pokefit.model.egg.Egg
import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies
import com.google.gson.Gson

class Database(context: Context) {
    var gson = Gson()
    var adapter: DatabaseAdapter = DatabaseAdapter(context)
    fun insertPokemonData(name: String?, `object`: String?, species: String?): Long {
        val dbb = adapter.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DatabaseAdapter.NAME, name)
        contentValues.put(DatabaseAdapter.OBJECT, `object`)
        contentValues.put(DatabaseAdapter.SPECIES, species)
        return dbb.insert(DatabaseAdapter.TABLE_NAME, null, contentValues)
    }

    fun updateDaycareSlot(slot: String, `object`: String?) {
        val dbb = adapter.writableDatabase
        val updateSlot = "UPDATE daycare SET Object = '$`object`' WHERE Slot_number = $slot;"
        dbb.execSQL(updateSlot)
    }

    fun updatePartySlot(slot: String, `object`: String?) {
        val dbb = adapter.writableDatabase
        val updateSlot = "UPDATE party SET Object = '$`object`' WHERE Slot_number = $slot;"
        dbb.execSQL(updateSlot)
    }

    val allDaycareData: String
        get() {
            val db = adapter.writableDatabase
            val columns = arrayOf(DatabaseAdapter.SLOT_NUMBER, DatabaseAdapter.OBJECT)
            val cursor = db.query("daycare", columns, null, null, null, null, null)
            val buffer = StringBuffer()
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.SLOT_NUMBER))
                val `object` = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.OBJECT))
                buffer.append("""
    ${name}___${`object`}___
    
    """.trimIndent())
            }
            return buffer.toString()
        }

    fun getPokemonObjectFromDaycare(slot: String): OwnedPokemon {
        val db = adapter.writableDatabase
        val columns = arrayOf(DatabaseAdapter.SLOT_NUMBER, DatabaseAdapter.OBJECT)
        val query = "SELECT * FROM daycare WHERE Slot_number = '$slot'"
        val cursor = db.rawQuery(query, null)
        cursor.moveToNext()
        val n = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.SLOT_NUMBER))
        val `object` = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.OBJECT))
        return gson.fromJson(`object`, OwnedPokemon::class.java)
    }

    fun getPartySlot(slot: String): String {
        val db = adapter.writableDatabase
        val columns = arrayOf(DatabaseAdapter.SLOT_NUMBER, DatabaseAdapter.OBJECT)
        val query = "SELECT * FROM party WHERE Slot_number = '$slot'"
        val cursor = db.rawQuery(query, null)
        cursor.moveToNext()
        val n = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.SLOT_NUMBER))
        return cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.OBJECT))
    }

    val currency: Int
        get() {
            val db = adapter.writableDatabase
            val columns = arrayOf(DatabaseAdapter.SLOT_NUMBER, DatabaseAdapter.OBJECT)
            val query = "SELECT * FROM currency WHERE Slot_number = '1'"
            val cursor = db.rawQuery(query, null)
            cursor.moveToNext()
            val n = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.SLOT_NUMBER))
            return cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseAdapter.AMOUNT))
        }

    fun updateCurrency(newValue: Int) {
        val dbb = adapter.writableDatabase
        val updateSlot = "UPDATE currency SET Amount = $newValue WHERE Slot_number = 1;"
        dbb.execSQL(updateSlot)
    }

    val eggObjectFromDaycare: Egg
        get() {
            val db = adapter.writableDatabase
            val columns = arrayOf(DatabaseAdapter.SLOT_NUMBER, DatabaseAdapter.OBJECT)
            val query = "SELECT * FROM daycare WHERE Slot_number = '3'"
            val cursor = db.rawQuery(query, null)
            cursor.moveToNext()
            val n = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.SLOT_NUMBER))
            val `object` = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.OBJECT))
            return gson.fromJson(`object`, Egg::class.java)
        }
    val allPokemonData: String
        get() {
            val db = adapter.writableDatabase
            val columns = arrayOf(DatabaseAdapter.NAME, DatabaseAdapter.OBJECT, DatabaseAdapter.SPECIES)
            val cursor = db.query(DatabaseAdapter.TABLE_NAME, columns, null, null, null, null, null)
            val buffer = StringBuffer()
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.NAME))
                val `object` = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.OBJECT))
                val species = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.SPECIES))
                buffer.append("""
    ${name}___${`object`}___$species
    
    """.trimIndent())
            }
            return buffer.toString()
        }
    val allPokemonDataAsList: ArrayList<String>
        get() {
            val pkmn = ArrayList<String>()
            val db = adapter.writableDatabase
            val columns = arrayOf(DatabaseAdapter.NAME, DatabaseAdapter.OBJECT, DatabaseAdapter.SPECIES)
            val cursor = db.query(DatabaseAdapter.TABLE_NAME, columns, null, null, null, null, null)
            val buffer = StringBuffer()
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.NAME))
                val `object` = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.OBJECT))
                val species = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.SPECIES))
                pkmn.add(name)
            }
            return pkmn
        }

    fun getPokemonObjectByName(name: String?): Pokemon {
        val db = adapter.writableDatabase
        val columns = arrayOf(DatabaseAdapter.NAME, DatabaseAdapter.OBJECT)
        val query = "SELECT * FROM pokemonFromApi WHERE Name = '$name'"
        val cursor = db.rawQuery(query, null)
        cursor.moveToNext()
        val n = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.NAME))
        val `object` = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.OBJECT))
        return gson.fromJson(`object`, Pokemon::class.java)
    }

    fun getSpeciesByName(name: String?): PokemonSpecies {
        val db = adapter.writableDatabase
        val columns = arrayOf(DatabaseAdapter.NAME, DatabaseAdapter.OBJECT)
        val query = "SELECT * FROM pokemonFromApi WHERE Name = '$name'"
        val cursor = db.rawQuery(query, null)
        cursor.moveToNext()
        val speciesObject = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.SPECIES))
        return gson.fromJson(speciesObject, PokemonSpecies::class.java)
    }

    fun checkTableExists(tableName: String): Boolean {
        val db = adapter.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $tableName", null)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.close()
                return true
            }
            cursor.close()
        }
        return false
    }

    fun updatePartyDetails(context: Context?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        for (i in 1..6) {
            val dbb = adapter.writableDatabase
            val partySlot = sharedPreferences.getString("partySlot$i", null)
            val updateSlot = "UPDATE party SET Object = '$partySlot' WHERE Slot_number = $i;"
            dbb.execSQL(updateSlot)
        }
    }

    fun retrievePartyDetails(context: Context?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        for (i in 1..6) {
            val db = adapter.writableDatabase
            val columns = arrayOf(DatabaseAdapter.SLOT_NUMBER, DatabaseAdapter.OBJECT)
            val query = "SELECT * FROM party WHERE Slot_number = $i;"
            val cursor = db.rawQuery(query, null)
            cursor.moveToNext()
            val pkmnObject = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseAdapter.OBJECT))
            editor.putString("partySlot$i", pkmnObject)
        }
        editor.apply()
    }

    class DatabaseAdapter(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_Version) {
        override fun onCreate(db: SQLiteDatabase) {
            try {
                db.execSQL(CREATE_PKMN_TABLE)
                db.execSQL(CREATE_PARTY_TABLE)
                db.execSQL(CREATE_PLAYER_TABLE)
                db.execSQL(CREATE_DAYCARE_TABLE)
                db.execSQL(CREATE_CURRENCY_TABLE)
                for (i in 1..6) {
                    db.execSQL("INSERT INTO party VALUES($i, null);")
                }
                db.execSQL("INSERT INTO player VALUES(1, null)")
                db.execSQL("INSERT INTO currency VALUES(1, 0)")
                for (i in 1..3) {
                    db.execSQL("INSERT INTO daycare VALUES($i, null);")
                }
            } catch (e: Exception) {
                println(e)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            try {
                db.execSQL(DROP_PKMN_TABLE)
                db.execSQL(DROP_PARTY_TABLE)
                db.execSQL(DROP_PLAYER_TABLE)
                onCreate(db)
            } catch (e: Exception) {
                println(e)
            }
        }

        companion object {
            private const val DATABASE_NAME = "mainDatabase"
            const val TABLE_NAME = "pokemonFromApi"
            private const val DATABASE_Version = 1
            const val NAME = "Name"
            const val OBJECT = "Object"
            const val SPECIES = "Species"
            const val SLOT_NUMBER = "Slot_number"
            const val AMOUNT = "Amount"
            private const val CREATE_PKMN_TABLE = "CREATE TABLE pokemonFromApi (Name VARCHAR(255),Object VARCHAR(225), Species VARCHAR(225));"
            private const val CREATE_PARTY_TABLE = "CREATE TABLE party(Slot_number VARCHAR(255), Object VARCHAR(255));"
            private const val CREATE_PLAYER_TABLE = "CREATE TABLE player(id VARCHAR(255), Object VARCHAR(255));"
            private const val CREATE_DAYCARE_TABLE = "CREATE TABLE daycare(Slot_number VARCHAR(255), Object VARCHAR(255));"
            private const val CREATE_CURRENCY_TABLE = "CREATE TABLE currency(Slot_number VARCHAR(255), Amount int);"
            private const val DROP_PKMN_TABLE = "DROP TABLE IF EXISTS pokemonFromApi"
            private const val DROP_PARTY_TABLE = "DROP TABLE IF EXISTS party"
            private const val DROP_PLAYER_TABLE = "DROP TABLE IF EXISTS player"
        }
    }

}