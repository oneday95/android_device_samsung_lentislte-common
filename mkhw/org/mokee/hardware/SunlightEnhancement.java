/*
 * Copyright (C) 2015 The mokee Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mokee.hardware;

import org.mokee.hardware.util.FileUtils;

import android.os.SystemProperties;

import java.io.File;

public class SunlightEnhancement {

    private static String FILE_HBM = "/sys/class/graphics/fb0/hbm";

    /**
     * Whether device supports HBM
     *
     * @return boolean Supported devices must return always true
     */
    public static boolean isSupported() {
        File f = new File(FILE_HBM);
        return f.exists();
    }

    /**
     * This method return the current activation status of HBM
     *
     * @return boolean Must be false when HBM is not supported or not activated, or
     * the operation failed while reading the status; true in any other case.
     */
    public static boolean isEnabled() {
        if (Integer.parseInt(FileUtils.readOneLine(FILE_HBM)) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method allows to setup HBM
     *
     * @param status The new HBM status
     * @return boolean Must be false if HBM is not supported or the operation
     * failed; true in any other case.
     */
    public static boolean setEnabled(boolean status) {
        if (status == true) {
            return FileUtils.writeLine(FILE_HBM, "1");
        } else {
            return FileUtils.writeLine(FILE_HBM, "0");
        }
    }

    /**
     * Whether adaptive backlight (CABL / CABC) is required to be enabled
     *
     * @return boolean False if adaptive backlight is not a dependency
     */
    public static boolean isAdaptiveBacklightRequired() {
        return false;
    }

    /**
     * Set this to true if the implementation is self-managed and does
     * it's own ambient sensing. In this case, setEnabled is assumed
     * to toggle the feature on or off, but not activate it. If set
     * to false, LiveDisplay will call setEnabled when the ambient lux
     * threshold is crossed.
     *
     * @return true if this enhancement is self-managed
     */
    public static boolean isSelfManaged() {
        return false;
    }
}
