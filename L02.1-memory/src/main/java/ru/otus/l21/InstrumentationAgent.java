package ru.otus.l21;

import java.lang.instrument.Instrumentation;

public class InstrumentationAgent {

    private static volatile Instrumentation globalInstrumentation;

    public static void premain(final String agentArgs, final Instrumentation inst)
    {
        System.out.println("premain...");
        globalInstrumentation = inst;
    }

    public static long getObjectSize(final Object object)
    {
        if (globalInstrumentation == null)
        {
            throw new IllegalStateException("Agent not initialized.");
        }
        return globalInstrumentation.getObjectSize(object);
    }
}
