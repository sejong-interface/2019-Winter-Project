package kr.or.teamserver.coinserver.service;

import kr.or.teamserver.coinserver.model.Washer;
import kr.or.teamserver.coinserver.persistence.WasherDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WasherServiceImpl implements WasherService {

    private final Logger logger = LoggerFactory.getLogger(WasherServiceImpl.class);
    private Washer washer;

    private Map<Long, Washer> washers = new HashMap<>();
    private WasherDAO washerDAO;

    @Autowired
    public WasherServiceImpl(WasherDAO washerDAO) { this.washerDAO = washerDAO; }

    @Override
    public Washer save(long electricPower) {
        long id = autoGenerateId();
        washerDAO.save(Washer.of(id, electricPower));
        return save(id, electricPower);
    }

    private long autoGenerateId() {
        if (washers.isEmpty()) {
            return 1;
        }

        List<Long> ids = washers.keySet().stream().sorted().collect(Collectors.toList());
        return Collections.max(ids) + 1;
    }

    @Override
    public Washer save(long id, long electricPower) {
        // TODO 데이터베이스 생성 후 저장된 데이터를 업데이트하고 업데이트 된 정보를 리턴
        logger.info("id={}, electricPower={}", id, electricPower);
        Washer washer = Washer.of(id, electricPower);
        washers.put(id, washer);
        washerDAO.save(Washer.of(id, electricPower));
        return washer;
    }

    @Override
    public Washer findOne(long id) {
        return washers.get(id);
    }

    @Override
    public List<Washer> findAll() {
        // TODO 편법이라, 데이터베이스 작업 필요
        return new ArrayList<>(washers.values());
    }

    @Override
    public void clearWashers() {
        washers.clear();
    }
}
