plugins {
    application 
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

repositories {
    mavenCentral() 
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1");
    implementation("com.google.guava:guava:31.1-jre");
    implementation("mysql:mysql-connector-java:8.0.26");
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
