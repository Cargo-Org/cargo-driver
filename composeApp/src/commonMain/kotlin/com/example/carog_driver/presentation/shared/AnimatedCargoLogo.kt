package com.example.carog_driver.presentation.shared

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val Orange = Color(0xFFFDB22E)
private val White = Color(0xFFFFFFFF)

private const val VP_W = 2396f
private const val VP_H = 1792f

private const val D_CARGO =
    "M1229,351c-37.8,2.5 -80.3,11 -115.5,23.3 -35,12.1 -77.7,34.1 " +
            "-106.5,54.8 -36.8,26.4 -66.2,54.1 -93.7,88.4 -58.1,72.6 " +
            "-94.5,158.3 -106.3,250 -5.9,46 -5,97.2 2.5,143 15,91.8 " +
            "55.9,175.8 120.1,246.7 19.6,21.6 52,50.3 76.3,67.5 10.5,7.4 " +
            "38.3,25 49.2,31.2l5.6,3.1 13.4,-6.5c69.7,-33.7 148.9,-22.9 " +
            "210.8,28.9l10.4,8.6 164.6,-0.2 164.6,-0.3 6.5,-2.3c15,-5.5 " +
            "27.7,-16.3 34.8,-29.9 7.7,-14.8 7.7,-15.1 7.7,-65.3 0,-40.1 " +
            "-0.2,-45.1 -1.8,-51 -5.8,-20.4 -21.4,-37 -41.2,-43.8l-8,-2.7 " +
            "-190.5,-0.6c-188,-0.5 -190.6,-0.6 -201,-2.6 -33.1,-6.6 " +
            "-60.9,-16.6 -84.4,-30.3 -71.5,-41.8 -117.8,-111.5 " +
            "-129.6,-195.1 -5.9,-41.2 -2.6,-81.2 10.1,-125.4 13.3,-45.8 " +
            "34.1,-80.9 68.3,-115 40.4,-40.3 86.8,-64.1 142.7,-73.2 " +
            "9.5,-1.6 28.4,-1.8 210.9,-2.3 184.2,-0.5 200.9,-0.7 206,-2.2 " +
            "23.1,-7.2 40,-25.2 45.7,-48.6 1.3,-5.6 1.6,-13.7 1.6,-49.2 " +
            "0.1,-48.2 -0.1,-49.8 -7.6,-65 -8.8,-18 -26.3,-30.6 -46.8,-33.9 " +
            "-7.4,-1.2 -400.1,-1.3 -418.9,-0.1z"

private const val D_TRAIL_1 =
    "M268.7,623.1c-10.9,2.6 -20.9,10.9 -26,21.7 -3,6.3 -3.2,7.4 " +
            "-3.2,17.7 0,9.9 0.3,11.6 2.7,16.8 3.5,7.5 12.3,16.6 19.5,20.3 " +
            "l5.8,2.9 227.5,0.3c251.6,0.2 231.5,0.7 242.3,-6 14.4,-9.1 " +
            "21.3,-29.4 15.8,-46.5 -2.9,-8.9 -8.4,-16.7 -15.1,-21.4 " +
            "-10.6,-7.4 7.8,-6.9 -241.5,-6.8 -126,0.1 -225.6,0.5 -227.8,1z"

private const val D_TRAIL_2 =
    "M140,799.6c-11.3,3.1 -20.2,10.5 -25.4,21.4 -2.9,6.1 -3.1,7.2 " +
            "-3.1,18 0,10.9 0.2,11.8 3.2,18.1 3.9,7.9 11.1,15 19.1,18.7 " +
            "l5.7,2.7 284,-0 284,-0 5.6,-2.8c7.1,-3.5 14.8,-11.1 18.2,-18.1 " +
            "11.5,-23.4 -0.5,-51.8 -24.5,-58.2 -8.1,-2.1 -558.7,-2 -566.8,0.2z"

private const val D_TRAIL_3 =
    "M356,975.6c-3,0.8 -7.3,2.5 -9.5,3.9 -5.9,3.4 -13.2,11.7 " +
            "-16.3,18.3 -2.4,5.1 -2.7,6.9 -2.7,16.7 0,10.3 0.2,11.4 " +
            "3.2,17.7 3.9,8.3 10.8,15.4 18.8,19.4l6,2.9 182.5,0.3 " +
            "c131.1,0.2 184,-0 187.7,-0.8 6.7,-1.5 16.1,-7.6 20.6,-13.5 " +
            "10,-13 11.3,-33.1 3.1,-47 -3.7,-6.4 -12.5,-14 -19.2,-16.7 " +
            "l-5.7,-2.3 -181.5,-0.2c-157.3,-0.2 -182.2,-0 -187,1.3z"

private const val D_TRAIL_4 =
    "M530.5,1151.4c-11.5,2.9 -20.7,10.5 -25.8,21.4 -2.5,5.4 " +
            "-3.2,8.3 -3.5,15.3 -0.9,18.2 7.3,32.6 22.7,39.8l5.6,2.6 " +
            "135.5,-0 135.5,-0 6,-2.9c16.1,-7.9 24.8,-25.5 21.7,-43.7 " +
            "-2.4,-13.9 -10.1,-24.5 -22.2,-30.3l-6.5,-3.1 -132,-0.2 " +
            "c-102.6,-0.1 -133.1,0.1 -137,1.1z"

private const val D_CAR_BODY =
    "M1769,642c-23.3,2.4 -41.7,17.6 -49.6,41 -1.8,5.3 -1.9,16.4 " +
            "-2.4,313l-0.5,307.5 -2.9,5.9c-3.8,7.7 -9.3,13.1 -17.4,16.9 " +
            "l-6.7,3.2 -181.8,0.5 -181.9,0.5 -1.9,2.4c-3.2,3.9 -3.4,6.9 " +
            "-0.9,12.5 14.9,34 19.8,53.3 20.6,82.1 0.3,8.8 1,17.3 1.6,18.8 " +
            "2.4,6.1 -15.5,5.7 245.3,5.7l238.7,-0 2.9,-2.9c2.9,-2.9 2.9,-3 " +
            "2.9,-15 0,-52.5 22.4,-105.3 59.3,-139.7 57.3,-53.4 133.1,-65.1 " +
            "201.2,-31.1 21.7,10.8 44.3,29.1 59.8,48.6 28,35.2 42.7,77.1 " +
            "42.7,121.9l0,12.4 3,3 3,3 28.3,-0.5c30.5,-0.5 33.6,-0.9 " +
            "46.3,-6.7 21.6,-9.8 37.9,-29.6 44,-53.7l2.4,-9.6 0,-178.3 " +
            "c0,-195.6 0.2,-188.8 -6,-212.3 -3.7,-14.1 -9.7,-29.1 -15.7,-39.6 " +
            "-2.6,-4.4 -15.3,-23.8 -28.3,-43 -34.2,-50.5 -95.4,-141.1 " +
            "-110.4,-163.6 -7.2,-10.6 -15.7,-22.6 -19,-26.8 -27.7,-34.4 " +
            "-68.2,-60.1 -111.8,-71 -22.4,-5.6 -19,-5.5 -142.8,-5.7 " +
            "-63,-0.1 -117.9,0.2 -122,0.6z" +
            "M2002.9,766.8c9.9,3.1 20.7,8.1 28.9,13.6 11.9,7.9 19.8,17.1 " +
            "37,43.1 8.7,13.2 24,36 33.8,50.5 65.1,95.8 89,131.9 92.2,139.2 " +
            "7,16.3 7.4,20.1 7.7,78.8 0.2,28.9 0.1,60.5 -0.2,70.2l-0.5,17.6 " +
            "-20.2,-17.6c-58,-51 -62.8,-54.7 -80.1,-63.2 -19.3,-9.4 " +
            "-41.6,-15.8 -62.4,-17.9 -6.4,-0.7 -44.4,-1.1 -100.8,-1.1 " +
            "-60.1,-0 -91.1,-0.3 -92.4,-1 -4.7,-2.6 -4.9,-4.4 -4.9,-44.9 " +
            "l0,-38.1 61.3,-0.2 61.3,-0.3 4.1,-2.7c2.5,-1.6 4.9,-4.4 " +
            "6.3,-7.2 2.1,-4.5 2.2,-5.5 2.1,-37.6 -0.1,-19.1 -0.6,-34.1 " +
            "-1.2,-35.7 -1.7,-4.5 -5,-8.3 -8.9,-10.3 -3.8,-1.9 -6.1,-2 " +
            "-64.5,-2l-60.5,-0 0,-63.5c0,-68.6 -0.1,-67.2 5.2,-71 " +
            "2.1,-1.4 9.8,-1.5 75.8,-1.2 73.1,0.3 73.5,0.3 80.9,2.5z"

private const val D_WHEEL_REAR =
    "M1143,1275c-26.6,2.8 -52.1,12.1 -74.5,27.4 -25.4,17.2 " +
            "-49,49.2 -59.5,80.5 -17.9,53.5 -7.9,109.9 27.4,153.7 " +
            "17.8,22.2 46.1,41.9 72.3,50.3 37.5,12.2 81.3,9.1 115.5,-8 " +
            "44.6,-22.3 74.6,-60.7 85.4,-109.4 3.5,-15.3 4.3,-41.3 2,-57.3 " +
            "-10.3,-69.6 -62.1,-124.2 -128.6,-135.6 -11.3,-1.9 -29.5,-2.7 " +
            "-40,-1.6z" +
            "M1170.7,1370.5c22.4,5.3 40.1,21.9 47.8,44.8 14,41.9 " +
            "-17.9,85.7 -62.5,85.7 -16.8,-0 -31.6,-6.5 -44.5,-19.4 " +
            "-13.3,-13.3 -19.5,-28.3 -19.5,-47 0,-17.9 6.3,-33 19.4,-46.1 " +
            "12.7,-12.8 27.6,-19.3 44.6,-19.4 4.7,-0 11.3,0.6 14.7,1.4z"

private const val D_WHEEL_FRONT =
    "M2002.3,1275c-35.8,3.7 -68.7,19.3 -93.8,44.5 -13.5,13.5 " +
            "-22.5,26 -30.9,43 -12.1,24.5 -17,45.2 -17,72.5 0,16.9 " +
            "1.2,27.2 4.9,41.6 12.6,49.7 53,93.4 101,109.3 29,9.6 " +
            "54.6,11 83.3,4.5 20,-4.5 35.9,-11.3 52.4,-22.6 68.8,-47 " +
            "90.2,-135.1 51.1,-209.8 -22.6,-43.2 -63.9,-73.4 " +
            "-111.3,-81.4 -11.3,-1.9 -29.6,-2.6 -39.7,-1.6z" +
            "M2031,1370.6c11.4,2.7 20.5,7.9 30.1,17.4 7,7 9.4,10.2 " +
            "12.7,17 5.2,10.8 7.2,19.1 7.2,30.5 0,26.1 -13.7,47.9 " +
            "-37.3,59 -24.5,11.7 -52.8,7 -72.2,-12 -7.7,-7.5 " +
            "-12.6,-15.6 -16.5,-27.3 -7,-20.8 -2.5,-45.1 11.4,-62.1 " +
            "8.9,-10.8 22.3,-19.4 35.1,-22.5 8,-1.9 21.5,-1.9 29.5,-0z"

private fun parsePath(d: String): Path = PathParser().parsePathString(d).toPath()


@Composable
fun AnimatedCargoLogo(
    modifier: Modifier = Modifier,
    truckChassisColor: Color = White,
    cargoSymbolColor: Color = Orange,
    speedTrailsColor: Color = Orange,
    truckWheelsColor: Color = White
) {
    val cargoY = remember { Animatable(0f) }
    val cargoX = remember { Animatable(0f) }
    val cargoLiftY = remember { Animatable(0f) }
    val cargoRotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(900)

            launch {
                cargoLiftY.animateTo(-50f, tween(150, easing = FastOutSlowInEasing))
                cargoLiftY.animateTo(0f, tween(200, easing = FastOutLinearInEasing))
                cargoLiftY.animateTo(-12f, tween(100, easing = FastOutSlowInEasing))
                cargoLiftY.animateTo(0f, tween(100, easing = FastOutLinearInEasing))
            }

            launch {
                cargoRotation.animateTo(-7f, tween(150, easing = FastOutSlowInEasing))
                cargoRotation.animateTo(0f, tween(200, easing = FastOutLinearInEasing))
                cargoRotation.animateTo(-2f, tween(100, easing = FastOutSlowInEasing))
                cargoRotation.animateTo(0f, tween(100, easing = FastOutLinearInEasing))
            }

            suspend fun shake(y: Float, x: Float, time: Int) {
                cargoY.animateTo(y, tween(time))
                cargoX.animateTo(x, tween(time))
            }

            shake(-24f, 8f, 70)
            shake(18f, -6f, 75)
            shake(-12f, 4f, 70)
            shake(8f, -3f, 65)
            shake(-5f, 2f, 55)
            shake(3f, -1f, 45)
            cargoY.animateTo(-1f, tween(35))
            shake(0f, 0f, 30)

            delay(1200)
        }
    }

    Box(modifier = modifier.aspectRatio(VP_W / VP_H)) {
        TruckChassis(
            offsetX = cargoX.value,
            offsetY = cargoY.value,
            modifier = Modifier.fillMaxSize(),
            color = truckChassisColor
        )
        SpeedTrails(
            modifier = Modifier.fillMaxSize(),
            color = speedTrailsColor
        )
        TruckCargo(
            offsetX = cargoX.value,
            offsetY = cargoY.value,
            liftY = cargoLiftY.value,
            rotation = cargoRotation.value,
            modifier = Modifier.fillMaxSize(),
            color = cargoSymbolColor
        )
        Wheels(
            modifier = Modifier.fillMaxSize(),
            color = truckWheelsColor
        )
    }
}

@Composable
private fun TruckChassis(
    offsetX: Float,
    offsetY: Float,
    modifier: Modifier = Modifier,
    color: Color
) {
    val pathCarBody = remember { parsePath(D_CAR_BODY) }

    Canvas(modifier = modifier) {
        val sx = size.width / VP_W
        val sy = size.height / VP_H

        withTransform({
            scale(sx, sy, pivot = Offset.Zero)
            translate(left = offsetX, top = offsetY)
        }) {
            drawPath(pathCarBody, color = color)
        }
    }
}

@Composable
private fun SpeedTrails(modifier: Modifier = Modifier, color: Color) {
    val paths = remember {
        listOf(
            parsePath(D_TRAIL_1),
            parsePath(D_TRAIL_2),
            parsePath(D_TRAIL_3),
            parsePath(D_TRAIL_4)
        )
    }

    val inf = rememberInfiniteTransition("trails")
    val targets = listOf(-240f, -330f, -290f, -220f)
    val offsets = listOf(0, 160, 320, 480)

    val animations = (0..3).map { i ->
        val spec = infiniteRepeatable<Float>(
            animation = tween(650, easing = FastOutLinearInEasing),
            initialStartOffset = StartOffset(offsets[i], StartOffsetType.FastForward)
        )
        val xState = inf.animateFloat(0f, targets[i], animationSpec = spec, label = "t${i}X")
        val aState = inf.animateFloat(1f, 0f, animationSpec = spec, label = "t${i}A")
        xState to aState
    }

    Canvas(modifier = modifier) {
        val sx = size.width / VP_W
        val sy = size.height / VP_H

        withTransform({ scale(sx, sy, pivot = Offset.Zero) }) {
            paths.forEachIndexed { index, path ->
                val (x, a) = animations[index]
                withTransform({ translate(left = x.value) }) {
                    drawPath(path, color, alpha = a.value)
                }
            }
        }
    }
}

@Composable
private fun TruckCargo(
    offsetX: Float,
    offsetY: Float,
    liftY: Float,
    rotation: Float,
    modifier: Modifier = Modifier,
    color: Color
) {
    val pathCargo = remember { parsePath(D_CARGO) }
    val cargoBounds = remember(pathCargo) { pathCargo.getBounds() }

    Canvas(modifier = modifier) {
        val sx = size.width / VP_W
        val sy = size.height / VP_H

        withTransform({
            scale(sx, sy, pivot = Offset.Zero)
            translate(left = offsetX, top = offsetY + liftY)
            rotate(degrees = rotation, pivot = Offset(cargoBounds.center.x, cargoBounds.bottom))
        }) {
            drawPath(pathCargo, color)
        }
    }
}

@Composable
private fun Wheels(
    modifier: Modifier = Modifier,
    color: Color
) {
    val pathWheelRear = remember { parsePath(D_WHEEL_REAR) }
    val pathWheelFront = remember { parsePath(D_WHEEL_FRONT) }

    Canvas(modifier = modifier) {
        val sx = size.width / VP_W
        val sy = size.height / VP_H

        withTransform({ scale(sx, sy, pivot = Offset.Zero) }) {
            drawPath(pathWheelRear, color)
            drawPath(pathWheelFront, color)
        }
    }
}