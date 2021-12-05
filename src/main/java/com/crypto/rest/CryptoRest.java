package com.crypto.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import com.crypto.domain.CalcResult;
import com.crypto.util.CalculationUtil;
import com.crypto.util.CryptoUtil;

@Path("/crypto")
@Produces(MediaType.APPLICATION_JSON)
public class CryptoRest {
	private CalculationUtil calculation;

	public CryptoRest(String secret, String iv) {
		calculation = new CalculationUtil();
		CryptoUtil.setSecret(secret);
		CryptoUtil.setIvSpec(iv);
	}

	@GET
	public Response hello() {
		return Response.ok("A simple Crypo application").build();
	}

	@PUT
	@Path("/pushAndRecalculate/{value}")
	public Response pushAndRecalculate(@PathParam("value") Double value) {
		return Response.ok(calculation.pushAndRecalculate(value)).build();
	}

	@PUT
	@Path("/pushRecalculateAndEncrypt/{value}")
	public Response pushRecalculateAndEncrypt(@PathParam("value") Double value) throws Exception {
		CalcResult result = calculation.pushAndRecalculate(value);
		String average = CryptoUtil.encrypt(result.getAverage().toString());
		String stdDev = CryptoUtil.encrypt(result.getStdDev().toString());
		return Response.ok(new CalcResult(average, stdDev)).build();
	}

	@GET
	@Path("/decrypt")
	public Response decrypt(@QueryParam("value") String value) throws Exception {
		return Response.ok(CryptoUtil.decrypt(value)).build();
	}

	@GET
	@Path("/generateKey")
	public Response generateKey(@QueryParam("password") String password, @QueryParam("salt") String salt) throws Exception {
		return Response.ok(
				DatatypeConverter.printHexBinary(CryptoUtil.getKeyFromPassword(password, salt).getEncoded())).build();
	}
	
	@GET
	@Path("/generateIV")
	public Response generateIV() throws Exception {
		return Response.ok(
				DatatypeConverter.printHexBinary(CryptoUtil.generateIv().getIV())).build();
	}

}