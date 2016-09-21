package com.company;

import java.io.File;
import javax.swing.JTextArea;
/**
 * Created by Sandra on 2016-05-23.
 */

public class RenameFiles {
    private static final int SETTING_PREFIX = 0;
    private static final int SETTING_SUFFIX = 1;
    private static final int SETTING_REMOVE = 2;

    public static void printNames(File[] fileNames, JTextArea log){
        for (File index : fileNames) {
            if (index.isHidden()) //fixed
                continue;
            log.append(index.getName() + "   \n");
        }
    }

    public static void renameNumbers(int startNumber, File[] fileNames, String directory) {
        for (File index : fileNames) {
            if (index.isHidden())
                continue;
            File oldName = new File(directory + "/" + index.getName());
            File newName = new File(directory + "/" + String.format("%03d", startNumber) + ".jpg");
            oldName.renameTo(newName);
            startNumber++;
        }
    }

    public static void renameNumbersLetters(int increment, int startNumber, File[] fileNames, String directory){
        int letter = 64;      // 'A' when casted over to char
        for (File index : fileNames){
            if (index.isHidden()) //fixed
                continue;
            String[] parts = index.getName().split("\\.");
            String extension = (parts.length < 2) ? "" : parts[1];
            String name = parts[0];

            File oldName = new File(directory + "/" + index.getName());
            if (index.getName().endsWith("opy.jpg")) {
                index.delete();
                System.out.println(index.getName());
                startNumber = startNumber + increment;
                letter = 64; // starts back at 'A'
                continue;
            }
            File newName = new File(directory + "/" + String.valueOf(startNumber) + "_" + (char)letter + "." + extension);
            oldName.renameTo(newName);
            letter++;
        }
    }

    public static void prefix(String prefix, File[] fileNames, String directory){

        renameHelper(prefix, fileNames, directory, SETTING_PREFIX);
//        for (File index : fileNames) {
//            if (index.isHidden()) //fixed
//                continue;
//            String[] parts = index.getName().split("\\.");
//            String extension = parts[1];
//            String name = parts[0];
//
//            File oldName = new File(directory + "/" + index.getName());
//            File newName = new File(directory + "/" + prefix + name + "." + extension);
//            oldName.renameTo(newName);
//        }

    }

    public static void suffix(String suffix, File[] fileNames, String directory) {
        renameHelper(suffix, fileNames, directory, SETTING_SUFFIX);
//        for (File index : fileNames) {
//            if (index.isHidden()) //fixed
//                continue;
//            String[] parts = index.getName().split("\\.");
//            String extension = parts[1];
//            String name = parts[0];
//            File oldName = new File(directory + "/" + index.getName());
//            File newName = new File(directory + "/" + name + suffix + "." + extension);
//            oldName.renameTo(newName);
//        }
        //HI THERE.jpg
    }

    public static void remove(String text, File[] fileNames, String directory) {
        renameHelper(text, fileNames, directory, SETTING_REMOVE);
    }


    /* TODO: Add another function that utilizes SETTING_REMOVE
    * to remove all occurrences of one text with the empty string
    * */
    public static void renameHelper(String text, File[] fileNames, String directory, int setting) {
        for (File index : fileNames) {
            if (index.isHidden()) //fixed
                continue;
            String[] parts = index.getName().split("\\.");
            String extension = parts[1];
            String name = parts[0];
            File oldName = new File(directory + "/" + index.getName());
            File newName = null;

            switch (setting) {
                case SETTING_PREFIX:
                    newName = new File(directory + "/" + text + name + "." + extension);
                    break;
                case SETTING_SUFFIX:
                    newName = new File(directory + "/" + name + text + "." + extension);
                    break;
                case SETTING_REMOVE:
                    newName = new File(directory + "/" + name.replaceAll(text, "").trim() + "." + extension);
                    break;
                default:
                    break;
            }

            if (newName != null)
                oldName.renameTo(newName);
        }
    }
}
