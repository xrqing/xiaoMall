package com.xiao.xiaomall.protal.service.impl;



import com.xiao.xiaomall.entity.*;
import com.xiao.xiaomall.mapper.*;
import com.xiao.xiaomall.protal.dao.HomeDao;
import com.xiao.xiaomall.protal.domain.FlashPromotionProduct;
import com.xiao.xiaomall.protal.domain.HomeContentResult;
import com.xiao.xiaomall.protal.domain.HomeFlashPromotion;
import com.xiao.xiaomall.protal.service.HomeService;
import com.xiao.xiaomall.protal.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 *首页内容管理  serviceImpl
 **/
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;

    @Autowired
    private HomeDao homeDao;

    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;

    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;

    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Autowired
    private CmsSubjectMapper subjectMapper;


    /**
     *获取首页内容
     * */
    @Override
    public HomeContentResult content() {
        HomeContentResult result = new HomeContentResult();
        //获取首页广告
        result.setHomeAdvertiseList(getHomeAdvertiseList());
        //获取推荐状态
        result.setBrandList(homeDao.getRecommendBrandList(0,4));
        //获取秒杀信息
        result.setHomeFlashPromotion(getHomeFlashPromotion());
        //获取新品推荐
        result.setNewProductList(homeDao.getNewProductList(0,4));
        //获取人气推荐
        result.setHotProductList(homeDao.getHotProductList(0,4));
        //获取专题推荐
        result.setSubjectList(homeDao.getRecommendSubjectList(0,4));
        return result;
    }

    //获取首页广告
    private List<SmsHomeAdvertise> getHomeAdvertiseList(){
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andTypeEqualTo(1).andStatusEqualTo(1);
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }

    //获取秒杀信息
    private HomeFlashPromotion getHomeFlashPromotion(){
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        //1:根据时间去获取秒杀活动
        Date date = new Date();
        SmsFlashPromotion flashPromotion = getFlashPromotion(date);
        if (flashPromotion!=null){
            //2:获取当前的秒杀场次
            SmsFlashPromotionSession flashPromotionSession = getFlashPromotionSession(date);
            if(flashPromotionSession!=null){
               homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
               homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
               //3:获取下一个场次信息
                SmsFlashPromotionSession nextFlashPromotionSession = getNextFlashPromotionSession(date);
                if (nextFlashPromotionSession!=null){
                    homeFlashPromotion.setStartTime(nextFlashPromotionSession.getStartTime());
                    homeFlashPromotion.setEndTime(nextFlashPromotionSession.getEndTime());
                }
                //4:获取秒杀商品
                List<FlashPromotionProduct> flashProductList = homeDao.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }
        return homeFlashPromotion;
    }

    //获取下一个场次信息
    private SmsFlashPromotionSession getNextFlashPromotionSession(Date date){
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria().andStartTimeGreaterThan(date);
        example.setOrderByClause("start_time asc");
        List<SmsFlashPromotionSession> smsFlashPromotionSessions = promotionSessionMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(smsFlashPromotionSessions)){
            return smsFlashPromotionSessions.get(0);
        }
        return null;
    }

    //获取当前的秒杀场次
    private SmsFlashPromotionSession getFlashPromotionSession(Date date){
        Date time = DateUtil.getTime(date);
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria().andStartTimeGreaterThanOrEqualTo(time).andEndTimeGreaterThanOrEqualTo(time);
        List<SmsFlashPromotionSession> smsFlashPromotionSessions = promotionSessionMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(smsFlashPromotionSessions)){
            return smsFlashPromotionSessions.get(0);
        }
        return null;
    }

    //根据时间去获取秒杀活动
    private SmsFlashPromotion getFlashPromotion(Date date){
        Date date1 = DateUtil.getDate(date);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        example.createCriteria().andStatusEqualTo(1).andStartDateGreaterThanOrEqualTo(date1).andEndDateGreaterThanOrEqualTo(date1);
        List<SmsFlashPromotion> smsFlashPromotions = flashPromotionMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(smsFlashPromotions)){
            return smsFlashPromotions.get(0);
        }
        return null;
    }
}
