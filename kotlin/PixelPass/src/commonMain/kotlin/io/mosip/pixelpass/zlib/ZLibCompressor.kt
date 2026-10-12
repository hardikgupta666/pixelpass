package io.mosip.pixelpass.zlib

import io.mosip.pixelpass.compressor.Compressor
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.util.zip.Deflater
import java.util.zip.DeflaterInputStream
import java.util.zip.InflaterInputStream

internal class ZLibCompressor: Compressor {

    override fun encode(input: ByteArray, level: Int) =
        DeflaterInputStream(input.inputStream(), Deflater(level)).readBytes()

    override fun decode(input: ByteArray) =
        InflaterInputStream(input.inputStream()).readBytes().also {
            val inflaterStream = InflaterInputStream(input.inputStream())
            val outputStream = ByteArrayOutputStream(DEFAULT_BUFFER_SIZE)
            inflaterStream.copyTo(outputStream)
            outputStream.toByteArray()
        }
}

private fun InflaterInputStream.copyTo(out: OutputStream, bufferSize: Int = DEFAULT_BUFFER_SIZE): Long {
    var bytesCopied: Long = 0
    val buffer = ByteArray(bufferSize)
    var bytes = read(buffer)
    while (bytes >= 0) {
        out.write(buffer, 0, bytes)
        bytesCopied += bytes
        bytes = read(buffer)
    }
    return bytesCopied
}
