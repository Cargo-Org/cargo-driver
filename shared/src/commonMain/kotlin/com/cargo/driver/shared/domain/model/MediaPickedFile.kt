package com.cargo.driver.shared.domain.model

data class MediaPickedFile(
    val name : String ,
    val sizeBytes: Long ,
    val mimeType : String ,
    val bytes : ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MediaPickedFile

        if (sizeBytes != other.sizeBytes) return false
        if (name != other.name) return false
        if (mimeType != other.mimeType) return false
        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sizeBytes.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + mimeType.hashCode()
        result = 31 * result + bytes.contentHashCode()
        return result
    }
}