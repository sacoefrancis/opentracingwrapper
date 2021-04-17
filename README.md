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
////    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.6.0'
////    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine'
    implementation 'org.slf4j:slf4j-simple:2.0.0-alpha1'
    implementation 'org.apache.kafka:kafka-clients:2.7.0'
    implementation 'io.jaegertracing:jaeger-client:1.5.0'
    implementation 'io.opentracing.contrib:opentracing-kafka-client:0.1.15'
    implementation 'org.reflections:reflections:0.9.10'
}
jar {
    manifest {
        attributes "Class-Path": configurations.compile.collect { it.absolutePath }.join(" ")
    }
    from {
        configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
}


export JAEGER_SERVICE_NAME=sample
export JAEGER_SAMPLER_TYPE=const
export JAEGER_SAMPLER_PARAM=1
export JAEGER_REPORTER_LOG_SPANS=true
export JAEGER_ENDPOINT=http://localhost:14268/api/traces
export JAEGER_URL_ENCODING=false
export JAEGER_SPAN_CONTEXT_KEY=francis_trace_id
