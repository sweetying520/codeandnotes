package com.dream.generatefile

import com.google.gson.Gson
import okhttp3.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import java.io.IOException
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/5/26
 */
private val boxShadowMap = mutableMapOf<String,String>()
private val borderRadiusMap = mutableMapOf<String,String>()
private val spacingMap = mutableMapOf<String,String>()
private val colorMap = mutableMapOf<String,String>()

fun main(){

    val url = "https://raw.githubusercontent.com/motaionx/figmaStyle/main/tokens.json"

    // 发送网络请求
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url) // 替换为你要请求的 JSON 文件的 URL
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 请求失败处理
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val json = response.body?.string()
                // 在这里处理获取到的 JSON 数据
                //Log.d("erdai", "onResponse: $json")
                val firstLevel = Gson().fromJson<Map<Any, Any>>(json, Map::class.java)
                val secondLevel = firstLevel["global"] as Map<*, *>
                secondLevel.forEach {
                    val key = it.key.toString()
                    val thirdLevel = it.value as Map<*, *>
                    val type = thirdLevel["type"] as String
                    when(type){
                        "borderRadius" ->{
                            //圆角
                            val value = thirdLevel["value"] as String
                            borderRadiusMap[key] = value
                        }
                        "spacing" ->{
                            //內间距
                            val value = thirdLevel["value"] as String
                            spacingMap[key] = value
                        }
                        "color" ->{
                            //颜色
                            val value = thirdLevel["value"] as String
                            colorMap[key] = value
                        }
                        "boxShadow" ->{
                            //阴影
                        }
                    }
                }
                //生成 xml 文件
                //1、创建 XML 文档
                val docFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
                //一、borderRadius
                //2、构建 doc
                val radiusBuilder: DocumentBuilder = docFactory.newDocumentBuilder()
                val radiusDoc: Document = radiusBuilder.newDocument()
                //3、创建根结点
                val radiusResourcesElement: Element = radiusDoc.createElement("resources")
                radiusDoc.appendChild(radiusResourcesElement)
                //4、添加 Radius 项
                borderRadiusMap.forEach {
                    val radiusElement = radiusDoc.createElement("dimen")
                    radiusElement.setAttribute("name",it.key)
                    radiusElement.textContent = it.value + "dp"
                    radiusResourcesElement.appendChild(radiusElement)
                }
                //二、spacing
                //2、构建 doc
                val spacingBuilder: DocumentBuilder = docFactory.newDocumentBuilder()
                val spacingDoc: Document = spacingBuilder.newDocument()
                //3、创建根结点
                val spacingResourcesElement: Element = spacingDoc.createElement("resources")
                spacingDoc.appendChild(spacingResourcesElement)
                //4、添加 Radius 项
                spacingMap.forEach {
                    val spacingElement = spacingDoc.createElement("dimen")
                    spacingElement.setAttribute("name",it.key)
                    spacingElement.textContent = it.value + "dp"
                    spacingResourcesElement.appendChild(spacingElement)
                }
                //三、color
                //2、构建 doc
                val colorBuilder: DocumentBuilder = docFactory.newDocumentBuilder()
                val colorDoc: Document = colorBuilder.newDocument()
                //3、创建根结点
                val colorResourcesElement: Element = colorDoc.createElement("resources")
                colorDoc.appendChild(colorResourcesElement)
                //4、添加 Radius 项
                colorMap.forEach {
                    val colorElement = colorDoc.createElement("color")
                    colorElement.setAttribute("name",it.key)
                    colorElement.textContent = it.value
                    colorResourcesElement.appendChild(colorElement)
                }

                // 将 XML 写入文件
                // 5、将 XML 写入文件
                val transformerFactory: TransformerFactory = TransformerFactory.newInstance()
                val transformer: Transformer = transformerFactory.newTransformer()
                transformer.setOutputProperty(OutputKeys.INDENT, "yes")
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")

                val radiusSource = DOMSource(radiusDoc)
                val radiusResult = StreamResult(File("app/src/main/res/values/atom_radius.xml"))
                transformer.transform(radiusSource, radiusResult)

                val spacingSource = DOMSource(spacingDoc)
                val spacingResult = StreamResult(File("app/src/main/res/values/atom_spacing.xml"))
                transformer.transform(spacingSource, spacingResult)

                val colorSource = DOMSource(colorDoc)
                val colorResult = StreamResult(File("app/src/main/res/values/atom_colors.xml"))
                transformer.transform(colorSource, colorResult)

                println("XML 文件生成成功！")
            }
        }
    })
}