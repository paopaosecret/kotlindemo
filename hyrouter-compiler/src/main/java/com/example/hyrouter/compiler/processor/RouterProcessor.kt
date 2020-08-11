package com.example.hyrouter.compiler.processor

import com.example.hyrouter.annotation.RouterFunction
import com.example.hyrouter.annotation.RouterPage
import com.example.hyrouter.compiler.*
import com.example.hyrouter.mapping.HyRouterManager
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

abstract class BaseProcessor : AbstractProcessor() {

    protected lateinit var mElements: Elements
    protected lateinit var mTypes: Types
    protected lateinit var mLogger: Logger
    protected lateinit var mFiler: Filer
    protected lateinit var mElementUtils: Elements

    override fun init(p0: ProcessingEnvironment) {
        super.init(p0)
        mElements = p0.elementUtils
        mTypes = p0.typeUtils
        mFiler = p0.filer
        mElementUtils = p0.elementUtils

        mLogger = Logger(p0.messager)
        mLogger.info("RouterProcess init")
        mLogger.info("RouterProcess init ${mFiler.toString()}")
    }

    override fun process(set: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        mLogger.info("RouterProcess process")
        if (set.isEmpty()) {
            mLogger.info("RouterProcess annotation null")
            return false
        }

        try {
            collectInfo(roundEnv)
        } catch (e: Exception) {
            mLogger.error(e)
        }
        return true
    }
    abstract fun collectInfo(roundEnv: RoundEnvironment)
}

/**
 * RouterPage 注解处理
 */
@AutoService(Processor::class)
@SupportedOptions(MODULE_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = ["com.example.hyrouter.annotation.RouterPage", "com.example.hyrouter.annotation.RouterFunction"])
class RouterPageProcessor : BaseProcessor() {
    override fun collectInfo(roundEnv: RoundEnvironment) {
        //TODO 获取RouterPage 注释的元素
        val elements = roundEnv.getElementsAnnotatedWith(RouterPage::class.java)
        val functionElements =  roundEnv.getElementsAnnotatedWith(RouterFunction::class.java)
        if (elements.isEmpty() && functionElements.isEmpty()) {
            mLogger.info("elements and functionElements is empty")
            return
        }
        mLogger.info("Found ${elements.size} RouterPage and ${functionElements.size} RouterFunction")

        /**
         * generate map code like:
         * Map<String, RouteMetadata>
         * 因为用MutableMap::class key:注解中添加的key，value自动对应类全限定类名
         */
        val map = ClassName("kotlin.collections", "MutableMap")
        val pageMap = map.parameterizedBy(String::class.asClassName(), String::class.asClassName())


        //TODO 定义Kotlin 路由mapping类:   -》原型：HyRouterManager
        //TODO 定义伴生对象
        val INSTANCE = TypeSpec.companionObjectBuilder()
            .addProperty(PropertySpec.builder("INSTANCE", HyRouterManager::class)
                .initializer("%L", "HyRouterManager()")
                .build())
            .build()

        //TODO 第二步：定义Kotlin 成员
        val nativePageMapping = PropertySpec.builder(MEMBER_NATIVE_PAGE_MAPPING, pageMap)
            .addModifiers(KModifier.PRIVATE)
            .initializer("%L", "mutableMapOf()")
            .build()
        val flutterPagerMapping = PropertySpec.builder(MEMBER_FLUTTER_PAGE_MAPPING, pageMap)
            .addModifiers(KModifier.PRIVATE)
            .initializer("%L", "mutableMapOf()")
            .build()
        val webPageMapping = PropertySpec.builder(MEMBER_WEB_PAGE_MAPPING, pageMap)
            .addModifiers(KModifier.PRIVATE)
            .initializer("%L", "mutableMapOf()")
            .build()
        val nativeFunctionMapping = PropertySpec.builder(MEMBER_NATIVE_FUNCTION_MAPPING, pageMap)
            .addModifiers(KModifier.PRIVATE)
            .initializer("%L", "mutableMapOf()")
            .build()
        val allMapping = PropertySpec.builder(MEMBER_ALL_MAPPING, pageMap)
            .initializer("%L", "mutableMapOf()")
            .build()

        //TODO 第三步：定义Kotlin 方法
        val initFunSpecBuild = FunSpec.builder(METHOD_INIT)
        initFunSpecBuild.addStatement(
            "//TODO 添加Native页面初始化 start"
        )
        elements.forEach {
            val routeAnn = it.getAnnotation(RouterPage::class.java)
            val key = routeAnn.key
            val simpleName = it.simpleName.toString()
            val qualifiedName = mElementUtils.getPackageOf(it).qualifiedName.toString()
            val className = "${qualifiedName}.${simpleName}"

            if (routeAnn.key.isNotBlank()) {
                //TODO 方法中添加语句
                initFunSpecBuild.addStatement(
                    "nativePageMapping.put(%S, %S)", key, className
                )
            }
        }
        initFunSpecBuild.addStatement(
            "//TODO 添加Native页面初始化 end\n"
        )

        initFunSpecBuild.addStatement(
            "//TODO 添加Native方法初始化 start"
        )
        functionElements.forEach{
            val routeAnn = it.getAnnotation(RouterFunction::class.java)
            val key = routeAnn.key
            val methodName = it.simpleName.toString()
            val qualifiedName = mElementUtils.getPackageOf(it).qualifiedName.toString()

            val typeElement = it.getEnclosingElement() as TypeElement
            val simpleName = typeElement.simpleName
            val className = "${qualifiedName}.${simpleName}"
            val value = "${className}#${methodName}"
            mLogger.info("qualifiedName:${qualifiedName}, simpleName:${simpleName}, methodName:${methodName}")
            if (routeAnn.key.isNotBlank()) {
                //TODO 方法中添加语句
                initFunSpecBuild.addStatement(
                    "nativeFunctionMapping.put(%S, %S)", key, value
                )
            }
        }
        initFunSpecBuild.addStatement(
            "//TODO 添加Native方法初始化 end\n"
        )

        initFunSpecBuild.addStatement("allMapping.clear()")
        initFunSpecBuild.addStatement("allMapping.putAll(flutterPagerMapping)")
        initFunSpecBuild.addStatement("allMapping.putAll(webPageMapping)")
        initFunSpecBuild.addStatement("allMapping.putAll(nativePageMapping)")
        initFunSpecBuild.addStatement("allMapping.putAll(nativeFunctionMapping)")
        val primaryConstructor = FunSpec.constructorBuilder()
            .addModifiers(KModifier.PRIVATE)
            .build()
        //TODO 第四步：生成类
        val mappingType = TypeSpec.classBuilder(ROUTER_MAPPING_CLASS_NAME)
            .primaryConstructor(primaryConstructor)
            .addKdoc(WARNINGS)
            .addProperty(nativePageMapping)
            .addProperty(flutterPagerMapping)
            .addProperty(webPageMapping)
            .addProperty(nativeFunctionMapping)
            .addProperty(allMapping)
            .addType(INSTANCE)
            .addFunction(initFunSpecBuild.build())
            .build()

        //TODO 第五步：生成Kotlin文件
        val kotlinFile = FileSpec.builder(PACKAGE, ROUTER_MAPPING_CLASS_NAME)
            .addType(mappingType)
            .build()

        //TODO 写入到目录  app/build/generated/source/kapt/debug/
        kotlinFile.writeTo(mFiler)
    }
}


