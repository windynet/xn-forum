/**
 * @Title SYSDictDAOImpl.java 
 * @Package com.xnjr.moom.dao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 上午10:22:32 
 * @version V1.0   
 */
package com.std.forum.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.forum.dao.ISYSDictDAO;
import com.std.forum.dao.base.support.AMybatisTemplate;
import com.std.forum.domain.SYSDict;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 上午10:22:32 
 * @history:
 */
@Repository("sysDictDAOImpl")
public class SYSDictDAOImpl extends AMybatisTemplate implements ISYSDictDAO {

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(SYSDict data) {
        return super.insert("insert_sysDict", data);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(SYSDict data) {
        return super.delete("delete_sysDict", data);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public SYSDict select(SYSDict condition) {
        return super.select("select_sysDict", condition, SYSDict.class);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public Long selectTotalCount(SYSDict condition) {
        return super.selectTotalCount("select_sysDict_count", condition);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<SYSDict> selectList(SYSDict condition) {
        return super.selectList("select_sysDict", condition, SYSDict.class);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<SYSDict> selectList(SYSDict condition, int start, int count) {
        return super.selectList("select_sysDict", start, count, condition,
            SYSDict.class);
    }

    /**
     * @see com.xnjr.base.dao.ISYSDictDAO#updateByGlobal(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public int update(SYSDict data) {
        return super.update("update_sysDict", data);
    }

}
