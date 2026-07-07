package io.mosip.pixelpass.brotli
import com.aayushatharva.brotli4j.Brotli4jLoader
import com.aayushatharva.brotli4j.encoder.BrotliOutputStream
import com.aayushatharva.brotli4j.decoder.BrotliInputStream
import com.aayushatharva.brotli4j.encoder.Encoder
import io.mosip.pixelpass.compressor.Compressor
import java.io.ByteArrayOutputStream

 class BrotliCompressor: Compressor {
    init {
        Brotli4jLoader.ensureAvailability()
    }
    override fun encode(input: ByteArray, qualityLevel: Int): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val params = Encoder.Parameters().setQuality(qualityLevel)

        BrotliOutputStream(outputStream, params).use { brotliStream ->
            brotliStream.write(input)
        }

        return outputStream.toByteArray()
    }
    override  fun decode(input: ByteArray): ByteArray{
        val inputStream = input.inputStream()
        BrotliInputStream(inputStream).use {
            brotliStream->return brotliStream.readBytes()
        }
    }
}