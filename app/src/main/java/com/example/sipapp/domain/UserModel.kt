package com.example.sipapp.domain

data class UserModel(
    var id: Int = 0,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var address: AddressModel? = null,
    var phone: String? = null,
    var website: String? = null,
    var company: CompanyModel? = null,
)

data class AddressModel(
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null,
    var geo: GeoModel? = null,
)

data class GeoModel(
    var lat: String? = null,
    var lng: String? = null,
)
data class CompanyModel(
    var name: String? = null,
    var catchPhrase: String? = null,
    var bs: String? = null,
)