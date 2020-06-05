package com.sy.s1.board.notice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sy.s1.board.BoardRepository;

@Repository		// 생략 가능
@Mapper			// 써줘야 함
public interface NoticeRepository extends BoardRepository {

}
