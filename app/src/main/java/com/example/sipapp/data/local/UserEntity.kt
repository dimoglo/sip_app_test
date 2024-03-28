package com.example.sipapp.data.local

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class UserEntity: RealmObject {
    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var username: String? = null
    var email: String? = null
    var address: AddressEntity? = null
    var phone: String? = null
    var website: String? = null
    var company: CompanyEntity? = null
}
class AddressEntity: EmbeddedRealmObject {
    var street: String? = null
    var suite: String? = null
    var city: String? = null
    var zipcode: String? = null
    var geo: GeoEntity? = null
}
class GeoEntity: EmbeddedRealmObject {
    var lat: String? = null
    var lng: String? = null
}
class CompanyEntity: EmbeddedRealmObject {
    var name: String? = null
    var catchPhrase: String? = null
    var bs: String? = null
}