package com.example.sipapp.data.mappers

import com.example.sipapp.data.local.AddressEntity
import com.example.sipapp.data.local.CompanyEntity
import com.example.sipapp.data.local.GeoEntity
import com.example.sipapp.data.local.UserEntity
import com.example.sipapp.data.remote.AddressDto
import com.example.sipapp.data.remote.CompanyDto
import com.example.sipapp.data.remote.GeoDto
import com.example.sipapp.data.remote.UserDto
import com.example.sipapp.domain.AddressModel
import com.example.sipapp.domain.CompanyModel
import com.example.sipapp.domain.GeoModel
import com.example.sipapp.domain.UserModel

fun UserDto.toEntity(): UserEntity {
    return UserEntity().apply {
        id = this@toEntity.id
        name = this@toEntity.name
        username = this@toEntity.username
        email = this@toEntity.email
        address = this@toEntity.address?.toEntity()
        phone = this@toEntity.phone
        website = this@toEntity.website
        company = this@toEntity.company?.toEntity()
    }
}

private fun AddressDto.toEntity(): AddressEntity {
    return AddressEntity().apply {
        street = this@toEntity.street
        suite = this@toEntity.suite
        city = this@toEntity.city
        zipcode = this@toEntity.zipcode
        geo = this@toEntity.geo?.toEntity()
    }
}

private fun GeoDto.toEntity(): GeoEntity {
    return GeoEntity().apply {
        lat = this@toEntity.lat
        lng = this@toEntity.lng
    }
}

private fun CompanyDto.toEntity(): CompanyEntity {
    return CompanyEntity().apply {
        name = this@toEntity.name
        catchPhrase = this@toEntity.catchPhrase
        bs = this@toEntity.bs
    }
}


fun UserEntity.toModel(): UserModel {
    return UserModel(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email,
        address = this.address?.toModel(),
        phone = this.phone,
        website = this.website,
        company = this.company?.toModel(),
    )
}

private fun AddressEntity.toModel(): AddressModel {
    return AddressModel(
        street = this.street,
        suite = this.suite,
        city = this.city,
        zipcode = this.zipcode,
        geo = this.geo?.toModel()
    )
}

private fun GeoEntity.toModel(): GeoModel {
    return GeoModel(
        lat = this.lat,
        lng = this.lng
    )
}

private fun CompanyEntity.toModel(): CompanyModel {
    return CompanyModel(
        name = this.name,
        catchPhrase = this.catchPhrase,
        bs = this.bs
    )
}
