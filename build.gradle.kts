import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar



plugins {
    id("java")
    application
    alias(libs.plugins.shadow)
}

group = "io.github.dovehometeam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.qqbot.sdk) {
        exclude(group = "ch.qos.logback")
        exclude(group = "com.squareup.okio")
    }
    implementation(libs.logback.core)
    implementation(libs.logback.classic)
    implementation(libs.okio)
    for (s in listOf("compileOnly", "annotationProcessor")) {
        add(s, libs.lombok)
    }
    implementation(libs.gson)
}

tasks {
    application {
        mainClass.set("io.github.dovehometeam.bot.Main")
        val r = file("run")
        if (!r.exists()) {
            r.mkdirs()
        }
        getByName<JavaExec>("run").workingDir(r)
//        applicationDefaultJvmArgs = listOf("-Djline.terminal=jline.UnsupportedTerminal")
    }

    withType<ShadowJar> {
        archiveFileName.set("nyc-test-${version}.jar")
    }
}
