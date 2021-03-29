# opentracingwrapper


plugins {
    id 'java'
}
group 'org.example'
version '1.0'
repositories {
    mavenCentral()
}
dependencies {

    implementation 'org.slf4j:slf4j-simple:2.0.0-alpha1'
    implementation 'org.apache.kafka:kafka-clients:2.7.0'
    implementation 'io.jaegertracing:jaeger-client:1.5.0'
    implementation 'io.opentracing.contrib:opentracing-kafka-client:0.1.15'
}
jar {
    manifest {
        attributes "Class-Path": configurations.compile.collect { it.absolutePath }.join(" ")
    }
    from {
        configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
}
