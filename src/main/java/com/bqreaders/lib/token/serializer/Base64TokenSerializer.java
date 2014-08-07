/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.serializer;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

import com.bqreaders.lib.token.TokenInfo;
import com.bqreaders.lib.token.signer.TokenSigner;
import com.google.common.base.Joiner;

/**
 * @author Alexander De Leon
 * 
 */
public class Base64TokenSerializer implements TokenSerializer {

	public static final String SEPARATOR = ".";
	public static final String SEPARATOR_REGEX = "\\.";

	@Override
	public String serialize(TokenInfo info, long expireTime, TokenSigner signer) {
		String token = Joiner.on(SEPARATOR).join(
				Base64.encodeBase64URLSafeString(info.serialize().getBytes(Charset.forName("UTF-8"))),
				Long.toHexString(expireTime));
		return Joiner.on(SEPARATOR).join(token, signer.sign(token));
	}

}
