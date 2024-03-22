package com.example.mylibrary.net.parser

import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * 针对下载文件的处理
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/15 11:18
 */
class FileParser<T>(
    val fileName: String,
    val filePath: String,
    val progress: (progerss: Long, total: Long) -> Unit
) : IParser {

    override fun isNeedParser(response: Response): Boolean {
        return true
    }

    override fun parse(response: Response): T? {
        TODO("下载文件")
        var inputStream: InputStream? = null
        val buffer = ByteArray(1024 * 8)
        var len = 0;
        var fos: FileOutputStream? = null
        try {
            inputStream = response.body?.byteStream()
            val total = response.body?.contentLength()
            var sum: Long = 0;
            val dir = File(filePath)
            if(!dir.exists()){
                dir.mkdirs()
            }
            var file = File(dir, fileName)
            fos = FileOutputStream(file)
            len = inputStream?.read(buffer)!!
            while(len != -1){
                sum += len.toLong()
                fos.write(buffer, 0, len)
                val finalSum = sum
                TODO("进度条的回调")
//                RequestManager.mMainHandler.post({ progress(sum, total) })
                len = inputStream.read(buffer)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                response.body?.close()
                inputStream?.close()
                fos?.close()
            } catch (e: Exception){ }
        }
    }

    override fun unParse(response: Response): T? {
        TODO("Not yet implemented")
    }
}