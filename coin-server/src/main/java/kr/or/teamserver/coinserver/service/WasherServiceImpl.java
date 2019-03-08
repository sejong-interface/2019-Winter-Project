package kr.or.teamserver.coinserver.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.teamserver.coinserver.model.Washer;
import kr.or.teamserver.coinserver.persistence.WasherDAO;

@Service
public class WasherServiceImpl implements WasherService {

    private final Logger logger = LoggerFactory.getLogger(WasherServiceImpl.class);
    private WasherDAO washerDAO;

    @Autowired
    public WasherServiceImpl(WasherDAO washerDAO) { this.washerDAO = washerDAO; }

    @Override
    public Washer save(long electricPower) {
        return washerDAO.save(Washer.of(null, electricPower));
    }

    @Override
    public Washer save(long id, long electricPower) {
        logger.info("id={}, electricPower={}", id, electricPower);
        return washerDAO.save(Washer.of(id, electricPower));
    }

    @Override
    public Washer findOne(long id) {
        return washerDAO.findById(id).orElseGet(null);
    }

    @Override
    public List<Washer> findAll() {
        List<Washer> washers = new ArrayList<>();
        washerDAO.findAll().forEach(washers::add);
        return washers;
    }
}
