package globaltracer;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerObjectFactory;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.propagation.TextMapCodec;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.TracingProducerInterceptor;
import io.opentracing.propagation.Format;
import io.opentracing.util.GlobalTracer;

public class CustomProducerInterceptor<K, V> extends TracingProducerInterceptor<K, V> {

    public CustomProducerInterceptor() {
        super();
        String jaegerServiceName = System.getenv("JAEGER_SERVICE_NAME");
        String urlEncoding = System.getenv("JAEGER_URL_ENCODING");
        String spanContextKey = System.getenv("JAEGER_SPAN_CONTEXT_KEY");
        System.out.println("***************************************************");
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv();
        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv();
        Configuration config = new Configuration(jaegerServiceName) {
            @Override
            protected JaegerTracer.Builder createTracerBuilder(String serviceName) {
                JaegerTracer.Builder builder = new JaegerTracer.Builder(serviceName);
                JaegerObjectFactory objectFactory = new JaegerObjectFactory();
                TextMapCodec textMapCodec = TextMapCodec.builder()
                        .withUrlEncoding(Boolean.parseBoolean(urlEncoding))
                        .withSpanContextKey(spanContextKey)
                        .withObjectFactory(objectFactory)
                        .build();

                builder.registerInjector(Format.Builtin.TEXT_MAP, textMapCodec);
                builder.registerExtractor(Format.Builtin.TEXT_MAP, textMapCodec);
                return builder;
            }
        }.withSampler(samplerConfig).withReporter(reporterConfig);
        Tracer tracer = config.getTracer();
        System.out.println(tracer);
        System.out.println("***************************************************");
        GlobalTracer.registerIfAbsent(tracer);
    }
}
