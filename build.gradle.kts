plugins {
    application 
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("org.openjfx.javafxplugin") version "0.0.13"
}

javafx {
    version = "11.0.2"
    modules("javafx.controls");
}

repositories {
    mavenCentral() 
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1");
    implementation("com.google.guava:guava:31.1-jre");
    implementation("mysql:mysql-connector-java:8.0.26");
    implementation("org.openjfx:javafx-controls:11.0.2")
}

application {
    mainClass.set("main.App") 
}

tasks {
    withType<Jar> {
        manifest {
            attributes["Main-Class"] = application.mainClass.get()
        }  
    }
    shadowJar {
        mergeServiceFiles()
        dependencies {
            include(dependency("mysql:mysql-connector-java"))
        }
    }
}

sourceSets {
    test {
        resources.srcDirs("src/main/resources")
    }
}
