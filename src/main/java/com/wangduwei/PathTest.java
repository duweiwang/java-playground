package com.wangduwei;

import java.io.File;
import java.io.IOException;

public class PathTest {


    public static void main(String[] args) {
        PathTest.getCanonicalPath("Users/wangduwei/.gradle/caches/transforms-2/files-2.1/c5c0462b0fb0a2478666441b81241b1a/appcompat-1.3.0/res/color/abc_hint_foreground_material_light.xml");
    }

    static String getCanonicalPath(String path) {
        File file = new File(path);
        try {
            String canonicalPath = file.getCanonicalPath();

            String parent = file.getParentFile().getAbsolutePath();
            if (canonicalPath.startsWith(parent)) {
                return canonicalPath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
