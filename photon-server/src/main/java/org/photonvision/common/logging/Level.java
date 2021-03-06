/*
 * Copyright (C) 2020 Photon Vision.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.photonvision.common.logging;

public enum Level {
    OFF(0, Logger.ANSI_BLACK),
    ERROR(1, Logger.ANSI_RED),
    WARN(2, Logger.ANSI_YELLOW),
    INFO(3, Logger.ANSI_GREEN),
    DEBUG(4, Logger.ANSI_WHITE),
    TRACE(5, Logger.ANSI_CYAN),
    DE_PEST(6, Logger.ANSI_WHITE);

    public final String colorCode;
    public final int code;

    Level(int code, String colorCode) {
        this.code = code;
        this.colorCode = colorCode;
    }
}
