package id.ac.itb.ee.lskk.lumen.yago;

import id.ac.itb.ee.lskk.lumen.core.impl.LumenConfig;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoLabel;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.cache.GridCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class YagoSimpleLabelLookupCli {

	private static final Logger log = LoggerFactory
			.getLogger(YagoSimpleLabelLookupCli.class);
	
	public static void main(String[] args) throws FileNotFoundException, IOException, GridException {
		String entityId = "Muhammad";
		
		try (AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(LumenConfig.class)) {
			Grid grid = appCtx.getBean(Grid.class);
			GridCache<String, YagoLabel> labelCache = YagoLabel.cache(grid);
			log.info("Label for {}: {}", entityId, labelCache.get(entityId));
		}
		
	}

}
