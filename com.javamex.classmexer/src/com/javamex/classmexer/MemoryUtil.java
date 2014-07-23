/*jadclipse*/// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10)
// Source File Name: MemoryUtil.java

package com.javamex.classmexer;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

// Referenced classes of package com.javamex.classmexer:
// Agent

public class MemoryUtil {

    public enum VisibilityFilter {
        ALL,
        PRIVATE_ONLY,
        NON_PUBLIC,
        NONE;
    }

    public static long memoryUsageOf(Object obj) {
        return Agent.getInstrumentation().getObjectSize(obj);
    }

    public static long deepMemoryUsageOf(Object obj) {
        return deepMemoryUsageOf(obj, VisibilityFilter.NON_PUBLIC);
    }

    public static long deepMemoryUsageOf(Object obj, VisibilityFilter referenceFilter) {
        return deepMemoryUsageOf0(Agent.getInstrumentation(), new HashSet(), obj, referenceFilter);
    }

    public static long deepMemoryUsageOfAll(Collection objs) {
        return deepMemoryUsageOfAll(objs, VisibilityFilter.NON_PUBLIC);
    }

    public static long deepMemoryUsageOfAll(Collection objs, VisibilityFilter referenceFilter) {
        Instrumentation instr = Agent.getInstrumentation();
        long total = 0L;
        Set counted = new HashSet(objs.size() * 4);
        for (Iterator iterator = objs.iterator(); iterator.hasNext();) {
            Object o = iterator.next();
            total += deepMemoryUsageOf0(instr, counted, o, referenceFilter);
        }

        return total;
    }

    private static long deepMemoryUsageOf0(Instrumentation instrumentation, Set counted, Object obj, VisibilityFilter filter)
            throws SecurityException {
        Stack st = new Stack();
        st.push(obj);
        long total = 0L;
        while (!st.isEmpty()) {
            Object o = st.pop();
            if (counted.add(Integer.valueOf(System.identityHashCode(o)))) {
                long sz = instrumentation.getObjectSize(o);
                total += sz;
                Class clz = o.getClass();
                Class compType = clz.getComponentType();
                if (compType != null && !compType.isPrimitive()) {
                    Object arr[] = (Object[]) o;
                    Object aobj[] = arr;
                    int j = 0;
                    for (int l = aobj.length; j < l; j++) {
                        Object el = aobj[j];
                        if (el != null)
                            st.push(el);
                    }

                }
                for (; clz != null; clz = clz.getSuperclass()) {
                    Field afield[] = clz.getDeclaredFields();
                    int i = 0;
                    for (int k = afield.length; i < k; i++) {
                        Field fld = afield[i];
                        int mod = fld.getModifiers();
                        if ((mod & 8) == 0 && isOf(filter, mod)) {
                            Class fieldClass = fld.getType();
                            if (!fieldClass.isPrimitive()) {
                                if (!fld.isAccessible())
                                    fld.setAccessible(true);
                                try {
                                    Object subObj = fld.get(o);
                                    if (subObj != null)
                                        st.push(subObj);
                                } catch (IllegalAccessException illAcc) {
                                    throw new InternalError((new StringBuilder("Couldn't read ")).append(fld).toString());
                                }
                            }
                        }
                    }

                }

            }
        }
        return total;
    }

    private static boolean isOf(VisibilityFilter f, int mod) {
        switch (f) {
        case ALL: // '\001'
            return true;

        case NONE: // '\004'
            return false;

        case PRIVATE_ONLY: // '\002'
            return (mod & 2) != 0;

        case NON_PUBLIC: // '\003'
            return (mod & 1) == 0;
        }
        throw new IllegalArgumentException((new StringBuilder("Illegal filter ")).append(mod).toString());
    }

    private MemoryUtil() {
    }

}
