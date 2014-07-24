package com.javamex.classmexer;

import java.io.File;
import java.lang.management.ManagementFactory;

import com.sun.tools.attach.VirtualMachine;

public class MemoryUtilAgentLoader {

    public static String defaultAgentJarFileName = "classmexer-0.0.3.2.jar";

    public static void loadAgent() {
        loadAgent(null);
    }

    public static void loadAgent(String agentJarFilePath) {
        if (Agent.isLoaded()) {
            return;
        }

        String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
        int p = nameOfRunningVM.indexOf('@');
        String pid = nameOfRunningVM.substring(0, p);

        try {
            VirtualMachine vm = null;
            try {
                vm = VirtualMachine.attach(pid);
            } catch (NoClassDefFoundError e) {
                if ("com/sun/tools/attach/VirtualMachine".equals(e.getMessage())) {
                    System.err.println("The JDK/lib/tools.jar is required in the classpath");
                    e.printStackTrace();
                }
                throw e;
            }

            if (agentJarFilePath == null) {

                String classpath = System.getProperty("java.class.path");
                String osName = System.getProperty("os.name");
                int indexOf = classpath.indexOf(defaultAgentJarFileName);
                int indexEnd = indexOf;
                int indexStart = -1;

                if (osName.startsWith("Windows")) {
                    FOR: for (int i = indexOf; i > 0; i--) {
                        char charAt = classpath.charAt(i);
                        switch (charAt) {
                        case ';':
                        case ' ':
                            indexStart = i + 1;
                            break FOR;
                        }
                    }
                } else {
                    FOR: for (int i = indexOf; i > 0; i--) {
                        char charAt = classpath.charAt(i);
                        switch (charAt) {
                        case ':':
                        case ' ':
                            indexStart = i + 1;
                            break FOR;
                        }

                    }
                }

                if (indexStart != -1) {
                    agentJarFilePath = classpath.substring(indexStart, indexEnd + defaultAgentJarFileName.length());
                } else {
                    throw new RuntimeException("Can't find the jar '" + defaultAgentJarFileName + "' from the classpath...");
                }
            }

            File file = new File(agentJarFilePath);
            boolean canRead = file.canRead();
            if (!canRead) {
                throw new RuntimeException("Can't find the jar: " + agentJarFilePath);
            }

            vm.loadAgent(agentJarFilePath, "");
            vm.detach();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Getter for defaultAgentJarFileName.
     * 
     * @return the defaultAgentJarFileName
     */
    public static String getDefaultAgentJarFileName() {
        return defaultAgentJarFileName;
    }

    /**
     * Setter for defaultAgentJarFileName.
     * 
     * @return the defaultAgentJarFileName
     */
    public static void setDefaultAgentJarFileName(String defaultAgentJarFileName) {
        MemoryUtilAgentLoader.defaultAgentJarFileName = defaultAgentJarFileName;
    }

    public static void main(String[] args) {
        loadAgent();
    }

}
