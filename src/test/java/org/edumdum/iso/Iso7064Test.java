package org.edumdum.iso;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.function.Function;

import org.edumdum.iso.Iso7064;
import org.junit.Test;

public class Iso7064Test
{
	private static final HashMap<String, Integer> DATA = new HashMap<String, Integer>();
	static
	{
		DATA.put("969500T3M8S4SQAMHJ", 50);
		DATA.put("969500KSV493XWY0PS", 54);
		DATA.put("7245005WBNJAFHBD0S", 55);
		DATA.put("724500VKKSH9QOLTFR", 38);
		DATA.put("724500884QS64MG71N", 76);
		DATA.put("724500884QS64MG71N64", 1);
	}

	private static final String EXCEPTION_TEMPLATE = "Invalid data format; expecting '^[0-9A-Z]{1,}$', found: '%s'.";

	@Test
	public final void testCompute()
	{
		assertException(Iso7064::compute, null, IllegalArgumentException.class, String.format(EXCEPTION_TEMPLATE, "null"));
		assertException(Iso7064::compute, "", IllegalArgumentException.class, String.format(EXCEPTION_TEMPLATE, ""));
		assertException(Iso7064::compute, "a", IllegalArgumentException.class, String.format(EXCEPTION_TEMPLATE, "a"));

		int response, result;

		for (String key : DATA.keySet())
		{
			response = DATA.get(key);
			result = Iso7064.compute(key);

			if (response != result)
			{
				fail(String.format("Invalid response for input '%s'; expecting: '%d', found: '%d'", key, response, result));
			}
		}
	}

	@Test
	public final void testComputeWithoutCheck()
	{
		int response, result;

		for (String key : DATA.keySet())
		{
			response = DATA.get(key);
			result = Iso7064.computeWithoutCheck(key);

			if (response != result)
			{
				fail(String.format("Invalid response for input '%s'; expecting: '%d', found: '%d'.", key, response, result));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static <T> void assertException(Function<T, ?> function, T input, Class exceptionClass, String exceptionMessage)
	{
		try
		{
			function.apply(input);
			fail(String.format("Expecting an exception of type '%s' with message '%s' to be thrown.", exceptionClass.getName(), exceptionMessage));
		}
		catch (Exception exception)
		{
			if (!exceptionClass.isInstance(exception))
			{
				fail(String.format("Invalid exception type; expecting: '%s', found: '%s'.", exceptionClass.getName(), exception.getClass().getName()));
			}
			else if (exceptionMessage != null && !exceptionMessage.equals(exception.getMessage()))
			{
				fail(String.format("Invalid exception message; expecting: '%s', found: '%s'.", exceptionMessage, exception.getMessage()));
			}
		}
	}
}
