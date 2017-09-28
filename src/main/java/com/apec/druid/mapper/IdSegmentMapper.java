package com.apec.druid.mapper;


import com.apec.druid.model.IdSegment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IdSegmentMapper {
    IdSegment queryByBizTag(@Param("bizTag") String bizTag);

    int insertSelective(IdSegment record);

    int updateByByBizTag(IdSegment record);
}