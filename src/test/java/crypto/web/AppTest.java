package crypto.web;

import static org.junit.Assert.assertEquals;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;

import com.crypto.domain.CalcResult;
import com.crypto.rest.CryptoRest;
import com.crypto.util.CalculationUtil;
import com.crypto.util.CryptoUtil;

public class AppTest {
	
	private static final String secret = "CC4BF4BA49C0DCF6E0F67B8F3E54AC13655E6B91935614E933C232CC03CCC018";
	private static final String iv = "8634738AC2AD9596869EAFBED2DE0248";

	@Test
	public void pushAndRecalculate() {
		CryptoRest rest = new CryptoRest(secret, iv);
		Object result = rest.pushAndRecalculate(4d).getEntity();
		assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "4");
		assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "0");

		result = rest.pushAndRecalculate(7d).getEntity();
		assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "5.5");
		assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "1.5");

		result = rest.pushAndRecalculate(6d).getEntity();
		assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "5.667");
		assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "1.248");

		result = rest.pushAndRecalculate(9d).getEntity();
		assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "6.5");
		assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "1.803");

		result = rest.pushAndRecalculate(1d).getEntity();
		assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "5.4");
		assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "2.728");
	}

	@Test
	public void pushRecalculateAndEncrypt() {
		CryptoRest rest = new CryptoRest(secret, iv);
		try {
			Object result = rest.pushAndRecalculate(4d).getEntity();
			assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "4");
			assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "0");

			result = rest.pushAndRecalculate(7d).getEntity();
			assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "5.5");
			assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "1.5");

			result = rest.pushAndRecalculate(6d).getEntity();
			assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "5.667");
			assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "1.248");

			result = rest.pushRecalculateAndEncrypt(9d).getEntity();
			System.out.println("Encrypt(6.5)  " + ((CalcResult) result).getEncryptedAverage());
			System.out.println("Encrypt(1.803)  " + ((CalcResult) result).getEncryptedStdDev());
			String average = rest.decrypt(((CalcResult) result).getEncryptedAverage().toString()).getEntity().toString();
			assertEquals(decimalFormatter(Double.parseDouble(average)), "6.5");
			String stdDev = rest.decrypt(((CalcResult) result).getEncryptedStdDev().toString()).getEntity().toString();
			assertEquals(decimalFormatter(Double.parseDouble(stdDev)), "1.803");

			result = rest.pushAndRecalculate(1d).getEntity();
			assertEquals(decimalFormatter(((CalcResult) result).getAverage()), "5.4");
			assertEquals(decimalFormatter(((CalcResult) result).getStdDev()), "2.728");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String decimalFormatter(Double value) {
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(value);
	}

}
