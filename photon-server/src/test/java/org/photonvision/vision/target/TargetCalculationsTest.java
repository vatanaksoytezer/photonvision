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

package org.photonvision.vision.target;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.math3.util.FastMath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opencv.core.*;
import org.photonvision.common.util.TestUtils;
import org.photonvision.common.util.numbers.DoubleCouple;
import org.photonvision.vision.frame.FrameStaticProperties;

public class TargetCalculationsTest {

    private static Size imageSize = new Size(800, 600);
    private static Point imageCenterPoint = new Point(imageSize.width / 2, imageSize.height / 2);
    private static final double diagFOV = Math.toRadians(70.0);

    private static final FrameStaticProperties props =
            new FrameStaticProperties((int) imageSize.width, (int) imageSize.height, diagFOV);
    private static final TrackedTarget.TargetCalculationParameters params =
            new TrackedTarget.TargetCalculationParameters(
                    true,
                    TargetOffsetPointEdge.Center,
                    new Point(),
                    imageCenterPoint,
                    new DoubleCouple(1.0, 0.0),
                    RobotOffsetPointMode.None,
                    props.horizontalFocalLength,
                    props.verticalFocalLength,
                    imageSize.width * imageSize.height);

    @BeforeEach
    public void Init() {
        TestUtils.loadLibraries();
    }

    @Test
    public void yawTest() {
        var targetPixelOffsetX = 100;
        var targetCenterPoint = new Point(imageCenterPoint.x + targetPixelOffsetX, imageCenterPoint.y);

        var trueYaw =
                Math.atan((imageCenterPoint.x - targetCenterPoint.x) / params.horizontalFocalLength);

        var yaw =
                TargetCalculations.calculateYaw(
                        imageCenterPoint.x, targetCenterPoint.x, params.horizontalFocalLength);

        assertEquals(FastMath.toDegrees(trueYaw), yaw, 0.025, "Yaw not as expected");
    }

    @Test
    public void pitchTest() {
        var targetPixelOffsetY = 100;
        var targetCenterPoint = new Point(imageCenterPoint.x, imageCenterPoint.y + targetPixelOffsetY);

        var truePitch =
                Math.atan((imageCenterPoint.y - targetCenterPoint.y) / params.verticalFocalLength);

        var pitch =
                TargetCalculations.calculatePitch(
                        imageCenterPoint.y, targetCenterPoint.y, params.verticalFocalLength);

        assertEquals(FastMath.toDegrees(truePitch) * -1, pitch, 0.025, "Pitch not as expected");
    }

    @Test
    public void targetOffsetTest() {
        Point center = new Point(0, 0);
        Size rectSize = new Size(10, 5);
        double angle = 30;
        RotatedRect rect = new RotatedRect(center, rectSize, angle);
        Point result =
                TargetCalculations.calculateTargetOffsetPoint(false, TargetOffsetPointEdge.Top, rect);
        assertEquals(4.3, result.x, 0.33, "Target offset x not as expected");
        assertEquals(2.5, result.y, 0.05, "Target offset Y not as expected");
    }
}
