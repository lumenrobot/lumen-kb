package id.ac.itb.ee.lskk.lumen.core;

import id.ac.itb.ee.lskk.relexid.core.RelEx;
import id.ac.itb.ee.lskk.relexid.core.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.restfb.types.StatusMessage;

/**
 *
 * @author ceefour
 */
public class FacebookChannel {
	
	private static final Logger log = LoggerFactory
			.getLogger(FacebookChannel.class);
	
	public static class FacebookPerception {
		Post post;
		String message;
		String replyTo;
		private final long fromId;
		private final String fromName;
		
		public FacebookPerception(Post post, long fromId, String fromName, String message, String replyTo) {
			super();
			this.post = post;
			this.fromId = fromId;
			this.fromName = fromName;
			this.message = message;
			this.replyTo = replyTo;
		}

		@Override
		public String toString() {
			return "FacebookPerception ["
					+ (post != null ? "post=" + post + ", " : "")
					+ (message != null ? "message=" + message + ", " : "")
					+ (replyTo != null ? "replyTo=" + replyTo + ", " : "")
					+ "fromId=" + fromId + ", "
					+ (fromName != null ? "fromName=" + fromName : "") + "]";
		}
		
	}
	
	public static class FacebookAction {
		Post originalPost;
		String destId;
		String replyMessage;
		private final long partnerId;
		private final String partnerName;
		
		public FacebookAction(Post originalPost, String destId,
				String replyMessage, long partnerId, String partnerName) {
			super();
			this.originalPost = originalPost;
			this.destId = destId;
			this.replyMessage = replyMessage;
			this.partnerId = partnerId;
			this.partnerName = partnerName;
		}

		@Override
		public String toString() {
			return "FacebookAction ["
					+ (destId != null ? "destId=" + destId + ", " : "")
					+ (replyMessage != null ? "replyMessage=" + replyMessage
							+ ", " : "") + "partnerId=" + partnerId + ", "
					+ (partnerName != null ? "partnerName=" + partnerName : "")
					+ "]";
		}
		
	}
	
	public static class FacebookActionResult {

		private final Post originalPost;
		private final String destId;
		private final long partnerId;
		private final String partnerName;
		private final Comment commented;

		public FacebookActionResult(Post originalPost, String destId,
				long partnerId, String partnerName, Comment commented) {
			this.originalPost = originalPost;
			this.destId = destId;
			this.partnerId = partnerId;
			this.partnerName = partnerName;
			this.commented = commented;
		}

		@Override
		public String toString() {
			return "FacebookActionResult ["
					+ (destId != null ? "destId=" + destId + ", " : "")
					+ "partnerId="
					+ partnerId
					+ ", "
					+ (partnerName != null ? "partnerName=" + partnerName
							+ ", " : "")
					+ (commented != null ? "commented=" + commented : "") + "]";
		}
		
	}
	
	@Inject
	private LumenSysConfig sysConfig;
	@Inject
	private RelEx relex;
	
	private DefaultFacebookClient fb;
	
	@PostConstruct
	public void init() {
		fb = new DefaultFacebookClient(sysConfig.getFacebookTenantAccessToken(), sysConfig.getFacebookAppSecret());
	}
	
	public List<FacebookPerception> poll() {
		List<FacebookPerception> perceptions = new ArrayList<>();
		final String meFeed = "/me/feed";
		log.info("Fetching {} ...", meFeed);
		Connection<Post> connection = fb.fetchConnection(meFeed, Post.class);
		log.info("{} posts: {}", connection.getData().size(), connection.getData());
		for (Post post : connection.getData()) {
			log.info("{} {} from {} {}: {}", 
					post.getType(), post.getId(), post.getFrom().getId(), post.getFrom().getName(), 
					post.getMessage());
			if ( post.getMessage() != null && "status".equals(post.getType()) && 
					sysConfig.getFacebookProfileId() != Long.valueOf(post.getFrom().getId()) &&
					!StringUtils.equals(sysConfig.getFacebookProfileName(), post.getFrom().getName()) ) {
				log.info("Fetching {} {} from {} {}...", post.getType(), post.getId(), post.getFrom().getId(), post.getFrom().getName());
				StatusMessage status = fb.fetchObject(post.getId(), StatusMessage.class);
				log.info("Status {} (comments: {}), {} likes, {} shares: {}", 
						post.getId(), post.getComments(), post.getLikesCount(), post.getSharesCount(), status);
				if (post.getComments() != null) {
					ImmutableList<Comment> myReplies = FluentIterable.from(post.getComments().getData()).filter(new Predicate<Comment>() {
						@Override
						public boolean apply(Comment input) {
							return sysConfig.getFacebookProfileId() == Long.valueOf(input.getFrom().getId()) ||
									StringUtils.equals(sysConfig.getFacebookProfileName(), input.getFrom().getName());
						};
					}).toList();
					if (!myReplies.isEmpty()) {
						log.debug("Post {} has my replies: {}", post.getId(), myReplies);
					} else {
						log.debug("Post {} not replied yet", post.getId());
						perceptions.add( new FacebookPerception(post, Long.valueOf(post.getFrom().getId()), post.getFrom().getName(), post.getMessage(), post.getId()) );
					}
				} else {
					log.debug("Post {} not replied yet", post.getId());
					perceptions.add( new FacebookPerception(post, Long.valueOf(post.getFrom().getId()), post.getFrom().getName(), post.getMessage(), post.getId()) );
				}
			}
		}
		if (!perceptions.isEmpty()) {
			log.info("{} perceptions: {}", perceptions.size(), perceptions);
		}
		return perceptions;
	}
	
	public List<FacebookAction> perceive(List<FacebookPerception> perceptions) {
		List<FacebookAction> actions = new ArrayList<FacebookAction>();
		for (FacebookPerception perception : perceptions) {
			String reply;
			try {
				Sentence sentence = relex.parse(perception.message);
				reply = "@" + perception.fromName + ":";
				reply += "\n\nStructure:\n" + sentence.toString();
				reply += "\n\nIn English:\n" + sentence.generate(Locale.ENGLISH, relex.getDictionary(), relex);
				reply += "\n\nIn Indonesian:\n" + sentence.generate(RelEx.INDONESIAN, relex.getDictionary(), relex);
			} catch (Exception e) {
				log.error(String.format("Cannot parse %s %s's status: %s", 
						perception.fromId, perception.fromName, perception.message), e);
				reply = ExceptionUtils.getStackTrace(e);
			}
			actions.add(new FacebookAction(perception.post, perception.replyTo, reply, perception.fromId, perception.fromName));
		}
		if (!actions.isEmpty()) {
			log.info("{} actions: {}", actions.size(), actions);
		}
		return actions;
	}

	public List<FacebookActionResult> respond(
			List<FacebookAction> actions) {
		List<FacebookActionResult> results = new ArrayList<>();
		for (FacebookAction action : actions) {
			final String destPath = "/" + action.destId + "/comments";
			log.info("Commenting {}... {}", destPath, action.replyMessage);
			Comment commented = fb.publish(destPath, Comment.class, Parameter.with("message", action.replyMessage));
			log.info("Commented {} for {} {} > {}", action.destId, action.partnerId, action.partnerName, commented);
			results.add(new FacebookActionResult(action.originalPost, action.destId, action.partnerId, action.partnerName,
					commented));
		}
		if (!results.isEmpty()) {
			log.info("{} results: {}", results.size(), results);
		}
		return results;
	}

}
