package com.lijiankun24.koala.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 * Hotfix.java
 * <p>
 * Created by lijiankun on 18/4/14.
 */
class Koala implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.logger.error("====== Hello Gradle Plugin =======")
        def android = project.extensions.findByType(AppExtension.class)
        android.registerTransform(new PreClass(project))
    }
}