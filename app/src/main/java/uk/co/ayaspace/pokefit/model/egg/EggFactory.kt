package uk.co.ayaspace.pokefit.model.egg

import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import uk.co.ayaspace.pokefit.model.utility.AbilityResource
import uk.co.ayaspace.pokefit.utils.Database
import com.google.gson.Gson

class EggFactory {
    fun buildEggFromParents(mother: OwnedPokemon, father: OwnedPokemon, dataAccess: Database): Egg? {
        // this logic will be flawed for the time being
        val gson = Gson()

        // Compare if Pokémon are compatible for breeding
        return if (areParentsCompatible(mother, father, dataAccess)) {
            val baby = dataAccess.getPokemonObjectByName(mother.name)
            val babyAbility = baby.abilities[0]
            var atkiv = 0
            var defiv = 0
            var hpiv = 0
            var spatkiv = 0
            var spdefiv = 0
            var spdiv = 0
            if (mother.name != "ditto" && father.name != "ditto") {
                atkiv = (Math.random() * 31).toInt()
                defiv = (Math.random() * 31).toInt()
                spdefiv = (Math.random() * 31).toInt()
                spatkiv = (Math.random() * 31).toInt()
                spdiv = (Math.random() * 31).toInt()
                hpiv = (Math.random() * 31).toInt()
            }

            // Make pokemon to put in egg
            val ownedPokemon = OwnedPokemon(baby.name, babyAbility.ability.name,
                    baby.stats[1].baseStat, baby.stats[2].baseStat, baby.stats[3].baseStat,
                    baby.stats[4].baseStat, baby.stats[0].baseStat, baby.stats[5].baseStat,
                    atkiv, defiv, spatkiv, spdefiv, hpiv, spdiv, baby.id, baby.types?.get(0).toString(), baby.types[0]
                    .toString(),
                    "pikachu")
            Egg(100, ownedPokemon)
        } else {
            // Returns a null value if parents aren't compatible for breeding
            null
        }
    }

    fun areParentsCompatible(mother: OwnedPokemon, father: OwnedPokemon, dataAccess: Database): Boolean {
        // Get egg groups for compatibility testing
        val motherEggGroups = ArrayList<String>()
        for (e in dataAccess.getSpeciesByName(mother.name).eggGroups!!) {
            motherEggGroups.add(e.name!!)
        }
        val fatherEggGroups = ArrayList<String>()
        for (e in dataAccess.getSpeciesByName(father.name).eggGroups!!) {
            fatherEggGroups.add(e.name!!)
        }

        // Compare if eggs are compatible based on egg group
        for (i in motherEggGroups) {
            for (j in fatherEggGroups) {
                if (i == j) {
                    return true
                }
            }
        }
        return (mother.species == "ditto") xor (father.species == "ditto")
    }
}