package io.mosip.pixelpass.factory

import io.mosip.pixelpass.brotli.BrotliCompressor
import io.mosip.pixelpass.compressor.Compressor
import io.mosip.pixelpass.shared.CompressionType
import io.mosip.pixelpass.zlib.ZLibCompressor


internal object CompressorFactory {
    fun create(type: CompressionType = CompressionType.ZLIB): Compressor {
        return when (type) {
            CompressionType.ZLIB -> ZLibCompressor()
            CompressionType.BROTLI -> BrotliCompressor()
        }
    }
}