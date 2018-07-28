package com.lijiankun24.koalaplugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project
import org.gradle.internal.impldep.org.apache.ivy.util.FileUtil

class PreClass extends Transform {

    Project project

    PreClass(Project project) {
        this.project = project
    }

    @Override
    String getName() {
        return "PreClass"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
//        super.transform(transformInvocation)
        transformInvocation.inputs.each { TransformInput input ->
            input.directoryInputs.each { DirectoryInput directoryInput ->

                def dest = transformInvocation.outputProvider.getContentLocation(directoryInput.name,
                    directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)

                FileUtil.copy(directoryInput.file, dest)
            }
        }
    }
}