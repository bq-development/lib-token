package com.bqreaders.lib.token.repository;

import com.bqreaders.silkroad.mongo.index.MongoIndex;
import com.bqreaders.lib.token.model.OneTimeAccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author Rubén Carrasco
 */
public class TokenMongoIndexes {

	private static final Logger LOG = LoggerFactory.getLogger(TokenMongoIndexes.class);

	@Autowired
	public void ensureIndexes(MongoOperations mongo) {
		LOG.info("Creating token mongo indexes");
		mongo.indexOps(OneTimeAccessToken.class).ensureIndex(
				new MongoIndex().on("expireAt", Direction.ASC).expires(0, TimeUnit.SECONDS).background()
						.getIndexDefinition());
	}
}
