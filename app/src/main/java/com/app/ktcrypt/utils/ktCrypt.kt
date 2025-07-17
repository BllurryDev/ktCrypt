package com.app.ktcrypt.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.nio.ByteBuffer
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import kotlin.reflect.KProperty

object ktCrypt {
    private const val Trans = "AES/GCM/NoPadding"
    private val secretKey = KeyStoreHelper.getOrCreateKey()

    fun encryptInt(value: Int): ByteArray {
        val buffer = ByteBuffer.allocate(4).putInt(value).array()
        val cipher = Cipher.getInstance(Trans)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(buffer)

        return iv + encrypted
    }

    fun decryptInt(data: ByteArray): Int {
        val iv = data.copyOfRange(0, 12)
        val encrypted = data.copyOfRange(12, data.size)
        val cipher = Cipher.getInstance(Trans)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        val decrypted = cipher.doFinal(encrypted)
        return ByteBuffer.wrap(decrypted).int
    }

    fun encryptFloat(value: Float): ByteArray {
        val buffer = ByteBuffer.allocate(4).putFloat(value).array()
        val cipher = Cipher.getInstance(Trans)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(buffer)
        return iv + encrypted
    }

    fun decryptFloat(data: ByteArray): Float {
        val iv = data.copyOfRange(0, 12)
        val encrypted = data.copyOfRange(12, data.size)
        val cipher = Cipher.getInstance(Trans)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        val decrypted = cipher.doFinal(encrypted)
        return ByteBuffer.wrap(decrypted).float
    }
}




class ktCryptInt(initial: Int) {
    private var encrypted by mutableStateOf(ktCrypt.encryptInt(initial))

    operator fun getValue(thisRef: Any?, property: KProperty<*>?): Int {
        return ktCrypt.decryptInt(encrypted)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        encrypted = ktCrypt.encryptInt(value)
    }

    override fun toString(): String = getValue(null, null).toString()
}

class ktCryptFloat(initial: Float) {
    private var encrypted by mutableStateOf(ktCrypt.encryptFloat(initial))

    operator fun getValue(thisRef: Any?, property: KProperty<*>?): Float {
        return ktCrypt.decryptFloat(encrypted)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
        encrypted = ktCrypt.encryptFloat(value)
    }

    override fun toString(): String = getValue(null, null).toString()
}


