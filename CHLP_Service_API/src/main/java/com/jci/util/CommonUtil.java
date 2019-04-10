package com.jci.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class CommonUtil {
	
	/**
	 * Reads the incoming URL into a JSON object.
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject readUrl(String url) throws IOException, JSONException {
		
	    try(InputStream is = new URL(url).openStream()) {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      return new JSONObject(jsonText);
	    } 
	}
	
	/**
	 * Method converts the incoming stream into a String object.
	 * @param rd
	 * @return
	 * @throws IOException
	 */
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}
	
	/**
	 * Method to retrieve x5c Key String. The method will first check the key in the
	 * vault, if it has expired a new key will be fetched from the Azure AD
	 * configuration.  Option to retrieve the x5c key from vault.
	 * 
	 * @param jwt
	 * @param configJson
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public String getX5cKeyString(String jwt, JSONObject configJson) throws IOException, JSONException {

		JSONObject jwksJson = getJwksJson(configJson);
		/*
		 * By splitting the JWT token, the header is extracted which is Base64 encoded.
		 * JWT header stores information regarding the 'kid' which indicates the public
		 * key used to sign the token
		 */
		String[] splitString = jwt.split("\\.");
		String base64EncodedHeader = splitString[0];
		Base64 base64Url = new Base64(true);
		JSONObject headerJson = new JSONObject(new String(base64Url.decode(base64EncodedHeader)));
		String kid = headerJson.getString("kid");

		/*
		 * Identifying the publicKeyString in the keyList using the 'kid' present in the
		 * JWT header
		 */
		JSONArray keyList = jwksJson.getJSONArray("keys");
		JSONObject encodingKeyObject = new JSONObject();
		for (int i = 0; i < keyList.length(); i++) {
			encodingKeyObject = keyList.getJSONObject(i);
			if (encodingKeyObject.getString("kid").equals(kid)) {
				break;
			}
		}
		JSONArray decode = encodingKeyObject.getJSONArray("x5c");
		return decode.get(0).toString();
	}
	
	private JSONObject getJwksJson(JSONObject configJson) throws IOException, JSONException {

		/*
		 * jwks_uri stores multiple key information. JWT token received could be signed
		 * with any of the available keys.
		 */
		String jwksUri = configJson.getString("jwks_uri");
		return readUrl(jwksUri);

	}
	
	/**
	 * Generates the public key used to sign the JWT token using the x5cKeyString.
	 * 
	 * @param x5cKeyString
	 * @return
	 * @throws CertificateException
	 */
	public PublicKey getPublicKey(String x5cKeyString) throws CertificateException {
		CertificateFactory factory = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate) factory
				.generateCertificate(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(x5cKeyString)));
		return cert.getPublicKey();
	}
	
	public Jws<Claims> decryptJWT(String jwt,PublicKey key){
		return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getRolesFromJWT(Jws<Claims> jwtClaims){
		List<String> groupList = new ArrayList<>();
		String subject = null;
		
		subject = jwtClaims.getBody().getSubject();
		if (null == subject) {
			throw new MalformedJwtException("Unable to read the request token");
		}
			
		groupList = (List<String>) jwtClaims.getBody().get("roles");
		
		return groupList;
	}
}
