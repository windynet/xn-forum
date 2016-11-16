package com.std.forum.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.forum.ao.ICategoryAO;
import com.std.forum.bo.ICategoryBO;
import com.std.forum.bo.IPlateBO;
import com.std.forum.bo.IProductBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Category;
import com.std.forum.domain.Plate;
import com.std.forum.domain.Product;
import com.std.forum.enums.EBoolean;
import com.std.forum.enums.ECategoryType;
import com.std.forum.exception.BizException;

@Service
public class CategoryAOImpl implements ICategoryAO {

    @Autowired
    private ICategoryBO categoryBO;

    @Autowired
    private IPlateBO plateBO;

    @Autowired
    private IProductBO productBO;

    @Override
    public String addCategory(Category data) {
        if (!EBoolean.NO.getCode().equals(data.getParentCode())) {
            checkParentCode(data.getParentCode());
        }
        return categoryBO.saveCategory(data);
    }

    @Override
    public void editCategory(Category data) {
        if (!categoryBO.isCategoryExist(data.getCode())) {
            throw new BizException("xn0000", "该分类编号不存在");
        }
        if (!EBoolean.NO.getCode().equals(data.getParentCode())) {
            checkParentCode(data.getParentCode());
        }
        categoryBO.refreshCategory(data);
    }

    private void checkParentCode(String parentCode) {
        if (!categoryBO.isCategoryExist(parentCode)) {
            throw new BizException("xn0000", "父节点编号不存在");
        }
    }

    @Override
    @Transactional
    public void dropCategory(String code) {
        Category data = categoryBO.getCategory(code);
        // 判断类别是否使用，使用后无法删除
        if (ECategoryType.PLATE.getCode().equals(data.getType())) {
            Plate condition = new Plate();
            condition.setKind(code);
            long totalCount = plateBO.getTotalCount(condition);
            if (totalCount > 0) {
                throw new BizException("xn0000", "该分类已使用，无法删除");
            }
        } else {
            Product condition = new Product();
            condition.setKind(code);
            long totalCount = productBO.getTotalCount(condition);
            if (totalCount > 0) {
                throw new BizException("xn0000", "该分类已使用，无法删除");
            }
        }
        if (EBoolean.NO.getCode().equals(data.getParentCode())) {
            Category condition = new Category();
            condition.setParentCode(code);
            List<Category> categoryList = categoryBO
                .queryCategoryList(condition);
            for (Category category : categoryList) {
                categoryBO.removeCategory(category.getCode());
            }
        }
        categoryBO.removeCategory(code);
    }

    @Override
    public Paginable<Category> queryCategoryPage(int start, int limit,
            Category condition) {
        return categoryBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Category> queryCategoryList(Category condition) {
        return categoryBO.queryCategoryList(condition);
    }

    @Override
    public Category getCategory(String code) {
        return categoryBO.getCategory(code);
    }
}
