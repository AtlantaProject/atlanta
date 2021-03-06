package com.atlanta.project.rest.response

import com.atlanta.project.rest.Snowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class RestActivity(
    @SerialName("type") val type: RestMessageActivityType,
    @SerialName("party_id") val partyId: String
)

@Serializable
data class RestMessageApplication(
    @SerialName("id") val id: Snowflake,
    @SerialName("cover_image") val cover: String,
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: String?,
    @SerialName("name") val name: String
)

@Serializable(with = RestMessageActivityTypeSerializer::class)
enum class RestMessageActivityType(val id: Int) {

    JOIN(1),

    SPECTATE(2),

    LISTEN(3),

    JOIN_REQUEST(5)

}

object RestMessageActivityTypeSerializer: KSerializer<RestMessageActivityType> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): RestMessageActivityType = RestMessageActivityType.values().first { it.id == decoder.decodeInt() }

    override fun serialize(encoder: Encoder, value: RestMessageActivityType) = encoder.encodeInt(value.id)

}