package kr.or.teamserver.coinserver.persistence;

import kr.or.teamserver.coinserver.model.Washer;
import org.springframework.data.repository.CrudRepository;

public interface WasherDAO extends CrudRepository<Washer, Long> {
}
