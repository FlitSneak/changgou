package com.flitsneak.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据id查询品牌
     * @return
     * @param id
     */
    Brand findById(Integer id);

    /**
     * 增加品牌
     * @param brand
     */
    void addBrand(Brand brand);

    /**
     * 修改品牌信息
     * @param brand
     */
    void updateBrand(Brand brand);

    /**
     * delete brand by id
     * @param id
     */
    void deleteBrand(Integer id);

    /**
     * search brand by multi-term
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    PageInfo<Brand> findPage(Integer page,Integer size);
    /**
     * 根据条件分页查询
     * @param page,size,brand
     * @return
     */
    PageInfo<Brand> page(Integer page, Integer size, Brand brand);
}
