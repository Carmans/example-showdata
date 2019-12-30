package cn.itcast.service.impl;

import cn.itcast.mapper.TAvgpvNumMapper;
import cn.itcast.pojo.AvgToPageBean;
import cn.itcast.pojo.TAvgpvNum;
import cn.itcast.pojo.TAvgpvNumExample;
import cn.itcast.service.AvgPvNum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvgPvNumImpl implements AvgPvNum {

    @Autowired
    private TAvgpvNumMapper mapper;

    @Override
    public String getByDates(String star, String end) {

        TAvgpvNumExample example = new TAvgpvNumExample();

        example.setOrderByClause("`dateStr` ASC");

        TAvgpvNumExample.Criteria criteria = example.createCriteria();

        criteria.andDatestrBetween(star,end);

        List<TAvgpvNum> lists = mapper.selectByExample(example);
        // 数组大小
        int size = 7;

        String[] dates = new String[size];
        double[] datas = new double[size];

        int i = 0;
        for (TAvgpvNum tAvgpvNum : lists) {
            dates[i]=tAvgpvNum.getDatestr();
            datas[i]=tAvgpvNum.getAvgpvnum().doubleValue();
            i++;
        }

        AvgToPageBean bean = new AvgToPageBean();
        bean.setDates(dates);
        bean.setData(datas);
        ObjectMapper om = new ObjectMapper();
        String beanJson= null;
        try {
            beanJson = om.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return beanJson;
    }
}
