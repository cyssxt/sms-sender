package com.cyssxt.smsspringbootstarter.dao;


import com.cyssxt.smsspringbootstarter.entity.MsgCodesEntity;
import org.springframework.data.repository.CrudRepository;

public interface SmsRepository extends CrudRepository<MsgCodesEntity,Integer> {
}
