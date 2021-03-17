# opentracingwrapper
dependencies {
    // testCompile group: 'junit', name: 'junit', version: '4.11'
// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    //   implementation group: 'org.slf4j', name: 'slf4j-api', version: '1.7.25'
    compile 'org.slf4j:slf4j-simple:2.0.0-alpha1'

    compile ('org.apache.kafka:kafka-clients:2.7.0'){
        exclude group: 'org.xerial.snappy'
    }

    compile ('io.jaegertracing:jaeger-client:1.5.0')
            {
                exclude group: 'org.jetbrains.kotlin'
                exclude group: 'com.squareup.okhttp3'
                exclude group: 'com.google.code.gson'
                exclude group: 'javax.annotation'

            }
    compile 'io.opentracing.contrib:opentracing-kafka-client:0.1.15'

}
