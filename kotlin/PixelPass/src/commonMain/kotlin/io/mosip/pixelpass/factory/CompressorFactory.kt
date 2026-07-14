package io.mosip.pixelpass.factory

import io.mosip.pixelpass.brotli.BrotliCompressor
import io.mosip.pixelpass.compressor.Compressor
import io.mosip.pixelpass.zlib.ZLibCompressor


object CompressorFactory {
    fun create(type: String = "zlib"): Compressor {
        return when (type.lowercase()) {
            "zlib" -> ZLibCompressor()
            "brotli" -> BrotliCompressor()
            else -> throw IllegalArgumentException("Unsupported compressor")
        }
    }
}