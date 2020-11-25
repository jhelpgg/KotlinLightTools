package fr.jhelp.kotlinLigthTools.math

import fr.jhelp.kotlinLight.ImportSwift
import fr.jhelp.kotlinLight.guard
import fr.jhelp.kotlinLigthTools.exceptions.IllegalArgumentException
import java.lang.Math.pow
import kotlin.math.floor
import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min

@ImportSwift("Foundation")

fun signum(number: Float): Int
{
    when
    {
        number < 0.0f -> return -1
        number > 0.0f -> return 1
        else          -> return 0
    }
}

fun absolute(number: Float): Float
{
    if (number < 0)
    {
        return -number
    }

    return number
}

fun limitInteger(value: Int, bound1: Int, bound2: Int): Int
{
    val minimum = min(bound1, bound2)

    if (value <= minimum)
    {
        return minimum
    }

    val maximum = max(bound1, bound2)

    if (value >= maximum)
    {
        return maximum
    }

    return value
}

fun floatToIntBytes(number: Float): Int
{
    val sign = signum(number)

    if (sign == 0)
    {
        return 0
    }

    val abs = absolute(number)
    val exponent = floor(log2(abs)).toInt() - 23
    val mantis = floor(abs.toDouble() * pow(2.0, -exponent.toDouble())).toInt()
    var response = 0L

    if (sign < 0)
    {
        response = 0x80000000
    }

    response = response or ((exponent + 150) shl 23).toLong()
    response = response or (mantis and 0x7FFFFF).toLong()
    return response.toInt()
}

/*

 * If the argument is {@code 0x7f800000}, the result is positive
 * infinity.
 *
 * <p>If the argument is {@code 0xff800000}, the result is negative
 * infinity.


 * <p>If the argument is any value in the range
 * {@code 0x7f800001} through {@code 0x7fffffff} or in
 * the range {@code 0xff800001} through
 * {@code 0xffffffff}, the result is a NaN.

  <blockquote><pre>{@code
 * int s = ((bits >> 31) == 0) ? 1 : -1;
 * int e = ((bits >> 23) & 0xff);
 * int m = (e == 0) ?
 *                 (bits & 0x7fffff) << 1 :
 *                 (bits & 0x7fffff) | 0x800000;
 * }</pre></blockquote>

 * Then the floating-point result equals the value of the mathematical
 * expression <i>s</i>&middot;<i>m</i>&middot;2<sup><i>e</i>-150</sup>.
             e - 150
    s * m * 2
 */
@Throws
fun floatFromIntBytes(intBytes: Int): Float
{
    if (intBytes == 0)
    {
        return 0f
    }

    val byte1 = (intBytes shr 24) and 0xFF
    val byte2 = (intBytes shr 16) and 0xFF
    val byte3 = (intBytes shr 8) and 0xFF
    val byte4 = intBytes and 0xFF

    ((byte1 != 0x7F && byte1 != 0xFF) || byte2 < 0x80).guard { throw IllegalArgumentException("The int bytes not represents a valid Float") }

    var sign = 0f

    if (byte1 >= 0x80)
    {
        sign = -1f
    }
    else
    {
        sign = 1f
    }

    val exponent = ((byte1 and 0x7F) shl 1) or (byte2 shr 7)
    var mantis = 0

    if (exponent == 0)
    {
        mantis = (byte2 and 0x7F) shl 16
        mantis = mantis or (byte3 shl 8)
        mantis = mantis or byte4
        mantis = mantis shl 1
    }
    else
    {
        mantis = (byte2 and 0x7F) shl 16
        mantis = mantis or (byte3 shl 8)
        mantis = mantis or byte4
        mantis = mantis or 0x800000
    }

    val power = pow(2.0, (exponent - 150).toDouble()).toFloat()
    return sign * mantis.toFloat() * power

}