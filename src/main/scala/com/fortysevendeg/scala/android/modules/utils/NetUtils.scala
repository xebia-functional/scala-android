package com.fortysevendeg.scala.android.modules.utils

import com.squareup.okhttp.{Headers, OkHttpClient}
import io.taig.communicator.result.Parser

import scala.concurrent.Future

trait NetUtils {
  
  def loadJson[T](url: String, headers: Seq[(String, String)] = Seq.empty)(implicit parser: Parser[T], client: OkHttpClient = new OkHttpClient()): Future[T] = {
    
    import io.taig.communicator._

    import scala.concurrent.ExecutionContext.Implicits.global
    
    Request(url)
      .headers(toHeaders(headers))
      .get()
      .parse[T]
      .transform(response => response.payload, throwable => throwable)
  }

  def toHeaders(headers: Seq[(String, String)]): Headers = {
    val headersBuilder = new Headers.Builder()
    headers map { header =>
      headersBuilder.add(header._1, header._2)
    }
    headersBuilder.build()
  }

}
