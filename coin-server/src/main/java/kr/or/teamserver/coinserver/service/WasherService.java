package kr.or.teamserver.coinserver.service;

import com.google.common.annotations.VisibleForTesting;
import kr.or.teamserver.coinserver.model.Washer;

import java.util.List;

public interface WasherService {

    Washer save(long electricPower);

    Washer save(long id, long electricPower);

    Washer findOne(long id);

    List<Washer> findAll();

    @VisibleForTesting
    void clearWashers();
}
