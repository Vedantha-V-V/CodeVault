package com.vedanthavv.codevault.util

import android.util.Base64
import android.util.Log
import java.nio.ByteBuffer
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import com.vedanthavv.codevault.BuildConfig

object EncryptionManager {
    private const val TAG = "CryptoManager"
    // 32-byte (256-bit) key. Replace with a securely stored key in production.
    private const val SECRET = BuildConfig.SECRET_KEY
    private val KEY_BYTES = SECRET.toByteArray(Charsets.UTF_8).let {
        // ensure 32 bytes
        if (it.size == 32) it else it.copyOf(32)
    }
    private val secretKey = SecretKeySpec(KEY_BYTES, "AES")
    private const val TRANSFORMATION = "AES/GCM/NoPadding"
    private const val IV_SIZE = 12 // recommended for GCM
    private const val TAG_LENGTH = 128

    fun encrypt(plain: String): String {
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            val iv = ByteArray(IV_SIZE)
            SecureRandom().nextBytes(iv)
            val spec = GCMParameterSpec(TAG_LENGTH, iv)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec)
            val cipherBytes = cipher.doFinal(plain.toByteArray(Charsets.UTF_8))

            // store IV + cipherText together
            val byteBuffer = ByteBuffer.allocate(iv.size + cipherBytes.size)
            byteBuffer.put(iv)
            byteBuffer.put(cipherBytes)
            return Base64.encodeToString(byteBuffer.array(), Base64.NO_WRAP)
        } catch (e: Exception) {
            Log.e(TAG, "encryption failed", e)
            return plain
        }
    }

    fun decrypt(encrypted: String): String {
        try {
            val all = Base64.decode(encrypted, Base64.NO_WRAP)
            if (all.size < IV_SIZE) return encrypted
            val iv = all.copyOfRange(0, IV_SIZE)
            val cipherBytes = all.copyOfRange(IV_SIZE, all.size)
            val cipher = Cipher.getInstance(TRANSFORMATION)
            val spec = GCMParameterSpec(TAG_LENGTH, iv)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
            val plainBytes = cipher.doFinal(cipherBytes)
            return String(plainBytes, Charsets.UTF_8)
        } catch (e: Exception) {
            Log.e(TAG, "decryption failed", e)
            return encrypted
        }
    }
}
