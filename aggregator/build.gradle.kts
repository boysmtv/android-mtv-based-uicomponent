plugins {
    kotlin("jvm")
    id("maven-publish")
    id("signing")
}

signing {
    useInMemoryPgpKeys(
        file(findProperty("signing.keyFile") as String).readText(),
        findProperty("signing.password") as String
    )
    sign(publishing.publications)
}

tasks.withType<JavaCompile>().configureEach {
    enabled = false
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.mtv.based.uicomponent"
            artifactId = "aggregator"
            version = "1.0.0"

            pom {
                packaging = "pom"

                name.set("aggregator")
                description.set("UI Component Aggregator")
                url.set("https://github.com/boysmtv/android-mtv-based-uicomponent")

                withXml {
                    val deps = asNode().appendNode("dependencies")

                    fun dep(artifact: String) {
                        val d = deps.appendNode("dependency")
                        d.appendNode("groupId", "com.mtv.based.uicomponent")
                        d.appendNode("artifactId", artifact)
                        d.appendNode("version", "1.0.0")
                    }

                    dep("core-ui")
                    dep("theme-ui")
                    dep("component-button")
                    dep("component-input")
                    dep("component-dialog")
                    dep("component-bottom-sheet")
                    dep("component-card")
                    dep("component-badge")
                    dep("component-checkbox")
                }
            }
        }
    }
}
