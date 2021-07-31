package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.BoardDto;
import com.cos.blog.dto.ReplyDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional 
	public void write(Board board, User user) {  // title, content, user
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional (readOnly = true)
	public Page<Board> list(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional (readOnly = true)
	public Board detail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("Error : 글을 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void update(BoardDto boardDto) {
		Board board = boardRepository.findById(boardDto.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("Error : 글을 찾을 수 없습니다.");
				}); // 영속화
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
	}
	
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void replyWrite (ReplyDto replyDto) {
		replyRepository.mSave(replyDto.getUserId(), replyDto.getBoardId(), replyDto.getContent());
	}
	
	@Transactional
	public void replyDelete (int replyId) {
		replyRepository.deleteById(replyId);
	}
}
