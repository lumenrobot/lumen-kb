/**
 * 
 */
package id.ac.itb.ee.lskk.lumen.cli;

import id.ac.itb.ee.lskk.relexid.core.RelEx;
import id.ac.itb.ee.lskk.relexid.core.Relation;
import id.ac.itb.ee.lskk.relexid.core.Sentence;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.base.Joiner;

/**
 * @author ceefour
 *
 */
public class LumenCli {

	@Inject
	private RelEx relex;
	
	public LumenCli() {
	}
	
	public void run(String[] args) {
		if (args == null || args.length == 0) {
			System.err.println("Please input sentence as command line arguments.");
			System.exit(1);
		}
		Sentence sentence = relex.parse(Joiner.on(' ').join(args));
		String reply = sentence.toString();
		reply += "\n\nIn English:\n" + sentence.generate(Locale.ENGLISH, relex.getDictionary(), relex);
		reply += "\n\nIn Indonesian:\n" + sentence.generate(RelEx.INDONESIAN, relex.getDictionary(), relex);
		reply += "\n\n" + sentence.getRelations().size() + " grammatical relationships.";
		for (Relation rel : sentence.getRelations()) {
			reply += "\n" + rel.toString();
		}
		System.out.println(reply);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(LumenCliConfig.class)) {
			appCtx.start();
			LumenCli lumenCli = appCtx.getBean(LumenCli.class);
			lumenCli.run(args);
		}
	}

}
