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
 * Callback class if the async execution is finish
 */
public interface SuperUserCommandCallback {
    /**
     * Callback event
     * @param command The command that finished
     */
    void onFinished(SuperUserCommand command);

}
