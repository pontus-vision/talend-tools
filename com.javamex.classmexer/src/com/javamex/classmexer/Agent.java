package com.javamex.classmexer;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation instr) {
        instrumentation = instr;
    }

    public static void agentmain(String agentArgs, Instrumentation instr) {
        instrumentation = instr;
    }

    protected static Instrumentation getInstrumentation() {
        Instrumentation instr = instrumentation;
        if (instr == null) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Agent 'classmexer.*.jar' not initialized, possible solutions are:");
            errorMessage
                    .append("\n\t- add the jar classmexer-0.0.3.1.jar to the classpath and load the agent in a static block (JDK/lib/tools.jar is also required) such as:   static { MemoryUtilAgentLoader.loadAgent()");
            errorMessage
                    .append("\n\t- load the agent in a static block (JDK/lib/tools.jar is also required) such as:   static { MemoryUtilAgentLoader.loadAgent(\"/path_of_jar/classmexer-0.0.3.1.jar\"); }");
            errorMessage
                    .append("\n\t- use the JVM java agent argument such as:    -javaagent:/path_of_jar/classmexer-0.0.3.1.jar");
            throw new IllegalStateException(errorMessage.toString());
        } else
            return instr;
    }

    private Agent() {
    }

    public static boolean isLoaded() {
        return instrumentation != null;
    }

    private static volatile Instrumentation instrumentation;
}
