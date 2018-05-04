/*
 * Copyright (c) 2015 - 2017. Vitaly Levitan.
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package xyz.wildapp.encrypt.rsa;

import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ByteUtils {
    private static Logger log = Logger.getLogger(ByteUtils.class.getName());

    private ByteUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String byte2Hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        for (byte aB : b) {
            String stmp = Integer.toHexString(aB & 0xff);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toLowerCase();
    }

    public static String byte2Hex(List<Byte> bytes) {
        return byte2Hex(toArray(bytes));
    }

    public static byte hex2Byte(char a1, char a2) {
        int k;
        if (a1 >= '0' && a1 <= '9') {
            k = a1 - 48;
        } else if (a1 >= 'a' && a1 <= 'f') {
            k = (a1 - 97) + 10;
        } else if (a1 >= 'A' && a1 <= 'F') {
            k = (a1 - 65) + 10;
        } else {
            k = 0;
        }
        k <<= 4;
        if (a2 >= '0' && a2 <= '9') {
            k += a2 - 48;
        } else if (a2 >= 'a' && a2 <= 'f') {
            k += (a2 - 97) + 10;
        } else if (a2 >= 'A' && a2 <= 'F') {
            k += (a2 - 65) + 10;
        } else {
            k += 0;
        }
        return (byte) (k & 0xff);
    }

    public static byte[] hex2Byte(String str) {
        int len = str.length();
        if (len % 2 != 0) {
            return new byte[0];
        }
        byte[] r = new byte[len / 2];
        int k = 0;
        for (int i = 0; i < str.length() - 1; i += 2) {
            r[k] = hex2Byte(str.charAt(i), str.charAt(i + 1));
            k++;
        }
        return r;
    }

    public static byte[] object2Byte(Object o) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(o);
            out.flush();
            bytes = bos.toByteArray();
            bos.close();
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage() + "\n", e);
        }
        return bytes;
    }

    public static Object byte2Object(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        Object o = null;
        try {
            in = new ObjectInputStream(bis);
            o = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.log(Level.WARNING, e.getMessage() + "\n", e);
        }
        return o;
    }


    public static byte[] toArray(List<Byte> list) {
        Byte[] barr = list.toArray(new Byte[0]);
        return ArrayUtils.toPrimitive(barr);
    }
}