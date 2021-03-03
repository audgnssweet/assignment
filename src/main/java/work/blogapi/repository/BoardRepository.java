package work.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.blogapi.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
