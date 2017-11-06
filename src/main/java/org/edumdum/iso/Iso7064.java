package org.edumdum.iso;

public class Iso7064
{
	private static final int CHARCODE_0 = (int) '0';
	private static final int CHARCODE_A = (int) 'A';

	private static final String FORMAT = "^[0-9A-Z]{1,}$";

	/**
	 * Check requirements.
	 * Letters are replaced into the string by digits, according to ISO 7064.
	 * Then the string is interpreted into a number.
	 * 
	 * @param rawValue
	 * 	- must be not `null`
	 * 	- must respect format `^[0-9A-Z]{1,}$`
	 * @return Modulo 97 of the interpreted number
	 * @throws IllegalArgumentException
	 */
	public static int compute(String rawValue)
		throws IllegalArgumentException
	{
		if (rawValue == null || !rawValue.matches(FORMAT))
		{
			throw new IllegalArgumentException(String.format("Invalid data format; expecting '%s', found: '%s'.", FORMAT, rawValue));
		}

		return mod97(rawValue);
	}

	/**
	 * Does NOT check requirements.
	 * Letters are replaced into the string by digits, according to ISO 7064.
	 * Then the string is interpreted into a number.
	 * 
	 * @param rawValue
	 * 	- must be not `null`
	 * 	- must respect format `^[0-9A-Z]{1,}$`
	 * @return Modulo 97 of the interpreted number
	 */
	public static int computeWithoutCheck(String rawValue)
	{
		return mod97(rawValue);
	}

	private static int mod97(String value)
	{
		int length = value.length();
		int buffer = 0;
		int charCode;

		for (int i = 0; i < length; ++i)
		{
			charCode = (int) value.charAt(i);

			buffer = charCode + (charCode >= CHARCODE_A ? buffer * 100 - CHARCODE_A + 10 : buffer * 10 - CHARCODE_0);

			if (buffer > 10000000)
			{
				buffer %= 97;
			}
		}

		return buffer % 97;
	}
}
