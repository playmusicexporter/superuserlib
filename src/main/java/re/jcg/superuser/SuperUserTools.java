/*
 * Copyright (c) 2017 Play Music Exporter Authors
 *
 * This is licensed under the GPLv3, see LICENSE.
 *
 *
 * An earlier version of this Software is available under the MIT License,
 * written by David Schulte in 2015, 
 * and can be downloaded here: https://github.com/Arcus92/PlayMusicExporter
 */

package re.jcg.superuser;

/**
 * Tools for the superuser
 */
public class SuperUserTools {
    /**
     * Private constructor
     */
    private SuperUserTools() {}

    /**
     * Copy a file with root permissions
     * @param src Source file
     * @param dest Destination file
     * @return Returns whether the command was successful
     */
    public static boolean fileCopy(String src, String dest) {
        SuperUserCommand superUserCommand = new SuperUserCommand(new String[] {
                "rm -f '" + dest + "'", // Remove destination file
                "cat '" + src + "' >> '" + dest + "'", // Using cat to copy file instead of cp, because you can use it without busybox
                "chmod 0777 '" + dest + "'", // Change the access mode to all users (chown sdcard_r will fail on some devices)
                "echo 'done'" // Fix to prevent the 'no output' bug in SuperUserCommand
        });

        // Don't spam the log
        superUserCommand.setHideStandardOutput(true);

        // Executes the command
        superUserCommand.execute();

        // Superuser permissions and command are successful
        return superUserCommand.commandWasSuccessful();
    }

    /**
     * Checks if the file exists
     * @param path The path to check
     * @return Returns whether the path exists
     */
    public static boolean fileExists(String path) {
        SuperUserCommand superUserCommand = new SuperUserCommand("ls '" + path + "'");

        // Don't spam the log
        superUserCommand.setHideStandardOutput(true);

        // Executes the command
        superUserCommand.execute();

        // Superuser permissions and command are successful
        return superUserCommand.commandWasSuccessful();
    }


    /**
     * Gets all bytes from one file
     * @param path The path to the file
     * @return Returns the byte array or null if the file doesn't exists
     */
    public static byte[] fileReadToByteArray(String path) {
        SuperUserCommand superUserCommand = new SuperUserCommand("cat '" + path + "'");

        // Don't spam the log with binary code
        superUserCommand.setHideInput(true);
        superUserCommand.setHideStandardOutput(true);
        superUserCommand.setBinaryStandardOutput(true);

        // Executes the command
        superUserCommand.execute();

        // Failed
        if (!superUserCommand.commandWasSuccessful())
            return null;

        return superUserCommand.getStandardOutputBinary();
    }

    /**
     * Gets all bytes from one file
     * @param path The path to the file
     * @param callback The callback
     */
    public static void fileReadToByteArrayAsync(String path, SuperUserCommandCallback callback) {
        SuperUserCommand superUserCommand = new SuperUserCommand("cat '" + path + "'");

        // Don't spam the log with binary code
        superUserCommand.setHideInput(true);
        superUserCommand.setHideStandardOutput(true);
        superUserCommand.setBinaryStandardOutput(true);

        superUserCommand.executeAsync(callback);
    }
}
