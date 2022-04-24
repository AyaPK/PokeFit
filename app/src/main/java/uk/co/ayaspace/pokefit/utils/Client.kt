package uk.co.ayaspace.pokefit.utils

import uk.co.ayaspace.pokefit.model.pokemon.Pokemon
import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList

object Client {
    fun getNamedAPIResourceListByEndpoint(endpoint: String?): NamedAPIResourceList {
        return NamedAPIResourceList.getByEndpoint(endpoint!!)
    }

//    fun getBerryById(id: Int): Berry {
//        return Berry.getById(id)
//    }
//
//    fun getBerryByName(name: String?): Berry {
//        return Berry.getByName(name)
//    }
//
//    fun getBerryList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("berry", limit, offset)
//    }
//
//    fun getBerryFirmnessById(id: Int): BerryFirmness {
//        return BerryFirmness.getById(id)
//    }
//
//    fun getBerryFirmnessByName(name: String?): BerryFirmness {
//        return BerryFirmness.getByName(name)
//    }
//
//    fun getBerryFirmnessList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("berry-firmness", limit, offset)
//    }
//
//    fun getBerryFlavorById(id: Int): BerryFlavor {
//        return BerryFlavor.getById(id)
//    }
//
//    fun getBerryFlavorByName(name: String?): BerryFlavor {
//        return BerryFlavor.getByName(name)
//    }
//
//    fun getBerryFlavorList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("berry-flavor", limit, offset)
//    }
//
//    fun getContestTypeById(id: Int): ContestType {
//        return ContestType.getById(id)
//    }
//
//    fun getContestTypeByName(name: String?): ContestType {
//        return ContestType.getByName(name)
//    }
//
//    fun getContestTypeList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("contest-type", limit, offset)
//    }
//
//    fun getContestEffectById(id: Int): ContestEffect {
//        return ContestEffect.getById(id)
//    }
//
//    fun getContestEffectList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("contest-effect", limit, offset)
//    }
//
//    fun getSuperContestEffectById(id: Int): SuperContestEffect {
//        return SuperContestEffect.getById(id)
//    }
//
//    fun getSuperContestEffectList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("super-contest-effect", limit, offset)
//    }
//
//    fun getEncounterMethodById(id: Int): EncounterMethod {
//        return EncounterMethod.getById(id)
//    }
//
//    fun getEncounterMethodByName(name: String?): EncounterMethod {
//        return EncounterMethod.getByName(name)
//    }
//
//    fun getEncounterMethodList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("encounter-method", limit, offset)
//    }
//
//    fun getEncounterConditionById(id: Int): EncounterCondition {
//        return EncounterCondition.getById(id)
//    }
//
//    fun getEncounterConditionByName(name: String?): EncounterCondition {
//        return EncounterCondition.getByName(name)
//    }
//
//    fun getEncounterConditionList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("encounter-condition", limit, offset)
//    }
//
//    fun getEncounterConditionValueById(id: Int): EncounterConditionValue {
//        return EncounterConditionValue.getById(id)
//    }
//
//    fun getEncounterConditionValueByName(name: String?): EncounterConditionValue {
//        return EncounterConditionValue.getByName(name)
//    }
//
//    fun getEncounterConditionValueList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("encounter-condition-value", limit, offset)
//    }
//
//    fun getEvolutionChainById(id: Int): EvolutionChain {
//        return EvolutionChain.getById(id)
//    }
//
//    fun getEvolutionChainList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("evolution-chain", limit, offset)
//    }
//
//    fun getEvolutionTriggerById(id: Int): EvolutionTrigger {
//        return EvolutionTrigger.getById(id)
//    }
//
//    fun getEvolutionTriggerByName(name: String?): EvolutionTrigger {
//        return EvolutionTrigger.getByName(name)
//    }
//
//    fun getEvolutionTriggerList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("evolution-trigger", limit, offset)
//    }
//
//    fun getGenerationById(id: Int): Generation {
//        return Generation.getById(id)
//    }
//
//    fun getGenerationByName(name: String?): Generation {
//        return Generation.getByName(name)
//    }
//
//    fun getGenerationList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("generation", limit, offset)
//    }
//
//    fun getPokedexById(id: Int): Pokedex {
//        return Pokedex.getById(id)
//    }
//
//    fun getPokedexByName(name: String?): Pokedex {
//        return Pokedex.getByName(name)
//    }
//
//    fun getPokedexList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("pokedex", limit, offset)
//    }
//
//    fun getVersionById(id: Int): Version {
//        return Version.getById(id)
//    }
//
//    fun getVersionByName(name: String?): Version {
//        return Version.getByName(name)
//    }
//
//    fun getVersionList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("version", limit, offset)
//    }
//
//    fun getVersionGroupById(id: Int): VersionGroup {
//        return VersionGroup.getById(id)
//    }
//
//    fun getVersionGroupByName(name: String?): VersionGroup {
//        return VersionGroup.getByName(name)
//    }
//
//    fun getVersionGroupList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("version-group", limit, offset)
//    }
//
//    fun getItemById(id: Int): Item {
//        return Item.getById(id)
//    }
//
//    fun getItemByName(name: String?): Item {
//        return Item.getByName(name)
//    }
//
//    fun getItemList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("item", limit, offset)
//    }
//
//    fun getItemAttributeById(id: Int): ItemAttribute {
//        return ItemAttribute.getById(id)
//    }
//
//    fun getItemAttributeByName(name: String?): ItemAttribute {
//        return ItemAttribute.getByName(name)
//    }
//
//    fun getItemAttributeList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("item-attribute", limit, offset)
//    }
//
//    fun getItemCategoryById(id: Int): ItemCategory {
//        return ItemCategory.getById(id)
//    }
//
//    fun getItemCategoryByName(name: String?): ItemCategory {
//        return ItemCategory.getByName(name)
//    }
//
//    fun getItemCategoryList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("item-category", limit, offset)
//    }
//
//    fun getItemFlingEffectById(id: Int): ItemFlingEffect {
//        return ItemFlingEffect.getById(id)
//    }
//
//    fun getItemFlingEffectByName(name: String?): ItemFlingEffect {
//        return ItemFlingEffect.getByName(name)
//    }
//
//    fun getItemFlingEffectList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("item-fling-effect", limit, offset)
//    }
//
//    fun getItemPocketById(id: Int): ItemPocket {
//        return ItemPocket.getById(id)
//    }
//
//    fun getItemPocketByName(name: String?): ItemPocket {
//        return ItemPocket.getByName(name)
//    }
//
//    fun getItemPocketList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("item-pocket", limit, offset)
//    }
//
//    fun getLocationById(id: Int): Location {
//        return Location.getById(id)
//    }
//
//    fun getLocationByName(name: String?): Location {
//        return Location.getByName(name)
//    }
//
//    fun getLocationList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("location", limit, offset)
//    }
//
//    fun getLocationAreaById(id: Int): LocationArea {
//        return LocationArea.getById(id)
//    }
//
//    fun getLocationAreaByName(name: String?): LocationArea {
//        return LocationArea.getByName(name)
//    }
//
//    fun getLocationAreaList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("location-area", limit, offset)
//    }
//
//    fun getPalParkAreaById(id: Int): PalParkArea {
//        return PalParkArea.getById(id)
//    }
//
//    fun getPalParkAreaByName(name: String?): PalParkArea {
//        return PalParkArea.getByName(name)
//    }
//
//    fun getPalParkAreaList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("pal-park-area", limit, offset)
//    }
//
//    fun getRegionById(id: Int): Region {
//        return Region.getById(id)
//    }
//
//    fun getRegionByName(name: String?): Region {
//        return Region.getByName(name)
//    }
//
//    fun getRegionList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("region", limit, offset)
//    }
//
//    fun getMachineById(id: Int): Machine {
//        return Machine.getById(id)
//    }
//
//    fun getMachineList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("machine", limit, offset)
//    }
//
//    fun getMoveById(id: Int): Move {
//        return Move.getById(id)
//    }
//
//    fun getMoveByName(name: String?): Move {
//        return Move.getByName(name)
//    }
//
//    fun getMoveList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("move", limit, offset)
//    }
//
//    fun getMoveAilmentById(id: Int): MoveAilment {
//        return MoveAilment.getById(id)
//    }
//
//    fun getMoveAilmentByName(name: String?): MoveAilment {
//        return MoveAilment.getByName(name)
//    }
//
//    fun getMoveAilmentList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("move-ailment", limit, offset)
//    }
//
//    fun getMoveBattleStyleById(id: Int): MoveBattleStyle {
//        return MoveBattleStyle.getById(id)
//    }
//
//    fun getMoveBattleStyleByName(name: String?): MoveBattleStyle {
//        return MoveBattleStyle.getByName(name)
//    }
//
//    fun getMoveBattleStyleList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("move-battle-style", limit, offset)
//    }
//
//    fun getMoveDamageClassById(id: Int): MoveDamageClass {
//        return MoveDamageClass.getById(id)
//    }
//
//    fun getMoveDamageClassByName(name: String?): MoveDamageClass {
//        return MoveDamageClass.getByName(name)
//    }
//
//    fun getMoveDamageClassList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("move-damage-class", limit, offset)
//    }
//
//    fun getMoveLearnMethodById(id: Int): MoveLearnMethod {
//        return MoveLearnMethod.getById(id)
//    }
//
//    fun getMoveLearnMethodByName(name: String?): MoveLearnMethod {
//        return MoveLearnMethod.getByName(name)
//    }
//
//    fun getMoveLearnMethodList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("move-learn-method", limit, offset)
//    }
//
//    fun getMoveTargetById(id: Int): MoveTarget {
//        return MoveTarget.getById(id)
//    }
//
//    fun getMoveTargetByName(name: String?): MoveTarget {
//        return MoveTarget.getByName(name)
//    }
//
//    fun getMoveTargetList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("move-target", limit, offset)
//    }
//
//    fun getAbilityById(id: Int): Ability {
//        return Ability.getById(id)
//    }
//
//    fun getAbilityByName(name: String?): Ability {
//        return Ability.getByName(name)
//    }
//
//    fun getAbilityList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("ability", limit, offset)
//    }
//
//    fun getCharacteristicById(id: Int): Characteristic {
//        return Characteristic.getById(id)
//    }
//
//    fun getCharacteristicList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("characteristic", limit, offset)
//    }
//
//    fun getEggGroupById(id: Int): EggGroup {
//        return EggGroup.getById(id)
//    }
//
//    fun getEggGroupByName(name: String?): EggGroup {
//        return EggGroup.getByName(name)
//    }
//
//    fun getEggGroupList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("egg-group", limit, offset)
//    }
//
//    fun getGenderById(id: Int): Gender {
//        return Gender.getById(id)
//    }
//
//    fun getGenderByName(name: String?): Gender {
//        return Gender.getByName(name)
//    }
//
//    fun getGenderList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("gender", limit, offset)
//    }
//
//    fun getGrowthRateById(id: Int): GrowthRate {
//        return GrowthRate.getById(id)
//    }
//
//    fun getGrowthRateByName(name: String?): GrowthRate {
//        return GrowthRate.getByName(name)
//    }
//
//    fun getGrowthRateList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("growth-rate", limit, offset)
//    }
//
//    fun getNatureById(id: Int): Nature {
//        return Nature.getById(id)
//    }
//
//    fun getNatureByName(name: String?): Nature {
//        return Nature.getByName(name)
//    }
//
//    fun getNatureList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("nature", limit, offset)
//    }
//
//    fun getPokeathlonStatById(id: Int): PokeathlonStat {
//        return PokeathlonStat.getById(id)
//    }
//
//    fun getPokeathlonStatByName(name: String?): PokeathlonStat {
//        return PokeathlonStat.getByName(name)
//    }
//
//    fun getPokeathlonStatList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("pokeathlon-stat", limit, offset)
//    }

    fun getPokemonById(id: Int): Pokemon {
        return Pokemon.getById(id)
    }

    fun getPokemonByName(name: String?): Pokemon {
        return Pokemon.getByName(name!!)
    }

    fun getPokemonList(limit: Int, offset: Int): NamedAPIResourceList {
        return NamedAPIResourceList.getList("pokemon", limit, offset)
    }

//    fun getPokemonColorById(id: Int): PokemonColor {
//        return PokemonColor.getById(id)
//    }
//
//    fun getPokemonColorByName(name: String?): PokemonColor {
//        return PokemonColor.getByName(name)
//    }
//
//    fun getPokemonColorList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("pokemon-color", limit, offset)
//    }
//
//    fun getPokemonFormById(id: Int): PokemonForm {
//        return PokemonForm.getById(id)
//    }
//
//    fun getPokemonFormByName(name: String?): PokemonForm {
//        return PokemonForm.getByName(name)
//    }
//
//    fun getPokemonFormList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("pokemon-form", limit, offset)
//    }
//
//    fun getPokemonHabitatById(id: Int): PokemonHabitat {
//        return PokemonHabitat.getById(id)
//    }
//
//    fun getPokemonHabitatByName(name: String?): PokemonHabitat {
//        return PokemonHabitat.getByName(name)
//    }
//
//    fun getPokemonHabitatList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("pokemon-habitat", limit, offset)
//    }
//
//    fun getPokemonShapeById(id: Int): PokemonShape {
//        return PokemonShape.getById(id)
//    }
//
//    fun getPokemonShapeByName(name: String?): PokemonShape {
//        return PokemonShape.getByName(name)
//    }
//
//    fun getPokemonShapeList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("pokemon-shape", limit, offset)
//    }
//
//    fun getPokemonSpeciesById(id: Int): PokemonSpecies {
//        return PokemonSpecies.getById(id)
//    }
//
//    fun getPokemonSpeciesByName(name: String?): PokemonSpecies {
//        return PokemonSpecies.getByName(name)
//    }
//
//    fun getPokemonSpeciesList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("pokemon-species", limit, offset)
//    }
//
//    fun getStatById(id: Int): Stat {
//        return Stat.getById(id)
//    }
//
//    fun getStatByName(name: String?): Stat {
//        return Stat.getByName(name)
//    }
//
//    fun getStatList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("stat", limit, offset)
//    }
//
//    fun getTypeById(id: Int): Type {
//        return Type.getById(id)
//    }
//
//    fun getTypeByName(name: String?): Type {
//        return Type.getByName(name)
//    }
//
//    fun getTypeList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("type", limit, offset)
//    }
//
//    fun getLanguageById(id: Int): Language {
//        return Language.getById(id)
//    }
//
//    fun getLanguageByName(name: String?): Language {
//        return Language.getByName(name)
//    }
//
//    fun getLanguageList(limit: Int, offset: Int): NamedAPIResourceList {
//        return NamedAPIResourceList.getList("language", limit, offset)
//    }
//
//    fun getMoveCategoryById(id: Int): MoveCategory {
//        return MoveCategory.getById(id)
//    }
//
//    fun getMoveCategoryByName(name: String?): MoveCategory {
//        return MoveCategory.getByName(name)
//    }

    fun getMoveCategoryList(limit: Int, offset: Int): NamedAPIResourceList {
        return NamedAPIResourceList.getList("move-category", limit, offset)
    }
}