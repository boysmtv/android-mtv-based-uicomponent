// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.signing)
}

val signingKeyFile: String? by project
val signingPassword: String? by project

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.library") || plugins.hasPlugin("java-library")) {

            publishing {
                publications {
                    create<MavenPublication>("release") {
                        afterEvaluate {
                            from(components["release"])
                        }
                        groupId = "com.mtv.based.uicomponent"
                        artifactId = project.name
                        version = "1.0.0"
                    }
                }

                repositories {
                    mavenLocal()
                }
            }

            if (!signingKeyFile.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
                signing {
                    useInMemoryPgpKeys(
                        file(signingKeyFile!!).readText(),
                        signingPassword!!
                    )
                    sign(publishing.publications)
                }
            }
        }
    }
}

tasks.register("publishAllModulesToMavenLocal") {
    group = "publishing"
    description = "Clean, assemble release, and publish all modules to Maven Local"

    val modules = listOf(
        ":core:core-ui",
        ":theme:theme-ui",
        ":component:component-button",
        ":component:component-input",
        ":component:component-dialog",
        ":component:component-bottom-sheet",
        ":component:component-card",
        ":component:component-badge",
        ":component:component-checkbox",
    )

    modules.forEach { modulePath ->
        dependsOn("${modulePath}:clean")
        dependsOn("${modulePath}:assembleRelease")
        dependsOn("${modulePath}:publishToMavenLocal")
    }
}
