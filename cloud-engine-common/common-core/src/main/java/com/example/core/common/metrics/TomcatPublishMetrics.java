package com.example.core.common.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

public class TomcatPublishMetrics implements MeterBinder {

    @Override
    public void bindTo(MeterRegistry registry) {

    }
}
