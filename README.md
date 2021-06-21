# opentracingwrapper


java -javaagent:/path/to/elastic-apm-agent-<version>.jar -Delastic.apm.service_name=my-cool-service -Delastic.apm.application_packages=org.example,org.another.example -Delastic.apm.server_url=http://localhost:8200 -jar my-application.jar

exec $(dirname $0)/kafka-run-class.sh $EXTRA_ARGS -javaagent:/path/to/jmx_prometheus_javaagent-0.11.0.jar=9408:/path/to/config/file/prometheus.yml org.apache.kafka.connect.cli.ConnectDistributed "$@"



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

curl -X POST http://localhost:8083/connectors -H "Content-Type: application/json" --data '{
  "name":"file-sink-connector-002",
  "config": {
    "tasks.max":"1",
    "batch.size":"1000",
    "batch.max.rows":"1000",
    "poll.interval.ms":"500",
    "connector.class":"org.apache.kafka.connect.file.FileStreamSinkConnector",
    "file":"/var/lib/kafka/data/names.txt",
    "topics":"number-topic-7",
    "key.converter": "org.apache.kafka.connect.storage.StringConverter",
    "key.converter.schemas.enable":"false",
    "value.converter": "org.apache.kafka.connect.storage.StringConverter",
    "key.converter.schemas.enable":"false"
  }
}'
export JAEGER_SERVICE_NAME=sample
export JAEGER_SAMPLER_TYPE=const
export JAEGER_SAMPLER_PARAM=1
export JAEGER_REPORTER_LOG_SPANS=true
export JAEGER_ENDPOINT=http://localhost:14268/api/traces
export JAEGER_URL_ENCODING=false
export JAEGER_SPAN_CONTEXT_KEY=francis_trace_id
