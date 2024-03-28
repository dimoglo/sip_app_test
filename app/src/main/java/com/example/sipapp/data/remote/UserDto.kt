package com.example.sipapp.data.remote

data class UserDto(
    var id: Int = 0,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var address: AddressDto? = null,
    var phone: String? = null,
    var website: String? = null,
    var company: CompanyDto? = null,
)

data class AddressDto(
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null,
    var geo: GeoDto? = null,
)

data class GeoDto(
    var lat: String? = null,
    var lng: String? = null,
)

data class CompanyDto(
    var name: String? = null,
    var catchPhrase: String? = null,
    var bs: String? = null,
)