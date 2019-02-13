package kr.or.teamserver.coinserver.persistence;

import kr.or.teamserver.coinserver.controller.dto.DeviceDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DeviceDAOImpl implements DeviceDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer createDevice(String token, String date){
        return jdbcTemplate.update("INSERT INTO device(token, date) VALUES (?, ?)", "fdfdfdf", "fsdfsdfsdf");
    }

    @Override
    public List<DeviceDto> listAll(){
        return jdbcTemplate.query("select * from device order by id desc", new deviceRowMapper());
    }

    @Override
    public Integer deleteDevice(Integer id){
        return jdbcTemplate.update("delete from device where id=?", id);
    }

    @Override
    public Integer updateDevice(String date, Integer id){
        return jdbcTemplate.update("update device set date=? where id=?",date,id);
    }
}

class deviceRowMapper implements RowMapper<DeviceDto>{
    public DeviceDto mapRow(ResultSet rs, int rowNum) throws SQLException{
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDevice(rs.getInt("id"),rs.getString("token"),rs.getString("date"));
        return deviceDto;
    }
}
