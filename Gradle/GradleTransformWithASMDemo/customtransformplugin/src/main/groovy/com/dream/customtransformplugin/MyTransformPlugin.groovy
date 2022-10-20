package com.dream.customtransformplugin

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import kotlin.jvm.functions.Function1
import org.gradle.api.Plugin
import org.gradle.api.Project


class MyTransformPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println 'Hello MyTransformPlugin....'

        //在这里注册 Transform
        //AppExtension VS AndroidComponentsExtension
        AndroidComponentsExtension extension = (AndroidComponentsExtension) project.getExtensions().getByType(AndroidComponentsExtension.class);
        extension.onVariants(extension.selector().all(), new Function1<Variant, Variant>() {
            @Override
            Variant invoke(Variant variant) {
                variant.getInstrumentation().transformClassesWith(BBFactory.class, InstrumentationScope.PROJECT, none -> null);
                variant.getInstrumentation().setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS);
                return variant;
            }
        })
    }
}