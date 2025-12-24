plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
    signing
}

group = "com.mtv.based.uicomponent"
version = "1.0.0"

android {
    namespace = "com.mtv.based.uicomponent.aggregator"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core:core-ui"))
    implementation(project(":theme:theme-ui"))
    implementation(project(":component:component-button"))
    implementation(project(":component:component-input"))
    implementation(project(":component:component-dialog"))
    implementation(project(":component:component-bottom-sheet"))
    implementation(project(":component:component-card"))
    implementation(project(":component:component-badge"))
    implementation(project(":component:component-checkbox"))
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                artifactId = "aggregator"
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        file(findProperty("signing.keyFile") as String).readText(),
        findProperty("signing.password") as String
    )
    sign(publishing.publications["release"])
}

/* push to local maven on mac */
//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>("release") {
//                from(components["release"])
//                artifactId = "aggregator"
//            }
//        }
//    }
//}

/* push to jitpack */
publishing {
    publications {
        create<MavenPublication>("release") {
            artifactId = "aggregator"
            pom {
                packaging = "pom"
                withXml {
                    val deps = asNode().appendNode("dependencies")
                    listOf(
                        "core-ui",
                        "theme-ui",
                        "component-button",
                        "component-input",
                        "component-dialog",
                        "component-bottom-sheet",
                        "component-card",
                        "component-badge",
                        "component-checkbox"
                    ).forEach { artifact ->
                        val depNode = deps.appendNode("dependency")
                        depNode.appendNode("groupId", "com.mtv.based.uicomponent")
                        depNode.appendNode("artifactId", artifact)
                        depNode.appendNode("version", "1.0.0")
                        depNode.appendNode("scope", "compile")
                    }
                }
            }
        }
    }
}


signing {
    useInMemoryPgpKeys(
        file(findProperty("signing.keyFile") as String).readText(),
        findProperty("signing.password") as String
    )
    sign(publishing.publications)
}