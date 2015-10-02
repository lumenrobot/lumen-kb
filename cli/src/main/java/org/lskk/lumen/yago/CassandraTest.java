package org.lskk.lumen.yago;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.gridgain.grid.GridException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class CassandraTest {

	private static final Logger log = LoggerFactory
			.getLogger(CassandraTest.class);
	
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException {
		try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
			Metadata metadata = cluster.getMetadata();
			log.info("Connected to {} with {} hosts: {}", 
					metadata.getClusterName(), metadata.getAllHosts().size(), metadata.getAllHosts());
			try (Session session = cluster.connect("yago_dev")) {
				session.execute("CREATE TABLE label ( id text PRIMARY KEY, preflabel text, label text, preferredmeaninglabels list<text>, givenname text, familyname text )");
			}
		}
	}

}
