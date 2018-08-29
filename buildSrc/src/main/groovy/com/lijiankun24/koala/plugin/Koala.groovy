package com.lijiankun24.koala.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Koala
 * <p>
 * Created by lijiankun24 on 18/7/29.
 */
class Koala implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def android = project.extensions.findByType(AppExtension.class)
        android.registerTransform(new PreClass(project))
    }
}