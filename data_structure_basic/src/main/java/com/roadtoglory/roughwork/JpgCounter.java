/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.roughwork;
/*
*
*

This class JpgCounter is created and managed by subhe
Created on 10-01-2026 at 17:11 for the project Basic personal requirement of counting photos

*
*
*/

import java.io.File;

public class JpgCounter {

    private static int totalCount = 0;

    public static void main(String[] args) {
        // Change this to your root directory path
        String rootPath = "D:\\VVI- Google Photos - Shadi photos\\Biye Documents\\Tanmoy DA - Photos";

        File rootDir = new File(rootPath);
        if (!rootDir.exists() || !rootDir.isDirectory()) {
            System.out.println("Invalid directory path");
            return;
        }

        countJpgsPerFolder(rootDir);
        System.out.println("The total no of photos are " + totalCount);
    }

    /**
     * Recursively counts JPG/JPEG files in each folder
     */
    public static int countJpgsPerFolder(File dir) {
        int jpgCount = 0;

        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively process sub-folder
                countJpgsPerFolder(file);
            }
            else if (isJpg(file)) {
                jpgCount++;
            }
        }

        // Print only if folder has JPGs
        if (jpgCount > 0) {
            System.out.println(dir.getName() + " -> " + jpgCount + " photos");
            totalCount += jpgCount;
        }

        return jpgCount;
    }

    /**
     * Checks if file is JPG or JPEG (case-insensitive)
     */
    private static boolean isJpg(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg");
    }
}
