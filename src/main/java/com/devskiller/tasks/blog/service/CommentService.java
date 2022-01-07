package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 * @throws IllegalArgumentException if there is no blog post for passed postId
	 */
	public List<CommentDto> getCommentsForPost(String postId) {
		return commentRepository.findCommentByPostId(postId)
			.stream().map(comment -> new CommentDto(
											comment.getId(),
											comment.getContent(),
											comment.getAuthor(), comment.getCreationDate()))
										.collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public String addComment(NewCommentDto newCommentDto) {
		Comment comment = commentRepository.save
			(new Comment(UUID.randomUUID().toString(),
				newCommentDto.getPostId(),
				newCommentDto.getAuthor(),
				newCommentDto.getContent(),
				LocalDateTime.now()));
		return comment.getPostId();
	}
}
