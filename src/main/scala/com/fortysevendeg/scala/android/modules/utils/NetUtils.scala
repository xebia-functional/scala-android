package com.fortysevendeg.scala.android.modules.utils

import java.io.{InputStreamReader, BufferedReader, InputStream}

import org.apache.http.{HttpEntity, HttpResponse}
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

import scala.util.{Failure, Success, Try}

trait NetUtils {
  
  val encoding = "UTF-8"

  def getJson(url: String, headers: Seq[(String, String)] = Seq.empty): Try[String] = {
    Try {
      val httpClient: DefaultHttpClient = new DefaultHttpClient
      val httpGet: HttpGet = new HttpGet(url)
      headers map { header =>
        httpGet.addHeader(header._1, header._2)
      }
      val httpResponse: HttpResponse = httpClient.execute(httpGet)
      val httpEntity: HttpEntity = httpResponse.getEntity
      val is: InputStream = httpEntity.getContent
      val reader: BufferedReader = new BufferedReader(new InputStreamReader(is, encoding), 8)
      val sb: StringBuilder = new StringBuilder
      var line: String = null
      while ( {
        line = reader.readLine
        line
      } != null) {
        sb.append(line + "\n")
      }
      is.close()

      sb.toString()
    } match {
      case Success(response) => Success(response)
      case Failure(ex) =>
        ex.printStackTrace()
        Failure(ex)
    }
  }

}
